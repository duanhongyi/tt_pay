package com.bytedance.caijing.tt_pay.net;

import com.google.gson.annotations.SerializedName;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter(AccessLevel.PROTECTED)
@Setter(AccessLevel.PROTECTED)
public abstract class TPResponse {

    @SerializedName("code")
    private String code;

    @SerializedName("msg")
    private String msg;

    @SerializedName("sub_code")
    private String subCode;

    @SerializedName("sub_msg")
    private String subMsg;

    /**
     * 返回签名字符串
     * @return
     */
    protected abstract String getSignString();

    public boolean isSuccess() {
        if (code.equals("10000")) {
           return true;
        }
        return false;
    }

}
