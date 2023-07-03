package springblog.persistence.dao.post.impl;


import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import springblog.bl.dto.PostDTO;
import springblog.persistence.dao.post.PostDao;
import springblog.persistence.entity.Post;
import springblog.persistence.entity.User;

@Repository
@Transactional
public class PostDaoImpl implements PostDao {
    private static String TABLENAME = "Post";

    private static String QUERY = "FROM " + TABLENAME;
	
	@Autowired
	private SessionFactory sessionFactory;


	@Override
	public List<Post> getAllPosts() {
		StringBuffer stringBuf = new StringBuffer(QUERY);
		@SuppressWarnings("unchecked")
		Query<Post> query = getSession().createQuery(stringBuf.toString());
		return query.list();
	}

	@Override
	public List<Post> searchPosts(String keyword) {
	    StringBuffer stringBuf = new StringBuffer(QUERY);
	    stringBuf.append(" AS P");
	    stringBuf.append(" WHERE P.title LIKE :keyword OR P.description LIKE : keyword");
	    stringBuf.append(" OR P.user.id IN (SELECT U.id FROM User U WHERE U.name LIKE :keyword)");

	    @SuppressWarnings("unchecked")
		Query<Post> query = getSession().createQuery(stringBuf.toString());
	    query.setParameter("keyword", "%" + keyword + "%");
	    return query.list();
	}

	@Override
	public void deletePost(int id) {
		String sql = "DELETE FROM Post WHERE id = :id";
		Query<?> query = getSession().createQuery(sql);
		query.setParameter("id", id);
		query.executeUpdate();
	}

	@Override
	public PostDTO getPostById(int id) {
		Post postObj = getSession().get(Post.class, id);
		return new PostDTO(postObj);
	}

	@Override
	public void editPost(Post post) {
		String sql = "UPDATE Post SET description = :description, title = :title, updated_at = :updatedAt WHERE id = :id";
		Query<?> query = getSession().createQuery(sql);
		query.setParameter("description", post.getDescription());
		query.setParameter("title", post.getTitle());
		query.setParameter("updatedAt", new Date());
		query.setParameter("id", post.getId());
		query.executeUpdate();
	}

	@Override
	public void savePost(Post post) {
		getSession().save(post);
	}

	@Override
	public List<Post> getUserPost(int id) {
		StringBuffer stringBuf = new StringBuffer(QUERY);
		stringBuf.append(" WHERE user_id = :id");
		@SuppressWarnings("unchecked")
		Query<Post> query = getSession().createQuery(stringBuf.toString());
		query.setParameter("id", id);
		return query.list();
	}
	
	@Override
	public Post findById(int id) {
		return getSession().get(Post.class, id);
	}
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Post findByUserId(int id) {
		StringBuffer stringBuf = new StringBuffer(QUERY);
		stringBuf.append(" WHERE user_id = :id");
		Query<Post> query = getSession().createQuery(stringBuf.toString());
		query.setParameter("id", id);
		return query.getSingleResult();
	}
}
