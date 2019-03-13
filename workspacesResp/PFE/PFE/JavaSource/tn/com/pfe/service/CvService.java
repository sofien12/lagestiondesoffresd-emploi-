package tn.com.pfe.service;

import java.math.BigDecimal;
import java.util.List;

import tn.com.pfe.domain.Cv;

public interface CvService
	{
	public Cv findById(Integer id);
	public List<Cv> findAll();
	public List<Cv> findByIdp(String Idp);
	public List<Cv> findByEducation(String Idp);
	public List<Cv> findByFormation (String Idp);
	public List<Cv> findByExperience (String Idp);
	public void create(Cv instance);
	public void update(Cv instance);
	public void delete(Cv instance);
}
