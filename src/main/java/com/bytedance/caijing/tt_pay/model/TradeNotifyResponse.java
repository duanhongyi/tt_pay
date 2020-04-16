package com.bytedance.caijing.tt_pay.model;


import com.google.gson.annotations.SerializedName;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class TradeNotifyResponse {

    @SerializedName("notify_id")
    private String notifyId;

    @SerializedName("sign_type")
    private String signType;

    @SerializedName("sign")
    private String sign;

    @SerializedName("app_id")
    private String appId;

    @SerializedName("event_code")
    private String eventCode;

    @SerializedName("merchant_id")
    private String merchantId;

    @SerializedName("out_order_no")
    private String outOrderNo;

    @SerializedName("trade_no")
    private String tradeNo;

    @SerializedName("total_amount")
    private String totalAmount;

    @SerializedName("pay_channel")
    private String payChannel;

    @SerializedName("pay_time")
    private String payTime;

    @SerializedName("pay_type")
    private String payType;

    @SerializedName("trade_status")
    private String tradeStatus;

    @SerializedName("trade_msg")
    private String tradeMsg;

    @SerializedName("extension")
    private String extension;

}
