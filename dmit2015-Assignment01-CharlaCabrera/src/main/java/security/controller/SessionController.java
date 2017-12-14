package security.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.omnifaces.util.Faces;

@Named
@SessionScoped
public class SessionController implements Serializable {
	private static final long serialVersionUID = 1L;

	public String logout() throws IOException {
		Faces.invalidateSession();
		return "/index.xhtml?faces-redirect=true";
	}
	
	public boolean isLoggedIn() {
		return Faces.getRemoteUser() != null;
	}
	
	public String getRemoteUser() {
		return Faces.getRemoteUser();
	}
	
	public boolean isUserInRole(String roleName) {
		return Faces.isUserInRole(roleName);
	}
}
