package tn.com.pfe.service;

import java.util.List;

import tn.com.pfe.domain.Postulant;

public interface PostulantService 
{
	public void create(Postulant instance);
	public void update(Postulant instance);
	public void delete(Postulant instance);
	public List <Postulant> findById(Integer id);
	public List<Postulant> findByEmail(String email);
	public List <Postulant > findByCin (String Cin);
	public List<Postulant> findAll();
}
