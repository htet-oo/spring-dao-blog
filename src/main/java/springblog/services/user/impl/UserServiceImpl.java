package springblog.services.user.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import springblog.bl.dto.PostDTO;
import springblog.bl.dto.UserDTO;
import springblog.persistence.dao.role.RoleDao;
import springblog.persistence.dao.user.UserDao;
import springblog.persistence.entity.Role;
import springblog.persistence.entity.User;
import springblog.services.user.UserService;
import springblog.web.form.UserForm;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	
	private PasswordEncoder passwordEncoder;

	@Override
	public List<UserDTO> getAllUsers() {
		List<User> userList = userDao.getAllUsers();
		if (userList == null) {
			return null;
		}
		return userList.stream().map(obj -> new UserDTO(obj)).toList();
	}

	@Override
	public void saveUser(UserForm userForm) {
		User user = new User();
		user.setName(userForm.getName());
		user.setEmail(userForm.getEmail());
		user.setPassword(passwordEncoder.encode(userForm.getPassword()));
		user.setCreated_at(new Date());
		user.getRoles().add(this.roleDao.findById(userForm.getRoleId()));
		userDao.saveUser(user);
	}

	@Override
	public void deleteUser(int id) {
		User user = userDao.searhUserById(id);
		userDao.deleteUser(user);

	}

	@Override
	public List<UserDTO> searchUser(String search) {
		List<User> userDtoList = userDao.searchUser(search);
		return userDtoList.stream().map(obj -> new UserDTO(obj)).toList();
	}

	@Override
	public UserDTO searhUserById(int id) {
		User user = userDao.searhUserById(id);
		return new UserDTO(user);
	}

	@Override
	public void editUser(UserForm userForm) {
		User user = userDao.searhUserById(userForm.getId());
		user.setName(userForm.getName());
		user.setEmail(userForm.getEmail());
		user.setPassword(userForm.getPassword());
		user.setUpdated_at(new Date());
		userDao.editUser(user);
	}

	@Override
	public void generateExcel(HttpServletResponse response) throws IOException {
		List<UserDTO> userDtoList = getAllUsers();
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("User List");
		HSSFRow row = sheet.createRow(0);
		row.createCell(0).setCellValue("Name");
		row.createCell(1).setCellValue("Email");
		row.createCell(2).setCellValue("Date");
		int dataRowIndex = 1;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(UserDTO userDto : userDtoList) {
			HSSFRow dataRow = sheet.createRow(dataRowIndex);
			dataRow.createCell(0).setCellValue(userDto.getName());
			dataRow.createCell(1).setCellValue(userDto.getEmail());
			Date createdAt = userDto.getCreated_at();
			if(createdAt != null) {
				String formattedDate = dateFormat.format(createdAt);
				dataRow.createCell(2).setCellValue(formattedDate);
			} else {
	            dataRow.createCell(2).setCellValue("");
	        }
			dataRowIndex ++;
		}
		ServletOutputStream ops = response.getOutputStream();
		workbook.write(ops);
		workbook.close();
		ops.close();
	}

	

}
