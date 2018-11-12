package demo.impl;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.dao.CommentMapper;
import demo.dao.ContentMapper;
import demo.pojo.Comment;
import demo.pojo.ContentWithBLOBs;
import demo.service.CommentService;
import demo.utils.Common;

@Service(value = "commentService")
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentMapper commentDao;
	
	@Autowired
	private ContentMapper contentDao;
	
	

	@Override
	public int deleteByPrimaryKey(String commentid) {
		
		return commentDao.deleteByPrimaryKey(commentid);
	}

	@Override
	public int insert(Map<String, Object> params) {
		// TODO Auto-generated method stub
		
		Comment comment = new Comment();
		String commentId = Common.getUuid();
		String contentid = params.get("contentid").toString();
		comment.setCommentid(commentId);
		
		comment.setCommentdate(new Date());
		comment.setContentid(contentid);
		comment.setCommentdetail(params.get("details").toString());
		comment.setObserverid(params.get("observerid").toString());
		commentDao.insert(comment);
		
		ContentWithBLOBs content = contentDao.selectByPrimaryKey(contentid);
		if (content.getCommentids()!=null) {
			content.setCommentids(content.getCommentids()+commentId+",");
		}
		else {
			content.setCommentids(commentId+",");
		}
	    
			
		return contentDao.updateByPrimaryKeyWithBLOBs(content);
	}

	@Override
	public int insertSelective(Comment record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Comment selectByPrimaryKey(String commentid) {
		// TODO Auto-generated method stub
		return commentDao.selectByPrimaryKey(commentid);
	}

	@Override
	public int updateByPrimaryKeySelective(Comment record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(Comment record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Comment record) {
		// TODO Auto-generated method stub
		return 0;
	}

}
