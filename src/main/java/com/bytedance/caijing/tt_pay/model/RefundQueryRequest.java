package com.bytedance.caijing.tt_pay.model;

import com.bytedance.caijing.tt_pay.TTPayService;
import com.bytedance.caijing.tt_pay.exception.InvalidRequestException;
import com.bytedance.caijing.tt_pay.net.BaseResponse;
import com.bytedance.caijing.tt_pay.net.TPRequest;
import com.bytedance.caijing.tt_pay.util.JsonUtil;
import com.bytedance.caijing.tt_pay.util.SignUtil;
import com.bytedance.caijing.tt_pay.util.ParamsUtil;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import lombok.*;
import lombok.Builder.Default;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@ToString
@Builder
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class RefundQueryRequest extends TPRequest {
    @SerializedName("method")
    @Default
    private String method = "tp.refund.query";

    @SerializedName("format")
    @Default
    private String format = "JSON";

    @SerializedName("charset")
    @Default
    private String charset = "utf-8";

    @SerializedName("sign_type")
    @Default
    private String signType = "MD5";

    @SerializedName("version")
    @Default
    private String version = "1.0";

    @SerializedName("path")
    @Default
    private String path = "gateway";

    @SerializedName("uid")
    @Default
    private String uid = "";

    @SerializedName("out_refund_no")
    @Default
    private String outRefundNo = "";

    @SerializedName("refundNo")
    @Default
    private String refundNo = "";

    @SerializedName("timestamp")
    @Default
    private String timestamp = "";

    @Override
    protected Map<String, Object> encode() throws IOException {

        Map<String, Object> bizContent = new TreeMap<>();
        bizContent.put("merchant_id", TTPayService.merchantId);
        bizContent.put("out_refund_no", outRefundNo);
        bizContent.put("refund_no", refundNo);
        bizContent.put("uid", uid);

        String bizContentJson = JsonUtil.toJSON(bizContent);

        Map<String, Object> signParams = new HashMap<>();
        signParams.put("app_id", TTPayService.appId);
        signParams.put("method", method);
        signParams.put("format", format);
        signParams.put("charset", charset);
        signParams.put("sign_type", signType);
        signParams.put("timestamp", "" + System.currentTimeMillis() / 1000);
        signParams.put("version", version);
        signParams.put("biz_content", bizContentJson);
        signParams.put("sign", SignUtil.BuildMd5WithSalt(signParams, TTPayService.appSecret));

        return signParams;
    }

    @Override
    protected String getLogId() {
        if ((null != outRefundNo) && (!"".equals(outRefundNo))) {
            return TTPayService.appId + "_" + this.outRefundNo + "_" + System.currentTimeMillis();
        } else {
            return TTPayService.appId + "_" + this.refundNo + "_" + System.currentTimeMillis();
        }
    }

    @Override
    protected String getUrl() {
        if (TTPayService.tpDomain.endsWith("/")) {
            return TTPayService.tpDomain + path;
        } else {
            return TTPayService.tpDomain + "/" + path;
        }
    }

    @Override
    protected Type getResponseType() {
        return new TypeToken<BaseResponse<RefundQueryResponse>>() {
        }.getType();
    }

    @Override
    public void valid() throws InvalidRequestException {
        ParamsUtil.checkAppId(TTPayService.appId);
        ParamsUtil.checkMerchantId(TTPayService.merchantId);
        ParamsUtil.checkUId(uid);
        if (null == outRefundNo && null == refundNo && outRefundNo.equals("") && refundNo.equals("")) {
            throw new InvalidRequestException("lack params:out_order_no or trade_no");
        }
        if (method == null || !method.equals("tp.refund.query")) {
            throw new InvalidRequestException("invalid param: method");
        }
    }
}
