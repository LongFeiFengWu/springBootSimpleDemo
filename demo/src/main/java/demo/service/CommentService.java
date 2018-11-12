package demo.service;

import java.util.Map;

import demo.pojo.Comment;

public interface CommentService {
	
    int deleteByPrimaryKey(String commentid);

    int insert(Map<String, Object> params);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(String commentid);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKeyWithBLOBs(Comment record);

    int updateByPrimaryKey(Comment record);

}
