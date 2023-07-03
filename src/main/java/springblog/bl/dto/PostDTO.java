package springblog.bl.dto;

import springblog.persistence.entity.Post;
import springblog.persistence.entity.User;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
	private int id;
	private String title;
	private String description;
	private Date created_at;
	private Date updated_at;
	private User user;
	private String author;

	public PostDTO(Post post) {
		this.id = post.getId();
		this.title = post.getTitle();
		this.description = post.getDescription();
		this.created_at = post.getCreated_at();
		this.updated_at = post.getUpdated_at();
		this.user = post.getUser();
		this.author = post.getAuthor();
	}
}
