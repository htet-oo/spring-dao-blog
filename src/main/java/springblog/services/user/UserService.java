package springblog.services.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import springblog.bl.dto.UserDTO;
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
}
