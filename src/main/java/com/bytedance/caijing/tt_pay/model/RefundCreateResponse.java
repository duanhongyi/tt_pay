package com.bytedance.caijing.tt_pay.model;

import com.bytedance.caijing.tt_pay.net.TPResponse;
import com.google.gson.annotations.SerializedName;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class RefundCreateResponse extends TPResponse {

    @SerializedName("out_order_no")
    private String outOrderNo;

    @SerializedName("out_refund_no")
    private String outRefundNo;

    @SerializedName("refund_no")
    private String refundNo;

    @SerializedName("refund_amount")
    private String refundAmount;

    @SerializedName("channel_ext")
    private String channelExt;

    @Override
    protected String getSignString() {
        return "";
    }
}

