package springblog.persistence.dao.post;

import java.util.List;

import springblog.bl.dto.PostDTO;
import springblog.persistence.entity.Post;

public interface PostDao {
	List<Post> getAllPosts();

	List<Post> searchPosts(String keyword);

	PostDTO getPostById(int id);
	
	Post findById(int id);

	void deletePost(int id);

	void editPost(Post post);

	void savePost(Post post);

	List<Post> getUserPost(int id);
	
	Post findByUserId(int id);
}
