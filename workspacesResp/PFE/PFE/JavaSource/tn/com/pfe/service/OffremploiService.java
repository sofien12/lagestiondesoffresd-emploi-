package tn.com.pfe.service;

import java.util.Hashtable;
import java.util.List;

import tn.com.pfe.domain.Createur;
import tn.com.pfe.domain.Offremploi;

public interface OffremploiService 
{
	public Offremploi findById(Integer id);
	public List<Offremploi> findAll();
	public List<Offremploi> findByIdC(Integer Idp);
	public List<Offremploi> findByIdR(String IdS, String IdR);
	public List<Offremploi> findByIdC( String IdR, String IdC);
	public List<Offremploi> findByIdS(String IdS);
	public void create(Offremploi instance);
	public void update(Offremploi instance);
	public void delete(Offremploi instance);
}
