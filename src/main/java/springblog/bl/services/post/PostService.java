package springblog.bl.services.post;

import java.util.List;

import springblog.bl.dto.PostDTO;

public interface PostService {
	List<PostDTO> getAllPosts();
}
