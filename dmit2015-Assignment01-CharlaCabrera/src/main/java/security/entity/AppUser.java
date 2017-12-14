package security.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.Length;

@Entity
public class AppUser {

	@Id
	@Column(nullable=false, unique=true, length=64)
	@Length(min=3,max=64,message="Login Name value must contain 3 to 64 characters")
	private String loginName;
	
	@Column(nullable=false, length=64)
	@Length(max=64,message="Password value cannot contain more than 64 characters")
	private String password;

	@OneToMany(mappedBy="appUser")
	private List<AppUserRole> appUserRoles;
	
	public AppUser() {
		super();
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<AppUserRole> getAppUserRoles() {
		return appUserRoles;
	}
	
}
