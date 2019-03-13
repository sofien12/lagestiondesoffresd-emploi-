/**
 * 
 */
package tn.com.pfe.view;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import tn.com.pfe.domain.Createur;
import tn.com.pfe.domain.Offremploi;
import tn.com.pfe.service.CreateurService;
import tn.com.pfe.service.OffremploiService;
import tn.com.security.PasswordSupport;
import utils.CommonCalendrier;
import utils.JSFUtils;

@Component("userBean")
@Scope("session")
public class UserBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private CreateurService CreateurService; 
	 
	private String test;
	private boolean loggedIn;
	private String loginMsgError;
	private Createur Createur;
	private Createur createurList;
	private Createur CreateurList;
	private List<Createur> filteredListCreateur;
	private List<Offremploi> offrep;
	private List<Createur> filteredListcreateur;
	
	
	public UserBean() {
		super();
		Createur=new Createur();
		
		// TODO Auto-generated constructor stub
		
	}

	@PostConstruct	
	public void initiate() {
	try{
	 
		}catch(Exception e){
        	e.printStackTrace();
        }
     }


public Createur responsable(Integer id)
{
	
	CreateurList=CreateurService.findById(id);
	
	return 	CreateurList;
}
   public void chargerParametreGlob() {
   try {
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
  
	public void chargerUtilisateur(String login) {
    try{
			
		Createur = new Createur();
		List<Createur> list= CreateurService.findEmail(login);
		List<Createur> listCin =CreateurService.findCin(login);
		if (list.size()>0 ){
			FacesContext fc=FacesContext.getCurrentInstance();		
			this.Createur = (Createur) list.get(0);
			if (this.Createur.getTypec().equals("responsable BU"))
			{
				test="BU";
			}
			else
				if (this.Createur.getTypec().equals("responsable Recrutement"))
				{
					test="R";
					System.out.println(test);
				}
				else
					if(this.Createur.getTypec().equals("responsable Structure"))
					{
						test="S";
					}
					else 
						if (this.Createur.getTypec().equals("responsable Capital humain"))
						{
							test ="Cp";
						}
					else
						if (this.Createur.getTypec().equals("responsable RH"))
						{
							test="RH";
						}
						else
							if(this.Createur.getTypec().equals("chargé recrutement"))
							{
								test="CR";
							}
			
         }
		else 
			if (listCin.size()>0)
			{ 
				FacesContext fc=FacesContext.getCurrentInstance();		
				this.Createur = (Createur) listCin.get(0);
				
				if (this.Createur.getTypec().equals("responsable BU"))
				{
					test="BU";
					System.out.println(test);
				}
				else
					if (this.Createur.getTypec().equals("responsable Recrutement"))
					{
						test="R";
						System.out.println(test);
					}
					else
						if(this.Createur.getTypec().equals("responsable Structure"))
						{
							test="S";
						}
						else 
							if (this.Createur.getTypec().equals("responsable Capital humain"))
							{
								test ="Cp";
							}
						else
							if (this.Createur.getTypec().equals("responsable RH"))
							{
								test="RH";
							}
							else
								if(this.Createur.getTypec().equals("chargé recrutement"))
								{
									test="CR";
								}
						
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

    public String logout(){
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session =  (HttpSession) context.getExternalContext().getSession(true);
		if(session==null){
			return "logout";
		}
		else{
			session.invalidate();
			return "logout";
		}
	}
  
  

 
 	
	

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoginMsgError(String loginMsgError) {
		this.loginMsgError = loginMsgError;
	}

	public String getLoginMsgError() {
		return loginMsgError;
	}

	public Createur getCreateur() {
		return Createur;
	}

	public void setCreateur(Createur createur) {
		Createur = createur;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public Createur getCreateurList() {
		return createurList;
	}

	public void setCreateurList(Createur createurList) {
		this.createurList = createurList;
	}

	public List<Createur> getFilteredListcreateur() {
		return filteredListcreateur;
	}

	public void setFilteredListcreateur(List<Createur> filteredListcreateur) {
		this.filteredListcreateur = filteredListcreateur;
	}


	
	
	

	
}