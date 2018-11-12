package demo.controller;

import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import demo.config.UnauthorizedException;
import demo.module.LoginInfo;
import demo.module.ResponseBean;
import demo.service.LoginService;
import demo.utils.JWTUtil;

@RestController
@RequestMapping(value = { "/login" })
public class LoginController {

	@Autowired
	private LoginService loginService;

	@PostMapping("/check")
	public ResponseBean login(@RequestBody Map<String, Object> params) {
		
		String username = params.get("username").toString();
		String password = params.get("password").toString();
		LoginInfo loginInfo = loginService.loginCheck(username);
		if (loginInfo.getUser().getPassword().equals(password)) {
			return new ResponseBean(200, "Login success", JWTUtil.sign(username, password));
		} else {
			throw new UnauthorizedException();
		}
	}
	
	@GetMapping("/article")
    public ResponseBean article() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return new ResponseBean(200, "You are already logged in", null);
        } else {
            return new ResponseBean(200, "You are guest", null);
        }
    }

	@GetMapping("/require_auth")
    @RequiresAuthentication
    public ResponseBean requireAuth() {
        return new ResponseBean(200, "You are authenticated", null);
    }

	@GetMapping("/require_role")
    @RequiresRoles("admin")
    public ResponseBean requireRole() {
        return new ResponseBean(200, "You are visiting require_role", null);
    }

	@GetMapping("/require_permission")
    @RequiresPermissions(logical = Logical.AND, value = {"view", "edit"})
    public ResponseBean requirePermission() {
        return new ResponseBean(200, "You are visiting permission require edit,view", null);
    }

	@RequestMapping(path = "/401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseBean unauthorized() {
        return new ResponseBean(401, "Unauthorized", null);
    }
}
