package tn.com.pfe.dao;

import java.sql.Timestamp;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import tn.com.pfe.domain.Cv;

/**
 * A data access object (DAO) providing persistence and search support for Cv
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see tn.com.pfe.domain.Cv
 * @author MyEclipse Persistence Tools
 */

public class CvDAO extends CommonDAO {
	private static final Logger log = LoggerFactory.getLogger(CvDAO.class);
	// property constants
	public static final String _NDIPLOMEE = "NDiplomee";
	public static final String _NDIPLOMEF = "NDiplomef";
	public static final String _NDIPLOMEEXPER = "NDiplomeexper";
	public static final String DOMAINE_EDUCATION = "domaineEducation";
	public static final String SPÉCIALITÉ = "spécialité";
	public static final String CYCLE = "cycle";
	public static final String REGIME_LINGUISTIQUE = "regimeLinguistique";
	public static final String ETABLISSEMENT_EDUCATION = "etablissementEducation";
	public static final String MENTION_DIPLOME = "mentionDiplome";
	public static final String PAYS_ETABLISSEMENT = "paysEtablissement";
	public static final String DESCRIPTION = "description";
	public static final String PHOTO_DIPLOMEE = "photoDiplomee";
	public static final String ETABLISSEMENT_EXPER = "etablissementExper";
	public static final String TYPE_FORMATION = "typeFormation";
	public static final String DESCRIPTION_EXPER = "descriptionExper";
	public static final String PHOTO_DIPLOME_EXPER = "photoDiplomeExper";
	public static final String ETABLISSEMENT_FORMATION = "etablissementFormation";
	public static final String TITRE_FORMATION = "titreFormation";
	public static final String PHOTO_DIPLOME_FORMATION = "photoDiplomeFormation";
	public static final String DESCRIPTION_FORMATION = "descriptionFormation";

	public void save(Cv transientInstance) {
		log.debug("saving Cv instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Cv persistentInstance) {
		log.debug("deleting Cv instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Cv findById(Integer id) {
		log.debug("getting Cv instance with id: " + id);
		try {
			Cv instance = (Cv) getCurrentSession().get("tn.com.pfe.domain.Cv", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Cv instance) {
		log.debug("finding Cv instance by example");
		try {
			List results = getCurrentSession().createCriteria("tn.com.pfe.domain.Cv")
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
		log.debug("finding Cv instance with property: " + propertyName + ", value: " + value );
		try {
			String queryString = "from Cv as model where model." + propertyName
					+ "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	public List findByPropert(String propertyName , String propertyNam , Object value) {
		log.debug("finding Cv instance with property: " + propertyNam + ", value :" + value  );
		try {
			String queryString = "from Cv as model where model." + propertyName
					+ " IS NOT NULL AND " +propertyNam + " = ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	public List findByNDiplomee(Object NDiplomee) {
		return findByProperty(_NDIPLOMEE, NDiplomee);
	}

	public List findByNDiplomef(Object NDiplomef) {
		return findByProperty(_NDIPLOMEF, NDiplomef);
	}

	public List findByNDiplomeexper(Object NDiplomeexper) {
		return findByProperty(_NDIPLOMEEXPER, NDiplomeexper);
	}

	public List findByDomaineEducation(Object domaineEducation) {
		return findByProperty(DOMAINE_EDUCATION, domaineEducation);
	}

	public List findBySpécialité(Object spécialité) {
		return findByProperty(SPÉCIALITÉ, spécialité);
	}

	public List findByCycle(Object cycle) {
		return findByProperty(CYCLE, cycle);
	}

	public List findByRegimeLinguistique(Object regimeLinguistique) {
		return findByProperty(REGIME_LINGUISTIQUE, regimeLinguistique);
	}

	public List findByEtablissementEducation(Object etablissementEducation) {
		return findByProperty(ETABLISSEMENT_EDUCATION, etablissementEducation);
	}

	public List findByMentionDiplome(Object mentionDiplome) {
		return findByProperty(MENTION_DIPLOME, mentionDiplome);
	}

	public List findByPaysEtablissement(Object paysEtablissement) {
		return findByProperty(PAYS_ETABLISSEMENT, paysEtablissement);
	}

	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List findByPhotoDiplomee(Object photoDiplomee) {
		return findByProperty(PHOTO_DIPLOMEE, photoDiplomee);
	}

	public List findByEtablissementExper(Object etablissementExper) {
		return findByProperty(ETABLISSEMENT_EXPER, etablissementExper);
	}

	public List findByTypeFormation(Object typeFormation) {
		return findByProperty(TYPE_FORMATION, typeFormation);
	}

	public List findByDescriptionExper(Object descriptionExper) {
		return findByProperty(DESCRIPTION_EXPER, descriptionExper);
	}

	public List findByPhotoDiplomeExper(Object photoDiplomeExper) {
		return findByProperty(PHOTO_DIPLOME_EXPER, photoDiplomeExper);
	}

	public List findByEtablissementFormation(Object etablissementFormation) {
		return findByProperty(ETABLISSEMENT_FORMATION, etablissementFormation);
	}

	public List findByTitreFormation(Object titreFormation) {
		return findByProperty(TITRE_FORMATION, titreFormation);
	}

	public List findByPhotoDiplomeFormation(Object photoDiplomeFormation) {
		return findByProperty(PHOTO_DIPLOME_FORMATION, photoDiplomeFormation);
	}

	public List findByDescriptionFormation(Object descriptionFormation) {
		return findByProperty(DESCRIPTION_FORMATION, descriptionFormation);
	}

	public List findAll() {
		log.debug("finding all Cv instances");
		try {
			String queryString = "from Cv";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Cv merge(Cv detachedInstance) {
		log.debug("merging Cv instance");
		try {
			Cv result = (Cv) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Cv instance) {
		log.debug("attaching dirty Cv instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Cv instance) {
		log.debug("attaching clean Cv instance");
		try {
			getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}