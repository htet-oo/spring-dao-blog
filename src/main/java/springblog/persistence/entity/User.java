package springblog.persistence.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import springblog.bl.dto.UserDTO;
import springblog.web.form.UserForm;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, length = 100)
	private String name;

	@Column(nullable = false, length = 50)
	private String email;

	@Column(nullable = false)
	private String password;

	@CreationTimestamp
	private Date created_at;

	@UpdateTimestamp
	private Date updated_at;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
	@JoinTable(name = "user_role",
			   joinColumns = @JoinColumn(name = "user_id"),
			   inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	
	@OneToMany(mappedBy = "user", cascade = {CascadeType.REMOVE,CascadeType.MERGE})
	private Set<Post> post;
	
	@Column(name = "reset_password_token",length = 45)
	private String resetPasswordToken;
	
	public User() {
    	super();
        this.roles = new HashSet<>();
    }
   
    public User(UserForm userForm) {
    	this.id = userForm.getId();
    	this.name = userForm.getName();
    	this.email = userForm.getEmail();
    	this.password = userForm.getPassword();
    }
    
    public User(UserDTO userDto) {
    	this.id = userDto.getId();
    	this.name = userDto.getName();
    	this.email = userDto.getEmail();
    	this.password = userDto.getPassword();
    }

}
