package tn.com.pfe.view;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;


import org.apache.commons.io.output.ByteArrayOutputStream;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfWriter;

import tn.com.pfe.domain.*;
import tn.com.pfe.service.CvService;
import utils.JSFUtils;

@Component("CvBean")
@Scope("view")
public class CvBean 

{   @Autowired
	private CvService cvService;
	private List <Cv> listeducation;
	private List <Cv> listFormation;
	private List <Cv> filteredListFormation;
	private List <Cv> listExperience;
	private List <Cv> filteredListExperience;
	private List<Cv> filteredListEducation;
	private PostulantBean postBean;
	private StreamedContent file;
	private DefaultStreamedContent image;
	public CvBean ()
	{
		
	}
	@PostConstruct
	public void initail()
	{
		try {
			filteredListEducation=new ArrayList<Cv>();
			filteredListExperience=new ArrayList<Cv>();
			filteredListFormation=new ArrayList<Cv>();
			postBean=(PostulantBean) JSFUtils.getExpressionValue("#{postulantBean}");
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
		}
	}
	
	public void ListCv(Postulant id) throws IOException
	{
		
		InputStream is = new ByteArrayInputStream(id.getPhotoIdentité());
		DefaultStreamedContent myImage = new DefaultStreamedContent(is, "image/png");
	   this.setImage(myImage);
		
		
		listeducation=cvService.findByEducation(id.getCin());
		filteredListEducation=new ArrayList<Cv>(listeducation);
		
		listFormation=cvService.findByFormation(id.getCin());
		filteredListFormation=new ArrayList<Cv>(listFormation);
		
		
		listExperience=cvService.findByExperience(id.getCin());
		filteredListExperience=new ArrayList<Cv>(listExperience);
		is.close();
	}
	public DefaultStreamedContent getImage() 
	{
		
		System.out.println("rabnoifuyyduhijdjfjkslmqù: "+ image);
		return image;
		}

	
	public void setImage(DefaultStreamedContent image) {
		
		this.image = image;
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
	
	public List<Cv> getListeducation() {
		return listeducation;
	}
	public void setListeducation(List<Cv> listeducation) {
		this.listeducation = listeducation;
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
	public List<Cv> getFilteredListEducation() {
		return filteredListEducation;
	}
	public void setFilteredListEducation(List<Cv> filteredListEducation) {
		this.filteredListEducation = filteredListEducation;
	}
	public StreamedContent getFile() {
		return file;
	}
	public void setFile(StreamedContent file) {
		this.file = file;
	}
	

}
