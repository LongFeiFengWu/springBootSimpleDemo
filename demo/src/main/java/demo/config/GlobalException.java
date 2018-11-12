package demo.config;

public class GlobalException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer code;

	public GlobalException(ResultEnum resultEnum) {
		super(resultEnum.getMsg());
		this.code = resultEnum.getCode();
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

}
