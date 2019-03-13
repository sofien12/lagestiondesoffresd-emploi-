package tn.com.pfe.dao;

import java.sql.Timestamp;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tn.com.pfe.domain.Validateur;

/**
 * A data access object (DAO) providing persistence and search support for
 * Validateur entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see tn.com.pfe.domain.Validateur
 * @author MyEclipse Persistence Tools
 */

public class ValidateurDAO extends CommonDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ValidateurDAO.class);
	// property constants
	public static final String OBESEVATION = "obesevation";
	public static final String VALIDER = "valider";

	public void save(Validateur transientInstance) {
		log.debug("saving Validateur instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Validateur persistentInstance) {
		log.debug("deleting Validateur instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	/*public void deleteOffre(String proprtyname , Integer IdOffre) {
		log.debug("deleting Validateur instance with property  :"+ IdOffre);
		try {
		
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}*/
	public Validateur findById(Integer id) {
		log.debug("getting Validateur instance with id: " + id);
		try {
			Validateur instance = (Validateur) getCurrentSession().get(
					"tn.com.pfe.domain.Validateur", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Validateur instance) {
		log.debug("finding Validateur instance by example");
		try {
			List results = getCurrentSession()
					.createCriteria("tn.com.pfe.domain.Validateur")
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
		log.debug("finding Validateur instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Validateur as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByObesevation(Object obesevation) {
		return findByProperty(OBESEVATION, obesevation);
	}

	public List findByValider(Object valider) {
		return findByProperty(VALIDER, valider);
	}

	public List findAll() {
		log.debug("finding all Validateur instances");
		try {
			String queryString = "from Validateur";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Validateur merge(Validateur detachedInstance) {
		log.debug("merging Validateur instance");
		try {
			Validateur result = (Validateur) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Validateur instance) {
		log.debug("attaching dirty Validateur instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Validateur instance) {
		log.debug("attaching clean Validateur instance");
		try {
			getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}