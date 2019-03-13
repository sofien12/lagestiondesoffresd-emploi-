package tn.com.pfe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.com.pfe.dao.PostulantDAO;
import tn.com.pfe.domain.Postulant;
@Service("PostulantService")
public class PostulantServiceImpl implements PostulantService

{
	@Autowired
	private PostulantDAO DAO;

@Autowired
public PostulantServiceImpl(PostulantDAO DAO) {
	this.DAO = DAO;
}

@Transactional
public void create(Postulant instance) {
	DAO.save(instance);
}

@Transactional
public void update(Postulant instance) {
	DAO.merge(instance);
}

@Transactional
public void delete(Postulant instance) {
	DAO.delete(instance);
}

@Transactional(readOnly=true)
public List<Postulant> findByEmail(String email) {
	
	return DAO.findByEmail(email);
}

@Transactional(readOnly=true)
public Postulant findById(Integer id) {
	return DAO.findById(id);
}
@Transactional (readOnly=true )
	public List <Postulant> findByCin(String cin){
		return DAO.findByCin(cin);
}
@Transactional (readOnly=true )
public List<Postulant> findAll()
{
	return DAO.findAll();
}
}
