package com.bytedance.caijing.tt_pay.net;

import com.google.gson.annotations.SerializedName;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter(AccessLevel.PROTECTED)
@Setter(AccessLevel.PROTECTED)
public class BaseResponse<T extends TPResponse> {

    @SerializedName("sign")
    private String sign;

    @SerializedName("response")
    private T response;

}
