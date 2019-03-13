package tn.com.pfe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.com.pfe.dao.MajDAO;
import tn.com.pfe.domain.Maj;
@Service("MAJService")
public class MajImpl implements MAJService {
	
	@Autowired
	private MajDAO DAO;
	
	@Autowired
	public MajImpl(MajDAO DAO)
	{
		this.DAO=DAO;
	}
	@Transactional(readOnly=true)
	public Maj findby(Integer id) {
		
		return DAO.findById(id);
	}
	@Transactional
	public void Create(Maj instant) {
		// TODO Auto-generated method stub
		DAO.save(instant);
	}

	@Transactional
	public void update(Maj instant) {
		// TODO Auto-generated method stub
		DAO.merge(instant);
	}
	@Transactional
	public void delete(Maj instant) {
		// TODO Auto-generated method stub
		DAO.delete(instant);
	}
	@Transactional(readOnly=true)
	public List<Maj> findall() {
		// TODO Auto-generated method stub
		return DAO.findAll();
	}
	@Transactional(readOnly=true)
	public List<Maj> MajPost(Integer idp) {
		// TODO Auto-generated method stub
		return DAO.findByProperty("postulant", idp);
	}

}
