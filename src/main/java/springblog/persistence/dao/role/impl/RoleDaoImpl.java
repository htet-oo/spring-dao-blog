package springblog.persistence.dao.role.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import springblog.persistence.dao.role.RoleDao;
import springblog.persistence.entity.Role;

@Repository
@Transactional
public class RoleDaoImpl implements RoleDao {
    private static String TABLENAME = "Role";

    private static String QUERY = "FROM " + TABLENAME;
	
    @Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Role> getAllRoles() {
		StringBuffer stringBuf = new StringBuffer(QUERY);
		Query<Role> query = getSession().createQuery(stringBuf.toString());
		return query.list();
	}
	
	@Override
	public Role findById(int id) {
		return getSession().get(Role.class, id);
	}
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

}
