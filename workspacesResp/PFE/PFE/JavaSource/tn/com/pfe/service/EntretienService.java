package tn.com.pfe.service;

import java.util.List;


import tn.com.pfe.domain.Entretien;

public interface EntretienService 
{

	public Entretien findById(Integer id)	;
	public List<Entretien>findByIdPost(Integer idp);
	public List<Entretien> findALL();
	public List<Entretien> findByIdc(String Ch);
	public List<Entretien>findByIdRe(Integer id, String n);
	public void Create(Entretien instant);
	public void update(Entretien instant);
	public void delete(Entretien instant);


}
