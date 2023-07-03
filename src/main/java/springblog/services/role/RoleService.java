package springblog.services.role;

import java.util.List;

import springblog.bl.dto.RoleDTO;
import springblog.persistence.entity.Role;

public interface RoleService {
	List<RoleDTO> getAllRoles();
}
