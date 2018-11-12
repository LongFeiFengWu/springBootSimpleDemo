package demo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import demo.config.CommonConfig;
import demo.config.GlobalException;
import demo.config.ResultEnum;
import demo.dao.UserMapper;
import demo.pojo.User;
import demo.service.RegService;
import demo.utils.AES;
import demo.utils.Common;

@Service(value = "regService")
public class RegServiceImpl implements RegService {

	private static Logger logger = LogManager.getLogger(RegServiceImpl.class);

	@Autowired
	private UserMapper userDao;

	@Autowired
	private CommonConfig commonConfig;

	@Override
	public List findAllRegUser() {

		String psw = commonConfig.getKeyForAES();
		List<User> users = new ArrayList<>();
		users = userDao.findAllRegUser();

		for (User user : users) {
			try {
				user.setPassword(AES.decryp(user.getPassword(), psw));
			} catch (Exception e) {
				logger.error(e);
			}
		}
		return users;
	}

	@Override
	public int insert(Map<String, Object> params) {

		if (userDao.selectByName(params.get("username").toString()) != null) {
			throw new GlobalException(ResultEnum.HAVE_REG);
		}

		User user = new User();
		String userid = Common.getUuid();
		String psw = commonConfig.getKeyForAES();
		Date regtime = new Date();
		user.setUserid(userid);
		user.setUsername(params.get("username").toString());

		try {
			user.setPassword(AES.encrypt(params.get("password").toString(), psw));
		} catch (Exception e) {
			logger.error(e);
		}
		user.setPhone(params.get("phone").toString());
		user.setEmail(params.get("email").toString());
		user.setRegtime(regtime);

		if (params.get("roleid") != null) {
			user.setRoleid(params.get("roleid").toString());
		} else {

			user.setRoleid("2");
		}
		return userDao.insert(user);
	}

	@Override
	public User selectByPrimaryKey(String userId) {

		String psw = commonConfig.getKeyForAES();
		User user = userDao.selectByPrimaryKey(userId);
		try {
			user.setPassword(AES.decryp(user.getPassword(), psw));
		} catch (Exception e) {
			logger.error(e);
		}
		return user;
	}

	@Override
	public int deleteByPrimaryKey(String userId) {
		return userDao.deleteByPrimaryKey(userId);
	}

	@Override
	public int updateByPrimaryKey(Map<String, Object> params) {

		User user = new User();
		String userId = params.get("userid").toString();
		user.setUserid(userId);
		String psw = commonConfig.getKeyForAES();
		user.setUsername(params.get("username").toString());

		try {
			user.setPassword(AES.encrypt(params.get("password").toString(), psw));
		} catch (Exception e) {
			logger.error(e);
		}
		user.setPhone(params.get("phone").toString());
		user.setEmail(params.get("email").toString());
		try {
			user.setRegtime(Common.timestampToDate(Long.parseLong(params.get("regtime").toString())));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e);
		}
		return userDao.updateByPrimaryKey(user);
	}

	@Override
	public User selectByName(String userName) {
		String psw = commonConfig.getKeyForAES();
		User user = userDao.selectByName(userName);
		try {
			user.setPassword(AES.decryp(user.getPassword(), psw));
		} catch (Exception e) {
			logger.error(e);
		}
		return user;
	}

}
