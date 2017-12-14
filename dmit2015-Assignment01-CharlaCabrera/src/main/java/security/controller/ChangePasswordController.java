package security.controller;

import java.io.Serializable;
import java.security.MessageDigest;
import java.util.Base64;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.jboss.logging.Logger;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import security.entity.AppUser;
import security.service.AppUserService;

@Named
@ViewScoped
public class ChangePasswordController implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger(ChangePasswordController.class);
	
	@Inject
	private AppUserService appUserService;
	private AppUser currentAppUser;
	
	@NotBlank(message="Current Password value is required")	
	private String currentPassword;
	
	@Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,}$",
			message="Password value must contain at least 6 characters with at least 1 uppercase letter, 1 lowercase letter, and 1 number")
	private String newPassword;
	private String confirmNewPassword;
	
	@PostConstruct
	public void init() {
		String loginName = Faces.getRemoteUser();
		currentAppUser = appUserService.findOneByLoginName(loginName);
	}
	
	public String changePassword() {
		String outcome = null;
		
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			byte[] hash = md.digest( currentPassword.getBytes() );
			String currentHashPassword = Base64.getEncoder().encodeToString(hash);
			if( currentHashPassword.equals( currentAppUser.getPassword() ) ) {
				if( newPassword.equals( confirmNewPassword ) ) {
					appUserService.changePassword(currentAppUser, newPassword);
					Messages.addFlashGlobalInfo("Successfully changed password");
					Faces.invalidateSession();
					outcome = "/index.xhtml?faces-redirect=true";
				} else {
					Messages.addGlobalError("The new password and confirm new password values must match");
				}
			} else {
				Messages.addGlobalError("The current password is incorrect.");
			}
		} catch (Exception e) {
			Messages.addGlobalError("Change password was not successful");
			log.error(e.getMessage());
		}
		
		return outcome;
	}
	
	public String cancel() {
		return "/protected/dashboard?faces-redirect=true";
	}

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmNewPassword() {
		return confirmNewPassword;
	}

	public void setConfirmNewPassword(String confirmNewPassword) {
		this.confirmNewPassword = confirmNewPassword;
	}
	

}
