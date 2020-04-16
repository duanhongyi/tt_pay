package com.bytedance.caijing.tt_pay.model;

import com.bytedance.caijing.tt_pay.net.TPResponse;
import com.google.gson.Gson;
import org.junit.Test;

public class TestTradeCreateResponse {

    @Test
    public void testTradeCreateResponse() {
        Gson gson = new Gson();

        String jsonString = "{\"code\":\"10000\",\"msg\":\"Success\",\"trade_no\":\"SP2019052414040009113154635974\"}";
        TPResponse tpResponse = gson.fromJson(jsonString, TradeCreateResponse.class);

        TradeCreateResponse tradeCreateResponse = (TradeCreateResponse) tpResponse;

        System.out.println(tradeCreateResponse.getTradeNo());
    }
}
