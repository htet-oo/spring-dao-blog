package springblog.services.role.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springblog.bl.dto.RoleDTO;
import springblog.persistence.dao.role.RoleDao;
import springblog.persistence.entity.Role;
import springblog.services.role.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleDao roleDao;
	@Override
	public List<RoleDTO> getAllRoles() {
		List<Role> roleList = roleDao.getAllRoles();
		if(roleList == null) {
			return null;
		}
		return roleList.stream().map(obj -> new RoleDTO(obj)).toList();
	}

}
