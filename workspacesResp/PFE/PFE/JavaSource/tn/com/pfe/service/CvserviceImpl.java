package tn.com.pfe.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.com.pfe.dao.CvDAO;
import tn.com.pfe.dao.PostulantDAO;
import tn.com.pfe.domain.Cv;
@Service("CvService")
public class CvserviceImpl implements CvService {
	@Autowired
	private CvDAO DAO;
	
	@Autowired
	public CvserviceImpl(CvDAO DAO)
	{
		this.DAO=DAO;
	}
	@Transactional(readOnly=true)
	public Cv findById(Integer id) {
		// TODO Auto-generated method stub
		return DAO.findById(id);
	}

	@Transactional(readOnly=true)
	public List<Cv> findAll() {
		// TODO Auto-generated method stub
		return DAO.findAll();
	}
	@Transactional(readOnly=true)
	public List<Cv> findByIdp(String Idp) {
		// TODO Auto-generated method stub
		return DAO.findByProperty("postulant", Idp);
	}
	@Transactional(readOnly=true)
	public List<Cv> findByEducation(String Idp)
	{
		return DAO.findByPropert("domaineEducation", "postulant",Idp);
	}
	@Transactional(readOnly=true)
	public List<Cv> findByFormation(String Idp)
	{
		return DAO.findByPropert("etablissementFormation", "postulant", Idp);
	}
	@Transactional(readOnly=true)
	public List<Cv> findByExperience(String Idp)
	{
		return DAO.findByPropert("etablissementExper", "postulant", Idp);

	}
	@Transactional
	public void create(Cv instance) {
		// TODO Auto-generated method stub
		DAO.save(instance);
	}

	@Transactional
	public void update(Cv instance) {
		DAO.merge(instance);
		
	}

	@Transactional
	public void delete(Cv instance) {
		// TODO Auto-generated method stub
		DAO.delete(instance);

	}
	
	

}
