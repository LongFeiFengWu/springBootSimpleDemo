package demo.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import demo.config.GlobalException;
import demo.config.ResultEnum;
import demo.pojo.Role;
import demo.service.RoleService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = { "/role" })
@RequiresRoles("admin")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@ApiOperation(value = "获取全部角色信息", notes = "获取全部角色信息")
	@RequestMapping(value = { "/findAllRole" }, produces = {
			"application/json;charset=UTF-8" }, method = RequestMethod.GET)
	public List getAllRoles() {

		return roleService.findAllRoles();
	}

	@ApiOperation(value = "获取指定角色信息", notes = "根据ID获取指定角色信息")
	@ApiImplicitParam(name = "roleId", value = "用户ID", required = true, dataType = "String", paramType = "path")
	@RequestMapping(value = { "/findRoleById/{roleId}" }, produces = {
			"application/json;charset=UTF-8" }, method = RequestMethod.GET)
	public Role findRoleById(@PathVariable String roleId) {

		return roleService.selectByPrimaryKey(roleId);
	}

	@ApiOperation(value = "删除指定角色信息", notes = "根据ID删除指定角色信息")
	@ApiImplicitParam(name = "roleId", value = "用户ID", required = true, dataType = "String", paramType = "path")
	@RequestMapping(value = { "/delRoleById/{roleId}" }, produces = {
			"application/json;charset=UTF-8" }, method = RequestMethod.DELETE)
	public int delRoleById(@PathVariable String roleId) {

		if (roleService.deleteByPrimaryKey(roleId) != 1) {
			throw new GlobalException(ResultEnum.UNKONW_ERROR);
		}

		return 1;
	}

	@ApiOperation(value = "创建角色信息", notes = "创建角色信息")
	@ApiImplicitParam(name = "Role", value = "角色详细实体", required = true, dataType = "Role")
	@RequestMapping(value = { "/addNewRole" }, method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public int addNewRole(@RequestBody Map<String, Object> params) {

		return roleService.insert(params);
	}

	@ApiOperation(value = "更新Role信息", notes = "更新角色信息")
	@ApiImplicitParam(name = "user", value = "角色详细实体", required = true, dataType = "Role")
	@RequiresAuthentication
	@RequestMapping(value = { "/updateRole" }, method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
	public int updateRole(@RequestBody Map<String, Object> params) {

		return roleService.updateByPrimaryKey(params);
	}
}
