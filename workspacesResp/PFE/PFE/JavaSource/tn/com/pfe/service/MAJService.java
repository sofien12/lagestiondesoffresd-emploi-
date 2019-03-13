package tn.com.pfe.service;

import java.util.List;

import tn.com.pfe.domain.Entretien;
import tn.com.pfe.domain.Maj;

public interface MAJService 
{
	public Maj findby(Integer id);
	public List<Maj>MajPost(Integer idp);
	public List <Maj>findall();
	public void Create(Maj instant);
	public void update(Maj instant);
	public void delete(Maj instant);
}
