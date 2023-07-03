package springblog.persistence.dao.role;

import java.util.List;

import springblog.persistence.entity.Role;

public interface RoleDao {
	List<Role> getAllRoles();
	
	Role findById(int id);
}
