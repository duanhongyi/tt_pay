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
public class RefundQueryResponse extends TPResponse {
    @SerializedName("out_refund_no")
    private String outRefundNo;

    @SerializedName("refund_no")
    private String outOrderNo;

    @SerializedName("trade_no")
    private String tradeNo;

    @SerializedName("refund_amount")
    private String refundAmount;

    @SerializedName("refund_status")
    private String refundStatus;

    @SerializedName("channel_ext")
    private String channelExt;

    @Override
    protected String getSignString() {
        return "";
    }
}
