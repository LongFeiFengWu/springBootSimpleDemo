package demo.config;

public enum ResultEnum {
	UNKONW_ERROR(-1, "未知错误"), 
	HAVE_REG(-2, "已注册"), 
	SUCCESS(0, "成功"), 
	NO_AUTH(204, "未授权"), 
	NOT_FOUND(404, "资源未找到"),

	;

	private Integer code;

	private String msg;

	ResultEnum(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
}
