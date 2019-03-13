package tn.com.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;

import tn.com.pfe.domain.Postulant;
import tn.com.pfe.view.UserBean;
import utils.JSFUtils;


public class User implements UserDetails {

    private String name;
    
	private String password;
	private Postulant user;

	public void setUser(Postulant user) {
		this.user = user;
	}

	public User(String name) {
		
		UserBean userBean=(UserBean) JSFUtils.getExpressionValue("#{userBean}");
		userBean.chargerUtilisateur(name);
		user = userBean.getPostulant();
		
		if (user!=null){
			this.name = user.getEmail();
			this.password=user.getPwd();
		}
	}
	

	public Collection<GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		grantedAuthorities.add(new GrantedAuthorityImpl("ROLE_VISITEUR"));
		return grantedAuthorities;
	}

	


	public Postulant getUser() {
		return user;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}


	public String getUsername() {
		// TODO Auto-generated method stub
		return name;
	}


	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}


	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}


	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}


	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
