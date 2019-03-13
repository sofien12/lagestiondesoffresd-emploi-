package tn.com.pfe.service;

import java.util.Hashtable;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.com.pfe.dao.OffremploiDAO;

import tn.com.pfe.domain.Offremploi;
@Service("OffremploiService")
public class OffremploiImpl implements OffremploiService {

	@Autowired
	private OffremploiDAO DAO;
	@Autowired
	public OffremploiImpl(OffremploiDAO DAO)
	{
		this.DAO=DAO;
	}
	@Transactional(readOnly=true)
	public  Offremploi findById(Integer id) {
		// TODO Auto-generated method stub
		return DAO.findById(id);
	}

	@Transactional(readOnly=true)
	public List<Offremploi> findAll() {
		// TODO Auto-generated method stub
		return DAO.findAll();
	}
	
	
	@Transactional
	public void create(Offremploi instance) {
		// TODO Auto-generated method stub
		DAO.save(instance);
	}

	@Transactional
	public void update(Offremploi instance) {
		// TODO Auto-generated method stub
		DAO.merge(instance);
	}

	@Transactional
	public void delete(Offremploi instance) {
		// TODO Auto-generated method stub
		DAO.delete(instance);
	}


}
