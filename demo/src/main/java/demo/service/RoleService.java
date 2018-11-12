package demo.service;

import java.util.List;
import java.util.Map;

import demo.pojo.Role;

public interface RoleService {
	
	Role selectByPrimaryKey(String roleId);

	List findAllRoles();

	int insert(Map<String, Object> params);

	int deleteByPrimaryKey(String roleId);

	int updateByPrimaryKey(Map<String, Object> params);

}
