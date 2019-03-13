package tn.com.pfe.dao;

import java.sql.Timestamp;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tn.com.pfe.domain.Candidature;

/**
 	* A data access object (DAO) providing persistence and search support for Candidature entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see tn.com.pfe.domain.Candidature
  * @author MyEclipse Persistence Tools 
 */

public class CandidatureDAO extends CommonDAO  {
	     private static final Logger log = LoggerFactory.getLogger(CandidatureDAO.class);
		//property constants
	public static final String EXPERIENCE = "experience";
	public static final String NIVEAU_ETUDE = "niveauEtude";
	public static final String MISSION_POST = "missionPost";



    
    public void save(Candidature transientInstance) {
        log.debug("saving Candidature instance");
        try {
            getCurrentSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Candidature persistentInstance) {
        log.debug("deleting Candidature instance");
        try {
            getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Candidature findById( Integer id) {
        log.debug("getting Candidature instance with id: " + id);
        try {
            Candidature instance = (Candidature) getCurrentSession().get("tn.com.pfe.domain.Candidature", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Candidature instance) {
        log.debug("finding Candidature instance by example");
        try {
            List results = getCurrentSession()
                    .createCriteria("tn.com.pfe.domain.Candidature")
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
      log.debug("finding Candidature instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Candidature as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getCurrentSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}
    public List findByPropertyPost(String propertyp, String propertyC, Integer idp , String ch)//return la liste de postulant qui n'a pas selectionner par CH
    {
    	log.debug("finding Candidature instance with property: " + propertyp
                + ", value: " + idp);
          try {
             String queryString = "from Candidature as model where model." 
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
    public List findByPropertyRest(String propertyp, String propertyC, Integer idp , String ch)//return la liste de postulant qui n'a pas selectionner par RC
    {
    	log.debug("finding Candidature instance with property: " + propertyp
                + ", value: " + idp);
          try {
             String queryString = "from Candidature as model where model." 
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
	public List findByExperience(Object experience
	) {
		return findByProperty(EXPERIENCE, experience
		);
	}
	
	public List findByNiveauEtude(Object niveauEtude
	) {
		return findByProperty(NIVEAU_ETUDE, niveauEtude
		);
	}
	
	public List findByMissionPost(Object missionPost
	) {
		return findByProperty(MISSION_POST, missionPost
		);
	}
	

	public List findAll() {
		log.debug("finding all Candidature instances");
		try {
			String queryString = "from Candidature";
	         Query queryObject = getCurrentSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	public List findByPropertdelet(String propertyName ) {
		log.debug("finding Candidature instance with property: " + propertyName  );
		try {
			String queryString = "from Candidature as model where model." + propertyName
					+ " IS  NULL " ;
			Query queryObject = getCurrentSession().createQuery(queryString);
			
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
    public Candidature merge(Candidature detachedInstance) {
        log.debug("merging Candidature instance");
        try {
            Candidature result = (Candidature) getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Candidature instance) {
        log.debug("attaching dirty Candidature instance");
        try {
            getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Candidature instance) {
        log.debug("attaching clean Candidature instance");
        try {
            getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}