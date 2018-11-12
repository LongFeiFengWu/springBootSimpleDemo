package demo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.ServletRequestAttributes;

import demo.dao.CommentMapper;
import demo.dao.ContentMapper;
import demo.dao.UserMapper;
import demo.pojo.Comment;
import demo.pojo.Content;
import demo.pojo.ContentWithBLOBs;
import demo.pojo.ContentWithComment;
import demo.service.ContentService;
import demo.service.RegService;
import demo.utils.Common;
import demo.utils.JWTUtil;

@Service(value = "contentService")
public class ContentServiceImpl implements ContentService{
	
	@Autowired
	private ContentMapper contentDao;
	
	@Autowired
	private CommentMapper commentDao;
	
	@Autowired
	private UserMapper userDao;

	

	@Override
	public int deleteByPrimaryKey(String contentid) {
		
		ContentWithBLOBs contentWithBLOBs = contentDao.selectByPrimaryKey(contentid);
		
	
		String userName = JWTUtil.getUsernameFromRequest();
		
		String userId = userDao.selectByName(userName).getUserid();
		
		if(!contentWithBLOBs.getSubmitterid().equals(userId)) {
			
			throw new UnauthenticatedException();
		}
		
		return contentDao.deleteByPrimaryKey(contentid);
	
	}

	@Override
	public int insert(Map<String, Object> params) {
		// TODO Auto-generated method stub
		ContentWithBLOBs contentWithBLOBs = new ContentWithBLOBs();
		contentWithBLOBs.setContentid(Common.getUuid());
		contentWithBLOBs.setContenddate(new Date());
		contentWithBLOBs.setSubmitterid(params.get("submitterId").toString());
		contentWithBLOBs.setContentdetail(params.get("details").toString());
		return contentDao.insert(contentWithBLOBs);
	}

	@Override
	public int insertSelective(ContentWithBLOBs record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ContentWithComment selectByPrimaryKey(String contentid) {
		// TODO Auto-generated method stub
		
		ContentWithBLOBs contentWithBLOBs = contentDao.selectByPrimaryKey(contentid);
		
		ContentWithComment contentWithComment = new ContentWithComment();
		
		contentWithComment.setContentWithBLOBs(contentWithBLOBs);
		List<Comment> comments = new ArrayList<>();
		
		
		if (contentWithBLOBs.getCommentids()!=null) {
			String[] ids = contentWithBLOBs.getCommentids().split(",");
			
			for (String id : ids) {
				if (id!=null) {
					comments.add(commentDao.selectByPrimaryKey(id));
				}
			}
			contentWithComment.setComments(comments);
			
		}
		
		return contentWithComment;
	}

	@Override
	public int updateByPrimaryKeySelective(ContentWithBLOBs record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(ContentWithBLOBs record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Content record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ContentWithBLOBs> selectBySubmitterId(String submitterId) {
		// TODO Auto-generated method stub
		return contentDao.selectBySubmitterId(submitterId);
	}

	@Override
	public List<ContentWithBLOBs> findAllContent() {
		// TODO Auto-generated method stub
		return contentDao.findAllContent();
	}

}
