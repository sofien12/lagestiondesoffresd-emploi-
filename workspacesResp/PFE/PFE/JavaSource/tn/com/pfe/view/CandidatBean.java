package tn.com.pfe.view;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import tn.com.pfe.domain.*;
import tn.com.pfe.service.CandidatureService;
import utils.JSFUtils;
import utils.email;

@Component("CandidatBean")
@Scope("view")
public class CandidatBean 
{
	@Autowired
	private CandidatureService candidService;
	private List<Candidature>ListCandidature;
	private List<Candidature>filteredListCandidat;
	private List<Candidature> ListCandidat;
	private List<Candidature> List;
	private Candidature candidat;
	private Boolean value;
	private UserBean userBean;
	private entretienBean entretien;
	private PostulantBean pb;
	private email Mail;
	String Email;
	String etatOffre;
	boolean test;
	private List<Postulant> Post;
	public CandidatBean()
	{
		
	}
	@PostConstruct
	public void initail()
	{
		try {
			filteredListCandidat=new ArrayList<Candidature>();
			ListCandidat= new ArrayList<Candidature>();
			candidat=new Candidature();
			userBean=(UserBean) JSFUtils.getExpressionValue("#{userBean}");
			entretien=(entretienBean) JSFUtils.getExpressionValue("#{entretienBean}");
			
			Email=new String ();
			
			Mail=new email();
			
			} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
		}
	}

	
	public List<Candidature> ListC(Integer Id)
	{
		if (userBean.getTest().equals("CR"))
		ListCandidature=candidService.findByIdPost(Id,"C");
		
		else
			if(userBean.getTest().equals("R"))
				ListCandidature=candidService.findByIdRe(Id,"V");
			return ListCandidature;
	}
	public void updateP()
	{
List=candidService.findByIdR("V");
		
		
		if (List.isEmpty())
			System.out.println("est vide");
		else
		{
			for (int i=0;i<List.size();i++)
			{
				if(List.get(i).getRetenue()==true)
				{
					List.get(i).setIdR("P");
					List.get(i).setEtatoffre("accepter");
					etatOffre="accepter";
					entretien.update(List.get(i));
				}
				else
					if (List.get(i).getRetenue()==false)
					{
						List.get(i).setIdR("N");
						List.get(i).setEtatoffre("refuser");
						etatOffre="refuser";
						
					}
					
				candidService.update(List.get(i));
				DescEmail(List.get(i).getPostulant());
				
			}
			JSFUtils.addSuccessMessage("Info :", "la validation de selection final des candidature a été  envoyer avec succée", "formCH");
		}
	}
	public void DescEmail(Integer i)
	{
		pb=(PostulantBean)JSFUtils.getExpressionValue("#{postulantBean}");
		
		Email=pb.EmailPostulant(i);

		System.out.println(Email);
		test=Mail.internet();
		
				
				if (test==true)
					Mail.envoyerDec("Votre offre",etatOffre,Email);
	
	}
	
	public void update ()
	{
	
		
			List=candidService.findByIdc("C");
		
		
		if (List.isEmpty())
			System.out.println("est vide");
		else
		{
			for (int i=0;i<List.size();i++)
			{
				
				if(List.get(i).getRetenue()==true)
				{
					List.get(i).setIdR("V");
					etatOffre="Accepter";
				}
				else
					if (List.get(i).getRetenue()==false)
					{
						
						List.get(i).setIdR("N");
						List.get(i).setEtatoffre("refuser");
						 etatOffre="refuser";
						 DescEmail(List.get(i).getPostulant());
					
					}
				
				
				List.get(i).setIdC("V");
				candidService.update(List.get(i));
			
			}
			
			
			JSFUtils.addSuccessMessage("Info :", "la selection des candidatures a été avec succes !", "formCH");
		
	}
	}
	/*public void onCellEdit(CellEditEvent event) {
	
	candidat.setRetenue((Boolean)event.getNewValue());
		candidService.update(candidat);
		ListCandidat=candidService.deletcandid();
		for(int i=0; i<ListCandidat.size();i++)
		{
			candidService.delete(ListCandidat.get(i));
		}
		
		JSFUtils.addSuccessMessage("Info :", "avec succès !", "formCH");
	}*/
	public void addMessage(Candidature itemC, Postulant item)
	{
		
		System.out.println(itemC.getExperience());
		candidService.update(itemC);
		
		if(itemC.getRetenue()==true)
		JSFUtils.addSuccessMessage("Info :", "la selection de "+item.getNom()+" "+item.getPrenom()+ " " + "a éte avec succès !", "formCH");
	}
		//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
		//candidat.setRetenue(summary);
		//System.out.println(candidat.getRetenue());
	
	public void onRowCancel(RowEditEvent event) {
		JSFUtils.addSuccessMessage("Info :", "modification annuler", "formCH");
	}
	public Candidature getCandidat() {
		return candidat;
	}
	public void setCandidat(Candidature candidat) {
		this.candidat = candidat;
	}
	public List<Candidature> getFilteredListCandidat() {
		return filteredListCandidat;
	}
	public void setFilteredListCandidat(List<Candidature> filteredListCandidat) {
		this.filteredListCandidat = filteredListCandidat;
	}
	public List<Candidature> getListCandidat() {
		return ListCandidat;
	}
	public void setListCandidat(List<Candidature> listCandidat) {
		ListCandidat = listCandidat;
	}
	public List<Candidature> getListCandidature() {
		return ListCandidature;
	}
	public void setListCandidature(List<Candidature> listCandidature) {
		ListCandidature = listCandidature;
	}
	public Boolean getValue() {
		return value;
	}
	public void setValue(Boolean value) {
		this.value = value;
	}
	
}
