package tn.com.pfe.view;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.primefaces.event.CellEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import tn.com.pfe.domain.*;
import tn.com.pfe.service.EntretienService;
import utils.CommonCalendrier;
import utils.JSFUtils;
import utils.email;

@Component("entretienBean")
@Scope("view")
public class entretienBean
{
	@Autowired
	private EntretienService entretienService;
	private List<Entretien> ListEntretien;
	private List<Entretien> ListEntretienV;
	private List<Entretien> ListEV;
	private List<Entretien> filtreListEV;
	private List<Entretien> filtreListEntretien;
	private UserBean userBean;
	private Entretien entretien;
	private List<Entretien> List;
	private MajBean majBean;
	private PostulantBean pb;
	String Email;
	email Mail;
	boolean test;
	
	@PostConstruct
	public void initial()
	{
		try {
			majBean=(MajBean)JSFUtils.getExpressionValue("#{MajBean}");
			userBean=(UserBean) JSFUtils.getExpressionValue("#{userBean}");
			List=new ArrayList<Entretien>();
			filtreListEV=new ArrayList<Entretien>();
			Mail=new email();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
		}
	}
	public List<Entretien> ListC(Integer Id)
	{
		if (userBean.getTest().equals("CR"))
			ListEntretien=entretienService.findByIdRe(Id,"C");
		
		else
			if(userBean.getTest().equals("R"))
				ListEntretien=entretienService.findByIdRe(Id,"V");
			return ListEntretien;
	}
	public void update (Candidature item)
	{
		entretien=new Entretien();
		try {
			entretien.setPostulant(item.getPostulant());
			entretien.setIdE("C");
			entretienService.Create(entretien);
			//JSFUtils.addSuccessMessage("Info :", "le postulant  a été  envoyer a l'entretien ", "formEntretien");
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
		}
		
	}
	public List<Entretien> ListE(Integer id)
	{
		Date d= CommonCalendrier.nowTime();
		String date= CommonCalendrier.formdate(d);
		
		ListEV=new ArrayList<Entretien>();
		
		if (userBean.getTest().equals("R"))
			{
			ListEntretienV=entretienService.findByIdRe(id,"E");
		
				for (int i=0; i<ListEntretienV.size();i++)
				{
					String dateE=CommonCalendrier.formdate(ListEntretienV.get(i).getDatentretie());
						if (dateE.equals(date))
						{	
							ListEV.add(ListEntretienV.get(i));	
							
						}
						
				}
				filtreListEV=new ArrayList<Entretien>(ListEV);
			}
		return ListEV;
	}
	
	public void planifier()
	{
			List=entretienService.findByIdc("C");
		
		if (List.isEmpty())
			System.out.println("est vide");
		else
		{
			for (int i=0;i<List.size();i++)
			{
					List.get(i).setIdE("V");
					entretienService.update(List.get(i));
			}
			JSFUtils.addSuccessMessage("Info :", "le Planifier d'entretien  des candidature a été  envoyer avec succée", "formEntretien");
		}
	}
	public void validerplanifier()
	{
	List=entretienService.findByIdc("V");
		
		if (List.isEmpty())
			System.out.println("est vide");
		else
		{
			for (int i=0;i<List.size();i++)
			{
					List.get(i).setIdE("E");
				
					entretienService.update(List.get(i));
					DescEmail(List.get(i).getPostulant(),List.get(i).getDatentretie());
			}
			JSFUtils.addSuccessMessage("Info :", "la validation de Planification  d'entretien  des candidature a été   avec succée", "formVEntretien");
		}
	}
	public void DescEmail(Integer i, Date d)
	{
		pb=(PostulantBean)JSFUtils.getExpressionValue("#{postulantBean}");
		String date=CommonCalendrier.formdate(d);
		System.out.println(date);
		Email=pb.EmailPostulant(i);

		System.out.println(Email);
		test=Mail.internet();				
				if (test==true)
					Mail.envoyerDec("l'entretien a : ",date,Email);
	
	}
	public void Emailavis(Integer i,String avis)
	{
		pb=(PostulantBean)JSFUtils.getExpressionValue("#{postulantBean}");
		Email=pb.EmailPostulant(i);
		
		System.out.println(Email);
		test=Mail.internet();
		if (test==true)
			Mail.envoyerDec("votre Entretien ",avis , Email);
			}
	public void validerentretien()
	{
	List=entretienService.findByIdc("E");
	Date d= CommonCalendrier.nowTime();
	String date= CommonCalendrier.formdate(d);
	int mm=d.getMonth()+1;
		if (List.isEmpty())
			System.out.println("est vide");
		else
		{
			for (int i=0;i<List.size();i++)
			{
				int m=List.get(i).getDatentretie().getMonth()+1;
				
				System.out.println(List.get(i).getDatentretie().getDate() +","+m );
				if (List.get(i).getDatentretie().getDate() == d.getDate() && m == mm  )
					{
					System.out.println("ofien");
						List.get(i).setIdE("P");
							if (List.get(i).getAvis().equals("accepter"))
								majBean.create(List.get(i).getPostulant());
						entretienService.update(List.get(i));
					Emailavis(List.get(i).getPostulant(),List.get(i).getAvis());
			
					}
			}
			JSFUtils.addSuccessMessage("Info :", "evaluation   d'entretien a été   avec succée", "formVEntretien");
		}
	}
	public void addMessageheure(Entretien itemC)
	{
		try {
			entretienService.update(itemC);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
		}
		
	}
	public List<Entretien> getListEntretien() {
		return ListEntretien;
	}
	public void setListEntretien(List<Entretien> listEntretien) {
		ListEntretien = listEntretien;
	}
	public List<Entretien> getFiltreListEntretien() {
		return filtreListEntretien;
	}
	public void setFiltreListEntretien(List<Entretien> filtreListEntretien) {
		this.filtreListEntretien = filtreListEntretien;
	}
	public List<Entretien> getListEntretienV() {
		return ListEntretienV;
	}
	public void setListEntretienV(List<Entretien> listEntretienV) {
		ListEntretienV = listEntretienV;
	}
	public List<Entretien> getListEV() {
		return ListEV;
	}
	public void setListEV(List<Entretien> listEV) {
		ListEV = listEV;
	}
	public List<Entretien> getFiltreListEV() {
		return filtreListEV;
	}
	public void setFiltreListEV(List<Entretien> filtreListEV) {
		this.filtreListEV = filtreListEV;
	}



}
