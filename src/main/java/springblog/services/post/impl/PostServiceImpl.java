package springblog.services.post.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springblog.bl.dto.PostDTO;
import springblog.persistence.dao.post.PostDao;
import springblog.persistence.dao.user.UserDao;
import springblog.persistence.entity.Post;
import springblog.persistence.entity.User;
import springblog.services.post.PostService;
import springblog.web.form.PostForm;

@Service("postService")
public class PostServiceImpl implements PostService {
	@Autowired
	private PostDao postDao;
	
	@Autowired
	private UserDao userDao;
	
	

	@Override
	public List<PostDTO> getAllPosts() {
		List<Post> posts = this.postDao.getAllPosts();
		if (posts == null) {
			return null;
		}

		return posts.stream().map(item -> new PostDTO(item)).collect(Collectors.toList());
	}

	@Override
	public List<PostDTO> searchPosts(String keyword) {
		List<Post> posts = this.postDao.searchPosts(keyword);
		if (posts == null) {
			return null;
		}
		return posts.stream().map(item -> new PostDTO(item)).collect(Collectors.toList());
	}

	@Override
	public void deletePost(int id) {
		postDao.deletePost(id);
	}

	@Override
	public PostDTO getPostById(int id) {
		PostDTO postDtoObj = postDao.getPostById(id);
		return postDtoObj;
	}

	@Override
	public void editPost(PostForm postForm) {
		Post post = postDao.findById(postForm.getId());
		User user = userDao.searhUserById(postForm.getUserId());
		post.setDescription(postForm.getDescription());
		post.setTitle(postForm.getTitle());
		post.setUser(user);
		postDao.editPost(post);
	}

	@Override
	public void savePost(PostForm postForm) {
		User user = userDao.searhUserById(postForm.getUserId());
		User authorName = userDao.findByEmail(postForm.getEmail());
		Post post = new Post();
		post.setId(postForm.getId());
		post.setDescription(postForm.getDescription());
		post.setTitle(postForm.getTitle());
		post.setCreated_at(new Date());
		post.setUser(user);
		post.setAuthor(authorName.getName());
		postDao.savePost(post);

	}

	@Override
	public List<PostDTO> getUserPost(int id) {
		List<Post> postList = postDao.getUserPost(id);
		return postList.stream().map(obj -> new PostDTO(obj)).toList();
	}

	@Override
	public void generateExcel(HttpServletResponse response) throws IOException {
		List<PostDTO> postDtoList = getAllPosts();
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Post List with user");
		HSSFRow row = sheet.createRow(0);
		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("Title");
		row.createCell(2).setCellValue("Description");
		row.createCell(3).setCellValue("Author");
		row.createCell(4).setCellValue("Date");
		int dataRowIndex = 1;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(PostDTO postDto : postDtoList) {
			String userName = postDto.getUser().getName();
			HSSFRow dataRow = sheet.createRow(dataRowIndex);
			dataRow.createCell(0).setCellValue(postDto.getId());
			dataRow.createCell(1).setCellValue(postDto.getTitle());
			dataRow.createCell(2).setCellValue(postDto.getDescription());
			dataRow.createCell(3).setCellValue(userName);
			dataRow.createCell(4).setCellValue(postDto.getCreated_at());
			Date createdAt = postDto.getCreated_at();
			if(createdAt != null) {
				String formattedDate = dateFormat.format(createdAt);
				dataRow.createCell(4).setCellValue(formattedDate);
			} else {
	            dataRow.createCell(4).setCellValue("");
	        }
			dataRowIndex ++;
		}
		ServletOutputStream ops = response.getOutputStream();
		workbook.write(ops);
		workbook.close();
		ops.close();
		
	}

	@Override
	public int matchUserPost(int id) {
		Post post = postDao.findById(id);
		return post.getUser().getId();
	}
	
}
