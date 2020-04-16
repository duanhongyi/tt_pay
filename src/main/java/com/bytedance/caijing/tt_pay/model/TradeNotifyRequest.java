package com.bytedance.caijing.tt_pay.model;


import com.bytedance.caijing.tt_pay.TTPayService;
import com.bytedance.caijing.tt_pay.exception.InvalidRequestException;
import com.bytedance.caijing.tt_pay.util.SignUtil;
import lombok.*;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ToString
@Builder
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)

public class TradeNotifyRequest {

    private String param;

    public TradeNotifyResponse decode() throws InvalidRequestException {
        this.valid();
        List<NameValuePair> values = URLEncodedUtils.parse(param, Charset.forName("UTF-8"));
        Map<String, Object> params = new HashMap<>();
        String sign = null;
        for (NameValuePair value:values) {
            if (value.getName().equals("sign")) {
                sign = value.getValue();
                continue;
            }
            params.put(value.getName(), value.getValue());
        }

        TradeNotifyResponse response = new TradeNotifyResponse();
        try{
            if(SignUtil.VerifyMd5WithRsa(params,sign, TTPayService.publicKey)) {
                if ( params.containsKey("notify_id") ) {
                    response.setNotifyId((String) params.get("notify_id"));
                }

                if ( params.containsKey("sign_type") ) {
                    response.setSignType((String) params.get("sign_type"));
                }

                if ( sign != null && !"".equals(sign) ) {
                    response.setSign((String) params.get("sign"));
                }

                if ( params.containsKey("app_id") ) {
                    response.setAppId((String) params.get("app_id"));
                }

                if (params.containsKey("event_code")) {
                    response.setEventCode((String) params.get("event_code"));
                }

                if (params.containsKey("merchant_id")) {
                    response.setMerchantId((String) params.get("merchant_id"));
                }

                if (params.containsKey("out_order_no")) {
                    response.setOutOrderNo((String) params.get("out_order_no"));
                }

                if (params.containsKey("trade_no")) {
                    response.setTradeNo((String) params.get("trade_no"));
                }

                if (params.containsKey("total_amount")) {
                    response.setTotalAmount((String) params.get("total_amount"));
                }

                if (params.containsKey("pay_channel")) {
                    response.setPayChannel((String) params.get("pay_channel"));
                }

                if (params.containsKey("pay_time")) {
                    response.setPayTime((String) params.get("pay_time"));
                }

                if (params.containsKey("pay_type")) {
                    response.setPayType((String) params.get("pay_type"));
                }

                if (params.containsKey("trade_status")) {
                    response.setTradeStatus((String) params.get("trade_status"));
                }

                if (params.containsKey("trade_msg")) {
                    response.setTradeMsg((String) params.get("trade_msg"));
                }

                if (params.containsKey("extension")) {
                    response.setExtension((String) params.get("extension"));
                }
            }
        }catch(SignatureException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException e){
            e.printStackTrace();
            throw new InvalidRequestException(e.getMessage());
        }
        return response;
    }


    public void valid() throws InvalidRequestException {
        if ((null == param) || ("".equals(param))) {
            throw new InvalidRequestException("lack param: param");
        }
    }

}
