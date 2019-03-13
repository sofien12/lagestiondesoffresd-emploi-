package tn.com.pfe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.com.pfe.dao.EntretienDAO;
import tn.com.pfe.domain.Candidature;
import tn.com.pfe.domain.Entretien;
@Service("EntretienService")
public class EntretienServiceImpl implements EntretienService {

	@Autowired
	private EntretienDAO DAO;
	
	@Autowired
	public EntretienServiceImpl(EntretienDAO DAO)
	{
		this.DAO=DAO;
	}
	@Transactional(readOnly=true)
	public Entretien findById(Integer id) {
		// TODO Auto-generated method stub
		return DAO.findById(id);
	}

	@Transactional(readOnly=true)
	public List<Entretien> findByIdPost(Integer idp) {
		// TODO Auto-generated method stub
		return DAO.findByProperty("postulant", idp);
	}
	@Transactional(readOnly=true)
	public List<Entretien> findByIdc(String Ch)
	{
		return DAO.findByProperty("idE", Ch);
	}
	@Transactional(readOnly=true)
	public List<Entretien>findByIdRe(Integer id, String n)
	{
		return DAO.findByPropertyRest("postulant","idE",id,n);
	}
	@Transactional(readOnly=true)
	public List<Entretien> findALL() {
		// TODO Auto-generated method stub
		return DAO.findAll();
	}

	@Transactional
	public void Create(Entretien instant) {
		// TODO Auto-generated method stub
		DAO.save(instant);
	}

	@Transactional
	public void update(Entretien instant) {
		// TODO Auto-generated method stub
		DAO.merge(instant);
	}

	@Override
	public void delete(Entretien instant) {
		// TODO Auto-generated method stub
		DAO.delete(instant);
	}

}
