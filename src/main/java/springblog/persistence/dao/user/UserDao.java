package springblog.persistence.dao.user;

import java.util.List;
import springblog.persistence.entity.User;

public interface UserDao {
	List<User> getAllUsers();

	void saveUser(User user);

	void deleteUser(User user);

	List<User> searchUser(String search);

	User searhUserById(int id);

	void editUser(User user);
	
	User findByEmail(String email);
	
	User findResetPasswordToken(String resetPasswordToken);
}
