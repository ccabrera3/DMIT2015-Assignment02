package security.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import security.entity.*;

@Stateful
public class AppUserService {

	@PersistenceContext(type=PersistenceContextType.EXTENDED)
	private EntityManager entityManager;
	
	public void createUser(AppUser currentUser, String roleName) throws NoSuchAlgorithmException {
		String plainTextPassword = currentUser.getPassword();
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte[] hash = md.digest( plainTextPassword.getBytes() );
		String hashPassword = Base64.getEncoder().encodeToString(hash);
		currentUser.setPassword(hashPassword);
		entityManager.persist(currentUser);
		
		AppUserRolePK userRoleId = new AppUserRolePK();
		userRoleId.setLoginName(currentUser.getLoginName());
		userRoleId.setRoleName(roleName);
		AppUserRole userRole = new AppUserRole();
		userRole.setId(userRoleId);
		entityManager.persist(userRole);
	}
	
	public AppUser findOneByLoginName(String loginName) {
		return entityManager.find(AppUser.class, loginName);
	}
	
	public void changePassword(AppUser currentUser, String newPassword) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte[] hash = md.digest( newPassword.getBytes() );
		String hashPassword = Base64.getEncoder().encodeToString(hash);
		currentUser.setPassword(hashPassword);
		entityManager.merge(currentUser);
		entityManager.flush();
	}
	
	public List<AppUser> findAll() {
		return entityManager.createQuery("SELECT au FROM AppUser au", AppUser.class).getResultList();
	}
	
}
