package com.bytedance.caijing.tt_pay;

import com.bytedance.caijing.tt_pay.exception.InvalidRequestException;
import com.bytedance.caijing.tt_pay.model.*;
import com.bytedance.caijing.tt_pay.net.APIResource;
import lombok.*;
import lombok.Builder.Default;

import java.util.Map;

@ToString
@Builder
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class TTPayService {
    @Default
    public static String appId = "";

    @Default
    public static String appSecret = "";

    @Default
    public static String merchantId = "";

    @Default
    public static String tpDomain = "https://tp-pay.snssdk.com";

    public static final String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDOZZ7iAkS3oN970+yDONe5TPhPrLHoNOZOjJjackEtgbptdy4PYGBGdeAUAz75TO7YUGESCM+JbyOz1YzkMfKl2HwYdoePEe8qzfk5CPq6VAhYJjDFA/M+BAZ6gppWTjKnwMcHVK4l2qiepKmsw6bwf/kkLTV9l13r6Iq5U+vrmwIDAQAB";

    public static TradeCreateResponse TradeCreate(TradeCreateRequest request) throws Exception {
        TradeCreateResponse response = new TradeCreateResponse();

        String versionBackend = request.getVersion();
        String versionApplet = request.getAppletVersion();

        request.valid();

        if ( versionBackend.equals("1.0") || versionApplet.equals("2.0+") || versionApplet.equals("1.0") ) {
            response = APIResource.call(request);
            request.setTradeNo(response.getTradeNo());
        }

        response.GenCashdeskAppletParams(request);
        return response;
    }

    public static TradeQueryResponse TradeQuery(TradeQueryRequest request) throws Exception {
        request.valid();
        return APIResource.call(request);
    }

    public static RefundQueryResponse RefundQuery(RefundQueryRequest request) throws Exception {
        request.valid();
        return APIResource.call(request);
    }
    public static RefundCreateResponse RefundCreate(RefundCreateRequest request) throws Exception {
        request.valid();
        return APIResource.call(request);
    }
    public static RefundNotifyResponse RefundNotify(RefundNotifyRequest request) throws Exception {
        request.valid();
        return request.decode(request.getParam());
    }

    public static TradeNotifyResponse TradeNotify(TradeNotifyRequest request) throws Exception {
        request.valid();
        return request.decode();
    }

}
