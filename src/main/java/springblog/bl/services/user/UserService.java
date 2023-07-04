package springblog.bl.services.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import springblog.bl.dto.UserDTO;
import springblog.exception.CustomerNotFoundException;
import springblog.persistence.entity.Role;
import springblog.persistence.entity.User;
import springblog.web.form.UserForm;

public interface UserService {
	List<UserDTO> getAllUsers();

	void saveUser(UserForm userForm);

	void deleteUser(int id);

	List<UserDTO> searchUser(String search);

	UserDTO searhUserById(int id);

	void editUser(UserForm userForm);
	
	void generateExcel(HttpServletResponse response) throws IOException;
	
	void updateResetPasswordToken(String token,String email) throws CustomerNotFoundException;
	
	User get(String resetPasswordToken);
	
	void updatePassword(User user , String newPassword);
}
