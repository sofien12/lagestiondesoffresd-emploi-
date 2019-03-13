package tn.com.pfe.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import tn.com.pfe.domain.Offremploi;
import tn.com.pfe.service.OffremploiService;
import utils.CommonCalendrier;

@Component("OffreBean")
@Scope("view")
public class OffreBean 
{
	@Autowired
	private OffremploiService offremploiService;
	
	private List<Offremploi> ListOffre;
	private List<Offremploi>offreparCandidature;
	private List<Offremploi> filteredListOffre;
	private List<Offremploi> List;
	private Offremploi offremploi;
	public OffreBean()
	{
		
	}
	
	
	@PostConstruct
	public void initiate() 
	{
		
		try {
			ListOffre=new ArrayList<Offremploi>();
			offreparCandidature= new ArrayList<Offremploi>();
			List=new ArrayList<Offremploi>();
			ListOffre=offremploiService.findAll();
			if (ListOffre.isEmpty())
			{
				System.out.println("Offre est videe");
			}
			else
			{
				for (int i=0; i<ListOffre.size();i++)
				{
				
					
					if (ListOffre.get(i).getSuspendu()==1 )
						List.add(ListOffre.get(i));
				}
				filteredListOffre=new ArrayList<Offremploi>(List);
			}
				
			
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
		}
	}
	public Offremploi offre(Integer item)
	{
		 offremploi=offremploiService.findById(item);
		return offremploi;
	}


	public List<Offremploi> getFilteredListOffre() {
		return filteredListOffre;
	}


	public void setFilteredListOffre(List<Offremploi> filteredListOffre) {
		this.filteredListOffre = filteredListOffre;
	}


	public List<Offremploi> getList() {
		return List;
	}


	public void setList(List<Offremploi> list) {
		List = list;
	}


	public Offremploi getOffremploi() {
		return offremploi;
	}


	public void setOffremploi(Offremploi offremploi) {
		this.offremploi = offremploi;
	}
	
}
