package tn.com.pfe.domain;

import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * Postulant entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="Postulant")
public class Postulant  implements java.io.Serializable {


    // Fields    

     private Integer idP;
     private String cin;
     private String nom;
     private String prenom;
     private String email;
     private String pwd;
     private String adresseEmail;
     private String adresse;
     private Integer codePostal;
     private Date dateNaissance;
     private String pays;
     private String nationalité;
     private String genre;
     private String etatCivil;
     
     private Integer tel;
     private String ville ;
     private byte[] photoCarteIdentité;
     private byte[] photoIdentité;
    


    // Constructors

    /** default constructor */
    public Postulant() {
    }
   
	/** minimal constructor */
    public Postulant(Integer idP, String cin, String nom, String prenom, String email, String pwd, String adresseEmail, String adresse, Integer codePostal, Date dateNaissance, String pays, String nationalité, String genre, byte[] photoCarteIdentité, byte[] photoIdentité) {
        this.idP = idP;
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.pwd = pwd;
        this.adresseEmail = adresseEmail;
        this.adresse = adresse;
        this.codePostal = codePostal;
        this.dateNaissance = dateNaissance;
        this.pays = pays;
        this.nationalité = nationalité;
        this.genre = genre;
        this.photoCarteIdentité = photoCarteIdentité;
        this.photoIdentité = photoIdentité;
    }
    
   

   
    // Property accessors
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator = "SEQUENCE")
    @SequenceGenerator(name="SEQUENCE", sequenceName = "SEQ_POS")
    @Column(name="Id_P", unique=true)
    public Integer getIdP() {
        return this.idP;
    }
    
    public void setIdP(Integer idP) {
        this.idP = idP;
    }
    @Column(name="CIN" , unique=true , nullable=false  )
    public String getCin() {
        return this.cin;
    }
    
    public void setCin(String cin) {
        this.cin = cin;
    }
    @Column(name="Nom")
    public String getNom() {
        return this.nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    @Column(name="Prenom" )
    public String getPrenom() {
        return this.prenom;
    }
    
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    @Column(name="email" )
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    @Column(name="PWD" , unique=true )
    public String getPwd() {
        return this.pwd;
    }
    
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    @Column(name="Adresse_Email" )
    public String getAdresseEmail() {
        return this.adresseEmail;
    }
    
    public void setAdresseEmail(String adresseEmail) {
        this.adresseEmail = adresseEmail;
    }
    @Column(name="Adresse ")
    public String getAdresse() {
        return this.adresse;
    }
    
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    @Column(name="Code_Postal"  )
    public Integer getCodePostal() {
        return this.codePostal;
    }
    
    public void setCodePostal(Integer codePostal) {
        this.codePostal = codePostal;
    }
    @Column(name="Date_Naissance")
    public Date getDateNaissance() {
        return this.dateNaissance;
    }
    
    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
@Column(name="Pays")
    public String getPays() {
        return this.pays;
    }
    
    public void setPays(String pays) {
        this.pays = pays;
    }
    @Column(name="nationalité" )
    public String getNationalité() {
        return this.nationalité;
    }
    
    public void setNationalité(String nationalité) {
        this.nationalité = nationalité;
    }
@Column(name="Genre" )
    public String getGenre() {
        return this.genre;
    }
    
    public void setGenre(String genre) {
        this.genre = genre;
    }
    @Column(name="Etat_Civil" )
    public String getEtatCivil() {
        return this.etatCivil;
    }
    
    public void setEtatCivil(String etatCivil) {
        this.etatCivil = etatCivil;
    }
    @Column(name="tel", nullable=true)
    public Integer getTel() {
        return this.tel;
    }
    
    public void setTel(Integer tel) {
        this.tel = tel;
    }
    @Column(name="Photo_Carte_Identité")
    @Lob
    public byte[] getPhotoCarteIdentité() {
        return this.photoCarteIdentité;
    }
    
    public void setPhotoCarteIdentité(byte[] photoCarteIdentité) {
        this.photoCarteIdentité = photoCarteIdentité;
    }
   
    
  

	@Column(name="Photo_Identité" )
    @Lob
    public byte[] getPhotoIdentité() {
        return this.photoIdentité;
    }
    
    public void setPhotoIdentité(byte[] photoIdentité) {
        this.photoIdentité = photoIdentité;
    }
@Column(name="ville")
	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

  


}