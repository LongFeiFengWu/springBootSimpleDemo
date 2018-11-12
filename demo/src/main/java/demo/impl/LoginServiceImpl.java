package demo.impl;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.module.LoginInfo;
import demo.pojo.Role;
import demo.pojo.User;
import demo.service.LoginService;
import demo.service.RegService;
import demo.service.RoleService;

@Service(value = "loginService")
public class LoginServiceImpl implements LoginService {
	private static Logger logger = LogManager.getLogger(LoginServiceImpl.class);

	@Autowired
	private RegService regService;

	@Autowired
	private RoleService roleService;

	@Override
	public LoginInfo loginCheck(String userName) {
		User user = regService.selectByName(userName);
		Role role = roleService.selectByPrimaryKey(user.getRoleid());

		LoginInfo loginInfo = new LoginInfo();
		loginInfo.setUser(user);
		loginInfo.setRole(role);
		return loginInfo;
	}

}
