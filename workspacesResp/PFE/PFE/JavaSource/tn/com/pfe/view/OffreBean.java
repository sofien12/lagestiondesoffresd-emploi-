package tn.com.pfe.view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.TimeZone;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;




import org.apache.commons.io.IOUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;

import tn.com.pfe.domain.Createur;
import tn.com.pfe.domain.Offremploi;
import tn.com.pfe.domain.Validateur;

import tn.com.pfe.service.OffremploiService;
import tn.com.pfe.service.CreateurService;

import tn.com.pfe.service.ValidateurService;

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
@Component("OffreBean")
@Scope("view")
public class OffreBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private OffremploiService OffremploiService; 
	private CreateurService CreateurService;
	private Offremploi offremploi;
	private TimeZone timeZone;
	private Createur Createur;
	private UserBean userBean;
	private valideBean validebean;
	private HashMap<Createur,Offremploi> CO;
	private List<Offremploi> offre;
	private List<Offremploi> filteredListoffre;
	private Createur createurList;
	private  Map<String,String>Role ;
	private  Map<String,String>experience ;
	private  Map<String,String> niveau ;
	private  Map<String,String> Annee ;
	private  Map<String,String>cycle ;
	private  Map<String,String>Valider ;
	private  Map<String,String>Region ;
	private  Map<String,String>Localité ;
	private List<Createur> filteredListcreateur;
	private Createur CreateurList;
	private List<Createur> ListCreateur;
	private List<Offremploi> findlisto;
	private List<Offremploi>filtrelisto;
	private List<Offremploi>listo;
	
	public OffreBean() 
	{

		
	}
	@PostConstruct
	public void desactiveroffre()
	{
		List<Offremploi> ls=new ArrayList<Offremploi>();
		Date d= CommonCalendrier.nowTime();
		int monthnow=d.getMonth()+1;
		try {
			ls=OffremploiService.findAll();
			for(int i=0;i<=ls.size();i++)
			{
			
					int monthdateO=ls.get(i).getDateFinal().getMonth()+1;
					int monthdateof=ls.get(i).getDateFinal().getMonth()+1; 
					if ((ls.get(i).getDateFinal().getDate()==d.getDate()) && (monthdateO==monthnow) )
					{
						ls.get(i).setSuspendu(1);
					}
					else
						if ((ls.get(i).getDateFinal().getDate()<=d.getDate() && (monthdateO <= monthnow  ))&& ((ls.get(i).getSuspendu()==1) || (ls.get(i).getSuspendu()==0)))
						ls.get(i).setSuspendu(0);
						OffremploiService.update(ls.get(i));
					
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	@PostConstruct
	public void activeroffre()
	{
		List<Offremploi> ls=new ArrayList<Offremploi>();
		Date d= CommonCalendrier.nowTime();
		int monthnow=d.getMonth()+1;
		try {
			ls=OffremploiService.findAll();
			for(int i=0;i<=ls.size();i++)
			{
			
					int monthdateO=ls.get(i).getDateDebut().getMonth()+1;
					int monthdateof=ls.get(i).getDateFinal().getMonth()+1; 
					if ((ls.get(i).getDateDebut().getDate()==d.getDate()) && (monthdateO==monthnow) )
					{
						ls.get(i).setSuspendu(1);
					}
					else
						if (((ls.get(i).getDateDebut().getDate()>=d.getDate()) && (monthdateO >= monthnow  ))&& ((ls.get(i).getSuspendu()==1) || (ls.get(i).getSuspendu()==0)))
						ls.get(i).setSuspendu(0);
						OffremploiService.update(ls.get(i));
					
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void suspendreoffre (Offremploi item)
	{
		// List<Offremploi> ls;
		 Date d= CommonCalendrier.nowTime();
			
		try {
			//ls=OffremploiService.findAll();
			
			
			{
				if(item.getIdCp().equals("V"))
				{	
					int monthnow=d.getMonth()+1;
					System.out.println(monthnow);
					int monthdateO=item.getDateDebut().getMonth()+1;
					int monthdateof=item.getDateFinal().getMonth()+1;
					Date doffre=item.getDateDebut();
					if ((doffre.getDate()<= d.getDate() && monthdateO <= monthnow) && (item.getDateFinal().getDate()>=d.getDate() && monthdateof >= monthnow))
					{	
						item.setSuspendu(1);		
					}
					else
					{
					System.out.println("erruer ya sofien");
					item.setSuspendu(0);
						
					}
			
					

					
				}
			}
			 
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("erruer ya sofien");
		}
		
	}
	@PostConstruct	
	public void initiate() {
	try{
		
		userBean=(UserBean) JSFUtils.getExpressionValue("#{userBean}");
		offremploi =new Offremploi();
		Createur=userBean.getCreateur();
		filteredListoffre=new ArrayList<Offremploi>();
		
		
		filtrelisto=new ArrayList<Offremploi>();
		
		ListCreateur=new ArrayList<Createur>();		
		
		Role = new HashMap<String, String>();
		Role.put("directeur general", "directeur general");
		Role.put("comptabilité","comptabilité");
		Role.put("développeur","développeur");
		Role.put("l'intelligence d'entreprise", "l'intelligence d'entreprise");
		Role.put("avocat","avocat");
		
		experience = new HashMap<String, String>();
		experience.put("3 ans ", "3 ans");
		experience.put("4 ans ","4 ans");
		experience.put("2 ans "," 2 ans ");
		experience.put("1 ans ", "1 ans ");
		
		niveau = new HashMap<String, String>();
		
		niveau.put("Licence en Informatique ", "Licence en Informatique");
		niveau.put("Licence en gestion ","Licence en gestion ");
		niveau.put("Licence en droit"," Licence en droit");
		niveau.put("Ingenieur en Informatique ", "Ingenieur en Informatique");
		
		Region=new HashMap<String, String>();
			Region.put("Ariana", "Ariana");
			Region.put("Beja", "Beja");
			Region.put("Seliana","Seliana");
			
		Localité=new HashMap<String, String>();
			Localité.put("Manzah5", "Manzah5");
			Localité.put("Centre ville", "Centre ville");
			Localité.put("Seliana", "Seliana");
			Localité.put("Beja", "Beja");
		
	}catch(Exception e)
	 {
        	e.printStackTrace();
        }
	}
	public List<Offremploi> listoffremploi()
	{
		listo=new ArrayList<Offremploi>();
		findlisto=OffremploiService.findAll();
		for(int i=0; i<findlisto.size();i++)
		{
			if(findlisto.get(i).getIdCp().equals("V"))
				listo.add(findlisto.get(i));
		}
		filtrelisto=new ArrayList<Offremploi>(listo);
		return listo;
		
		}
	public void remover(Offremploi item)
	{	validebean=(valideBean) JSFUtils.getExpressionValue("#{valideBean}");
	System.out.println(item.getIdOffre());
		if (item.getIdS().equals("S"))
		{
			if (validebean.getValidateur().getValider().equals("Valider"))
			{
				item.setIdS("V");
			}
			else
				if (validebean.getValidateur().getValider().equals("Rejeter"))
				{
					item.setIdS("N");
				}
		OffremploiService.update(item);
		offre.remove(item);
		filteredListoffre=new ArrayList<Offremploi>(offre);
		}
		else
			if(item.getIdS().equals("V") && item.getIdR().equals("R"))
			{
				if (validebean.getValidateur().getValider().equals("Valider"))
				{
					item.setIdR("V");
					
				}
				else
					if (validebean.getValidateur().getValider().equals("Rejeter"))
					{
						item.setIdR("N");
					}
				OffremploiService.update(item);
				offre.remove(item);
				filteredListoffre=new ArrayList<Offremploi>(offre);
			}
			else
				if(item.getIdS().equals("V") && item.getIdR().equals("V") && item.getIdCp().equals("C"))
				{
					if (validebean.getValidateur().getValider().equals("Valider"))
					{
						item.setIdCp("V");
						suspendreoffre ( item);
						
					}
					else
						if (validebean.getValidateur().getValider().equals("Rejeter"))
						{
							item.setIdCp("N");
							
						}
					
					
					OffremploiService.update(item);
					offre.remove(item);
					filteredListoffre=new ArrayList<Offremploi>(offre);
				}
	}
	public List<Offremploi> findByIdc()
	{
		if (userBean.getTest().equals("BU"))
		{
			offre=OffremploiService.findByIdC(userBean.getCreateur().getIdR());
			filteredListoffre=new ArrayList<Offremploi>(offre);
		}
		else
			if (userBean.getTest().equals("RH"))
			{
				offre=OffremploiService.findByIdC(userBean.getCreateur().getIdR());
				filteredListoffre=new ArrayList<Offremploi>(offre);
			}
			else
				if(userBean.getTest().equals("CR"))
				{
					offre= new ArrayList<Offremploi>();
					filteredListoffre=new ArrayList<Offremploi>(offre);
				}
			
		return offre;
	}
	
	public List<Offremploi> findbyoffre()
	{	if(userBean.getTest().equals("S"))
		{	offre=OffremploiService.findByIdS("S");
		filteredListoffre=new ArrayList<Offremploi>(offre);
			
			
		}
	else
		if (userBean.getTest().equals("R"))
		{	offre=OffremploiService.findByIdR("V", "R");
		filteredListoffre=new ArrayList<Offremploi>(offre);
				
		}
		 else
			 if (userBean.getTest().equals("Cp"))
			 {
				 offre=OffremploiService.findByIdC("V","C");
					filteredListoffre=new ArrayList<Offremploi>(offre);
			 }
			
	return offre;
				
	}
	public Offremploi findbyIdO (Integer item)
	{
		offremploi=OffremploiService.findById(item);
		return offremploi;
	}
public Createur nomprenom(Integer np)
{

	CreateurList=userBean.responsable(np);
	
	ListCreateur.add(CreateurList);

	return CreateurList;
	
}
public void modification(Offremploi item)
{RequestContext context = RequestContext.getCurrentInstance();
	try {
		OffremploiService.update(item);
		if (item.getSuspendu()==1)
		JSFUtils.addSuccessMessage("Info :", " la publication de l'offre a été  activer  ", "formG");
		else
			JSFUtils.addSuccessMessage("Info :", " la publication de l'offre a été  desactiver  ", "formG");
	} catch (Exception e) {
		// TODO: handle exception$
		System.out.println("errer");
	}
}
public void editoffre() {
	RequestContext context = RequestContext.getCurrentInstance();
	try{
		
		
		OffremploiService.update(offremploi);
		JSFUtils.addSuccessMessage("Info :", "l'offre  a été modifié avec succès !", "formoEditoffre");
		context.addCallbackParam("loggedIn", true);
		
	  }catch(Exception e){
		  e.printStackTrace();
		  JSFUtils.addErrorMessage("Erreur :", e.getMessage(), "formoEditoffre");
		  context.addCallbackParam("loggedIn", false);
	    }
	 }
public void initaddOffre()
{
	offremploi=new Offremploi();
}


 	public void addOffre() {
	RequestContext context = RequestContext.getCurrentInstance();
	try{
		
		
		offremploi.setCreateur(Createur.getIdR());
		offremploi.setSuspendu(0);
		offremploi.setIdS("S"); //responsable structure
		offremploi.setIdR("R"); // responsable Recretument
		offremploi.setIdCp("C"); //responsable capital humaine
			if (userBean.getTest().equals("BU"))
			{
				offremploi.setTypeOffre("Type siege");
				offremploi.setRegion("Ariana");
				offremploi.setLocalité("Urbain Nord ");
			}
			else
				if (userBean.getTest().equals("RH"))
				{
					offremploi.setTypeOffre("Type reseau");

				}
		
		OffremploiService.create(offremploi);
		filteredListoffre.add(offremploi);
		
		System.out.println(this.offremploi.getRole());
		
		if (userBean.getTest().equals("BU"))
		JSFUtils.addSuccessMessage("Info :", "l'offre   a été ajouté avec succès !", "formBU");
		else
			JSFUtils.addSuccessMessage("Info :", "l'offre   a été ajouté avec succès !", "formRH");	
		context.addCallbackParam("loggedIn", true);
		offremploi=new Offremploi();
		
	  }catch(Exception e){
		  
		  e.printStackTrace();
		  JSFUtils.addErrorMessage("Erreur :", e.getMessage(), "formBU");
		 
		  context.addCallbackParam("loggedIn", false);
	    }
	 }
	

		
	
	public void setTimeZone(TimeZone timeZone) {
		this.timeZone = timeZone;
	}

	public TimeZone getTimeZone() {
		return TimeZone.getDefault();
	}
	
	

	public Map<String, String> getNiveau() {
		return niveau;
	}


	public void setNiveau(Map<String, String> niveau) {
		this.niveau = niveau;
	}


	public Map<String, String> getRole() {
		return Role;
	}


	public void setRole(Map<String, String> role) {
		Role = role;
	}




	public Map<String, String> getExperience() {
		return experience;
	}


	public void setExperience(Map<String, String> experience) {
		this.experience = experience;
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


	public Offremploi getOffremploi() {
		return offremploi;
	}


	public void setOffremploi(Offremploi offremploi) {
		this.offremploi = offremploi;
	}


	public Createur getCreateur() {
		return Createur;
	}


	public void setCreateur(Createur createur) {
		Createur = createur;
	}


	public List<Offremploi> getOffre() {
		return offre;
	}


	public void setOffre(List<Offremploi> offre) {
		this.offre = offre;
	}


	public List<Offremploi> getFilteredListoffre() {
		return filteredListoffre;
	}


	public void setFilteredListoffre(List<Offremploi> filteredListoffre) {
		this.filteredListoffre = filteredListoffre;
	}


	public List<Createur> getFilteredListcreateur() {
		return filteredListcreateur;
	}


	public void setFilteredListcreateur(List<Createur> filteredListcreateur) {
		this.filteredListcreateur = filteredListcreateur;
	}


	public Createur getCreateurList() {
		return CreateurList;
	}


	public void setCreateurList(Createur createurList) {
		CreateurList = createurList;
	}


	public List<Createur> getListCreateur() {
		return ListCreateur;
	}


	public void setListCreateur(List<Createur> listCreateur) {
		ListCreateur = listCreateur;
	}

	public Map<String, String> getValider() {
		return Valider;
	}
	public void setValider(Map<String, String> valider) {
		Valider = valider;
	}

	public Map<String, String> getRegion() {
		return Region;
	}

	public void setRegion(Map<String, String> region) {
		Region = region;
	}

	public Map<String, String> getLocalité() {
		return Localité;
	}

	public void setLocalité(Map<String, String> localité) {
		Localité = localité;
	}

	public List<Offremploi> getFiltrelisto() {
		return filtrelisto;
	}

	public void setFiltrelisto(List<Offremploi> filtrelisto) {
		this.filtrelisto = filtrelisto;
	}






	
	
	
}
