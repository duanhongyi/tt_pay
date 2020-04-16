package com.bytedance.caijing.tt_pay.net;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

import com.bytedance.caijing.tt_pay.exception.InvalidRequestException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public abstract class TPRequest {
    protected abstract Map<String, Object> encode() throws IOException;

    protected abstract String getLogId();

    protected abstract String getUrl();

    protected abstract Type getResponseType();

    public abstract void valid() throws InvalidRequestException;

}
