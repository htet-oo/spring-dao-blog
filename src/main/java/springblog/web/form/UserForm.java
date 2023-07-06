package springblog.web.form;

import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import springblog.bl.dto.UserDTO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserForm {
	
	
	private int id;

	@NotBlank(message = "Please Enter Name")
	private String name;

	@NotBlank(message = "Please Enter Email")
	private String email;

	@NotBlank(message = "Please Enter Password")
	private String password;
	
	private int roleId;
	
	private List<String> roles;

	public UserForm(UserDTO userDto) {
		this.id = userDto.getId();
		this.name = userDto.getName();
		this.email = userDto.getEmail();
		this.password = userDto.getPassword();
		this.roles=userDto.getRoles().stream().map(role -> role.getAuthority()).toList();
	}

}
