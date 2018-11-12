package demo.controller;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.module.ResponseBean;
import demo.service.CommentService;

@RestController
@RequestMapping(value = { "/comment" })
@RequiresRoles("user")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/submit")
	public ResponseBean submitContent(@RequestBody Map<String, Object> params) {
		
		return new ResponseBean(200, "submit success", commentService.insert(params));
	}
	
	@GetMapping("/findByCommentId/{commentId}")
	public ResponseBean findByCommentId(@PathVariable String commentId) {
		
		return new ResponseBean(200, "find success", commentService.selectByPrimaryKey(commentId));
	}
	
	@DeleteMapping("/deleteByCommentId/{commentId}")
    public ResponseBean deleteByCommentId(@PathVariable String commentId) {
		
		return new ResponseBean(200, "delete success", commentService.deleteByPrimaryKey(commentId));
	}
	

}
