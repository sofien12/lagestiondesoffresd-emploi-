package tn.com.pfe.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.*;

/**
 * Offremploi entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="offremploi")
public class Offremploi implements java.io.Serializable {

	// Fields

	private Integer idOffre;
	private Integer createur;
	private String role;
	private String niveauEtude;
	private String minimumExper;
	private String mission;
	private String typeOffre;
	private String region;
	private String localité;
	private Integer suspendu;
	private Date dateDebut;
	private Date dateFinal;
	private Date dateCloture;
	private String idS;
	private String idR;
	private String idCp;

	

	// Constructors

	/** default constructor */
	public Offremploi() {
	}

	/** minimal constructor */
	public Offremploi(Integer idOffre) {
		this.idOffre = idOffre;
	}

	/** full constructor */
	
	// Property accessors

	@Id
	 @GeneratedValue (strategy=GenerationType.AUTO, generator = "SEQUENCE")
	    @SequenceGenerator(name="SEQUENCE", sequenceName = "SEQ_offre")
	    @Column(name="Id_offre", unique=true, nullable=false)
	public Integer getIdOffre() {
		return this.idOffre;
	}

	public void setIdOffre(Integer idOffre) {
		this.idOffre = idOffre;
	}

	@Column(name="Id_C" , unique=true, nullable=false)
	
   public Integer getCreateur() {
		return this.createur;
	}

	public void setCreateur(Integer createur) {
		this.createur = createur;
	}
	@Column(name="Role")
	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}
@Column(name="niveau_etude")
	public String getNiveauEtude() {
		return this.niveauEtude;
	}

	public void setNiveauEtude(String niveauEtude) {
		this.niveauEtude = niveauEtude;
	}
@Column(name="minimum_exper")
	public String getMinimumExper() {
		return this.minimumExper;
	}

	public void setMinimumExper(String minimumExper) {
		this.minimumExper = minimumExper;
	}
@Column(name="mission")
	public String getMission() {
		return this.mission;
	}

	public void setMission(String mission) {
		this.mission = mission;
	}
@Column( name="type_offre")
	public String getTypeOffre() {
		return this.typeOffre;
	}

	public void setTypeOffre(String typeOffre) {
		this.typeOffre = typeOffre;
	}
@Column(name="region")
	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}
@Column(name="localité")
	public String getLocalité() {
		return this.localité;
	}

	public void setLocalité(String localité) {
		this.localité = localité;
	}
@Column(name="suspendu")
	public Integer getSuspendu() {
		return this.suspendu;
	}

	public void setSuspendu(Integer suspendu) {
		this.suspendu = suspendu;
	}
	
	@Column (name="Date_cloture")
public Date getDatCloture() {
		return dateCloture;
	}

	public void setDatCloture(Date datCloture) {
		this.dateCloture = datCloture;
	}

@Column(name="date_Debut")
	public Date getDateDebut() {
		return this.dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
@Column(name="Date_final")
	public Date getDateFinal() {
		return this.dateFinal;
	}

	public void setDateFinal(Date dateFinal) {
		this.dateFinal = dateFinal;
	}
	@Column(name="id_S")
	public String getIdS() {
		return idS;
	}

	public void setIdS(String idS) {
		this.idS = idS;
	}
@Column(name="id_R")
	public String getIdR() {
		return idR;
	}

	public void setIdR(String idR) {
		this.idR = idR;
	}
@Column(name="Id_cp")
	public String getIdCp() {
		return idCp;
	}

	public void setIdCp(String idCp) {
		this.idCp = idCp;
	}


}