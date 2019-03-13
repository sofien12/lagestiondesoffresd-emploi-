package tn.com.pfe.service;

import java.util.List;

import tn.com.pfe.domain.Createur;
import tn.com.pfe.domain.Validateur;

public interface ValidateurService
{
	public  Validateur findById(Integer id);
	public List<Validateur> findAll();
	public List<Validateur> findByOffre(Integer IdOffre);
	public void create(Validateur instance);
	public void update(Validateur instance);
	public void delete(Validateur instance);

}
