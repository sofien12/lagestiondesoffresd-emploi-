package tn.com.pfe.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

public class CommonDAO {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	// Utilisée pour le traitement des traces
	public Integer updateBeforeDelete(String tableName, String tableCollId, Object cutimod, Date datemod, Object idRow) {
		try {
			SimpleDateFormat dateFormat= new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			String queryString = "UPDATE " + tableName + " SET cutimod = ?, datemod = TO_DATE('" + dateFormat.format(datemod) + "','dd/MM/yyyy ') WHERE " + tableCollId + " = ?";
			List<Object> bindVars = new ArrayList<Object>();
			bindVars.add(cutimod);
			bindVars.add(idRow);
			SQLQuery sqlQuery = getCurrentSession().createSQLQuery(queryString);
			for (int i=0;i<bindVars.size();i++){
				sqlQuery.setParameter(i, bindVars.get(i));
			}
			return sqlQuery.executeUpdate();
			
		} catch (RuntimeException re) {
			throw re;
		}
	}	
	
	public Integer executeQuery(String sql,List<Object> bindVars) {
		try {
			String queryString = sql;
			SQLQuery sqlQuery = getCurrentSession().createSQLQuery(queryString);
			for (int i=0;i<bindVars.size();i++){
				sqlQuery.setParameter(i, bindVars.get(i));
			}
			return sqlQuery.executeUpdate();
			
		} catch (RuntimeException re) {
			throw re;
		}
	}	
	
	public List<Object> findByQuery(String sql) {
	try {
		 SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql);
		 return sqlQuery.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}	
	
	public List<Object> findByQuery(String sql,List<Object> bindVars) {
		try {
			String queryString = sql;
			SQLQuery sqlQuery = getCurrentSession().createSQLQuery(queryString);
			for (int i=0;i<bindVars.size();i++){
				sqlQuery.setParameter(i, bindVars.get(i));
			}
			return sqlQuery.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	public List findByQuery(Class<?> entityClass,String sql,List<Object> bindVars) {
		try {
			String queryString = sql;
			SQLQuery sqlQuery = getCurrentSession().
			createSQLQuery(queryString).addEntity(entityClass);
			for (int i=0;i<bindVars.size();i++){
				sqlQuery.setParameter(i, bindVars.get(i));
			}
			return sqlQuery.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
    public Integer findIntegerByQuery(String sql, List<Object> bindVars) {
     try {
			String queryString = sql;
			SQLQuery sqlQuery = getCurrentSession().createSQLQuery(queryString);
			for (int i=0;i<bindVars.size();i++){
				sqlQuery.setParameter(i, bindVars.get(i));
			}
			List<Object> list= sqlQuery.list();
			if(list.size()==0){
				return null;
			}else{
				if(list.get(0) == null){
					return null;
				}
			}
			Object object = list.get(0);
		    return Integer.parseInt(object.toString());
		} catch (RuntimeException re) {
			throw re;
		}
	}

public Long findLongByQuery(String sql, List<Object> bindVars) {
 try {
			String queryString = sql;
			SQLQuery sqlQuery = getCurrentSession().createSQLQuery(queryString);
			for (int i=0;i<bindVars.size();i++){
				sqlQuery.setParameter(i, bindVars.get(i));
			}
			List<Object> list= sqlQuery.list();
			if(list.size()==0){
				return null;
			}else{
				if(list.get(0) == null){
					return null;
				}
			}
			Object object = list.get(0);
		    return new Long(object.toString());
		} catch (RuntimeException re) {
			throw re;
		}
	}

public Double findDoubleByQuery(String sql, List<Object> bindVars) {
        
		try {
			String queryString = sql;
			SQLQuery sqlQuery = getCurrentSession().createSQLQuery(queryString);
			for (int i=0;i<bindVars.size();i++){
				sqlQuery.setParameter(i, bindVars.get(i));
			}
			List<Object> list= sqlQuery.list();
			if(list.size()==0){
				return null;
			}else{
				if(list.get(0) == null){
					return null;
				}
			}
			Object object = list.get(0);
		    return new Double(object.toString());
		} catch (RuntimeException re) {
			throw re;
		}
	}
   public String findStringByQuery(String sql, List<Object> bindVars) {
   try {
			String queryString = sql;
			SQLQuery sqlQuery = getCurrentSession().createSQLQuery(queryString);
			for (int i=0;i<bindVars.size();i++){
				sqlQuery.setParameter(i, bindVars.get(i));
			}
			List<Object> list= sqlQuery.list();
			if(list.size()==0){
				return null;
			}
			
			Object object = list.get(0);
		    return object.toString();
		} catch (RuntimeException re) {
			throw re;
		}
	}
   
   public java.util.Date findDateByQuery(String sql, List<Object> bindVars) {
	   try {
			String queryString = sql;
			SQLQuery sqlQuery = getCurrentSession().createSQLQuery(queryString);
			for (int i=0;i<bindVars.size();i++){
				sqlQuery.setParameter(i, bindVars.get(i));
			}
			List<Object> list= sqlQuery.list();
			if(list.size()==0){
				return null;
			}
				
			Object object = list.get(0);
			return (java.util.Date) object;
			} catch (RuntimeException re) {
				throw re;
			}
		}
   
   public List<Object> readByCriteria(Class<?> entityClass,List<Criterion> criterion,List<Order> orders) {
		Criteria crit = getCurrentSession().createCriteria(entityClass);
		for (Criterion c : criterion) {
			if (c!=null)
			crit.add(c);
		}
		for (Order o : orders) {
			if (o!=null)
			crit.addOrder(o);
		}
		List<Object> list= crit.list();
		return list;
	}
  
}	