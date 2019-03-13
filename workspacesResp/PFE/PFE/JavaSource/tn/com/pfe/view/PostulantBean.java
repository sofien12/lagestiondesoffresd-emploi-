package tn.com.pfe.view;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.primefaces.model.DefaultStreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import tn.com.pfe.domain.*;
import tn.com.pfe.service.PostulantService;
import utils.JSFUtils;

@Component("postulantBean")
@Scope("view")
public class PostulantBean 
{
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private PostulantService postulantService;
	private List<Postulant> PostulantfindALL;
	private List<Candidature> candidat;
	private List<Postulant> listPostulant;
	private List<Postulant> listPostulantEntretien;
	private List<Postulant> listPostulantevaluerEntretien;
	private List<Postulant> filteredListPostulantevaluerEntretien;
	private List<Postulant> filteredListPostulant;
	private List<Postulant> filteredListPostulantEntretien;
	private CandidatBean Candida;
	private entretienBean entretie;
	private List<Entretien> entretien;
	private Postulant postulant;
	private Postulant pMaj;
	private Postulant pEmail; 
	private Postulant pR;
	private DefaultStreamedContent image;
	

	public PostulantBean()
	{
		
	}
	
	@PostConstruct
	public void initail()
	{
		try {
			listPostulant=new ArrayList<Postulant> ();
			filteredListPostulant=new ArrayList<Postulant> ();
			
			filteredListPostulantEntretien=new ArrayList<Postulant>();
			listPostulantEntretien=new ArrayList<Postulant> ();
			
			filteredListPostulantevaluerEntretien=new ArrayList<Postulant>();
			listPostulantevaluerEntretien=new  ArrayList<Postulant> ();
			
			postulant=new Postulant();
			
			pMaj=new Postulant ();
			pEmail=new Postulant();
			
			Candida=(CandidatBean) JSFUtils.getExpressionValue("#{CandidatBean}");
			entretie=(entretienBean) JSFUtils.getExpressionValue("#{entretienBean}");
			PostulantfindALL=postulantService.findAll();
				for(int i=0; i<PostulantfindALL.size();i++)
				{
					candidat=Candida.ListC(PostulantfindALL.get(i).getIdP());
					if(candidat.isEmpty()== false)
					{
						listPostulant.add(PostulantfindALL.get(i));
						
					
					}
					
				}
				
				filteredListPostulant=new ArrayList<Postulant> (listPostulant);
				
				for(int i=0; i<PostulantfindALL.size();i++)
				{
					entretien=entretie.ListC(PostulantfindALL.get(i).getIdP());
					if(entretien.isEmpty()== false)
					{
						listPostulantEntretien.add(PostulantfindALL.get(i));
					
					}
				}
				filteredListPostulantEntretien=new ArrayList<Postulant> (listPostulantEntretien); 
				
				for(int i=0; i<PostulantfindALL.size();i++)
				{
					entretien=entretie.ListE(PostulantfindALL.get(i).getIdP());
					if(entretien.isEmpty()== false)
					{
						listPostulantevaluerEntretien.add(PostulantfindALL.get(i));
					
					}
				}
				filteredListPostulantevaluerEntretien=new ArrayList<Postulant> (listPostulantevaluerEntretien); 
			
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
		}
	}
	public String CinPostulant  (Integer id)
	{
		pMaj=	postulantService.findById(id);
		return pMaj.getCin();
	}
	public String EmailPostulant (Integer id)
	{
		
		pEmail=	postulantService.findById(id);
		return pEmail.getEmail();
	}
	public Postulant recherchePostulantMaj(Integer id)
	{
		pR= postulantService.findById(id);
		return pR;
	}
	

	public DefaultStreamedContent getImage() {
		/*InputStream is = new ByteArrayInputStream( postulant.getPhotoIdentité());
		DefaultStreamedContent myImage = new DefaultStreamedContent(is, "image/png");
		is.close();*/
		return image;
		}

	
	public void setImage(DefaultStreamedContent image) {
		this.image = image;
	}


	public List<Postulant> getFilteredListPostulant() {
		return filteredListPostulant;
	}

	public void setFilteredListPostulant(List<Postulant> filteredListPostulant) {
		this.filteredListPostulant = filteredListPostulant;
	}

	public List<Postulant> getPostulantfindALL() {
		return PostulantfindALL;
	}

	public void setPostulantfindALL(List<Postulant> postulantfindALL) {
		PostulantfindALL = postulantfindALL;
	}

	public List<Candidature> getCandidat() {
		return candidat;
	}

	public void setCandidat(List<Candidature> candidat) {
		this.candidat = candidat;
	}

	public List<Postulant> getListPostulant() {
		return listPostulant;
	}

	public void setListPostulant(List<Postulant> listPostulant) {
		this.listPostulant = listPostulant;
	}

	public CandidatBean getCandida() {
		return Candida;
	}

	public void setCandida(CandidatBean candida) {
		Candida = candida;
	}

	public Postulant getPostulant() {
		return postulant;
	}

	public void setPostulant(Postulant postulant) {
		this.postulant = postulant;
	}

	public List<Postulant> getFilteredListPostulantEntretien() {
		return filteredListPostulantEntretien;
	}

	public void setFilteredListPostulantEntretien(
			List<Postulant> filteredListPostulantEntretien) {
		this.filteredListPostulantEntretien = filteredListPostulantEntretien;
	}

	public List<Postulant> getListPostulantEntretien() {
		return listPostulantEntretien;
	}

	public void setListPostulantEntretien(List<Postulant> listPostulantEntretien) {
		this.listPostulantEntretien = listPostulantEntretien;
	}

	public List<Postulant> getListPostulantevaluerEntretien() {
		return listPostulantevaluerEntretien;
	}

	public void setListPostulantevaluerEntretien(
			List<Postulant> listPostulantevaluerEntretien) {
		this.listPostulantevaluerEntretien = listPostulantevaluerEntretien;
	}

	public List<Postulant> getFilteredListPostulantevaluerEntretien() {
		return filteredListPostulantevaluerEntretien;
	}

	public void setFilteredListPostulantevaluerEntretien(
			List<Postulant> filteredListPostulantevaluerEntretien) {
		this.filteredListPostulantevaluerEntretien = filteredListPostulantevaluerEntretien;
	}

	public Postulant getpR() {
		return pR;
	}

	public void setpR(Postulant pR) {
		this.pR = pR;
	}
	
	
}
