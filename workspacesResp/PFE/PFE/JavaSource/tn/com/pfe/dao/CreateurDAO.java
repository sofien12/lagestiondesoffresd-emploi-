package tn.com.pfe.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tn.com.pfe.domain.Createur;

/**
 	* A data access object (DAO) providing persistence and search support for Createur entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see tn.com.pfe.domain.Createur
  * @author MyEclipse Persistence Tools 
 */

public class CreateurDAO extends CommonDAO {
	     private static final Logger log = LoggerFactory.getLogger(CreateurDAO.class);
		//property constants
	public static final String EMAIL = "email";
	public static final String PWD = "pwd";
	public static final String NOM = "nom";
	public static final String PRENOM = "prenom";
	public static final String TYPEC = "typec";
	public static final String CIN = "cin";



    
    public void save(Createur transientInstance) {
        log.debug("saving Createur instance");
        try {
            getCurrentSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Createur persistentInstance) {
        log.debug("deleting Createur instance");
        try {
            getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Createur findById( Integer id) {
        log.debug("getting Createur instance with id: " + id);
        try {
            Createur instance = (Createur) getCurrentSession()
                    .get("tn.com.pfe.domain.Createur", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Createur instance) {
        log.debug("finding Createur instance by example");
        try {
            List results = getCurrentSession()
                    .createCriteria("tn.com.pfe.domain.Createur")
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
      log.debug("finding Createur instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Createur as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getCurrentSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByEmail(Object email
	) {
		return findByProperty(EMAIL, email
		);
	}
	
	public List findByPwd(Object pwd
	) {
		return findByProperty(PWD, pwd
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
	
	public List findByTypec(Object typec
	) {
		return findByProperty(TYPEC, typec
		);
	}
	
	public List findByCin(Object cin
	) {
		return findByProperty(CIN, cin
		);
	}
	

	public List findAll() {
		log.debug("finding all Createur instances");
		try {
			String queryString = "from Createur";
	         Query queryObject = getCurrentSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Createur merge(Createur detachedInstance) {
        log.debug("merging Createur instance");
        try {
            Createur result = (Createur) getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Createur instance) {
        log.debug("attaching dirty Createur instance");
        try {
            getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Createur instance) {
        log.debug("attaching clean Createur instance");
        try {
            getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}