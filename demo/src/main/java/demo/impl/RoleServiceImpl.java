package demo.impl;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.dao.RoleMapper;
import demo.pojo.Role;
import demo.service.RoleService;
import demo.utils.Common;

@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {

	private static Logger logger = LogManager.getLogger(RoleServiceImpl.class);
	
	@Autowired
	private RoleMapper roleMapper;
	
	@Override
	public Role selectByPrimaryKey(String roleId) {
		return roleMapper.selectByPrimaryKey(roleId);
	}

	@Override
	public List findAllRoles() {
		return roleMapper.findAllRoles();
	}

	@Override
	public int insert(Map<String, Object> params) {
		
		Role role = new Role();
		role.setRoleid(Common.getUuid());
		role.setRolename(params.get("rolename").toString());
		role.setPermission(params.get("permission").toString());
		return roleMapper.insert(role);
	}

	@Override
	public int deleteByPrimaryKey(String roleId) {
		return roleMapper.deleteByPrimaryKey(roleId);
	}

	@Override
	public int updateByPrimaryKey(Map<String, Object> params) {
		Role role = new Role();
		role.setRoleid(params.get("roleid").toString());
		role.setRolename(params.get("rolename").toString());
		role.setPermission(params.get("permission").toString());
		return roleMapper.updateByPrimaryKey(role);
	}

}
