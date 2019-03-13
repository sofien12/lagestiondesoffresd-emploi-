/**
 * 
 */
package tn.com.pfe.view;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.util.Date;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import tn.com.pfe.domain.Cv;
import tn.com.pfe.domain.Postulant;
import tn.com.pfe.service.CvService;
import tn.com.pfe.service.PostulantService;
import tn.com.security.PasswordSupport;
import tn.com.security.User;
import utils.CommonCalendrier;
import utils.JSFUtils;
import utils.email;

@Component("userBean")
@Scope("session")
public class UserBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private PostulantService PostulantService; 
	
	private boolean loggedIn;
	private String loginMsgError;
	private Postulant Postulant;
	 private UploadedFile ufile;
	 private Cv cv=new Cv();
	 private Integer acceesPostulant;
	 private Integer acceesResponsable;
	 private CvService CvService;
	 private email e;
	 private String date;
	 
	Boolean test ;
	List<Postulant> p;

	
	public UserBean() {
		super();
		Postulant=new Postulant();
		
		// TODO Auto-generated constructor stub
		
	}

	@PostConstruct	
	public void initiate() {
	try{
		Date  d=CommonCalendrier.nowTime();
		date=CommonCalendrier.formdate(d);
		
		e=new email();
		
		
		}catch(Exception e){
        	e.printStackTrace();
        }
     }
	
	public void addPostulant() {
		RequestContext context = RequestContext.getCurrentInstance();
		p=PostulantService.findAll();
		try{
			Postulant.setPwd(PasswordSupport.getMD5Hash(this.Postulant.getPwd()));
			test=true;
			for (int i=0;i<p.size();i++)
			{
					if (this.Postulant.getEmail().equals(p.get(i).getEmail()))
							{
								JSFUtils.addErrorMessage("Erreur :","adresse  Email est deja ajouter svp modifier Votre Email ", "formp");
								test=false;
								break;
							}
					
			}
			if(test==false )
			{
				  context.addCallbackParam("loggedIn", false);
			}
			else
			{
				boolean test =e.internet();
			
				if (test==true)
				{
					e.envoyerDec("activation ","bienvenue", Postulant.getNom(), date, Postulant.getEmail());
					PostulantService.create(Postulant);
					context.addCallbackParam("loggedIn", true);
				
				
				}
				else
				{
					System.out.println("funckyou man");
				}
			}
			
			
		  }catch(Exception e){
			  e.printStackTrace();
			  JSFUtils.addErrorMessage("Erreur :", e.getMessage(), "formCoordonnee");
			  context.addCallbackParam("loggedIn", false);
		    }
		 }
	public void updateIdentification()
	{		RequestContext context = RequestContext.getCurrentInstance();

	try{
		List<Postulant>Pl=PostulantService.findById(this.Postulant.getIdP());
		Postulant.setIdP(Pl.get(0).getIdP());
		test=true;
	
		for (int i=0;i<p.size();i++)
		{
			if (this.Postulant.getCin().equals(p.get(i).getCin()))
					{
						
							test=false;
							
					}
			
		}
			if(test==false )
			{
				JSFUtils.addErrorMessage("Erreur :","le Postulant est deja Existe ", "formIdentification");
				  context.addCallbackParam("loggedIn", false);
			}
			else
				if 
			(this.Postulant.getPhotoCarteIdentité()== null)
			{
			JSFUtils.addErrorMessage("Erreur :","svp choisir votre carte identité  ", "formIdentification");
			context.addCallbackParam("loggedIn", false);
			}
			else
			{
				
				PostulantService.update(Postulant);
				context.addCallbackParam("loggedIn", true);
				
			}
	
	}catch(Exception e){
		  e.printStackTrace();
		  JSFUtils.addErrorMessage("Erreur :", e.getMessage(), "formIdentification");
		  context.addCallbackParam("loggedIn", false);
	    }
	}
	public void updateCoordonnee()
	{		RequestContext context = RequestContext.getCurrentInstance();

	try{
		List<Postulant>Pl=PostulantService.findById(this.Postulant.getIdP());
		
		
			if 
		(this.Postulant.getPhotoIdentité()== null)
			{
		JSFUtils.addErrorMessage("Erreur :","svp choisir votre Photo identité  ", "formCoordonnee");
		context.addCallbackParam("loggedIn", false);
			}
			else
			{ Postulant.setIdP(Pl.get(0).getIdP());
							PostulantService.update(Postulant);
				context.addCallbackParam("loggedIn", true);
			}
	
	}catch(Exception e){
		  e.printStackTrace();
		  JSFUtils.addErrorMessage("Erreur :", e.getMessage(), "formCoordonnee");
		  context.addCallbackParam("loggedIn", false);
	    }
	}
	
	  public void test()
	    {
			RequestContext context = RequestContext.getCurrentInstance();
			
			try{
				System.out.println("bonjour");
				context.addCallbackParam("loggedIn", true);


			}catch(Exception e)
			{
				
			}
	    }
	
	
	public void upload(FileUploadEvent event) 
	{
	    ufile = event.getFile();
	    String fileName = ufile.getFileName();
	    String contentType = ufile.getContentType();
	    byte[] contents = ufile.getContents(); 
	    Postulant.setPhotoCarteIdentité(contents);
	    
	}
    //Identité
public void uploadPhoto(FileUploadEvent event) 
	{
	    ufile = event.getFile();
	    String fileName = ufile.getFileName();
	    String contentType = ufile.getContentType();
	    byte[] contents = ufile.getContents(); 
	    Postulant.setPhotoIdentité(contents);
	    
	}
	
   public void initUtilisateur(){
		Postulant = new Postulant();
		System.out.println("bonjour");
   }
   
   public void  chargerParametreGlob() 
   {
	   
	
   }
  
	public void chargerUtilisateur(String login) 
	{
    try{
			
		Postulant = new Postulant();
		List<Postulant> list= PostulantService.findByEmail(login);
		List<Postulant> listCin =PostulantService.findByCin(login);
		if (list.size()>0 )
		{
			FacesContext fc=FacesContext.getCurrentInstance();		
			this.Postulant = (Postulant) list.get(0);
			
         }
		else 
			if (listCin.size()>0)
			{ 
				FacesContext fc=FacesContext.getCurrentInstance();		
				this.Postulant = (Postulant) listCin.get(0);
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
    public void editCoordonne() {
    	RequestContext context = RequestContext.getCurrentInstance();
    	try{
    		
    		PostulantService.update(Postulant);
    		JSFUtils.addSuccessMessage("Info :", "les Coordonnées  a été modifié avec succès !", "formEditCoordonnee");
    		context.addCallbackParam("loggedIn", true);
    		
    	  }catch(Exception e){
    		  e.printStackTrace();
    		  JSFUtils.addErrorMessage("Erreur :", e.getMessage(), "formEditCoordonnee");
    		  context.addCallbackParam("loggedIn", false);
    	    }
    	 }
  

 
 	
	public Postulant getPostulant() {
		return Postulant;
	}

	public void setPostulant(Postulant postulant) {
		Postulant = postulant;
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

	public UploadedFile getUfile() {
		return ufile;
	}

	public void setUfile(UploadedFile ufile) {
		this.ufile = ufile;
	}

	public Cv getCv() {
		return cv;
	}

	public void setCv(Cv cv) {
		this.cv = cv;
	}

	

	public Integer getAcceesPostulant() {
		return acceesPostulant;
	}

	public void setAcceesPostulant(Integer acceesPostulant) {
		this.acceesPostulant = acceesPostulant;
	}

	public Integer getAcceesResponsable() {
		return acceesResponsable;
	}

	public void setAcceesResponsable(Integer acceesResponsable) {
		this.acceesResponsable = acceesResponsable;
	}

	
}