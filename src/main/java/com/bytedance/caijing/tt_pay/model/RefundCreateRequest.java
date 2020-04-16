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
public class RefundCreateRequest extends TPRequest {
    @SerializedName("method")
    @Default
    private String method = "tp.refund.create";

    @SerializedName("format")
    @Default
    private String format = "JSON";

    @SerializedName("charset")
    @Default
    private String charset = "utf-8";

    @SerializedName("sign_type")
    @Default
    private String signType = "MD5";

    @SerializedName("timestamp")
    @Default
    private String timestamp = "";

    @SerializedName("version")
    @Default
    private String version = "1.0";

    @SerializedName("path")
    @Default
    private String path = "gateway";

    @SerializedName("out_order_no")
    @Default
    private String outOrderNo = "";

    @SerializedName("trade_no")
    @Default
    private String tradeNo = "";

    @SerializedName("uid")
    @Default
    private String uid = "";

    @SerializedName("out_refund_no")
    @Default
    private String outRefundNo = "";

    @SerializedName("refund_amount")
    @Default
    private Long refundAmount = Long.valueOf(0);

    @SerializedName("notify_url")
    @Default
    private String notifyUrl = "";

    @SerializedName("risk_info")
    @Default
    private String riskInfo = "";

    @SerializedName("settlement_product_code")
    @Default
    private String settlementProductCode = "";

    @SerializedName("settlement_ext")
    @Default
    private String settlementExt = "";

    @SerializedName("product_code")
    @Default
    private String productCode = "";

    @SerializedName("payment_type")
    @Default
    private String paymentType = "";

    @SerializedName("trans_code")
    @Default
    private String transCode = "";

    @SerializedName("reason")
    @Default
    private String reason = "";

    @SerializedName("third_refund_account")
    @Default
    private String thirdRefundAccount = "";

    @Override
    protected Map<String, Object> encode() throws IOException {
        Map<String, Object> bizContent = new HashMap<>();
        bizContent.put("merchant_id", TTPayService.merchantId);
        bizContent.put("out_order_no", outOrderNo);
        bizContent.put("trade_no", tradeNo);
        bizContent.put("uid", uid);
        bizContent.put("out_refund_no", outRefundNo);
        bizContent.put("refund_amount", refundAmount);
        bizContent.put("notify_url", notifyUrl);
        bizContent.put("risk_info", riskInfo);
        bizContent.put("settlement_product_code", settlementProductCode);
        bizContent.put("settlement_ext", settlementExt);
        bizContent.put("product_code", productCode);
        bizContent.put("payment_type", paymentType);
        bizContent.put("trans_code", transCode);
        bizContent.put("reason", reason);
        bizContent.put("third_refund_account", thirdRefundAccount);

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
            return TTPayService.appId + "_" + this.outOrderNo + "_" + System.currentTimeMillis();
        } else {
            return TTPayService.appId + "_" + this.tradeNo + "_" + System.currentTimeMillis();
        }
    }

    @Override
    protected String getUrl() {
        if (TTPayService.tpDomain.endsWith("/")) {
            return TTPayService.tpDomain + path;
        } else {
            return TTPayService.tpDomain +"/"+ path;
        }
    }

    @Override
    protected Type getResponseType() {
        return new TypeToken<BaseResponse<RefundCreateResponse>>() {
        }.getType();
    }

    @Override
    public void valid() throws InvalidRequestException {
        ParamsUtil.checkAppId(TTPayService.appId);
        ParamsUtil.checkMerchantId(TTPayService.merchantId);
        ParamsUtil.checkUId(uid);
        ParamsUtil.checkOutRefundNo(outRefundNo);
        ParamsUtil.checkRefundAmount(refundAmount);
        ParamsUtil.checkNotifyUrl(notifyUrl);
        ParamsUtil.checkRiskInfo(riskInfo);
    }

}
