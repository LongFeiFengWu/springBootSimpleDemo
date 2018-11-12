package demo.handle;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.shiro.ShiroException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

import demo.config.GlobalException;
import demo.config.UnauthorizedException;
import demo.module.ResponseBean;
import demo.module.Result;
import demo.utils.ResultUtil;

@ControllerAdvice
public class ExceptionHandle {

	private final static Logger logger = LogManager.getLogger(ExceptionHandle.class);

	// 捕捉shiro的异常
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(ShiroException.class)
	public ResponseBean handle401(ShiroException e) {
		return new ResponseBean(401, e.getMessage(), null);
	}

	// 捕捉UnauthorizedException
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(UnauthorizedException.class)
	public ResponseBean handle401() {
		return new ResponseBean(401, "Unauthorized", null);
	}

	// 捕捉其他所有异常
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseBean globalException(HttpServletRequest request, Throwable ex) {
		logger.error("[系统异常]{}", ex);
		return new ResponseBean(getStatus(request).value(), ex.getMessage(), null);
	}

	private HttpStatus getStatus(HttpServletRequest request) {
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		if (statusCode == null) {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return HttpStatus.valueOf(statusCode);
	}

	/*
	 * @ExceptionHandler(value = Exception.class)
	 * 
	 * @ResponseBody public Result Handle(Exception e) {
	 * 
	 * if (e instanceof GlobalException) { GlobalException exception =
	 * (GlobalException) e; return ResultUtil.error(exception.getCode(),
	 * exception.getMessage());
	 * 
	 * } else { // 将系统异常以打印出来 logger.error("[系统异常]{}", e); return
	 * ResultUtil.error(-1, "未知错误"); }
	 * 
	 * }
	 */

}
