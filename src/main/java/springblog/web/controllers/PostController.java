package springblog.web.controllers;

import java.io.IOException;
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

import springblog.bl.dto.PostDTO;
import springblog.bl.services.post.PostService;
import springblog.persistence.dao.user.UserDao;
import springblog.persistence.entity.User;
import springblog.web.form.PostForm;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserDao userDao;

    @RequestMapping("/posts/list")
    public ModelAndView getAllPosts(Authentication authentication) {

        ModelAndView mv = new ModelAndView("postListView");

        String email = authentication.getName();

        User loggedInUser = userDao.findByEmail(email);

        List<PostDTO> postList =
                postService.getUserPost(loggedInUser.getId());

        mv.addObject("postList", postList);

        return mv;
    }

    @RequestMapping("/posts/search")
    public ModelAndView searchPost(
            @RequestParam("keyword") String keyword) {
        ModelAndView mv = new ModelAndView("postListView");
        List<PostDTO> postList = postService.searchPosts(keyword);
        mv.addObject("postList", postList);
        return mv;
    }

    @RequestMapping("/posts/delete")
    public ModelAndView deletePost(
            @RequestParam int deleteId,
            Authentication authentication) {

        String email = authentication.getName();

        User loggedInUser = userDao.findByEmail(email);

        PostDTO post = postService.getPostById(deleteId);

        if (loggedInUser != null && post != null && post.getUser() != null) {

            int postOwnerId = post.getUser().getId();

            if (postOwnerId == loggedInUser.getId()) {
                postService.deletePost(deleteId);
            }
        }

        return new ModelAndView("redirect:/posts/list");
    }

    @RequestMapping("/posts/edit")
    public ModelAndView update(
            @RequestParam int updateObjId) {

        ModelAndView mv = new ModelAndView("postUpdateView");

        PostDTO postDTO = postService.getPostById(updateObjId);

        PostForm postForm = new PostForm(postDTO);

        mv.addObject("updateForm", postForm);

        return mv;
    }

    @RequestMapping(
            value = "/posts/edit/save",
            method = RequestMethod.POST)
    public ModelAndView editPost(
            @ModelAttribute("updateForm")
            @Valid PostForm postForm,
            BindingResult result,
            Authentication authentication) {

        ModelAndView mv = new ModelAndView();

        String email = authentication.getName();

        postForm.setEmail(email);

        User user = userDao.findByEmail(email);

        if (user != null) {
            postForm.setUserId(user.getId());
        }

        if (result.hasErrors()) {
            mv.setViewName("postUpdateView");
            return mv;
        }

        postService.editPost(postForm);

        mv.setViewName("redirect:/posts/list");

        return mv;
    }

    @RequestMapping("/posts/create")
    public ModelAndView create(
            Authentication authentication) {

        ModelAndView mv = new ModelAndView("postCreateView");

        PostForm postForm = new PostForm();

        String email = authentication.getName();

        User user = userDao.findByEmail(email);

        if (user != null) {
            postForm.setUserId(user.getId());
            postForm.setEmail(email);
        }

        mv.addObject("saveForm", postForm);

        return mv;
    }

    @RequestMapping(
            value = "/posts/create/save",
            method = RequestMethod.POST)
    public ModelAndView savePost(
            @ModelAttribute("saveForm")
            @Valid PostForm postForm,
            BindingResult result,
            Authentication authentication) {

        ModelAndView mv = new ModelAndView();

        String email = authentication.getName();

        postForm.setEmail(email);

        User user = userDao.findByEmail(email);

        if (user == null) {
            mv.setViewName("postCreateView");
            return mv;
        }

        postForm.setUserId(user.getId());

        if (result.hasErrors()) {
            mv.setViewName("postCreateView");
            return mv;
        }

        postService.savePost(postForm);

        mv.setViewName("redirect:/posts/list");

        return mv;
    }

    @RequestMapping("/posts/userPostList")
    public ModelAndView userPostList(
            @RequestParam int userId,
            Authentication authentication) {

        ModelAndView mv = new ModelAndView("userPostList");

        List<PostDTO> userPostList =
                postService.getUserPost(userId);

        mv.addObject("userPost", userPostList);

        String email = authentication.getName();

        User loggedInUser = userDao.findByEmail(email);

        boolean canCreate = false;

        if (loggedInUser != null && loggedInUser.getId() == userId) {
            canCreate = true;
        }

        mv.addObject("canCreate", canCreate);

        return mv;
    }

    @RequestMapping("/excelPost")
    public void generateExcelReport(
            HttpServletResponse response)
            throws IOException {

        postService.generateExcel(response);
    }
}