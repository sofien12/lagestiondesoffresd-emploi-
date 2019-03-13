package tn.com.pfe.service;

import java.util.Hashtable;
import java.util.List;

import tn.com.pfe.domain.Offremploi;

public interface OffremploiService 
{
	public Offremploi findById(Integer id);
	public List<Offremploi> findAll();
		public void create(Offremploi instance);
	public void update(Offremploi instance);
	public void delete(Offremploi instance);
}
