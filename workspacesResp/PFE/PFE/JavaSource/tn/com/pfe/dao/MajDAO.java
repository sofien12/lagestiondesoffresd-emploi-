package tn.com.pfe.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tn.com.pfe.domain.Maj;

/**
 * A data access object (DAO) providing persistence and search support for Maj
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see tn.com.pfe.domain.Maj
 * @author MyEclipse Persistence Tools
 */

public class MajDAO extends CommonDAO {
	private static final Logger log = LoggerFactory.getLogger(MajDAO.class);

	// property constants

	public void save(Maj transientInstance) {
		log.debug("saving Maj instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Maj persistentInstance) {
		log.debug("deleting Maj instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Maj findById(Integer id) {
		log.debug("getting Maj instance with id: " + id);
		try {
			Maj instance = (Maj) getCurrentSession().get("tn.com.pfe.domain.Maj", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Maj instance) {
		log.debug("finding Maj instance by example");
		try {
			List results = getCurrentSession().createCriteria("tn.com.pfe.domain.Maj")
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
		log.debug("finding Maj instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Maj as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all Maj instances");
		try {
			String queryString = "from Maj";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Maj merge(Maj detachedInstance) {
		log.debug("merging Maj instance");
		try {
			Maj result = (Maj) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Maj instance) {
		log.debug("attaching dirty Maj instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Maj instance) {
		log.debug("attaching clean Maj instance");
		try {
			getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}