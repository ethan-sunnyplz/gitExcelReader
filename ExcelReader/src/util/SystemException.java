package util;

import java.text.DecimalFormat;
 
/**
 * RuntimeException 클래스
 * @author 송덕재
 *
 */
public class SystemException extends RuntimeException {
	
	private static DecimalFormat codeFormat = new DecimalFormat("0000");

	private String code;   

	private Object[] args;

	/**
	 * 예외 생성
	 *   
	 * @param code
	 *            예외(에러) 코드
	 * @param args
	 *            에러 출력시 사용될 아규먼트, 없을 경우 null 셋팅한다.
	 */
	public SystemException(String code, Object[] args) {
		this.code = code;
		this.args = args;
	}

	/**
	 * 예외 생성
	 * 
	 * @param codeNum
	 *            예외(에러) 코드
	 * @param args
	 *            에러 출력시 사용될 아규먼트, 없을 경우 null 셋팅한다.
	 */
	public SystemException(int codeNum, Object[] args) {
		this.code = codeFormat.format(codeNum);
		this.args = args;
	}

	/**
	 * 예외(에러) 코드 반환
	 * 
	 * @return String 예외(에러) 코드
	 */
	public String getCode() {
		return this.code;
	}

	/**
	 * 에러 출력시 사용될 아규먼트 반환
	 * 
	 * @return Object[] 에러 출력시 사용될 아규먼트
	 */
	public Object[] getArguments() {
		return this.args;
	}
}