package com.bytedance.caijing.tt_pay.util;

import com.bytedance.caijing.tt_pay.exception.InvalidRequestException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParamsUtil {
	// Regular expressions
	private static final String  RegExp_AppId  = "^[0-9a-zA-Z-_]{1,32}$";
	private static final String  RegExp_MerchantId  = "^[0-9a-zA-Z-_]{1,32}$";
	private static final String  RegExp_Uid         = "^.{1,32}$";
	private static final String  RegExp_SignType    = "^[0-9a-zA-Z]{1,10}$";
	private static final String  RegExp_Version     = "^[0-9].[0-9]$";
	private static final String  RegExp_TimeStamp   = "^[0-9]{1,19}$";
	private static final String  RegExp_OutRefundNo = "^$|^[a-zA-Z0-9-_]{1,32}$";
	private static final String  RegExp_RefundNo = "^$|^[a-zA-Z0-9-_]{1,64}$";
	private static final String  RegExp_Url        = "^(.*):(.*)$|(https|http):\\/\\/[-A-Za-z0-9+&@#\\/%?=~_|!:,.;]+[-A-Za-z0-9+&@#\\/%=~_|]";
	private static final String  RegExp_RiskInfo = "^\\{(\".*\":\".*\",)*\".*\":\".*\"}$";
	private static final String  RegExp_OutOrderNo = "^$|^[0-9a-zA-Z-_]{1,32}$";
	private static final String  RegExp_TradeNo = "^$|^[a-zA-Z0-9-_]{1,64}$";
	private static final String  RegExp_TotalAmount = "^[1-9][0-9]*$";
	private static Pattern pattern;
	private static Matcher matcher;

	public static void checkAppId(String appId)throws InvalidRequestException{
		pattern = Pattern.compile(RegExp_AppId);
		matcher = pattern.matcher(appId);
		if(!matcher.matches()){
			throw new InvalidRequestException("invalid param: appId");
		}
	}

	public static void checkMerchantId(String merchantId)throws InvalidRequestException{
		pattern = Pattern.compile(RegExp_MerchantId);
		matcher = pattern.matcher(merchantId);
		if(!matcher.matches()){
			throw new InvalidRequestException("invalid param: merchantId");
		}
	}

	public static void checkUId(String uid)throws InvalidRequestException{
		pattern = Pattern.compile(RegExp_Uid);
		matcher = pattern.matcher(uid);
		if(!matcher.matches()){
			throw new InvalidRequestException("invalid param: uid");
		}
	}

	public static void checkSignType(String sighType)throws InvalidRequestException{
		pattern = Pattern.compile(RegExp_SignType);
		matcher = pattern.matcher(sighType);
		if(!matcher.matches()){
			throw new InvalidRequestException("invalid param: sighType");
		}
	}

	public static void checkVersion(String version)throws InvalidRequestException{
		pattern = Pattern.compile(RegExp_Version);
		matcher = pattern.matcher(version);
		if(!matcher.matches()){
			throw new InvalidRequestException("invalid param: version");
		}
	}

	public static void checkTimeStamp(String timeStamp)throws InvalidRequestException{
		pattern = Pattern.compile(RegExp_TimeStamp);
		matcher = pattern.matcher(timeStamp);
		if(!matcher.matches()){
			throw new InvalidRequestException("invalid param: timeStamp");
		}
	}

	public static void checkTradeTime(String tradeTime) throws InvalidRequestException{
		pattern = Pattern.compile(RegExp_TimeStamp);
		matcher = pattern.matcher(tradeTime);
		if(!matcher.matches()){
			throw new InvalidRequestException("invalid param: trade_time");
		}
	}

	public static void checkValidTime(String validTime)throws InvalidRequestException{
		pattern = Pattern.compile(RegExp_TimeStamp);
		matcher = pattern.matcher(validTime);
		if(!matcher.matches()){
			throw new InvalidRequestException("invalid param: valid_time");
		}
	}

	public static void checkOutRefundNo(String outRefundNo)throws InvalidRequestException{
		pattern = Pattern.compile(RegExp_OutRefundNo);
		matcher = pattern.matcher(outRefundNo);
		if(!matcher.matches()){
			throw new InvalidRequestException("invalid param: outRefundNo");
		}
	}

	public static void checkRefundNo(String refundNo)throws InvalidRequestException{
		pattern = Pattern.compile(RegExp_RefundNo);
		matcher = pattern.matcher(refundNo);
		if(!matcher.matches()){
			throw new InvalidRequestException("invalid param: refundNo");
		}
	}

	public static void checkTPDomain(String tpDomain)throws InvalidRequestException{
		pattern = Pattern.compile(RegExp_Url);
		matcher = pattern.matcher(tpDomain);
		if(!matcher.matches()){
			throw new InvalidRequestException("invalid param: tpDomain");
		}
	}

	public static void checkNotifyUrl(String notifyUrl)throws InvalidRequestException{
		pattern = Pattern.compile(RegExp_Url);
		matcher = pattern.matcher(notifyUrl);
		if(!matcher.matches()){
			throw new InvalidRequestException("invalid param: notifyUrl");
		}
	}

	public static void checkReturnUrl(String returnUrl)throws InvalidRequestException{
		pattern = Pattern.compile(RegExp_Url);
		matcher = pattern.matcher(returnUrl);
		if(!matcher.matches()){
			throw new InvalidRequestException("invalid param: returnUrl");
		}
	}

	public static void checkRiskInfo(String riskInfo)throws InvalidRequestException{
		pattern = Pattern.compile(RegExp_RiskInfo);
		matcher = pattern.matcher(riskInfo);
		if(!matcher.matches()){
			throw new InvalidRequestException("invalid param: riskInfo");
		}
	}
	public static void checkOutOrderNo(String outOrderNo)throws InvalidRequestException{
		pattern = Pattern.compile(RegExp_OutOrderNo);
		matcher = pattern.matcher(outOrderNo);
		if(!matcher.matches()){
			throw new InvalidRequestException("invalid param: outOrderNo");
		}
	}

	public static void checkTradeNo(String tradeNo)throws InvalidRequestException{
		pattern = Pattern.compile(RegExp_TradeNo);
		matcher = pattern.matcher(tradeNo);
		if(!matcher.matches()){
			throw new InvalidRequestException("invalid param: tradeNo");
		}
	}

	public static void checkTotalAmount(Long totalAmount)throws InvalidRequestException{
		if( totalAmount <= 0 ) {
			throw new InvalidRequestException("invalid param: totalAmout");
		}
	}

	public static void  checkCurrency(String currency) throws InvalidRequestException {
		if(currency == null || currency.equals("")) {
			throw new InvalidRequestException("invalid param: currency");
		}
	}

	public static void  checkSubject(String subject) throws InvalidRequestException {
		if(subject == null || subject.equals("")) {
			throw new InvalidRequestException("invalid param: subject");
		}
	}

	public static void  checkBody(String body) throws InvalidRequestException {
		if(body == null || body.equals("")) {
			throw new InvalidRequestException("invalid param: body");
		}
	}

	public static void  checkProductCode(String productCode) throws InvalidRequestException {
		if(productCode == null || productCode.equals("")) {
			throw new InvalidRequestException("invalid param: productCode");
		}
	}

	public static void  checkPaymentType(String paymentType) throws InvalidRequestException {
		if(paymentType == null || paymentType.equals("")) {
			throw new InvalidRequestException("invalid param: paymentType");
		}
	}

	public static void  checkTradeType(String tradeType) throws InvalidRequestException {
		if(tradeType == null || tradeType.equals("")) {
			throw new InvalidRequestException("invalid param: tradeType");
		}
	}

	public static void checkRefundAmount(Long refundAmount)throws InvalidRequestException{
		if( refundAmount <= 0 ) {
			throw new InvalidRequestException("invalid param: refundAmount");
		}
	}

}
