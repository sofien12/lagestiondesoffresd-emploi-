package tn.com.pfe.dao;

import java.sql.Timestamp;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tn.com.pfe.domain.Entretien;

/**
 	* A data access object (DAO) providing persistence and search support for Entretien entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see tn.com.pfe.domain.Entretien
  * @author MyEclipse Persistence Tools 
 */

public class EntretienDAO extends CommonDAO  {
	     private static final Logger log = LoggerFactory.getLogger(EntretienDAO.class);
		//property constants
	public static final String AVIS = "avis";
	public static final String HEURE = "heure";
	public static final String PRECENCE = "precence";



    
    public void save(Entretien transientInstance) {
        log.debug("saving Entretien instance");
        try {
            getCurrentSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Entretien persistentInstance) {
        log.debug("deleting Entretien instance");
        try {
            getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Entretien findById( Integer id) {
        log.debug("getting Entretien instance with id: " + id);
        try {
            Entretien instance = (Entretien) getCurrentSession()
                    .get("tn.com.pfe.domain.Entretien", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Entretien instance) {
        log.debug("finding Entretien instance by example");
        try {
            List results = getCurrentSession()
                    .createCriteria("tn.com.pfe.domain.Entretien")
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
      log.debug("finding Entretien instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Entretien as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getCurrentSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

    public List findByPropertyPost(String propertyp, String propertyC, Integer idp , String ch)
    {
    	log.debug("finding Entretien instance with property: " + propertyp
                + ", value: " + idp);
          try {
             String queryString = "from Entretien as model where model." 
             						+ propertyp + "= ? AND "+propertyC+ "= ?" ;
             Query queryObject = getCurrentSession().createQuery(queryString);
    		 queryObject.setParameter(0, idp);
    		 queryObject.setParameter(1, ch);
    		 return queryObject.list();
          } catch (RuntimeException re) {
             log.error("find by property name failed", re);
             throw re;
          }
    }
    public List findByPropertyRest(String propertyp, String propertyC, Integer idp , String ch)
    {
    	log.debug("finding Entretien instance with property: " + propertyp
                + ", value: " + idp);
          try {
             String queryString = "from Entretien as model where model." 
             						+ propertyp + "= ? AND "+propertyC+ "= ?" ;
             Query queryObject = getCurrentSession().createQuery(queryString);
    		 queryObject.setParameter(0, idp);
    		 queryObject.setParameter(1, ch);
    		 return queryObject.list();
          } catch (RuntimeException re) {
             log.error("find by property name failed", re);
             throw re;
          }
    }
    
    
	public List findByAvis(Object avis
	) {
		return findByProperty(AVIS, avis
		);
	}
	
	public List findByHeure(Object heure
	) {
		return findByProperty(HEURE, heure
		);
	}
	
	public List findByPrecence(Object precence
	) {
		return findByProperty(PRECENCE, precence
		);
	}
	

	public List findAll() {
		log.debug("finding all Entretien instances");
		try {
			String queryString = "from Entretien";
	         Query queryObject = getCurrentSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Entretien merge(Entretien detachedInstance) {
        log.debug("merging Entretien instance");
        try {
            Entretien result = (Entretien) getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Entretien instance) {
        log.debug("attaching dirty Entretien instance");
        try {
            getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Entretien instance) {
        log.debug("attaching clean Entretien instance");
        try {
            getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}