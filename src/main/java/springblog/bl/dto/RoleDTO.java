package springblog.bl.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import springblog.persistence.entity.Role;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {
	private int id;
	private String name;
	
	public RoleDTO(Role role) {
		this.id = role.getId();
		this.name = role.getName();
	}
}
