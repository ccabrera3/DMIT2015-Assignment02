package security.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class AppUserRole {

	@EmbeddedId
	private AppUserRolePK id;
	
	@ManyToOne
	@JoinColumn(name="loginName", insertable=false, updatable=false)
	private AppUser appUser;
	
	public AppUserRole() {
		super();
	}


	public AppUserRolePK getId() {
		return id;
	}
	public void setId(AppUserRolePK id) {
		this.id = id;
	}
	
}
