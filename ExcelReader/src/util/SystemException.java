package util;

import java.text.DecimalFormat;
 
/**
 * RuntimeException Ŭ����
 * @author �۴���
 *
 */
public class SystemException extends RuntimeException {
	
	private static DecimalFormat codeFormat = new DecimalFormat("0000");

	private String code;   

	private Object[] args;

	/**
	 * ���� ����
	 *   
	 * @param code
	 *            ����(����) �ڵ�
	 * @param args
	 *            ���� ��½� ���� �ƱԸ�Ʈ, ���� ��� null �����Ѵ�.
	 */
	public SystemException(String code, Object[] args) {
		this.code = code;
		this.args = args;
	}

	/**
	 * ���� ����
	 * 
	 * @param codeNum
	 *            ����(����) �ڵ�
	 * @param args
	 *            ���� ��½� ���� �ƱԸ�Ʈ, ���� ��� null �����Ѵ�.
	 */
	public SystemException(int codeNum, Object[] args) {
		this.code = codeFormat.format(codeNum);
		this.args = args;
	}

	/**
	 * ����(����) �ڵ� ��ȯ
	 * 
	 * @return String ����(����) �ڵ�
	 */
	public String getCode() {
		return this.code;
	}

	/**
	 * ���� ��½� ���� �ƱԸ�Ʈ ��ȯ
	 * 
	 * @return Object[] ���� ��½� ���� �ƱԸ�Ʈ
	 */
	public Object[] getArguments() {
		return this.args;
	}
}