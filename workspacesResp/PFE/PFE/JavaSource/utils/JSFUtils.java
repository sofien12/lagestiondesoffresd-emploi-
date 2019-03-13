package utils;

/*
 * 
 * Copyright 2017 Meher Ammar.
 * 
 */


import java.text.MessageFormat;
import java.util.List;
import java.util.ResourceBundle;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.el.ValueExpression;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;

/**
 * Class implements JsfUtil functions
 *
 * <br/>$LastChangedRevision:$
 * <br/>$LastChangedDate:$
 *
 * @author Meher Ammar
 */
public class JSFUtils
{
	public static final String BUNDLE_VALUE = "#{bundle.%s}";
	public static final String ACTION_SUCCESS = "actionSuccess";
	public static final String CHOOSE_PARENT = "chooseParent";
	public static final String VALUE_NAME = "value";
	private static ResourceBundle bundle;
	
	public static void addSuccessMessage(String msg,String detail,String messageComponent){
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg,detail);
		FacesContext.getCurrentInstance().addMessage(messageComponent, facesMsg);
	}
	
	public static void addErrorMessage(String msg,String detail,String messageComponent){
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, detail);
		FacesContext.getCurrentInstance().addMessage(messageComponent, facesMsg);
	}
	
	public static void addWarningMessage(String msg,String detail,String messageComponent){
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, msg, detail);
		FacesContext.getCurrentInstance().addMessage(messageComponent, facesMsg);
	}
	
	public static Object getExpressionValue (String expression)
	{
		FacesContext fc = FacesContext.getCurrentInstance();
		ELContext ctx = fc.getELContext();
		ExpressionFactory factory = fc.getApplication().getExpressionFactory();
		ValueExpression ve = factory.createValueExpression(ctx, expression, Object.class);
		if (ve != null){
			return ve.getValue(ctx);
		}else{
			return null;
		}
	}
	
	public static ValueExpression getExpression (String expression)
	{
		FacesContext fc = FacesContext.getCurrentInstance();
		ELContext ctx = fc.getELContext();
		ExpressionFactory factory = fc.getApplication().getExpressionFactory();
		return factory.createValueExpression(
				ctx, 
				expression, 
				Object.class);
	}
	
	public static MethodExpression getMethodExpression(String expression, Class<?> type, Class<?> ... types)
	{
		FacesContext fc = FacesContext.getCurrentInstance();
		ELContext ctx = fc.getELContext();
		ExpressionFactory factory = fc.getApplication().getExpressionFactory();
		return factory.createMethodExpression(
				ctx, 
				expression, 
				type, types);
	}

	public static ValueExpression getExpression(String expression, Class<?> type)
	{
		FacesContext fc = FacesContext.getCurrentInstance();
		ELContext ctx = fc.getELContext();
		ExpressionFactory factory = fc.getApplication().getExpressionFactory();
		return factory.createValueExpression(
				ctx, 
				expression, 
				type);
	}
	
	public static String getMessage(String chave) {
		return getResourceBundle().getString(chave);
	}

	public static String getMessage(String chave, Object... parametros) {
		chave = getResourceBundle().getString(chave);
		return MessageFormat.format(chave, parametros);
	}

	public static ResourceBundle getResourceBundle() {       
		FacesContext context = FacesContext.getCurrentInstance();
		bundle = context.getApplication().getResourceBundle(context, "msgs");        
		return bundle;
	}
	
	public static SelectItem[] getSelectItems(List<?> entities, boolean selectOne)
	{
		int size = selectOne ? entities.size() + 1 : entities.size();
		SelectItem[] items = new SelectItem[size];
		int i = 0;
		if (selectOne) {
			items[0] = new SelectItem("", "---");
			i++;
		}
		for (Object x : entities) {
			items[i++] = new SelectItem(x, x.toString());
		}
		return items;
	}
	
	public static String getRequestParameter(String key)
	{
	 	return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(key);
	}
	
	public static Object getObjectFromRequestParameter(String requestParameterName, Converter converter, UIComponent component)
	{
		String theId = JSFUtils.getRequestParameter(requestParameterName);
		return converter.getAsObject(FacesContext.getCurrentInstance(), component, theId);
	}

	/**
	 * Function sets success flag callback parameter. This flag could be used
	 * from javascript function on application client side through the <b>args</b> 
	 * function argument.
	 * @param successFlag - flag
	 */
	public static void setSuccessFlag (boolean successFlag)
	{
		RequestContext context = RequestContext.getCurrentInstance();
		context.addCallbackParam(ACTION_SUCCESS, successFlag);
	}

	/**
	 * Fucnion sets callback parameter to allow use this value from javascript 
	 * on application client side through the <b>args</b> function argument.
	 * @param paramName - parameter name
	 * @param value - parameter value
	 */
	public static void setCallbackParameter (String paramName, Object value)
	{
		RequestContext context = RequestContext.getCurrentInstance();
		context.addCallbackParam(paramName, value);
	}
	
}

