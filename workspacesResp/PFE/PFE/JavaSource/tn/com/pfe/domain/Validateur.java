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
 * Validateur entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table
public class Validateur implements java.io.Serializable {

	// Fields

	private Integer idProc;
	private Integer createur;
	private Integer offremploi;
	private String obesevation;
	private String  valider;
	private Date dateP;

	// Constructors

	/** default constructor */
	public Validateur() {
	}

	/** minimal constructor */
	public Validateur(Integer idProc) {
		this.idProc = idProc;
	}

	/** full constructor */
	public Validateur(Integer idProc, Integer createur,
			Integer offremploi, String obesevation, String valider,
			Date dateP) {
		this.idProc = idProc;
		this.createur = createur;
		this.offremploi = offremploi;
		this.obesevation = obesevation;
		this.valider = valider;
		this.dateP = dateP;
	}

	// Property accessors
	@Id
	 @GeneratedValue (strategy=GenerationType.AUTO, generator = "SEQUENCE")
	    @SequenceGenerator(name="SEQUENCE", sequenceName = "SEQ_offre")
	    @Column(name="Id_Proc", unique=true, nullable=false)
	public Integer getIdProc() {
		return this.idProc;
	}

	public void setIdProc(Integer idProc) {
		this.idProc = idProc;
	}
@Column(name="Id_C")
	public Integer getCreateur() {
		return this.createur;
	}

	public void setCreateur(Integer createur) {
		this.createur = createur;
	}
@Column(name="Id_off")
	public Integer getOffremploi() {
		return this.offremploi;
	}

	public void setOffremploi(Integer offremploi) {
		this.offremploi = offremploi;
	}
@Column(name="Obesevation")
	public String getObesevation() {
		return this.obesevation;
	}

	public void setObesevation(String obesevation) {
		this.obesevation = obesevation;
	}
@Column(name="Valider")
	public String getValider() {
		return this.valider;
	}

	public void setValider(String valider) {
		this.valider = valider;
	}
@Column(name="Date_P")
	public Date getDateP() {
		return this.dateP;
	}

	public void setDateP(Date dateP) {
		this.dateP = dateP;
	}

}