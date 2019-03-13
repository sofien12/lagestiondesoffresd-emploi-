package tn.com.pfe.view;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import tn.com.pfe.domain.Entretien;
import tn.com.pfe.service.EntretienService;

@Component("EntertienBean")
@Scope("view")
public class EntretienBean 
{
	@Autowired
	private EntretienService entretienservice;
	private List<Entretien> entretien;
	private List<Entretien> filtrentretien;
	public EntretienBean ()
	{
		
	}
	@PostConstruct
	public void initial()
	{
		try {
			entretien=new ArrayList<Entretien>();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
		}
	}
	public List<Entretien> Listc(Integer id)
	{
		entretien= entretienservice.findByIdRe(id,"P");
		
		filtrentretien=new ArrayList<Entretien>(entretien);
		
		return entretien;
	}
	public List<Entretien> getFiltrentretien() {
		return filtrentretien;
	}
	public void setFiltrentretien(List<Entretien> filtrentretien) {
		this.filtrentretien = filtrentretien;
	}




}
