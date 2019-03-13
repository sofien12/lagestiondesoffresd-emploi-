package tn.com.pfe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.com.pfe.dao.CreateurDAO;
import tn.com.pfe.dao.ValidateurDAO;
import tn.com.pfe.domain.Createur;
import tn.com.pfe.domain.Validateur;
@Service("ValidateurService")
public class ValidateurImpl implements ValidateurService {

	@Autowired
	private ValidateurDAO DAO;
	
	@Autowired
	public ValidateurImpl(ValidateurDAO DAO)
	{
		this.DAO=DAO;
	}
	@Transactional (readOnly=true)
	public Validateur findById(Integer id) {
		// TODO Auto-generated method stub
		return DAO.findById(id);
		}

	@Transactional (readOnly=true)
	public List<Validateur> findAll() {
		// TODO Auto-generated method stub
		return DAO.findAll();
	}
	@Transactional (readOnly=true)
	public List<Validateur> findByOffre(Integer IdOffre)
	{
		return DAO.findByProperty("offremploi", IdOffre);
		
	}

	@Transactional 
	public void create(Validateur instance) {
		DAO.save(instance);
	}

	@Transactional
	public void update(Validateur instance) {
			DAO.merge(instance);
	}
	@Transactional
	public void delete(Validateur instance) {
		// TODO Auto-generated method stub
		DAO.delete(instance);
		
	}
}
