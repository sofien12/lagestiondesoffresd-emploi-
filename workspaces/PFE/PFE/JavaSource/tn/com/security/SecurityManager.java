package tn.com.security;
 
import java.io.IOException;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.stereotype.Component;
import tn.com.pfe.view.UserBean;
import utils.JSFUtils;

@Component("loginBean")
@Scope("request")
public class SecurityManager implements UserDetailsService {

	private User springSecurityUser;
	private static Logger logger = Logger.getLogger(SecurityManager.class.getName());  
	
	public User getSpringSecurityUser() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		springSecurityUser = (User) securityContext.getAuthentication()
		.getPrincipal();
		return springSecurityUser;
	}


	public UserDetails loadUserByUsername(String username)
	throws UsernameNotFoundException, DataAccessException {
		
		UserBean userBean=(UserBean) JSFUtils.getExpressionValue("#{userBean}");
		User user = new User(username);
		userBean.setLoggedIn(false);
	    userBean.setLoginMsgError("Utilisateur ou mot de passe invalide !");
		if (user.getUser().getIdP() != null) {
			
				userBean.setLoggedIn(true);
		    	return user;
			}
		 else{
			userBean.setLoggedIn(false);
		    userBean.setLoginMsgError("Utilisateur ou mot de passe invalide !");
			throw new BadCredentialsException("");
		}
		
	}

	public String doLogin() throws IOException, ServletException {

		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		RequestDispatcher dispatcher = ((ServletRequest) context.getRequest())
		.getRequestDispatcher("/modules/members/j_spring_security_check");
		dispatcher.forward((ServletRequest) context.getRequest(),
				(ServletResponse) context.getResponse());
		FacesContext jsf = FacesContext.getCurrentInstance();
		ExternalContext extCtxt = jsf.getExternalContext();
		Exception e = (Exception) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(
		AbstractAuthenticationProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY);
			
	    if (!(e instanceof BadCredentialsException) && !(e instanceof AuthenticationServiceException))
		{ 
	        FacesContext fc=FacesContext.getCurrentInstance();		
			UserBean userBean=(UserBean) fc.getApplication().createValueBinding("#{userBean}").getValue(fc);
		    userBean.chargerParametreGlob();
		 }
		
	    FacesContext.getCurrentInstance().responseComplete();
		// It's OK to return null here because Faces is just going to exit.	
		return null;

	}
	 
		
	public void setSpringSecurityUser(User springSecurityUser) {
		this.springSecurityUser = springSecurityUser;
	}
	
}