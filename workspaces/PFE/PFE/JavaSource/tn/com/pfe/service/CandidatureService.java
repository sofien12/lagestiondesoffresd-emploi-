package tn.com.pfe.service;

import java.util.List;

import tn.com.pfe.domain.Candidature;

public interface CandidatureService 
{
	public Candidature findbyId(Integer Id);
	public List<Candidature> findAll();
	public List<Candidature> findByIdPost(Integer IdP);
	public void create(Candidature instance);
	public void update(Candidature instance);
	public void delete(Candidature instance);

}
