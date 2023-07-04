package springblog.bl.services.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import springblog.bl.dto.UserDTO;
import springblog.persistence.dao.user.UserDao;
import springblog.persistence.entity.User;

@Service
public class RoleDetailsService implements UserDetailsService {
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByEmail(username);
		System.out.println(user.getEmail());
		UserDTO userDto = new UserDTO(user);
		if(user == null) {
			throw new UsernameNotFoundException("Rewrite Username or Password");
		}
		
		return org.springframework.security.core.userdetails.User.builder()
				.username(userDto.getEmail())
				.password(userDto.getPassword())
				.authorities(userDto.getRoles())
				.build();
	}

}
