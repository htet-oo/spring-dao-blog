package springblog.web.controllers;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import net.bytebuddy.utility.RandomString;
import springblog.bl.services.user.UserService;
import springblog.persistence.entity.User;
import springblog.web.form.ForgetPasswordForm;
import springblog.web.form.ResetPasswordForm;

@Controller
public class ForgetPasswordController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@RequestMapping("/forget_password")
	public ModelAndView forgetPassword() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("forgetPasswordForm", new ForgetPasswordForm());
		return mv;
	}
	
	@PostMapping("/forget_password")
	public String processForgetPasswordForm(@ModelAttribute("forgetPasswordForm") @Valid ForgetPasswordForm forgetPasswordForm,BindingResult result) throws UnsupportedEncodingException, MessagingException{
		if (result.hasErrors()) {
			return "/forget_password";
		}
		String email = forgetPasswordForm.getEmail();
		String token = RandomString.make(45);
		userService.updateResetPasswordToken(token, email);
	    String resetPasswordLink = ServletUriComponentsBuilder.fromCurrentContextPath()
	            .path("/reset_password")
	            .queryParam("token", token)
	            .toUriString();
		sendEmail(email,resetPasswordLink);
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
	public ModelAndView resetPasswordForm(@RequestParam String token) {
		ModelAndView mv = new ModelAndView();
		User user = userService.get(token);
		String mail = user.getEmail();
		ResetPasswordForm resetPasswordForm = new ResetPasswordForm();
		resetPasswordForm.setEmail(mail);
		mv.addObject("resetPasswordForm", resetPasswordForm);
		mv.addObject("token",token);
		mv.addObject("email", mail);
		return mv;
	}
	
	@PostMapping("/reset_password")
	public String processResetPassword(@ModelAttribute("resetPasswordForm") @Valid ResetPasswordForm resetPasswordForm,BindingResult result) {
		ModelAndView mv = new ModelAndView();
		String token = resetPasswordForm.getToken();
		String password = resetPasswordForm.getPassword();
		String confirmPassword = resetPasswordForm.getConfirmPassword();
		User user = userService.get(token);
	    if (!password.equals(confirmPassword)) {
	        result.rejectValue("confirmPassword", "password.mismatch", "Password and Confirm Password must match");
	    }
	    if (result.hasErrors()) {
	        mv.addObject("token", token);
	        mv.addObject("email", resetPasswordForm.getEmail());
	        return "/reset_password";
		}
		if(user != null) {
			userService.updatePassword(user, password);
		}
		return "/login";
	}

}
