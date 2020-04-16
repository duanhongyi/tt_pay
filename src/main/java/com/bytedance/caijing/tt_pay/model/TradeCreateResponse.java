package com.bytedance.caijing.tt_pay.model;

import com.bytedance.caijing.tt_pay.TTPayService;
import com.bytedance.caijing.tt_pay.exception.InvalidRequestException;
import com.bytedance.caijing.tt_pay.net.TPResponse;
import com.bytedance.caijing.tt_pay.util.JsonUtil;
import com.bytedance.caijing.tt_pay.util.SignUtil;
import com.google.gson.annotations.SerializedName;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


@ToString
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class TradeCreateResponse extends TPResponse {

    @SerializedName("trade_no")
    private String tradeNo;

    @SerializedName("applet_params")
    private String appletParams;

    public void GenCashdeskAppletParams(TradeCreateRequest req)  {
        String appletVersion = req.getAppletVersion();
        Map<String, String> appletMap = new HashMap<String, String>();

        switch (appletVersion) {
            case "1.0":
                appletMap.put("1.0", this.GetCashdeskAppletParams1_0(req));
                break;
            case "2.0":
                appletMap.put("2.0", this.GetCashdeskAppletParams2_0(req));
                break;
            case "2.0+":
                appletMap.put("1.0", this.GetCashdeskAppletParams1_0(req));
                appletMap.put("2.0", this.GetCashdeskAppletParams2_0(req));
                break;
        }

        appletParams = JsonUtil.toJSON(appletMap);
    }

    private String GetCashdeskAppletParams1_0(TradeCreateRequest req) {
        Map<String, Object> appletParams = new HashMap<String, Object>();

        appletParams.put("app_id", TTPayService.appId);
        appletParams.put("sign_type", req.getSignType());
        appletParams.put("timestamp", "" + System.currentTimeMillis() / 1000);
        appletParams.put("trade_no", req.getTradeNo());
        appletParams.put("merchant_id", TTPayService.merchantId);
        appletParams.put("uid", req.getUid());
        appletParams.put("total_amount", req.getTotalAmount());
        appletParams.put("params",  "{\"url\":\"" + req.getParams() + "\"}");

        appletParams.put("sign", SignUtil.BuildMd5WithSalt(appletParams, TTPayService.appSecret));

        appletParams.put("method", "tp.trade.confirm");
        appletParams.put("pay_type", req.getPayType());
        appletParams.put("pay_channel", req.getPayChannel());
        appletParams.put("risk_info", req.getRiskInfo());

        return JsonUtil.toJSON(appletParams);
    }

    private String GetCashdeskAppletParams2_0(TradeCreateRequest req) {
        Map<String, Object> appletParams = new HashMap<String, Object>();

        appletParams.put("app_id", TTPayService.appId);
        appletParams.put("sign_type", req.getSignType());
        appletParams.put("timestamp", "" + System.currentTimeMillis() / 1000);
        appletParams.put("merchant_id", TTPayService.merchantId);
        appletParams.put("uid", req.getUid());
        appletParams.put("total_amount", req.getTotalAmount().toString());
        appletParams.put("out_order_no", req.getOutOrderNo());
        appletParams.put("product_code", req.getProductCode());
        appletParams.put("notify_url", req.getNotifyUrl());
        appletParams.put("trade_type", req.getTradeType());
        appletParams.put("payment_type", req.getPaymentType());
        appletParams.put("subject", req.getSubject());
        appletParams.put("body", req.getBody());
        appletParams.put("trade_time", req.getTradeTime());
        appletParams.put("valid_time", req.getValidTime());
        appletParams.put("currency", req.getCurrency());
        appletParams.put("version", req.getVersion());
        appletParams.put("alipay_url", req.getAlipayUrl());
        appletParams.put("wx_url", req.getWxUrl());
        appletParams.put("wx_type", req.getWxType());
        
        appletParams.put("sign", SignUtil.BuildMd5WithSalt(appletParams, TTPayService.appSecret));

        appletParams.put("risk_info", req.getRiskInfo());

        return JsonUtil.toJSON(appletParams);
    }

    @Override
    protected String getSignString() {
        return "";
    }
}
