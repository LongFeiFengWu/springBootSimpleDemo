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
import demo.service.ContentService;

@RestController
@RequestMapping(value = { "/content" })
@RequiresRoles("user")
public class ContentController {
	
	@Autowired
	private ContentService contentService;

	
	@PostMapping("/submit")
	public ResponseBean submitContent(@RequestBody Map<String, Object> params) {
		
		return new ResponseBean(200, "submit success", contentService.insert(params));
	}
	
	@DeleteMapping("/deleteByContentId/{contentId}")
    public ResponseBean deleteByContentId(@PathVariable String contentId) {
		
		return new ResponseBean(200, "delete success", contentService.deleteByPrimaryKey(contentId));
	}
	
	
	@GetMapping("/findAll")
	public ResponseBean findAllContent() {
		
		return new ResponseBean(200, "find success", contentService.findAllContent());
	}
	
	@GetMapping("/findByContentId/{contentId}")
	public ResponseBean findByContentId(@PathVariable String contentId) {
		
		return new ResponseBean(200, "find success", contentService.selectByPrimaryKey(contentId));
	}
	
	@GetMapping("/findBySubmitterId/{submitterId}")
	public ResponseBean findBySubmitterId(@PathVariable String submitterId) {
		
		return new ResponseBean(200, "find success", contentService.selectBySubmitterId(submitterId));
	}
	
}
