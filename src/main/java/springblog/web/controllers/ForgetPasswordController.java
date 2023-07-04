package springblog.web.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.bytebuddy.utility.RandomString;
import springblog.bl.services.user.UserService;
import springblog.exception.CustomerNotFoundException;

@Controller
public class ForgetPasswordController {
	@Autowired
	private UserService userService;
	@RequestMapping("/forget_password")
	public String forgetPassword() {
		return "/forget_password";
	}
	
	@PostMapping("/forget_password")
	public String processForgetPasswordForm(HttpServletRequest request,Model model) {
		String email = request.getParameter("email");
		String token = RandomString.make(45);
		try {
			userService.updateResetPasswordToken(token, email);
		} catch (CustomerNotFoundException e) {
			model.addAttribute("error",e.getMessage());
		}
		return "/forget_password";
	}

}
