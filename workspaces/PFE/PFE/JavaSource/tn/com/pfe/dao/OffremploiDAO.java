package tn.com.pfe.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import tn.com.pfe.domain.Offremploi;

/**
 * A data access object (DAO) providing persistence and search support for
 * Offremploi entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see tn.com.pfe.domain.Offremploi
 * @author MyEclipse Persistence Tools
 */

public class OffremploiDAO extends CommonDAO {
	private static final Logger log = LoggerFactory
			.getLogger(OffremploiDAO.class);
	// property constants
	public static final String ROLE = "role";
	public static final String NIVEAU_ETUDE = "niveauEtude";
	public static final String MINIMUM_EXPER = "minimumExper";
	public static final String MISSION = "mission";
	public static final String TYPE_OFFRE = "typeOffre";
	public static final String REGION = "region";
	public static final String LOCALITÉ = "localité";
	public static final String SUSPENDU = "suspendu";
	public static final String ID_S = "idS";
	public static final String ID_R = "idR";
	public static final String ID_CP = "idCp";

	public void save(Offremploi transientInstance) {
		log.debug("saving Offremploi instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Offremploi persistentInstance) {
		log.debug("deleting Offremploi instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Offremploi findById(Integer id) {
		log.debug("getting Offremploi instance with id: " + id);
		try {
			Offremploi instance = (Offremploi) getCurrentSession().get(
					"tn.com.pfe.domain.Offremploi", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	public List findByPropert(String propertyName , String propertyNam , Object IdS ,Object IdR  ) {
		log.debug("finding Offremploi instance with property: " + propertyName + ", value :" + IdS     );
		
		
		try {
			String queryString = "from Offremploi as model where model." + propertyName
					+ "= ?  AND model." +propertyNam + " = ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0,IdS);
			queryObject.setParameter(1, IdR);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByPropertCp( String propertyNam , String propertyNamCp  ,Object IdR , Object IdC ) {
		
		log.debug("finding Offremploi instance with property: " + propertyNam + ", value :" + IdR     );
		
		try {
			String queryString = "from Offremploi as model where model." +propertyNam + " = ? AND model."+propertyNamCp+ " = ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, IdR);
			queryObject.setParameter(1, IdC);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	public List findByExample(Offremploi instance) {
		log.debug("finding Offremploi instance by example");
		try {
			List results = getCurrentSession()
					.createCriteria("tn.com.pfe.domain.Offremploi")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Offremploi instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Offremploi as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByRole(Object role) {
		return findByProperty(ROLE, role);
	}

	public List findByNiveauEtude(Object niveauEtude) {
		return findByProperty(NIVEAU_ETUDE, niveauEtude);
	}

	public List findByMinimumExper(Object minimumExper) {
		return findByProperty(MINIMUM_EXPER, minimumExper);
	}

	public List findByMission(Object mission) {
		return findByProperty(MISSION, mission);
	}

	public List findByTypeOffre(Object typeOffre) {
		return findByProperty(TYPE_OFFRE, typeOffre);
	}

	public List findByRegion(Object region) {
		return findByProperty(REGION, region);
	}

	public List findByLocalité(Object localité) {
		return findByProperty(LOCALITÉ, localité);
	}

	public List findBySuspendu(Object suspendu) {
		return findByProperty(SUSPENDU, suspendu);
	}

	public List findByIdS(Object idS) {
		return findByProperty(ID_S, idS);
	}

	public List findByIdR(Object idR) {
		return findByProperty(ID_R, idR);
	}

	public List findByIdCp(Object idCp) {
		return findByProperty(ID_CP, idCp);
	}

	public List findAll() {
		log.debug("finding all Offremploi instances");
		try {
			String queryString = "from Offremploi";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Offremploi merge(Offremploi detachedInstance) {
		log.debug("merging Offremploi instance");
		try {
			Offremploi result = (Offremploi) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Offremploi instance) {
		log.debug("attaching dirty Offremploi instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Offremploi instance) {
		log.debug("attaching clean Offremploi instance");
		try {
			getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}