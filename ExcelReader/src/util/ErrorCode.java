package util;

/**
 * 에러코드 클래스
 * @author 송덕재
 *
 */
public class ErrorCode {

	/**
	 * 알수없는 에러
	 */ 
	public static final int UNKNOWN_ERROR = -1;

	/**
	 * DB Select Error
	 */
	public static final int DB_SELECT_ERROR = 2001;

	/**
	 * DB Insert Error
	 */
	public static final int DB_INSERT_ERROR = 2002;

	/**
	 * DB Update Error
	 */
	public static final int DB_UPDATE_ERROR = 2003;

	/**
	 * DB Delete Error
	 */
	public static final int DB_DELETE_ERROR = 2004;

	/**
	 * RTP Pricing Calculation Error
	 */
	public static final int RTP_CALCULATION_ERROR= 3001;

	/**
	 * TOU Pricing Calculation Error
	 */
	public static final int TOU_CALCULATION_ERROR= 3002;

	/**
	 * 에러 메세지를 구한다.
	 * 
	 * @param errorCode
	 * @return errorMsg
	 */
	public static String getErrorMsg(int errorCode,Object[] args) {
		String errorMsg = "";
		errorMsg = args[0]+" 에서 ";
		switch (errorCode) {
		
		case UNKNOWN_ERROR:
			errorMsg =errorMsg+ "알수없는 에러가 발생하였습니다.";
			break;
			
		case DB_INSERT_ERROR:
		case DB_UPDATE_ERROR:
		case DB_DELETE_ERROR:
		case DB_SELECT_ERROR:
			errorMsg = errorMsg+" 데이터 베이스 작업 중  문제가 발생하였습니다.";
			break;		
		case RTP_CALCULATION_ERROR:
			errorMsg = errorMsg+" RTP 요금 계산중 문제가 발생하였습니다.";
			break;		
		case TOU_CALCULATION_ERROR:
			errorMsg = errorMsg+" TOU 요금 계산중 문제가 발생하였습니다.";
			break;		
		default:
			errorMsg = "등록되지 않은 에러코드입니다.";
			break;
		}

		return errorMsg;
	}

	/**
	 * NM 서버의 사용자 에러 메세지를 구한다.
	 * 고객에게 보내질 에러 메시지
	 * @param errorCode
	 * @return
	 */
	public static String getUserErrorMsg(String errorCode) {
		int nmErrorCode = Integer.parseInt(errorCode);
		String errorMsg= "";

		switch (nmErrorCode) {
		case UNKNOWN_ERROR:
			errorMsg = " 알 수 없는 오류가 발생하였습니다. 시스템 관리자에게 문의 바랍니다.";
			break;
		case DB_SELECT_ERROR:
			errorMsg = " 정보 조회 중 오류가 발생하였습니다.  시스템 관리자에게 문의 바랍니다.";
			break;
		case DB_DELETE_ERROR:
			errorMsg = " 정보  삭제 중 오류가 발생하였습니다.  시스템 관리자에게 문의 바랍니다.";
			break;
		case DB_INSERT_ERROR:
			errorMsg = " 정보  입력 중 오류가 발생하였습니다.  시스템 관리자에게 문의 바랍니다.";
			break;
		case DB_UPDATE_ERROR:
			errorMsg = " 정보  수정 중 오류가 발생하였습니다.  시스템 관리자에게 문의 바랍니다.";
			break;
		case RTP_CALCULATION_ERROR:
			errorMsg = " RTP 요금 계산중 문제가 발생하였습니다. 시스템 관리자에게 문의 바랍니다.";
			break;		
		case TOU_CALCULATION_ERROR:
			errorMsg = " TOU 요금 계산중 문제가 발생하였습니다. 시스템 관리자에게 문의 바랍니다.";
			break;		
		}

		return errorMsg;
	}

}