package demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import demo.thirdPart.ueditor.ActionEnter;

import javax.servlet.http.HttpServletRequest;


@Controller
public class UeditorController {


	@Autowired
	private ActionEnter actionEnter;

	@ResponseBody
	@RequestMapping("/ueditor/exec")
	public String exe(HttpServletRequest request){
		return actionEnter.exec(request);
	}
		
}
