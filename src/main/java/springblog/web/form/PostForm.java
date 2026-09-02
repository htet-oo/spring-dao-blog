package springblog.web.form;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import springblog.bl.dto.PostDTO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostForm {

    private int id;

    @NotBlank(message = "Please Enter Description")
    private String description;

    @NotBlank(message = "Please Enter Title")
    private String title;

    private int userId;

    private String email;

    public PostForm(PostDTO postDto) {
        this.id = postDto.getId();
        this.description = postDto.getDescription();
        this.title = postDto.getTitle();
        this.userId = postDto.getUser().getId();
    }
}