package springblog.web.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import springblog.bl.dto.RoleDTO;
import springblog.bl.dto.UserDTO;
import springblog.bl.services.role.RoleService;
import springblog.bl.services.user.UserService;
import springblog.persistence.dao.post.PostDao;
import springblog.web.form.UserForm;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired PostDao postDao;

	@RequestMapping("/users/list")
	public ModelAndView getAllUsers(Authentication authentication) {
		ModelAndView mv = new ModelAndView("userListView");
		List<UserDTO> userDtoList = this.userService.getAllUsers();
		mv.addObject("users", userDtoList);
		
		var role = authentication.getAuthorities();
		mv.addObject("role",role);
		return mv;
	}

	@RequestMapping("/users/create")
	public ModelAndView createUser() {
		ModelAndView mv = new ModelAndView("userCreateView");
		List<RoleDTO> roles = roleService.getAllRoles();
		mv.addObject("saveUser", new UserForm());
		mv.addObject("roles",roles);
		return mv;
	}

	@RequestMapping(value = "/users/create/save", method = RequestMethod.POST)
	public ModelAndView saveUser(@ModelAttribute("saveUser") @Valid UserForm userForm,
								 BindingResult result) {
		ModelAndView mv = new ModelAndView();
		if(result.hasErrors()) {
			mv.setViewName("userCreateView");
			return mv;
		}
		userService.saveUser(userForm);
		mv.setViewName("redirect:/users/list");
		return mv;
	}

	@RequestMapping("/users/delete")
	public ModelAndView deleteUser(@RequestParam int deleteId) {
		ModelAndView mv = new ModelAndView();
		userService.deleteUser(deleteId);
		mv.setViewName("redirect:/users/list");
		return mv;
	}

	@RequestMapping("/users/search")
	public ModelAndView searchUser(@RequestParam("keyword") String keyword) {
		ModelAndView mv = new ModelAndView("userListView");
		List<UserDTO> userDtoList = this.userService.searchUser(keyword);
		mv.addObject("users", userDtoList);
		return mv;
	}

	@RequestMapping("/users/edit")
	public ModelAndView editUser(@RequestParam int updateObjId) {
		ModelAndView mv = new ModelAndView("userEditView");
		UserDTO userDto = userService.searhUserById(updateObjId);
		mv.addObject("editUser", new UserForm(userDto));
		return mv;
	}

	@RequestMapping(value = "/users/edit/save", method = RequestMethod.POST)
	public ModelAndView saveEditUser(@ModelAttribute("editUser") @Valid UserForm userForm,
									 BindingResult result) {
		ModelAndView mv = new ModelAndView();
		if(result.hasErrors()) {
			mv.setViewName("userEditView");
			return mv;
		}
		userService.editUser(userForm);
		mv.setViewName("redirect:/users/list");
		return mv;
	}
	
	@RequestMapping("/excelUser")
	public void generateExcelReport(HttpServletResponse response) throws Exception{
		response.setContentType("application/octet-stream");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment;filename=list.xls";
		response.setHeader(headerKey, headerValue);
		userService.generateExcel(response);
	}
}
