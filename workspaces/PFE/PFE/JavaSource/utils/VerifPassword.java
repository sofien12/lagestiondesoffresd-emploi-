package utils;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import tn.com.pfe.view.UserBean;
import tn.com.security.PasswordSupport;


public class VerifPassword implements Validator {
	

	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		FacesContext fc=FacesContext.getCurrentInstance();		
		UserBean bean = (UserBean) fc.getApplication().createValueBinding("#{userBean}").getValue(fc);
		if (!bean.getPostulant().getPwd().equals(PasswordSupport.getMD5Hash((String)value)))
			throw new ValidatorException(new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Mot de passe invalide!",
					"Veuillez réessayer."));
	}
}