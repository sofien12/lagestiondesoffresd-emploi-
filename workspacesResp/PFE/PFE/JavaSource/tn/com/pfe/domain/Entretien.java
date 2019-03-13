package tn.com.pfe.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.*;


/**
 * Entretien entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="entretien")
public class Entretien  implements java.io.Serializable {


    // Fields    

     private Integer identretien;
     private Integer postulant;
     private String avis;
     private Date datentretie;
     private Date heure;
     private String precence;
     private String idE;

    // Constructors

    /** default constructor */
    public Entretien() {
    }

	/** minimal constructor */
    

   
    // Property accessors
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator = "SEQUENCE")
    @SequenceGenerator(name="SEQUENCE", sequenceName = "SEQ_Entre")
    @Column(name="identretien")
    public Integer getIdentretien() {
        return this.identretien;
    }
    
    public void setIdentretien(Integer identretien) {
        this.identretien = identretien;
    }
@Column(name="IDPOST")
    public Integer getPostulant() {
        return this.postulant;
    }
    
    public void setPostulant(Integer postulant) {
        this.postulant = postulant;
    }
@Column(name="avis")
    public String getAvis() {
        return this.avis;
    }
    
    public void setAvis(String avis) {
        this.avis = avis;
    }
@Column(name="datentretie")
    public Date getDatentretie() {
        return this.datentretie;
    }
    
    public void setDatentretie(Date datentretie) {
        this.datentretie = datentretie;
    }
@Column(name="heure")
    public Date getHeure() {
        return this.heure;
    }
    
    public void setHeure(Date heure) {
        this.heure = heure;
    }
@Column(name="precence")
    public String getPrecence() {
        return this.precence;
    }
    
    public void setPrecence(String precence) {
        this.precence = precence;
    }
@Column(name="ide")
	public String getIdE() {
		return idE;
	}

	public void setIdE(String idE) {
		this.idE = idE;
	}
   





}