package demo.impl;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.config.SMSConfig;
import demo.utils.OkHttpUtil;

@Service(value = "smsService")
public class SmsServiceImpl {

	@Autowired
	private SMSConfig smsConfig;

	public String sendSMS(String vercode, String phone) throws Exception {

		String url = smsConfig.getUrl();
		String content = smsConfig.getContent().replace("*", vercode);
		Map<String, String> queries = new HashMap<>();
		queries.put("uid", smsConfig.getUid());
		queries.put("username", smsConfig.getUsername());
		queries.put("token", smsConfig.getToken());
		queries.put("appid", smsConfig.getAppid());
		queries.put("mobile", phone);
		queries.put("content", URLEncoder.encode(content, "gbk"));
		String reString = OkHttpUtil.get(url, queries);

		return reString;
	}
}
