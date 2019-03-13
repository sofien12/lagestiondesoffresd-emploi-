package tn.com.pfe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.com.pfe.dao.CreateurDAO;
import tn.com.pfe.domain.Createur;
@Service("CreateurService")
public class CreateurServiceImpl implements CreateurService {

	@Autowired
	private CreateurDAO DAO;
	
	@Autowired
	public CreateurServiceImpl(CreateurDAO DAO)
	{
		this.DAO=DAO;
	}
	@Transactional (readOnly=true)
	public Createur findById(Integer id) {
		// TODO Auto-generated method stub
		return DAO.findById(id);
	}

	@Transactional (readOnly=true)
	public List<Createur> findAll() {
		// TODO Auto-generated method stub
		return DAO.findAll();
	}

	@Transactional (readOnly=true)
	public List<Createur> findEmail(String Email) {
		// TODO Auto-generated method stub
		return DAO.findByEmail(Email);
	}
	@Transactional (readOnly=true)
	public List<Createur> findCin(String Cin) {
		// TODO Auto-generated method stub
		return DAO.findByCin(Cin);
	}

	@Transactional 
	public void create(Createur instance) {
		DAO.save(instance);
	}

	@Transactional
	public void update(Createur instance) {
			DAO.merge(instance);
	}

	@Transactional
	public void delete(Createur instance) {
		// TODO Auto-generated method stub
		DAO.delete(instance);

	}
	

}
