package demo.service;

import java.util.List;
import java.util.Map;

import demo.pojo.Content;
import demo.pojo.ContentWithBLOBs;
import demo.pojo.ContentWithComment;

public interface ContentService {
	
    int deleteByPrimaryKey(String contentid);

    int insert(Map<String, Object> params);

    int insertSelective(ContentWithBLOBs record);

    ContentWithComment selectByPrimaryKey(String contentid);
    
    List<ContentWithBLOBs> selectBySubmitterId(String submitterId);
    
    List<ContentWithBLOBs> findAllContent();
    
    int updateByPrimaryKeySelective(ContentWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ContentWithBLOBs record);

    int updateByPrimaryKey(Content record);

}
