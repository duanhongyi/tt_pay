package com.bytedance.caijing.tt_pay.model;

import com.bytedance.caijing.tt_pay.TTPayService;
import com.bytedance.caijing.tt_pay.exception.InvalidRequestException;
import com.bytedance.caijing.tt_pay.util.SignUtil;
import com.google.gson.annotations.SerializedName;
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
public class RefundNotifyRequest {
    @SerializedName("param")
    private String param;

    public RefundNotifyResponse decode(String param) throws Exception {
        this.valid();
        List<NameValuePair> values = URLEncodedUtils.parse(param, Charset.forName("UTF-8"));
        Map<String, Object> params = new HashMap<String, Object>();
        String sign = null;
        for (NameValuePair value : values) {
            if (value.getName().equals("sign")) {
                sign = value.getValue();
                continue;
            }
            params.put(value.getName(), value.getValue());
        }
        RefundNotifyResponse response = new RefundNotifyResponse();
        try {
            if (SignUtil.VerifyMd5WithRsa(params, sign, TTPayService.publicKey)) {
                if (params.containsKey("notify_id")) {
                    response.setNotifyId(String.valueOf(params.get("notify_id")));
                }

                if (params.containsKey("sign_type")) {
                    response.setSignType(String.valueOf(params.get("sign_type")));
                }

                if (sign != null && !"".equals(sign)) {
                    response.setSign(String.valueOf(params.get("sign")));
                }

                if (params.containsKey("app_id")) {
                    response.setAppId(String.valueOf(params.get("add_id")));
                }

                if (params.containsKey("event_code")) {
                    response.setEventCode(String.valueOf(params.get("event_code")));
                }

                if (params.containsKey("out_refund_no")) {
                    response.setOutRefundNo(String.valueOf(params.get("out_refund_no")));
                }

                if (params.containsKey("refund_no")) {
                    response.setRefundNo(String.valueOf(params.get("refund_no")));
                }

                if (params.containsKey("refund_amount")) {
                    response.setRefundAmount(String.valueOf(params.get("refund_amount")));
                }

                if (params.containsKey("refund_time")) {
                    response.setRefundTime(String.valueOf(params.get("refund_time")));
                }

                if (params.containsKey("merchant_id")) {
                    response.setMerchantId(String.valueOf(params.get("merchant_id")));
                }

                if (params.containsKey("refund_status")) {
                    response.setRefundStatus(String.valueOf(params.get("refund_status")));
                }
            }
        } catch (SignatureException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException e) {
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

