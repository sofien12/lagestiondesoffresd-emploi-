package tn.com.pfe.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


/**
 * Createur entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="Createur")
public class Createur  implements java.io.Serializable {


    // Fields    

     private Integer idR;
     private String email;
     private String pwd;
     private String nom;
     private String prenom;
     private String typec;
     private String cin;


    // Constructors

    /** default constructor */
    public Createur() {
    }

	/** minimal constructor */
 

   
    // Property accessors
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="Id_R")
    public Integer getIdR() {
        return this.idR;
    }
    
    public void setIdR(Integer idR) {
        this.idR = idR;
    }
    @Column(name="Email")
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    @Column(name="pwd")
    public String getPwd() {
        return this.pwd;
    }
    
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    @Column(name="Nom")
    public String getNom() {
        return this.nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    @Column(name="Prenom")
    public String getPrenom() {
        return this.prenom;
    }
    
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    @Column(name="typec")
    public String getTypec() {
        return this.typec;
    }
    
    public void setTypec(String typec) {
        this.typec = typec;
    }
    @Column(name="Cin")
    public String getCin() {
        return this.cin;
    }
    
    public void setCin(String cin) {
        this.cin = cin;
    }
   








}