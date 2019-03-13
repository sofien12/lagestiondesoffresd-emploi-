package tn.com.pfe.view;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.TimeZone;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


import tn.com.pfe.domain.Cv;
import tn.com.pfe.domain.Postulant;
import tn.com.pfe.service.CvService;

import org.apache.commons.io.IOUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;

import utils.CommonCalendrier;
import utils.JSFUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.primefaces.model.StreamedContent;
import org.primefaces.model.DefaultStreamedContent;  
import org.primefaces.model.UploadedFile;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;

import java.io.InputStream;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
@Component("CvBean")
@Scope("view")
public class CvBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private CvService CvService; 
	
	private TimeZone timeZone;
	private Postulant Postulant;
	private List<Cv> listCv;
	private Map<String,String> typeFormation;
	private  Map<String,String>etablissement ;
	private  Map<String,String>Domaine ;
	private  Map<String,String>specialité ;
	private  Map<String,String> etablissementEducation ;
	private Map<String,String >Regime;
	private  Map<String,String> Annee ;
	private  Map<String,String>cycle ;
	private List<Cv> filteredListCv;
	private List <Cv> listeducation;
	private List <Cv> listFormation;
	private List <Cv> filteredListFormation;
	private List <Cv> listExperience;
	private List <Cv> filteredListExperience;
	private List<Cv> filteredListEducation;
	private Cv selectedCv ;
	private DefaultStreamedContent image;
	private StreamedContent file;
	 private UploadedFile ufile;
	public CvBean() 
	{
		
	}
	

	@PostConstruct	
	public void initiate() {
	try{
		UserBean userBean=(UserBean) JSFUtils.getExpressionValue("#{userBean}");
		Postulant = userBean.getPostulant();
		String idp=Postulant.getCin();
		listCv = CvService.findByIdp(idp);
		filteredListCv = new ArrayList<Cv>(listCv);
		
		listeducation=CvService.findByEducation(idp);
		filteredListEducation=new ArrayList<Cv>(listeducation);
		
		listFormation=CvService.findByFormation(idp);
		filteredListFormation= new ArrayList<Cv>(listFormation);
		
		listExperience =CvService.findByExperience(idp);
		filteredListExperience=new ArrayList<Cv>(listExperience);
		
		typeFormation = new HashMap<String, String>();
		typeFormation.put("Stage", "Stage");
		typeFormation.put("Experience professionnelle","Experience professionnelle");
		
		etablissement = new HashMap<String, String>();
			etablissement.put("Tunis", "Tunis");
			etablissement.put("Sfax","Sfax");
		etablissement.put("Bizerte","Bizerte");
		etablissement.put("Sousse","Sousse");
		etablissement.put("Monastir","Monastir");
		
	Domaine=new HashMap<String,String>();
		Domaine.put("Informatique", "Informatique");
		Domaine.put("Gestion", "Gestion");
		Domaine.put("Droite","Droite");
		
	specialité=new HashMap<String,String>();
		specialité.put("Développement", "Développement");
		specialité.put("Informatique Décisionnelle","Informatique Décisionnelle");
		specialité.put("Systeme embarque", "Systeme Embarque");
		specialité.put("Comptabilité", "Comptabilité");
		specialité.put("Droite", "Droite");
		
		etablissementEducation=new HashMap<String,String>();
		etablissementEducation.put("faculté de science de tunis","Faculté de science de tunis");
		etablissementEducation.put("Institut Supérieur de Gestion ","Institut Supérieur de Gestion ");
		etablissementEducation.put("Institut supérieur d'informatique","Institut supérieur d'informatique");
		etablissementEducation.put("faculté de droit", "faculté de droit");
		
		cycle=new HashMap<String,String>();
			cycle.put("Licence Appliquée", "Licence Appliquée");
			cycle.put("Licence Fondamentale", "Licence Fondamentale");
			cycle.put("Master professionnelle", "Master professionnelle");
			cycle.put("Master de recherche ", "Master de recherche ");
			cycle.put("Doctorat", "Doctorat");
			cycle.put("ingénieur", "ingénieur");
	
	Annee=new HashMap<String,String>();
		Annee.put("2010", "2010");
		Annee.put("2011", "2011");
		Annee.put("2012", "2012");
		Annee.put("2013", "2013");
		Annee.put("2014", "2014");
		Annee.put("2015", "2015");
		Annee.put("2016", "2016");
		Annee.put("2017", "2017");
		
	Regime=new HashMap<String,String >();
		Regime.put("Arabic ","Arabic ");
		Regime.put(" Anglais ", " Anglais ");
		Regime.put("Francais", "Francais ");
		
		
	}catch(Exception e)
	 {
        	e.printStackTrace();
        }
	}
	
	//dowload  file 
	public void dowloand(byte[] photo) throws IOException, DocumentException
	{
		 Document doc = new  Document(PageSize.A4, 50, 50, 50, 50);
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        ByteArrayInputStream in ;
	        PdfWriter writer;
	        writer = PdfWriter.getInstance(doc, out);
	        doc.open();
	        Image img=Image.getInstance(photo);
	        doc.add(img);
	        doc.close();
	        in = new ByteArrayInputStream(out.toByteArray());
	        file = new DefaultStreamedContent(in, "application/pdf", "Diplome.pdf");
		 
		 in.close();
		
	}
	
	


		//affiche image
	public DefaultStreamedContent getImage() throws IOException {
		InputStream is = new ByteArrayInputStream( Postulant.getPhotoIdentité());
		DefaultStreamedContent myImage = new DefaultStreamedContent(is, "image/png");
		is.close();
		return myImage;
		}
		//upload  file 
	public void upload(FileUploadEvent event) 
	{
	    ufile = event.getFile();
	    String fileName = ufile.getFileName();
	    String contentType = ufile.getContentType();
	    byte[] contents = ufile.getContents(); 
	    selectedCv.setPhotoDiplomee(contents);
	}
	public void uploadExperience(FileUploadEvent event)
	{
		 ufile = event.getFile();
		    String fileName = ufile.getFileName();
		    String contentType = ufile.getContentType();
		    byte[] contents = ufile.getContents(); 
		    selectedCv.setPhotoDiplomeExper(contents);
	}
	
public void uploadFormation (FileUploadEvent event)
{
	ufile = event.getFile();
    	String fileName = ufile.getFileName();
    	String contentType = ufile.getContentType();
    	byte[] contents = ufile.getContents(); 
    selectedCv.setPhotoDiplomeFormation(contents);
}
	public void setImage(DefaultStreamedContent image) {
			this.image = image;
		}


public void initaddclient()
{
	selectedCv=new Cv();
}

	public void addClient() {
	RequestContext context = RequestContext.getCurrentInstance();
	try{
		
		if (this.selectedCv.getPhotoDiplomee()==null)
		{
			JSFUtils.addErrorMessage("Erreur :"," choisir votre diplome ", "formEducation");
			 JSFUtils.addErrorMessage("Erreur :", "choisir votre diplome ", "formAddClient");
			  context.addCallbackParam("loggedIn", false);
		}
		else
		{
		selectedCv.setPostulant(Postulant.getCin());
		CvService.create(selectedCv);
		listeducation.add(selectedCv);
		filteredListEducation = new ArrayList<Cv>(listeducation);
		JSFUtils.addSuccessMessage("Info :", "Education  a été ajouté avec succès !", "formMain");
		context.addCallbackParam("loggedIn", true);
		}
	  }catch(Exception e){
		  e.printStackTrace();
		  JSFUtils.addErrorMessage("Erreur :", e.getMessage(), "formMain");
		  JSFUtils.addErrorMessage("Erreur :", e.getMessage(), "formEducation");
		  context.addCallbackParam("loggedIn", false);
	    }
	 }
	
 	public void addFormation() {
		RequestContext context = RequestContext.getCurrentInstance();
		try{
			if (this.selectedCv.getPhotoDiplomeFormation()==null)
			{
				JSFUtils.addErrorMessage("Erreur :"," choisir votre certificat ", "formAddformation");
				JSFUtils.addErrorMessage("Info :", "choisir votre certificat !", "formAddClient");
				  context.addCallbackParam("loggedIn", false);
			}
			else
			{
			selectedCv.setPostulant(Postulant.getCin());
			//selectedCv.setDatecre(CommonCalendrier.nowTime());
			CvService.create(selectedCv);
			listFormation.add(selectedCv);
			filteredListFormation = new ArrayList<Cv>(listeducation);
			JSFUtils.addSuccessMessage("Info :", "Formation  a été ajouté avec succès !", "formMain");
			context.addCallbackParam("loggedIn", true);
			}
		  }catch(Exception e){
			  e.printStackTrace();
			  JSFUtils.addErrorMessage("Erreur :", e.getMessage(), "formMain");
			  JSFUtils.addSuccessMessage("Info :", "Formation  a été ajouté avec succès !", "formAddformation");
			  context.addCallbackParam("loggedIn", false);
		    }
		 }
	public void addExperience() {
		RequestContext context = RequestContext.getCurrentInstance();
		try{
			if (this.selectedCv.getPhotoDiplomeExper()==null)
			{
				JSFUtils.addErrorMessage("Erreur :"," choisir votre affectation ", "formAddClient");
				  context.addCallbackParam("loggedIn", false);
			}
			else
			{
			selectedCv.setPostulant(Postulant.getCin());
			//selectedCv.setDatecre(CommonCalendrier.nowTime());
			CvService.create(selectedCv);
			listExperience.add(selectedCv);
			filteredListExperience = new ArrayList<Cv>(listExperience);
			JSFUtils.addSuccessMessage("Info :", "Experience  a été ajouté avec succès !", "formMain");
			context.addCallbackParam("loggedIn", true);
			}
		  }catch(Exception e){
			  e.printStackTrace();
			  JSFUtils.addErrorMessage("Erreur :", e.getMessage(), "formMain");
			  JSFUtils.addSuccessMessage("Info :", "Experience  a été ajouté avec succès !", "formAddClient");
			  context.addCallbackParam("loggedIn", false);
		    }
		 }
	
		
	public void editClient() {
	RequestContext context = RequestContext.getCurrentInstance();
	try{
		selectedCv.setPostulant(Postulant.getCin());
		CvService.update(selectedCv);
		JSFUtils.addSuccessMessage("Info :", "Education a été modifié avec succès !", "formEditClient");
		context.addCallbackParam("loggedIn", true);
		
	  }catch(Exception e){
		  e.printStackTrace();
		  JSFUtils.addErrorMessage("Erreur :", e.getMessage(), "formEditClient");
		  context.addCallbackParam("loggedIn", false);
	    }
	 }
	
	public void editFormation() {
		RequestContext context = RequestContext.getCurrentInstance();
		try{
			selectedCv.setPostulant(Postulant.getCin());
			CvService.update(selectedCv);
			JSFUtils.addSuccessMessage("Info :", "Formation a été modifié avec succès !", "formEditClient");
			context.addCallbackParam("loggedIn", true);
			
		  }catch(Exception e){
			  e.printStackTrace();
			  JSFUtils.addErrorMessage("Erreur :", e.getMessage(), "formEditClient");
			  context.addCallbackParam("loggedIn", false);
		    }
		 }
	
	public void editExperience() {
		RequestContext context = RequestContext.getCurrentInstance();
		try{
			selectedCv.setPostulant(Postulant.getCin());
			CvService.update(selectedCv);
			JSFUtils.addSuccessMessage("Info :", "Experience a été modifié avec succès !", "formEditClient");
			context.addCallbackParam("loggedIn", true);
			
		  }catch(Exception e){
			  e.printStackTrace();
			  JSFUtils.addErrorMessage("Erreur :", e.getMessage(), "formEditClient");
			  context.addCallbackParam("loggedIn", false);
		    }
		 }
	
	public void deleteClient() {
	RequestContext context = RequestContext.getCurrentInstance();
	try{
		CvService.delete(selectedCv);
		listeducation.remove(selectedCv);
		filteredListEducation = new ArrayList<Cv>(listeducation);
		JSFUtils.addSuccessMessage("Info :", "Education a été supprimé avec succès !", "formMain");
		context.addCallbackParam("loggedIn", true);
		
	  }catch(Exception e){
		  e.printStackTrace();
		  JSFUtils.addErrorMessage("Erreur :", e.getMessage(), "formMain");
		  context.addCallbackParam("loggedIn", false);
	    }
	 }
	public void deleteExperience() {
		RequestContext context = RequestContext.getCurrentInstance();
		try{
			CvService.delete(selectedCv);
			listExperience.remove(selectedCv);
			filteredListExperience = new ArrayList<Cv>(listExperience);
			JSFUtils.addSuccessMessage("Info :", "Experience été supprimé avec succès !", "formMain");
			
			context.addCallbackParam("loggedIn", true);
			
		  }catch(Exception e){
			  e.printStackTrace();
			  JSFUtils.addErrorMessage("Erreur :", e.getMessage(), "formMain");
			  
			  context.addCallbackParam("loggedIn", false);
		    }
		 }
	
	public void deleteFormation() {
		RequestContext context = RequestContext.getCurrentInstance();
		try{
			CvService.delete(selectedCv);
			listFormation.remove(selectedCv);
			filteredListFormation = new ArrayList<Cv>(listFormation);
			JSFUtils.addSuccessMessage("Info :", "Formation été supprimé avec succès !", "formMain");
			context.addCallbackParam("loggedIn", true);
			
		  }catch(Exception e){
			  e.printStackTrace();
			  JSFUtils.addErrorMessage("Erreur :", e.getMessage(), "formMain");
			  context.addCallbackParam("loggedIn", false);
		    }
		 }
	
	public void setTimeZone(TimeZone timeZone) {
		this.timeZone = timeZone;
	}

	public TimeZone getTimeZone() {
		return TimeZone.getDefault();
	}
	
	

	public Postulant getPostulant() {
		return Postulant;
	}

	public void setPostulant(Postulant postulant) {
		Postulant = postulant;
	}
	public CvService getCvService() {
		return CvService;
	}

	public void setCvService(CvService cvService) {
		CvService = cvService;
	}

	public List<Cv> getListCv() {
		return listCv;
	}

	public void setListCv(List<Cv> listCv) {
		this.listCv = listCv;
	}

	public List<Cv> getFilteredListCv() {
		return filteredListCv;
	}

	public void setFilteredListCv(List<Cv> filteredListCv) {
		this.filteredListCv = filteredListCv;
	}

	public Cv getSelectedCv() {
		return selectedCv;
	}

	public void setSelectedCv(Cv selectedCv) {
		this.selectedCv = selectedCv;
	}

	public List<Cv> getListeducation() {
		return listeducation;
	}

	public void setListeducation(List<Cv> listeducation) {
		this.listeducation = listeducation;
	}

	public List<Cv> getFilteredListEducation() {
		return filteredListEducation;
	}

	public void setFilteredListEducation(List<Cv> filteredListEducation) {
		this.filteredListEducation = filteredListEducation;
	}

	public List<Cv> getListFormation() {
		return listFormation;
	}

	public void setListFormation(List<Cv> listFormation) {
		this.listFormation = listFormation;
	}

	public List<Cv> getFilteredListFormation() {
		return filteredListFormation;
	}

	public void setFilteredListFormation(List<Cv> filteredListFormation) {
		this.filteredListFormation = filteredListFormation;
	}

	public List<Cv> getListExperience() {
		return listExperience;
	}

	public void setListExperience(List<Cv> listExperience) {
		this.listExperience = listExperience;
	}

	public List<Cv> getFilteredListExperience() {
		return filteredListExperience;
	}

	public void setFilteredListExperience(List<Cv> filteredListExperience) {
		this.filteredListExperience = filteredListExperience;
	}

public StreamedContent getFile()  {
		
		return file;
	}
	public void setFile(StreamedContent file) {
		this.file = file;
	}
	

	public UploadedFile getUfile() {
		 byte[] contents = ufile.getContents(); 
		    selectedCv.setPhotoDiplomee(contents);
		return ufile;
	}


	public void setUfile(UploadedFile ufile) {
		this.ufile = ufile;
	}


	public Map<String,String> getTypeFormation() {
		return typeFormation;
	}


	public void setTypeFormation(Map<String,String>typeFormation) {
		this.typeFormation = typeFormation;
	}


	public Map<String, String> getEtablissement() {
		return etablissement;
	}


	public void setEtablissement(Map<String, String> etablissement) {
		this.etablissement = etablissement;
	}


	public Map<String, String> getDomaine() {
		return Domaine;
	}


	public void setDomaine(Map<String, String> domaine) {
		Domaine = domaine;
	}


	public Map<String, String> getSpecialité() {
		return specialité;
	}


	public void setSpecialité(Map<String, String> specialité) {
		this.specialité = specialité;
	}


	public Map<String, String> getEtablissementEducation() {
		return etablissementEducation;
	}


	public void setEtablissementEducation(Map<String, String> etablissementEducation) {
		this.etablissementEducation = etablissementEducation;
	}


	public Map<String, String> getCycle() {
		return cycle;
	}


	public void setCycle(Map<String, String> cycle) {
		this.cycle = cycle;
	}


	public Map<String, String> getAnnee() {
		return Annee;
	}


	public void setAnnee(Map<String, String> annee) {
		Annee = annee;
	}


	public Map<String, String> getRegime() {
		return Regime;
	}


	public void setRegim(Map<String, String> regim) {
		this.Regime = regim;
	}
	
	
}
