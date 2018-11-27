package demo.controller;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.impl.MailServiceImpl;
import demo.impl.SmsServiceImpl;
import demo.module.ResponseBean;
import demo.utils.Common;
import demo.utils.RandomValidateCodeUtil;

@RestController
public class CommonController {

	@Autowired
	private SmsServiceImpl smsService;

	@Autowired
	private MailServiceImpl mailService;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	private static Logger logger = LogManager.getLogger(CommonController.class);

	/**
	 * 生成验证码
	 */
	@RequestMapping(value = "/getVerify")
	public void getVerify(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("image/jpeg");// 设置相应类型,告诉浏览器输出的内容为图片
			response.setHeader("Pragma", "No-cache");// 设置响应头信息，告诉浏览器不要缓存此内容
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expire", 0);
			RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil();
			randomValidateCode.getRandcode(request, response);// 输出验证码图片方法
		} catch (Exception e) {
			logger.error("获取验证码失败>>>>   ", e);
		}
	}

	@PostMapping("/sendToPhone")
	public ResponseBean sendToPhone(@RequestBody Map<String, Object> params) throws Exception {

		String verCode = Common.getRandomNumber(6);
		String phone = params.get("phone").toString();

		String resStr = smsService.sendSMS(verCode, phone);

		if (resStr.contains("success")) {
			stringRedisTemplate.opsForValue().set(phone, verCode);
			stringRedisTemplate.expire(phone, 600, TimeUnit.SECONDS);

			return new ResponseBean(200, "send to phone success", null);

		}

		return new ResponseBean(500, "send to phone fail", resStr);
	}

	@PostMapping("/sendToMail")
	public ResponseBean sendToMail(@RequestBody Map<String, Object> params) throws Exception {

		String verCode = Common.getRandomNumber(6);
		String desEmail = params.get("email").toString();

		try {
			mailService.sendSimpleMail(desEmail, verCode);
		} catch (Exception e) {
			return new ResponseBean(500, "send mail fail", e.getMessage());
		}

		stringRedisTemplate.opsForValue().set(desEmail, verCode);
		stringRedisTemplate.expire(desEmail, 600, TimeUnit.SECONDS);

		return new ResponseBean(200, "send mail success", null);
	}

}
