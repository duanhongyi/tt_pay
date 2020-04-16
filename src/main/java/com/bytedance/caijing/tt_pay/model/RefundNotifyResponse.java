package com.bytedance.caijing.tt_pay.model;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@ToString
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class RefundNotifyResponse {
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

    @SerializedName("out_refund_no")
    private String outRefundNo;

    @SerializedName("refund_no")
    private String refundNo;

    @SerializedName("refund_amount")
    private String refundAmount;

    @SerializedName("refund_time")
    private String refundTime;

    @SerializedName("merchant_id")
    private String merchantId;

    @SerializedName("refund_status")
    private String refundStatus;

}
