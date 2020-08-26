package com.zhangna.tidb.binlog.util;


import lombok.Data;

/**
 * @Author zhangna
 * @Date 2019-09-19 16:20
 * @Description
 */
@Data
public class BaseException extends RuntimeException {
	private static final long serialVersionUID = -3413544650518947209L;

	private int code = -1;
	private String bizCode = "UNKNOWN_ERROR";

	public BaseException(String msg, Throwable e) {
		super(msg, e);
	}
	
	public BaseException(BaseErrorDescriptor descriptor) {
		this(descriptor.getCode(), descriptor.getBizCode(), descriptor.getMessage());
	}

	public BaseException(BaseErrorDescriptor descriptor, String appendingMessage) {
		this(descriptor.getCode(), descriptor.getBizCode(), descriptor.getMessage() + ": " + appendingMessage);
	}

	public BaseException(BaseErrorDescriptor descriptor, BaseException cause) {
		this(descriptor.getCode(), descriptor.getBizCode(), descriptor.getMessage(), cause);
	}

	public BaseException(String message) {
		super(message);
	}

	public BaseException(String bizCode, String message) {
		super(message);
		this.bizCode = bizCode;
	}

	public BaseException(int code, String bizCode) {
		this(code, bizCode, null, null);
	}

	public BaseException(int code, String bizCode, String message) {
		this(code, bizCode, message, null);
	}

	/**
	 *
	 * Web 层可使用该方法，方便在异常处理层统一打日志。
	 * 正常的Service层应该将异常在本系统log出，而后抛出不带cause的bizException(或者cause也是BizException)，保证内部异常不扩散到其他系统。
	 *
	 * @param code
	 * @param bizCode
	 * @param message
	 * @param cause
	 */
	public BaseException(int code, String bizCode, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
		this.bizCode = bizCode;
	}

}
