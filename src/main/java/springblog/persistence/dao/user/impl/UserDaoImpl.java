package springblog.persistence.dao.user.impl;
import java.util.List;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import springblog.persistence.dao.user.UserDao;
import springblog.persistence.entity.User;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {
    private static String TABLENAME = "User";

    private static String QUERY = "FROM " + TABLENAME;
	@Autowired
	private SessionFactory sessionFactory;
	

	@Override
	public List<User> getAllUsers() {
		StringBuffer stringBuf = new StringBuffer(QUERY);
		@SuppressWarnings("unchecked")
		Query<User> query = getSession().createQuery(stringBuf.toString());
		return query.list();
	}

	@Override
	public void saveUser(User user) {
		getSession().save(user);
	}

	@Override
	public void deleteUser(User user) {
		getSession().delete(user);
	}

	@Override
	public List<User> searchUser(String keyword) {
		StringBuffer stringBuf = new StringBuffer(QUERY);
		stringBuf.append(" AS U");
		stringBuf.append(" WHERE U.name LIKE :keyword OR U.email LIKE :keyword");
		@SuppressWarnings("unchecked")
		Query<User> query = getSession().createQuery(stringBuf.toString());
		query.setParameter("keyword", "%" + keyword + "%"); 
		return query.list();
	}

	@Override
	public User searhUserById(int id) {
		return getSession().get(User.class, id);
	}

	@Override
	public void editUser(User user) {
		String sql = "UPDATE User SET name = :name, email = :email, password = :password, updated_at = :updatedAt, reset_password_token = :resetPasswordToken, image =:image WHERE id = :id";
		Query<?> query = getSession().createQuery(sql);
		query.setParameter("name", user.getName());
		query.setParameter("email", user.getEmail());
		query.setParameter("password", user.getPassword());
		query.setParameter("updatedAt", user.getUpdated_at());
		query.setParameter("id", user.getId());
		query.setParameter("resetPasswordToken", user.getResetPasswordToken());
		query.setParameter("image", user.getImage());
		query.executeUpdate();
	}
	
	@Override
	public User findByEmail(String email) {
		StringBuffer stringBuf = new StringBuffer(QUERY);
		stringBuf.append(" WHERE email =:email");
		@SuppressWarnings("unchecked")
		List<User> user = getSession().createQuery(stringBuf.toString()).setParameter("email", email).list();
//		return getSession().createQuery(stringBuf.toString(),User.class).setParameter("email", email).getSingleResult();
		return (!user.isEmpty()) ? user.get(0) : null;
	}
	
	@Override
	public User findResetPasswordToken(String resetPasswordToken) {
		StringBuffer stringBuf = new StringBuffer(QUERY);
		stringBuf.append(" WHERE reset_password_token =: resetPasswordToken");
		return getSession().createQuery(stringBuf.toString(),User.class).setParameter("resetPasswordToken", resetPasswordToken).getSingleResult();
	}
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

}
