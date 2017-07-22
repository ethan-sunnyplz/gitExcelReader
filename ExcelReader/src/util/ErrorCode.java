package util;

/**
 * �����ڵ� Ŭ����
 * @author �۴���
 *
 */
public class ErrorCode {

	/**
	 * �˼����� ����
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
	 * ���� �޼����� ���Ѵ�.
	 * 
	 * @param errorCode
	 * @return errorMsg
	 */
	public static String getErrorMsg(int errorCode,Object[] args) {
		String errorMsg = "";
		errorMsg = args[0]+" ���� ";
		switch (errorCode) {
		
		case UNKNOWN_ERROR:
			errorMsg =errorMsg+ "�˼����� ������ �߻��Ͽ����ϴ�.";
			break;
			
		case DB_INSERT_ERROR:
		case DB_UPDATE_ERROR:
		case DB_DELETE_ERROR:
		case DB_SELECT_ERROR:
			errorMsg = errorMsg+" ������ ���̽� �۾� ��  ������ �߻��Ͽ����ϴ�.";
			break;		
		case RTP_CALCULATION_ERROR:
			errorMsg = errorMsg+" RTP ��� ����� ������ �߻��Ͽ����ϴ�.";
			break;		
		case TOU_CALCULATION_ERROR:
			errorMsg = errorMsg+" TOU ��� ����� ������ �߻��Ͽ����ϴ�.";
			break;		
		default:
			errorMsg = "��ϵ��� ���� �����ڵ��Դϴ�.";
			break;
		}

		return errorMsg;
	}

	/**
	 * NM ������ ����� ���� �޼����� ���Ѵ�.
	 * ������ ������ ���� �޽���
	 * @param errorCode
	 * @return
	 */
	public static String getUserErrorMsg(String errorCode) {
		int nmErrorCode = Integer.parseInt(errorCode);
		String errorMsg= "";

		switch (nmErrorCode) {
		case UNKNOWN_ERROR:
			errorMsg = " �� �� ���� ������ �߻��Ͽ����ϴ�. �ý��� �����ڿ��� ���� �ٶ��ϴ�.";
			break;
		case DB_SELECT_ERROR:
			errorMsg = " ���� ��ȸ �� ������ �߻��Ͽ����ϴ�.  �ý��� �����ڿ��� ���� �ٶ��ϴ�.";
			break;
		case DB_DELETE_ERROR:
			errorMsg = " ����  ���� �� ������ �߻��Ͽ����ϴ�.  �ý��� �����ڿ��� ���� �ٶ��ϴ�.";
			break;
		case DB_INSERT_ERROR:
			errorMsg = " ����  �Է� �� ������ �߻��Ͽ����ϴ�.  �ý��� �����ڿ��� ���� �ٶ��ϴ�.";
			break;
		case DB_UPDATE_ERROR:
			errorMsg = " ����  ���� �� ������ �߻��Ͽ����ϴ�.  �ý��� �����ڿ��� ���� �ٶ��ϴ�.";
			break;
		case RTP_CALCULATION_ERROR:
			errorMsg = " RTP ��� ����� ������ �߻��Ͽ����ϴ�. �ý��� �����ڿ��� ���� �ٶ��ϴ�.";
			break;		
		case TOU_CALCULATION_ERROR:
			errorMsg = " TOU ��� ����� ������ �߻��Ͽ����ϴ�. �ý��� �����ڿ��� ���� �ٶ��ϴ�.";
			break;		
		}

		return errorMsg;
	}

}