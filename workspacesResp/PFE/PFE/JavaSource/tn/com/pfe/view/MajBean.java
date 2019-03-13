package tn.com.pfe.view;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import tn.com.pfe.domain.Maj;
import tn.com.pfe.domain.Postulant;
import tn.com.pfe.service.MAJService;
import utils.JSFUtils;

@Component("MajBean")
@Scope("view")
public class MajBean 
{
	@Autowired
	private MAJService majservice;
	private Maj maj;
	private PostulantBean pb;
	private Postulant postulat;  
	private String   idpost;
	private Map<String,Integer> Mappostulant ;
	private List<Maj> Listmaj;
	private StreamedContent file;
	private String test;
	
	public MajBean()
	{
	}
	
	@PostConstruct
	public void initial ()
	{
		try {
			test="N";
			Mappostulant= new HashMap<String, Integer>();
			pb=(PostulantBean)JSFUtils.getExpressionValue("#{postulantBean}");
			Listmaj=majservice.findall();
			for (int i=0; i<Listmaj.size();i++)
			{
				String Cin=pb.CinPostulant (Listmaj.get(i).getPostulant());
				Mappostulant.put(Cin, Listmaj.get(i).getPostulant());
			
			}
		
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void rechercheP()
	{
		
		
			if (idpost == null)
				{
				System.out.println("choisir un candidat");
				postulat=new Postulant();
				}
			else
			{
			test="V";	
			Integer id=Integer.parseInt(idpost);
			postulat=pb.recherchePostulantMaj(id);
			System.out.println(this.postulat.getNom());
			}
		
	}
	
	
	public void  updateMaj()
	{RequestContext context = RequestContext.getCurrentInstance();
		Integer id=Integer.parseInt(idpost);
		maj.setPostulant(id);
		 List<Maj> ls=majservice.MajPost(id);

		try {
			 majservice.update(maj);
			 
			 System.out.println(ls.size());
			 postulat=new Postulant();
			 idpost=null;
				for(int i=0; i<ls.size();i++)
				{
					if(ls.get(i).getPosition()==null)
						majservice.delete(ls.get(i));
				}
				JSFUtils.addSuccessMessage("Info :", "Mise a jour administrative a été Enregistre avec succès !", "addMAJ");
				context.addCallbackParam("loggedIn", true);
	} catch (Exception e) {
			// TODO: handle exception
		context.addCallbackParam("loggedIn", false);
		}
		
	}
public void inialMAJ()
{
	RequestContext context = RequestContext.getCurrentInstance();
	maj=new Maj();
	if(idpost == null)
		 {
		System.out.println(idpost);
			context.addCallbackParam("loggedIn", false);
			  JSFUtils.addErrorMessage("Erreur :", "choisir Votre candidat", "formf");

		 }
	else
		 context.addCallbackParam("loggedIn", true);
}
	public void create(Integer IdPost )
	{
		maj=new Maj();
		try {
			maj.setPostulant(IdPost);
			majservice.Create(maj);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("sofien MAJ");
		}
	}
	
	public Postulant getPostulat() {
		return postulat;
	}

	public void setPostulat(Postulant postulat) {
		this.postulat = postulat;
	}

	public Map<String,Integer> getPostulant() {
		return Mappostulant;
	}

	public void setPostulant(Map<String,Integer > postulant) {
		this.Mappostulant = postulant;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public String getIdpost() {
		return idpost;
	}

	public void setIdpost(String idpost) {
		this.idpost = idpost;
	}

	public Maj getMaj() {
		return maj;
	}

	public void setMaj(Maj maj) {
		this.maj = maj;
	}
	
	
}
