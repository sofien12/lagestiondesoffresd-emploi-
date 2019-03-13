package tn.com.security;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import tn.com.pfe.view.UserBean;
import utils.JSFUtils;


public class LoginErrorPhaseListener implements PhaseListener
{
    private static final long serialVersionUID = -1216620620302322995L;
 
    
    public void beforePhase(final PhaseEvent arg0)
    {
    	UserBean userBean=(UserBean) JSFUtils.getExpressionValue("#{userBean}");
    	Exception e = (Exception) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(AbstractAuthenticationProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY);
        if (e instanceof BadCredentialsException){
        	if(userBean.isLoggedIn() == false){
        		JSFUtils.addErrorMessage("Attention :", userBean.getLoginMsgError(), "loginBtn");	
        	}
        }
        if (e instanceof AuthenticationServiceException){
        	if(userBean.isLoggedIn() == false){
        		JSFUtils.addErrorMessage("Attention :", userBean.getLoginMsgError(), "loginBtn");	
        	}
        }
    }
 
    
    public void afterPhase(final PhaseEvent arg0){}
 
    public PhaseId getPhaseId(){
        return PhaseId.RENDER_RESPONSE;
    }
 
}