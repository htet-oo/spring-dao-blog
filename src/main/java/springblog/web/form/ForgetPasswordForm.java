package springblog.web.form;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import springblog.validation.EmailExit;

@Getter
@Setter
public class ForgetPasswordForm {
	
	@EmailExit
	@NotBlank
	private String email;
	
}
