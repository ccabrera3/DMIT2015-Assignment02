package security.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.jboss.logging.Logger;
import org.omnifaces.util.Messages;

import security.entity.AppUser;
import security.service.AppUserService;

@Named
@ViewScoped
public class CreateAppUserController implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(CreateAppUserController.class.getName());
	
	@Inject
	private AppUserService appUserService;
	
	private AppUser currentAppUser = new AppUser();
	@Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,}$",
			message="Password value must contain at least 6 characters with at least 1 uppercase letter, 1 lowercase letter, and 1 number")
	private String password;
	
	private String[] appRoleNames = {"Administrator","Employee","Customer"};
	@NotBlank(message="Role Name value is required")
	private String selectedRoleName;
	
	public void createUser() {
		try {
			currentAppUser.setPassword(password);
			appUserService.createUser(currentAppUser, selectedRoleName);
			currentAppUser = new AppUser();
			Messages.addGlobalInfo("Successfully created user");
		} catch (Exception e) {
			Messages.addGlobalError("Failed to create create user");
			log.debug(e.getMessage());
		}
	}

	public AppUser getCurrentAppUser() {
		return currentAppUser;
	}

	public void setCurrentAppUser(AppUser currentAppUser) {
		this.currentAppUser = currentAppUser;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String[] getAppRoleNames() {
		return appRoleNames;
	}

	public String getSelectedRoleName() {
		return selectedRoleName;
	}

	public void setSelectedRoleName(String selectedRoleName) {
		this.selectedRoleName = selectedRoleName;
	}

	public List<AppUser> getAllAppUsers() {
		return appUserService.findAll();
	}
}
