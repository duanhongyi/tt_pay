package com.bytedance.caijing.tt_pay.model;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.junit.Test;

import java.nio.charset.Charset;
import java.util.List;

public class TestTradeNotifyRequest {

    @Test
    public void testParseUrl() {

        String query = "event_code=trade_status_sync&extension=%7B%22channel_code%22%3A%22LL_fangxinjie_1977%22%2C%22channel_merchant_order_no%22%3A%2211905210002203466466%22%2C%22channel_order_no%22%3A%222019052172521793%22%7D&merchant_id=fangxinjie&notify_id=SP2019052116131126729886806466&out_order_no=6693390424557490701&pay_channel=ANY_AGREEMENT_PAY&pay_time=1558426402&pay_type=ANY_AGREEMENT_PAY&real_amount=802133&sign=sWPeQGWvrooewI01IQsK4aWBe4OkIhzNKLRB7cq3sXoKX%2FTEduFqAPiuIpSpZDnyNQxREhYufXi8VyPxSgvc0poZ8m%2FixDON3XfmjSHF43TDbFTD%2BbJK0oLNcCcQ%2FhwjM%2BW8dt%2B9es%2BTM3iUv5SrNClkXdsDaxqVxtbOpS1kDS0%3D&sign_type=RSA&status=SUCCESS&total_amount=802133&trade_msg=success&trade_no=SP2019052116131126729886806466&trade_status=TRADE_SUCCESS";
        List<NameValuePair> params = URLEncodedUtils.parse(query, Charset.forName("UTF-8"));
        System.out.println(params.toString());

    }
}
