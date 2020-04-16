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


@Builder
@ToString
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class TradeCreateRequest extends TPRequest {
    @SerializedName("method")
    @Default
    private String method = "tp.trade.create";

    @SerializedName("format")
    @Default
    private String format = "JSON";

    @SerializedName("charset")
    @Default
    private String charset = "utf-8";

    @SerializedName("sign_type")
    @Default
    private String signType = "MD5";

    @SerializedName("path")
    @Default
    private String path = "gateway";

    @SerializedName("timestamp")
    @Default
    private String timestamp = "";

    @SerializedName("applet_version")
    @Default
    private String appletVersion = "2.0+";

    @SerializedName("version")
    @Default
    private String version = "1.0";

    @SerializedName("out_order_no")
    @Default
    private String outOrderNo = "";

    @SerializedName("uid")
    @Default
    private String uid = "";

    @SerializedName("uid_type")
    @Default
    private String uidType = "";

    @SerializedName("total_amount")
    @Default
    private Long totalAmount = Long.valueOf(0);

    @SerializedName("currency")
    @Default
    private String currency = "";

    @SerializedName("trade_type")
    @Default
    private String tradeType = "";

    @SerializedName("subject")
    @Default
    private String subject = "";

    @SerializedName("body")
    @Default
    private String body = "";

    @SerializedName("product_code")
    @Default
    private String productCode = "";

    @SerializedName("payment_type")
    @Default
    private String paymentType = "";

    @SerializedName("pay_type")
    @Default
    private String payType = "";

    @SerializedName("trade_time")
    @Default
    private String tradeTime = "";

    @SerializedName("valid_time")
    @Default
    private String validTime = "";

    @SerializedName("notify_url")
    @Default
    private String notifyUrl = "";

    @SerializedName("risk_info")
    @Default
    private String riskInfo = "";

    @SerializedName("params")
    @Default
    private String params = "";

    @SerializedName("product_id")
    @Default
    private String productID = "";

    @SerializedName("pay_channel")
    @Default
    private String payChannel = "";

    @SerializedName("pay_discount")
    @Default
    private String payDiscount = "";

    @SerializedName("service_fee")
    @Default
    private String serviceFee = "";

    @SerializedName("alipay_url")
    @Default
    private String alipayUrl = "";

    @SerializedName("wx_url")
    @Default
    private String wxUrl = "";

    @SerializedName("wx_type")
    @Default
    private String wxType = "";

    @SerializedName("ext")
    @Default
    private String extParam = "";

    @SerializedName("trade_no")
    @Default
    private String tradeNo = "";

    @Override
    protected Map<String, Object> encode() throws IOException {
        Map<String, Object> bizContent = new HashMap<>();
        bizContent.put("out_order_no", outOrderNo);
        bizContent.put("uid", uid);
        bizContent.put("uid_type", uidType);
        bizContent.put("merchant_id", TTPayService.merchantId);
        bizContent.put("total_amount", totalAmount);
        bizContent.put("currency", currency);
        bizContent.put("subject", subject);
        bizContent.put("body", body);
        bizContent.put("product_code", productCode);
        bizContent.put("payment_type", paymentType);
        bizContent.put("trade_time", tradeTime);
        bizContent.put("valid_time", validTime);
        bizContent.put("notify_url", notifyUrl);
        bizContent.put("service_fee", serviceFee);
        bizContent.put("risk_info", riskInfo);

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
        return TTPayService.appId + "_" + TTPayService.merchantId + "_" + outOrderNo + "_" + System.currentTimeMillis();
    }

    @Override
    protected String getUrl() {
        if (TTPayService.tpDomain.endsWith("/")) {
            return TTPayService.tpDomain + path;
        }
        return TTPayService.tpDomain + "/" + path;
    }

    @Override
    protected Type getResponseType() {
        return new TypeToken<BaseResponse<TradeCreateResponse>>() {
        }.getType();
    }

    @Override
    public void valid() throws InvalidRequestException {
        ParamsUtil.checkAppId(TTPayService.appId);
        if (method == null || !method.equals("tp.trade.create")) {
            throw new InvalidRequestException("invalid param: method");
        }
        ParamsUtil.checkOutOrderNo(outOrderNo);
        ParamsUtil.checkUId(uid);
        ParamsUtil.checkMerchantId(TTPayService.merchantId);
        ParamsUtil.checkTotalAmount(totalAmount);
        ParamsUtil.checkCurrency(currency);
        ParamsUtil.checkSubject(subject);
        ParamsUtil.checkBody(body);
        ParamsUtil.checkTradeTime(tradeTime);
        ParamsUtil.checkValidTime(validTime);
        ParamsUtil.checkNotifyUrl(notifyUrl);
        ParamsUtil.checkRiskInfo(riskInfo);
        if( appletVersion.equals("2.0") || appletVersion.equals("2.0+") ) {
            ParamsUtil.checkProductCode(productCode);
            ParamsUtil.checkPaymentType(paymentType);
            ParamsUtil.checkTradeType(tradeType);
        }
    }

}
