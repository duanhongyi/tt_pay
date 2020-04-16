package com.bytedance.caijing.tt_pay.net;


import com.bytedance.caijing.tt_pay.model.TradeCreateResponse;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;
import com.google.gson.Gson;

public class TestBaseResponse {

    @Test
    public void testParseBaseResponse() {
        Gson gson = new Gson();
        String jsonString = "{\"response\":{\"code\":\"10000\",\"msg\":\"Success\",\"trade_no\":\"SP2019052414040009113154635974\"},\"sign\":\"XVzPkwhCGlidJ5wazaCq9PxQqYSlDW6ZUXGlZ2jgPDIh4Sv09uRQKgkj22Ye+haXtg6fOCTrusbpP60VH8ow4+c2ILlHUTGgw0iVCZzzntiA9Kep0hrWmEQBuLv8lXHsTJMZJYOfz+cH/ygvWR2iQ80DOiKxfqtYs5V7+X7ATts=\"}";
        BaseResponse<TradeCreateResponse> baseResponse = gson.fromJson(jsonString, new TypeToken<BaseResponse<TradeCreateResponse>>() {
        }.getType());
        System.out.println(baseResponse.toString());
        System.out.println(baseResponse.getResponse().getClass());
    }
}
