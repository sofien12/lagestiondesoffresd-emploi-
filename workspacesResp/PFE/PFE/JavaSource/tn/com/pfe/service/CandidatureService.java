package tn.com.pfe.service;

import java.util.List;

import tn.com.pfe.domain.Candidature;

public interface CandidatureService 
{
	public Candidature findbyId(Integer Id);
	public List<Candidature> findAll();
	public List<Candidature> findByIdPost(Integer IdP, String Ch);//return la list de Postulant qui n'a pas selectionner 
	public List<Candidature> findByIdc(String Ch);
	public List<Candidature>findByIdR(String ch);
	public List<Candidature>findByIdRe(Integer id, String n);
	public void create(Candidature instance);
	public void update(Candidature instance);
	public void delete(Candidature instance);
	public List<Candidature> deletcandid();

}
