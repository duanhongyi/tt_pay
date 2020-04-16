package com.bytedance.caijing.tt_pay.model;

import com.bytedance.caijing.tt_pay.TTPayService;
import com.bytedance.caijing.tt_pay.exception.InvalidRequestException;
import com.bytedance.caijing.tt_pay.net.BaseResponse;
import com.bytedance.caijing.tt_pay.net.TPRequest;
import com.bytedance.caijing.tt_pay.util.JsonUtil;
import com.bytedance.caijing.tt_pay.util.SignUtil;
import com.bytedance.caijing.tt_pay.util.ParamsUtil;
import com.google.gson.annotations.SerializedName;
import lombok.Builder.Default;
import com.google.gson.reflect.TypeToken;
import lombok.*;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@ToString
@Builder
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class TradeQueryRequest extends TPRequest {

    @SerializedName("method")
    @Default
    private String method = "tp.trade.query";

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

    @SerializedName("timestamp")
    @Default
    private String timestamp = "";

    @SerializedName("uid")
    @Default
    private String uid = "";

    @SerializedName("uid_type")
    @Default
    private String uidType = "";

    @SerializedName("outOrderNo")
    @Default
    private String outOrderNo = "";

    @SerializedName("tradeNo")
    @Default
    private String tradeNo = "";

    @SerializedName("path")
    @Default
    private String path = "gateway";

    @Override
    protected Map<String, Object> encode() throws IOException {
        Map<String, Object> bizContent = new HashMap<>();
        bizContent.put("merchant_id", TTPayService.merchantId);
        bizContent.put("uid", uid);
        bizContent.put("uid_type", uidType);
        bizContent.put("out_order_no", outOrderNo);
        bizContent.put("trade_no", tradeNo);

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
        if ((null != outOrderNo) && (!"".equals(outOrderNo))) {
            return TTPayService.appId + "_" + outOrderNo + "_" + System.currentTimeMillis();
        } else {
            return TTPayService.appId + "_" + tradeNo + "_" + System.currentTimeMillis();
        }
    }

    @Override
    protected String getUrl() {
        if (TTPayService.tpDomain.endsWith("/")) {
            return TTPayService.tpDomain + path;
        } else {
            return TTPayService.tpDomain + "/"+path;
        }
    }

    @Override
    protected Type getResponseType() {
        return new TypeToken<BaseResponse<TradeQueryResponse>>() {
        }.getType();
    }

    @Override
    public void valid() throws InvalidRequestException {
        ParamsUtil.checkAppId(TTPayService.appId);
        ParamsUtil.checkMerchantId(TTPayService.merchantId);
        ParamsUtil.checkUId(uid);
        if (null == outOrderNo && null == tradeNo && outOrderNo.equals("") && tradeNo.equals("")) {
            throw new InvalidRequestException("invalid params: outOrderNo and TradeNo can't both be blank");
        }
        if (method == null || !method.equals("tp.trade.query")) {
            throw new InvalidRequestException("invalid param: method");
        }
    }
}
