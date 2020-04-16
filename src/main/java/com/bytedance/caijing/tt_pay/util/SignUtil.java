package com.bytedance.caijing.tt_pay.util;

import com.bytedance.caijing.tt_pay.TTPayLog;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.binary.Base64;

import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;

public class SignUtil {
    public static String BuildMd5WithSalt(Map<String, Object> dataMap, String salt) {
        String signStr = GenSignStr(dataMap);
        TTPayLog.debug("SignUtil.sign", "signStr:" + signStr);
        return DigestUtils.md5Hex(signStr + salt);
    }

    private static String GenSignStr(Map<String, Object> data) {
        StringBuilder sb = new StringBuilder();
        Set<String> entrySet = data.keySet();
        List<String> list = new ArrayList<String>(entrySet);
        Collections.sort(list);
        for (String key: list) {
            if ( key.equals("") || data.get(key).equals("") || data.get(key)==null) {
                continue;
            }
            sb.append(key);
            sb.append("=");
            sb.append(data.get(key));
            sb.append("&");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    public static boolean VerifyMd5WithRsa(Map<String, Object> params, String sign, String publicKey) throws SignatureException, InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException {
        String signStr = GenSignStr(params);
        return RsaVerify(signStr, sign, publicKey);
    }

    private static boolean RsaVerify(String target, String verifySign, String publicKey) throws SignatureException, NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException {
        //获取公钥
        byte[] keyBytes = Base64.decodeBase64(publicKey);//解密由base64编码的公钥
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes); // 构造X509EncodedKeySpec对象
        //公钥验签
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicK = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initVerify(publicK);
        signature.update(target.getBytes());
        byte[] signBytes = Base64.decodeBase64(verifySign);
        return signature.verify(signBytes);
    }

}
