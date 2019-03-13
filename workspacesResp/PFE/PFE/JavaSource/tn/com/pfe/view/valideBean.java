package tn.com.pfe.view;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import tn.com.pfe.domain.Createur;
import tn.com.pfe.domain.Offremploi;
import tn.com.pfe.domain.Validateur;
import utils.CommonCalendrier;
import utils.JSFUtils;
import tn.com.pfe.service.*;
@Component("valideBean")
@Scope("view")
public class valideBean 
{
	private static final long serialVersionUID = 1L;
		// TODO Auto-generated constructor st
	@Autowired
	private ValidateurService ValidateurService;
	private Validateur Validateur;
	private List<Validateur> ListValidateur;
	private List<Validateur> List;
	private List<Offremploi> ListOffre;
	private List<Validateur> filteredListValidateur;
	private Createur CreateurList;
	private List <Createur>ListCreateur;
	private UserBean userBean;
	private OffreBean cvbean;
	
	public valideBean() {
		
	}
	@PostConstruct
	public void initial()
	{
		try
	{	
			cvbean=(OffreBean) JSFUtils.getExpressionValue("#{OffreBean}");
			ListCreateur=new ArrayList<Createur>();
			List=new ArrayList<Validateur>();
			ListOffre=cvbean.findByIdc();
			
			if (ListOffre.isEmpty())
			{
			System.out.println("sofien trabelsi");
				
			}
			else
			{
					for (int i=0; i<ListOffre.size();i++)
					{
				
						ListValidateur=ValidateurService.findByOffre(ListOffre.get(i).getIdOffre());
						System.out.println(ListValidateur.size());
					for(int j=0;j<ListValidateur.size();j++)
				List.add(ListValidateur.get(j));
						
					}
					
			filteredListValidateur= new ArrayList<Validateur>(List);
			}
			
	}catch(Exception e)
	 {
		e.printStackTrace();
      }
	}
	public List<Validateur> lesvalidateur()
	{
	
		return List;
	}
	public Createur nomprenom(Integer np)
	{
		userBean=(UserBean) JSFUtils.getExpressionValue("#{userBean}");
		
		CreateurList=userBean.responsable(np);
		ListCreateur.add(CreateurList);
		
		return CreateurList;
		
	}
	public void inialProcessus()
	{
		
		
		userBean=(UserBean) JSFUtils.getExpressionValue("#{userBean}");
		
		
		
		Validateur =new Validateur();
		
		Validateur.setCreateur(userBean.getCreateur().getIdR());
	
	}
	
	public void addProcessus()
	{	
		RequestContext context = RequestContext.getCurrentInstance();
		try{
			cvbean.remover(cvbean.getOffremploi());
			Validateur.setOffremploi(cvbean.getOffremploi().getIdOffre());	
			Validateur.setDateP(CommonCalendrier.nowTime());
			ValidateurService.create(Validateur);
			
		System.out.println(Validateur.getDateP());
		if (Validateur.getValider().equals("Valider"))
		{
			JSFUtils.addSuccessMessage("Info :", "l'offre a été Valider et  Envoyer  avec succès !", "formoEditProcessus");
		}
		else
		{
			JSFUtils.addSuccessMessage("Info :", "l'offre a été Rejeté", "formoEditProcessus");
		}
		context.addCallbackParam("loggedIn", true);
		}catch(Exception e){
			  e.printStackTrace();
			  JSFUtils.addErrorMessage("Erreur :", e.getMessage(), "formoEditProcessus");
			 
			  context.addCallbackParam("loggedIn", false);
		    }
	}
	public Validateur getValidateur() {
		return Validateur;
	}
	public void setValidateur(Validateur validateur) {
		Validateur = validateur;
	}
	
	public List<Validateur> getListValidateur() {
		return ListValidateur;
	}
	public void setListValidateur(List<Validateur> listValidateur) {
		ListValidateur = listValidateur;
	}
	
	public List<Validateur> getFilteredListValidateur() {
		return filteredListValidateur;
	}
	public void setFilteredListValidateur(List<Validateur> filteredListValidateur) {
		this.filteredListValidateur = filteredListValidateur;
	}
	public List<Validateur> getList() {
		return List;
	}
	public void setList(List<Validateur> list) {
		List = list;
	}
	
	
}
