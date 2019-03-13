package tn.com.pfe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.com.pfe.dao.CandidatureDAO;
import tn.com.pfe.domain.Candidature;
@Service("CandidatureService")
public class CandidatureServiceImpl implements CandidatureService {
	@Autowired
	private CandidatureDAO DAO;
	
	@Autowired
	public CandidatureServiceImpl(CandidatureDAO DAO)
	{
		this.DAO=DAO;
	}
	
	@Transactional(readOnly=true)
	public Candidature findbyId(Integer Id) {
		// TODO Auto-generated method stub
		return DAO.findById(Id);
	}

	@Transactional(readOnly=true)
	public List<Candidature> findAll() {
		// TODO Auto-generated method stub
		return DAO.findAll();
	}

	@Transactional(readOnly=true)
	public List<Candidature> findByIdPost(Integer IdP , String Ch) {
		// TODO Auto-generated method stub
		return DAO.findByPropertyPost("postulant","idC", IdP ,  Ch);
	}
	@Transactional(readOnly=true)
	public List<Candidature> findByIdc(String Ch)
	{
		return DAO.findByProperty("idC", Ch);
	}
	@Transactional(readOnly=true)
	public List<Candidature>findByIdR(String ch)
	{
		return DAO.findByProperty("idR", ch);
	}
	@Transactional(readOnly=true)
	public List<Candidature>findByIdRe(Integer id, String n)
	{
		return DAO.findByPropertyRest("postulant","idR",id,n);
	}
	@Transactional(readOnly=true)
	public List<Candidature> deletcandid()
	{
	return DAO.findByPropertdelet("postulant");	
	}
	@Transactional
	public void create(Candidature instance) {
		// TODO Auto-generated method stub
		DAO.save(instance);

	}
	@Transactional
	public void update(Candidature instance) {
		// TODO Auto-generated method stub
		DAO.merge(instance);

	}
	@Transactional
	public void delete(Candidature instance) {
		// TODO Auto-generated method stub
		DAO.delete(instance);

	}

}
