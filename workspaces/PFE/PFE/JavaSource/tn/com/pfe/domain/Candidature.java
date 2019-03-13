package tn.com.pfe.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * Candidature entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="Candidature")
public class Candidature  implements java.io.Serializable {


    // Fields    

     private Integer idCandidature;
     private Integer offremploi;
     private Integer postulant;
     private String experience;
     private String niveauEtude;
     private String missionPost;
     private Date dateenregis;
     private String idC;
     private String etatoffre;


    // Constructors

    /** default constructor */
    public Candidature() {
    }

	/** minimal constructor */
    
    
    public Candidature(Integer idCandidature) {
        this.idCandidature = idCandidature;
    }
    
    /** full constructor */
  
    // Property accessors
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator = "SEQUENCE")
    @SequenceGenerator(name="SEQUENCE", sequenceName = "SEQ_CANDID")
    @Column(name="ID_CANDIDATURE", unique=true, nullable=false)
    public Integer getIdCandidature() {
        return this.idCandidature;
    }
    
    public void setIdCandidature(Integer idCandidature) {
        this.idCandidature = idCandidature;
    }

    @Column(name="IDOFF")
    public Integer getOffremploi() {
        return this.offremploi;
    }
    
    public void setOffremploi(Integer offremploi) {
        this.offremploi = offremploi;
    }
@Column(name="IDPOST")
    public Integer getPostulant() {
        return this.postulant;
    }
    
    public void setPostulant(Integer postulant) {
        this.postulant = postulant;
    }
@Column(name="EXPERIENCE")
    public String getExperience() {
        return this.experience;
    }
    
    public void setExperience(String experience) {
        this.experience = experience;
    }
    @Column(name="NIVEAU_ETUDE")
    public String getNiveauEtude() {
        return this.niveauEtude;
    }
    
    public void setNiveauEtude(String niveauEtude) {
        this.niveauEtude = niveauEtude;
    }
    @Column(name="MISSION_POST")
    public String getMissionPost() {
        return this.missionPost;
    }
    
    public void setMissionPost(String missionPost) {
        this.missionPost = missionPost;
    }
 @Column(name="DATEENREGIS")
    public Date getDateenregis() {
        return this.dateenregis;
    }
    
    public void setDateenregis(Date dateenregis) {
        this.dateenregis = dateenregis;
    }
@Column(name="idch")
	public String getIdC() {
		return idC;
	}

	public void setIdC(String idC) {
		this.idC = idC;
	}
@Column(name="etatoffre")
	public String getEtatoffre() {
		return etatoffre;
	}

	public void setEtatoffre(String etatoffre) {
		this.etatoffre = etatoffre;
	}
   




}