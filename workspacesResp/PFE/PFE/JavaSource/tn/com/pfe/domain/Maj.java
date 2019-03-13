package tn.com.pfe.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Maj entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="MAJ")
public class Maj implements java.io.Serializable {

	// Fields

	private Integer idmaj;
	private Integer postulant;
	private String position;
	private String grade;
	private String echelle;
	private Date dateP;
	private String provisoire;
	private Date datedebut;
	private Date dateFinal;
	// Constructors

	/** default constructor */
	public Maj() {
	}

	/** minimal constructor */
	public Maj(Integer idmaj) {
		this.idmaj = idmaj;
	}

	/** full constructor */
	public Maj(Integer idmaj, Integer postulant) {
		this.idmaj = idmaj;
		this.postulant = postulant;
	}

	// Property accessors
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator = "SEQUENCE")
    @SequenceGenerator(name="SEQUENCE", sequenceName = "SEQ_MAJ")
    @Column(name="idmaj")
	public Integer getIdmaj() {
		return this.idmaj;
	}

	public void setIdmaj(Integer idmaj) {
		this.idmaj = idmaj;
	}
	
	@Column(name="idpost")
	public Integer getPostulant() {
		return this.postulant;
	}

	public void setPostulant(Integer postulant) {
		this.postulant = postulant;
	}
	@Column(name ="position")
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
 @Column(name="grade")
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
	@Column(name="echelle")
	public String getEchelle() {
		return echelle;
	}

	public void setEchelle(String echelle) {
		this.echelle = echelle;
	}
@Column(name="datepecuniaire")
	public Date getDateP() {
		return dateP;
	}

	public void setDateP(Date dateP) {
		this.dateP = dateP;
	}
@Column( name="PROVISOIRE")
	public String getProvisoire() {
		return provisoire;
	}

	public void setProvisoire(String provisoire) {
		this.provisoire = provisoire;
	}
@Column(name="datedebut")
	public Date getDatedebut() {
		return datedebut;
	}

	public void setDatedebut(Date datedebut) {
		this.datedebut = datedebut;
	}
@Column(name="datefinal")
	public Date getDateFinal() {
		return dateFinal;
	}

	public void setDateFinal(Date dateFinal) {
		this.dateFinal = dateFinal;
	}
	
	
}