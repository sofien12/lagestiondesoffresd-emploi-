package tn.com.pfe.view;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import tn.com.pfe.domain.Candidature;
import tn.com.pfe.domain.Offremploi;
import tn.com.pfe.service.CandidatureService;
import utils.CommonCalendrier;
import utils.JSFUtils;

@Component("CandidatBean")
@Scope("view")
public class CandidatBean 
{
	
	@Autowired
	private CandidatureService candidService;
	private Candidature candida;
	private List<Candidature> ListCandidature;
	private List<Candidature>filteredListCandidature;
	private UserBean userBean;
	private OffreBean offremploi;
	boolean test=true;
	
		public CandidatBean()
			{
	
			}
		@PostConstruct
		public void initiate() {
			try{
				userBean=(UserBean) JSFUtils.getExpressionValue("#{userBean}");
				ListCandidature=new ArrayList<Candidature>();
				filteredListCandidature=new ArrayList<Candidature>();
				
				}catch(Exception e){
		        	e.printStackTrace();
		        }
		     }
public void initialddcandidat(Offremploi item)
{
	
	
	candida=new Candidature();
	
	candida.setOffremploi(item.getIdOffre());
	candida.setPostulant(userBean.getPostulant().getIdP());
	candida.setExperience(item.getMinimumExper());
	candida.setMissionPost(item.getMission());
	candida.setNiveauEtude(item.getNiveauEtude());
	
}

public List<Candidature> ListC()
{
	ListCandidature=candidService.findByIdPost(userBean.getPostulant().getIdP());
	filteredListCandidature=new ArrayList<Candidature>(ListCandidature);
		return ListCandidature;
}

public void addCandidature() {
	RequestContext context = RequestContext.getCurrentInstance();
	offremploi=(OffreBean) JSFUtils.getExpressionValue("#{OffreBean}");
	ListCandidature=candidService.findByIdPost(candida.getPostulant());

	try{
		
		
		if (ListCandidature.isEmpty())
		{
			System.out.println("la List est vide");
		}
		else
		{
			for( int i=0; i<ListCandidature.size();i++)		
			{
				if (ListCandidature.get(i).getOffremploi().equals(candida.getOffremploi()) )
							{
							
							test=false;
							break;
							}
			else{
					test=true;					
				}	
			}
		}
		
		if (test == true)
		{
			candida.setDateenregis(CommonCalendrier.nowTime());
			candida.setIdC("C");
		candidService.create(candida);
		JSFUtils.addSuccessMessage("Info :", "postuler a l'offre  a ete ajouter avec succes", "formCandidature");
		context.addCallbackParam("loggedIn", true);
		filteredListCandidature.add(candida);
		}
		else
		{
			JSFUtils.addErrorMessage("Erreur :", "vous etes  deja enregistrés dans l'offre  d'emploi ", "formCandidature");
			context.addCallbackParam("loggedIn", true);
		}
		
		
	  }catch(Exception e){
		  e.printStackTrace();
		  JSFUtils.addErrorMessage("Erreur :", e.getMessage(), "formCandidature");
		 
		  context.addCallbackParam("loggedIn", false);
	    }
	 }

public Candidature getCandida() {
	return candida;
}
public void setCandida(Candidature candida) {
	this.candida = candida;
}
public List<Candidature> getFilteredListCandidature() {
	return filteredListCandidature;
}
public void setFilteredListCandidature(List<Candidature> filteredListCandidature) {
	this.filteredListCandidature = filteredListCandidature;
}
public List<Candidature> getListCandidature() {
	return ListCandidature;
}
public void setListCandidature(List<Candidature> listCandidature) {
	ListCandidature = listCandidature;
}


}
