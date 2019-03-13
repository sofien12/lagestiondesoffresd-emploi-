package tn.com.pfe.dao;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tn.com.pfe.domain.Postulant;

/**
 	* A data access object (DAO) providing persistence and search support for Postulant entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see tn.com.pfe.domain.Postulant
  * @author MyEclipse Persistence Tools 
 */

public class PostulantDAO extends CommonDAO  {
	     private static final Logger log = LoggerFactory.getLogger(PostulantDAO.class);
		//property constants
	public static final String CIN = "cin";
	public static final String NOM = "nom";
	public static final String PRENOM = "prenom";
	public static final String EMAIL = "email";
	public static final String PWD = "pwd";
	public static final String ADRESSE_EMAIL = "adresseEmail";
	public static final String ADRESSE = "adresse";
	public static final String PAYS = "pays";
	public static final String NATIONALITÉ = "nationalité";
	public static final String GENRE = "genre";
	public static final String ETAT_CIVIL = "etatCivil";
	public static final String TEL = "tel";
	public static final String PHOTO_CARTE_IDENTITÉ = "photoCarteIdentité";
	public static final String PHOTO_IDENTITÉ = "photoIdentité";



    
    public void save(Postulant transientInstance) {
        log.debug("saving Postulant instance");
        try {
            getCurrentSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Postulant persistentInstance) {
        log.debug("deleting Postulant instance");
        try {
            getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Postulant findById( Integer id) {
        log.debug("getting Postulant instance with id: " + id);
        try {
            Postulant instance = (Postulant) getCurrentSession()
                    .get("tn.com.pfe.domain.Postulant", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Postulant instance) {
        log.debug("finding Postulant instance by example");
        try {
            List results = getCurrentSession()
                    .createCriteria("tn.com.pfe.domain.Postulant")
                    .add(Example.create(instance))
            .list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding Postulant instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Postulant as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getCurrentSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByCin(Object cin
	) {
		return findByProperty(CIN, cin
		);
	}
	
	public List findByNom(Object nom
	) {
		return findByProperty(NOM, nom
		);
	}
	
	public List findByPrenom(Object prenom
	) {
		return findByProperty(PRENOM, prenom
		);
	}
	
	public List findByEmail(Object email
	) {
		return findByProperty(EMAIL, email);
	}
	
	public List findByPwd(Object pwd
	) {
		return findByProperty(PWD, pwd);
	}
	
	public List findByAdresseEmail(Object adresseEmail
	) {
		return findByProperty(ADRESSE_EMAIL, adresseEmail
		);
	}
	
	public List findByAdresse(Object adresse
	) {
		return findByProperty(ADRESSE, adresse
		);
	}
	
	public List findByPays(Object pays
	) {
		return findByProperty(PAYS, pays
		);
	}
	
	public List findByNationalité(Object nationalité
	) {
		return findByProperty(NATIONALITÉ, nationalité
		);
	}
	
	public List findByGenre(Object genre
	) {
		return findByProperty(GENRE, genre
		);
	}
	
	public List findByEtatCivil(Object etatCivil
	) {
		return findByProperty(ETAT_CIVIL, etatCivil
		);
	}
	
	public List findByTel(Object tel
	) {
		return findByProperty(TEL, tel
		);
	}
	
	public List findByPhotoCarteIdentité(Object photoCarteIdentité
	) {
		return findByProperty(PHOTO_CARTE_IDENTITÉ, photoCarteIdentité
		);
	}
	
	public List findByPhotoIdentité(Object photoIdentité
	) {
		return findByProperty(PHOTO_IDENTITÉ, photoIdentité
		);
	}
	

	public List findAll() {
		log.debug("finding all Postulant instances");
		try {
			String queryString = "from Postulant";
	         Query queryObject = getCurrentSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Postulant merge(Postulant detachedInstance) {
        log.debug("merging Postulant instance");
        try {
            Postulant result = (Postulant) getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Postulant instance) {
        log.debug("attaching dirty Postulant instance");
        try {
            getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Postulant instance) {
        log.debug("attaching clean Postulant instance");
        try {
            getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}