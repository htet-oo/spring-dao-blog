package springblog.web.controllers;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.apache.tiles.autotag.core.runtime.annotation.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.bytebuddy.utility.RandomString;
import springblog.bl.services.user.UserService;
import springblog.exception.CustomerNotFoundException;
import springblog.mail.Utility;
import springblog.persistence.entity.User;

@Controller
public class ForgetPasswordController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@RequestMapping("/forget_password")
	public String forgetPassword() {
		return "/forget_password";
	}
	
	@PostMapping("/forget_password")
	public String processForgetPasswordForm(HttpServletRequest request,Model model) throws UnsupportedEncodingException, MessagingException {
		String email = request.getParameter("email");
		String token = RandomString.make(45);
		try {
			userService.updateResetPasswordToken(token, email);
			String resetPasswordLink = Utility.getSitUrl(request) + "/reset_password?token=" + token;
			sendEmail(email,resetPasswordLink);
			model.addAttribute("message", "we have sent a reset password link to your email");
		} catch (CustomerNotFoundException e) {
			model.addAttribute("error",e.getMessage());
		} catch(UnsupportedEncodingException | MessagingException e) {
			model.addAttribute("error","Error while sending mail");
		}
		return "/forget_password";
	}

	private void sendEmail(String email, String resetPasswordLink) throws UnsupportedEncodingException, MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		helper.setFrom("www.htetooaa@gmail.com", "Me");
		helper.setTo(email);
		String subject = "Here's your link to reset password.";
		String content = "<p>Hello,</p>"
					   + "<p>Your Reset Password Link is Here</p>"
					   +"<p><b><a href =\""+ resetPasswordLink + "\">change my password</a></b></p>";
		
		helper.setSubject(subject);
		helper.setText(content,true);
		mailSender.send(message);
		
	}
	
	@GetMapping("/reset_password")
	public String resetPasswordForm(@RequestParam String token,Model model){
		User user = userService.get(token);
		if(user == null) {
			model.addAttribute("message", "Invalid Token");
			return "message";
		}
		model.addAttribute("token",token);
		return "reset_password";
	}
	
	@PostMapping("/reset_password")
	public void processResetPassword(HttpServletRequest request,Model model) {
		String token = request.getParameter("token");
		String password = request.getParameter("password");
		User user = userService.get(token);
		if(user != null) {
			userService.updatePassword(user, password);
		}
	}

}
