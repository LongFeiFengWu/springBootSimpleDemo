package demo.service;

import java.util.List;
import java.util.Map;

import demo.pojo.User;

public interface RegService {

	User selectByPrimaryKey(String userId);

	User selectByName(String userName);

	List findAllRegUser();

	int insert(Map<String, Object> params);

	int deleteByPrimaryKey(String userId);

	int updateByPrimaryKey(Map<String, Object> params);

}
