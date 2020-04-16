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
public class TradeQueryResponse extends TPResponse {

    @SerializedName("trade_no")
    private String tradeNo;

    @SerializedName("out_order_no")
    private String outOrderNo;

    @SerializedName("merchant_id")
    private String merchantId;

    @SerializedName("uid")
    private String uid;

    @SerializedName("m_id")
    private String mId;

    @SerializedName("create_time")
    private String createTime;

    @SerializedName("pay_time")
    private String payTime;

    @SerializedName("trade_time")
    private String tradeTime;

    @SerializedName("expire_time")
    private String expireTime;

    @SerializedName("trade_status")
    private String tradeStatus;

    @SerializedName("trade_name")
    private String tradeName;

    @SerializedName("trade_desc")
    private String tradeDesc;

    @SerializedName("total_amount")
    private String totalAmount;

    @SerializedName("currency")
    private String currency;

    @SerializedName("pay_channel")
    private String payChannel;

    @SerializedName("coupon_no")
    private String couponNo;

    @SerializedName("real_amount")
    private String realAmount;

    @SerializedName("channel_ext")
    private String channelExt;

    @Override
    protected String getSignString() {
        return "";
    }


}
