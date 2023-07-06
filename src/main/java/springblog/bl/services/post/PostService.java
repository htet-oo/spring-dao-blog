package springblog.bl.services.post;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import springblog.bl.dto.PostDTO;
import springblog.web.form.PostForm;

public interface PostService {
	List<PostDTO> getAllPosts();

	List<PostDTO> searchPosts(String search);

	void deletePost(int id);

	PostDTO getPostById(int id);

	void editPost(PostForm postForm);

	void savePost(PostForm postForm);

	List<PostDTO> getUserPost(int id);
	
	void generateExcel(HttpServletResponse response) throws IOException;
	
	int matchUserPost(int id);
}
