package springblog.web.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import springblog.bl.dto.PostDTO;
import springblog.bl.dto.UserDTO;
import springblog.services.post.PostService;
import springblog.services.user.UserService;
import springblog.web.form.PostForm;

@Controller
public class PostController {
	@Autowired
	private PostService postService;

	@Autowired
	private UserService userService;
	
	@RequestMapping("/posts/list")
	public ModelAndView getAllPosts(Authentication authentication) {
		List<PostDTO> postDtoList = this.postService.getAllPosts();
		ModelAndView mv = new ModelAndView("postListView");
		mv.addObject("posts", postDtoList);
		
		String email = authentication.getName();
		mv.addObject("account",email);
		return mv;
	}

	@RequestMapping("/posts/search")
	public ModelAndView searchPost(@RequestParam("keyword") String keyword) {
		ModelAndView mv = new ModelAndView("postListView");
		List<PostDTO> postDtoList = this.postService.searchPosts(keyword);
		mv.addObject("posts", postDtoList);
		return mv;
	}

	@RequestMapping("/posts/delete")
	public ModelAndView deletePost(@RequestParam int deleteId) {
		ModelAndView mv = new ModelAndView("postDeleteView");
		postService.deletePost(deleteId);
		mv.setViewName("redirect:/posts/list");
		return mv;
	}

	@RequestMapping("/posts/edit")
	public ModelAndView update(@RequestParam int updateObjId) {
		ModelAndView mv = new ModelAndView("postUpdateView");
		PostDTO postDto = postService.getPostById(updateObjId);
		List<UserDTO> userDtoList = this.userService.getAllUsers();
		int selectedUserId = postService.matchUserPost(updateObjId);
		mv.addObject("updateForm", new PostForm(postDto));
		mv.addObject("users", userDtoList);
		mv.addObject("selectedUserId", selectedUserId);
		return mv;
	}

	@RequestMapping(value = "/posts/edit/save", method = RequestMethod.POST)
	public ModelAndView editPost(@ModelAttribute("updateForm") @Valid PostForm postForm, BindingResult result) {
		ModelAndView mv = new ModelAndView();
		if (result.hasErrors()) {
			mv.setViewName("postUpdateView");
			return mv;
		}
		postService.editPost(postForm);
		mv.setViewName("redirect:/posts/list");
		return mv;
	}

	@RequestMapping("/posts/create")
	public ModelAndView create() {
		ModelAndView mv = new ModelAndView("postCreateView");
		List<UserDTO> userDtoList = this.userService.getAllUsers();
		mv.addObject("saveForm", new PostForm());
		mv.addObject("users", userDtoList);
		return mv;
	}

	@RequestMapping(value = "/posts/create/save", method = RequestMethod.POST)
	public ModelAndView savePost(@ModelAttribute("saveForm") @Valid PostForm postForm, BindingResult result, Authentication authentication) {
		ModelAndView mv = new ModelAndView();
		if (result.hasErrors()) {
			mv.setViewName("postCreateView");
			return mv;
		}
		String email = authentication.getName();
		postForm.setEmail(email);
		postService.savePost(postForm);
		mv.setViewName("redirect:/posts/list");
		return mv;
	}

	@RequestMapping("/posts/userPostList")
	public ModelAndView userPostList(@RequestParam int userId) {
		ModelAndView mv = new ModelAndView("userPostList");
		List<PostDTO> userPostList = this.postService.getUserPost(userId);
		mv.addObject("userPost", userPostList);
		return mv;
	}
	
	@RequestMapping("/excelPost")
	public void generateExcelReport(HttpServletResponse response) throws Exception{
		response.setContentType("application/octet-stream");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment;filename=list.xls";
		response.setHeader(headerKey, headerValue);
		postService.generateExcel(response);
	}

}
