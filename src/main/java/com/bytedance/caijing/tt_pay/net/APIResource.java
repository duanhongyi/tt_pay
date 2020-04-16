package com.bytedance.caijing.tt_pay.net;

import com.bytedance.caijing.tt_pay.TTPayLog;
import com.bytedance.caijing.tt_pay.exception.*;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;


import java.io.*;
import java.util.Iterator;
import java.util.Map;


/**
 * extends the abstract class when you need requset anything from bytedance
 */
public abstract class APIResource {

    private static final String CHARSET = "UTF-8";

    private static final String FORMAT = "JSON";

    private static final String VERSION = "1.0";

    private final static PoolingHttpClientConnectionManager poolConnManager = new PoolingHttpClientConnectionManager();  //连接池管理器

    static {
        poolConnManager.setMaxTotal(2000); // 最大连接数
        poolConnManager.setDefaultMaxPerRoute(1000); // 路由最大连接数
    }

    private static final Gson GSON = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .enableComplexMapKeySerialization() ////支持Map的key为复杂对象的形式
            .create();

    private static CloseableHttpClient getCloseableHttpClient() {
        return HttpClients.custom()
                .setConnectionManager(poolConnManager)
                .build();
    }


    private static String httpPost(String url, String requestParams, String logId) throws APIException {
        TTPayLog.debug("APIResource.makeURLConnectionRequest", " http url : " + url + ", request:" + requestParams);
        String body = null;
        CloseableHttpClient httpClient = getCloseableHttpClient();
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = null;
        try {
            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
            httpPost.setHeader("X-Tt-Logid", logId);
            StringEntity stringEntity = new StringEntity(requestParams, CHARSET);
            stringEntity.setContentType("application/x-www-form-urlencoded");
            httpPost.setEntity(stringEntity);
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            int httpCode = response.getStatusLine().getStatusCode();
            body = EntityUtils.toString(entity, CHARSET);
            if (httpCode < 200 || httpCode >= 300) {
                throw new APIException(body, httpCode);
            }
        } catch (IOException e) {
            throw new APIException(e.getMessage(), 0);
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return body;
    }

    public static <T extends TPResponse> T call(TPRequest tpRequest) throws APIException, TTPayException, IOException, InvalidRequestException {
        Map<String, Object> signParams = tpRequest.encode();
        StringBuilder urlValues = new StringBuilder();

        for (String key: signParams.keySet()) {
            Object val = signParams.get(key);
            if (urlValues.length() == 0) {
                urlValues.append(key);
                urlValues.append("=");
                urlValues.append(val);
            } else {
                urlValues.append("&");
                urlValues.append(key);
                urlValues.append("=");
                urlValues.append(val);
            }
        }

        String data = httpPost(tpRequest.getUrl(), urlValues.toString(), tpRequest.getLogId());

        TTPayLog.debug("APIResource.Call", " http response : " + data);

        BaseResponse<T> baseResponse = GSON.fromJson(data, tpRequest.getResponseType());
        T tpResponse = baseResponse.getResponse();
        if(!tpResponse.isSuccess()) {
            throw new TTPayException(tpResponse.getCode(), tpResponse.getMsg(), tpResponse.getSubCode(), tpResponse.getSubMsg(), "");
        }
        return tpResponse;
    }
}

