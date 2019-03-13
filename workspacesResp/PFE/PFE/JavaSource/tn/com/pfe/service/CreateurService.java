package tn.com.pfe.service;

import java.util.List;

import tn.com.pfe.domain.Createur;

public interface CreateurService 
{
	public  Createur findById(Integer id);
	public List<Createur> findAll();
	public List<Createur> findEmail(String Email);
	public List<Createur> findCin(String Cin);
	public void create(Createur instance);
	public void update(Createur instance);
	public void delete(Createur instance);
}
