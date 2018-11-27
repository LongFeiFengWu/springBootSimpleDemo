package demo.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import demo.config.GlobalException;
import demo.config.ResultEnum;
import demo.module.ResponseBean;
import demo.pojo.User;
import demo.service.RegService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = { "/user" })
public class UserController {

	@Autowired
	private RegService regService;

	@ApiOperation(value = "获取全部用户信息", notes = "获取全部用户信息")
	@RequestMapping(value = { "/findAllReg" }, produces = {
			"application/json;charset=UTF-8" }, method = RequestMethod.GET)
	@RequiresRoles("admin")
	public List getAllRegUsers() {

		return regService.findAllRegUser();
	}

	@ApiOperation(value = "获取指定用户信息", notes = "根据ID获取指定用户信息")
	@ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "String", paramType = "path")
	@RequestMapping(value = { "/findUserById/{userId}" }, produces = {
			"application/json;charset=UTF-8" }, method = RequestMethod.GET)
	@RequiresRoles("admin")
	public User findUserById(@PathVariable String userId) {

		return regService.selectByPrimaryKey(userId);
	}

	@ApiOperation(value = "删除指定用户信息", notes = "根据ID删除指定用户信息")
	@ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "String", paramType = "path")
	@RequestMapping(value = { "/delUserById/{userId}" }, produces = {
			"application/json;charset=UTF-8" }, method = RequestMethod.DELETE)
	@RequiresRoles("admin")
	public int delUserById(@PathVariable String userId) {

		if (regService.deleteByPrimaryKey(userId) != 1) {
			throw new GlobalException(ResultEnum.UNKONW_ERROR);
		}

		return 1;
	}

	@ApiOperation(value = "创建用户信息", notes = "根据User对象创建用户信息")
	@ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
	@RequestMapping(value = { "/addNewUser" }, method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public ResponseBean addNewUser(@RequestBody Map<String, Object> params) {

		try {
			regService.insert(params);
		} catch (Exception e) {
			return new ResponseBean(500, "addNewUser fail", e.getMessage());
		}

		return new ResponseBean(200, "addNewUser success", null);
	}

	@ApiOperation(value = "更新用户信息", notes = "根据User对象更新用户信息")
	@ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
	@RequiresRoles("admin")
	@RequestMapping(value = { "/updateUser" }, method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
	public int updateUser(@RequestBody Map<String, Object> params) {

		return regService.updateByPrimaryKey(params);
	}

}
