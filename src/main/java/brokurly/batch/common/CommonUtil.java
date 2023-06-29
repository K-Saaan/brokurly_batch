package brokurly.batch.common;

import org.springframework.util.StringUtils;

// 공통 함수 정의


public class CommonUtil {
	
	/**
	 * 신용카드번호 마스킹, 하이픈 추가
	 * @param str : 신용카드번호 14~16자리(하이픈 포함 가능)
	 * @return 14자리 : 1111-**-****-4444
	 *         15자리 : 1111-***-****-4444
	 *         16자리 : 1111-****-****-4444
	 *                
	 */
    public static String maskCardNo(String str) {
		if(StringUtils.isEmpty(str)) {
			return str;
		}
		
    	String defPattern = "(\\d{4})-?(\\d{4})-?(\\d{4})-?(\\d{4})"; // 기본
    	//String acrdPattern = "(\\d{4})-?(\\d{6})-?(\\d{5})"; // AMEX
    	//String dcrdPattern = "(\\d{4})-?(\\d{6})-?(\\d{4})"; // 다이너스
    	String acrdPattern = "(\\d{4})-?(\\d{3})-?(\\d{4})-?(\\d{4})"; // AMEX
    	String dcrdPattern = "(\\d{4})-?(\\d{2})-?(\\d{4})-?(\\d{4})"; // 다이너스       	

    	return str.replaceAll(defPattern, "$1-****-****-$4");
	}
    
    /**
	 * 휴대폰번호 마스킹, 하이픈 추가
	 * @param str : 휴대폰번호 10자리 또는 11자리(하이픈 포함 가능)
	 * @return : 010-1**-22**, 010-11**-22**
	 */
	public static String maskTelNo(String str) {
		if(StringUtils.isEmpty(str)) {
			return str;
		}
		
		str = str.replaceAll("(\\d{3})-?(\\d{1,2})\\d{2}-?(\\d{2})\\d{2}", "$1-$2**-$3**");

		return str;
	}
	
	/**
	 * 계좌번호 마스킹 추가
	 * @param str : 계좌번호
	 * @return : 중간 6자리 마스킹
	 */
    public static String maskAccountNo(String str) {
		if(StringUtils.isEmpty(str)) {
			return str;
		}
		
		str = str.replaceAll("-", "");

		int maskLength = 6;
		if(str.length() < maskLength) {
			maskLength = str.length();
		}
		int startIndex = (str.length() - maskLength)/2;

		StringBuilder sb = new StringBuilder(str);
		for(int i = startIndex; i<(startIndex + maskLength); i++) {
			sb.setCharAt(i, '*');
		}

		return sb.toString();
	}
    
	/**
	 * 주민등록번호 마스킹, 하이픈 추가
	 * @param str : 주민등록번호
	 * @param isMask : 마스킹 여부
	 * @return 
	 */
    public static String maskRegNo(String str, boolean isMask) {
		if(StringUtils.isEmpty(str)) {
			return str;
		}
		
    	str = str.replaceAll("(\\d{6})-?(\\S{7})", "$1-$2");
    	if(isMask) {
        	String pattern = "(.{6}$)";
        	str = str.replaceAll(pattern, "******");
    	}
		return str;
    }
    
    /**
	 * 이름 마스킹
	 * @param str : 이름
	 * @return : 2자 이름: 끝 '*' 마스킹 처리
	 * 				3자 이름: 중간 '*' 마스킹 처리
	 * 				4자이상 이름: 성 뒤 2글자 '*' 마스킹 처리
	 */
    public static String maskName(String str) {
		if(StringUtils.isEmpty(str)) {
			return str;
		}
		
		StringBuilder sb = new StringBuilder(str);
		
		if(sb.length() > 1) {
			sb.setCharAt(1, '*');
			if(sb.length() > 3) {
				sb.setCharAt(2, '*');
			}
		}
		
		return sb.toString();
	}

	/**
	 * 문자열에서 숫자만 추출
	 * @param str : 문자열
	 * @return : 문자열에서 숫자만 추출
	 */
	public static String StringOnlyNum(String str){
		str = str.replaceAll("[^0-9]", "");;
		return str;
	}
}
