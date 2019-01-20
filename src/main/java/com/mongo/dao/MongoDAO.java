package com.mongo.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongo.document.Cliente;

@Repository
@Scope("prototype")
public class MongoDAO {
	private static Logger log = LoggerFactory.getLogger(MongoDAO.class);
	@Autowired
	@Qualifier("MiMongoTemplate")
	private MongoTemplate template;
	
	public boolean save(Cliente cliente) {
		log.info(String.format("MT: %s", template));
		try {
			template.save(cliente);
			return true;
		} catch (Exception e) {
			log.info("Error: " + e);			
		}
		return false;
	}
	
	public Cliente getCliente(String id){
		try {
			Query query = new Query(Criteria.where("_id").is(id));
			return template.find(query, Cliente.class).stream().findFirst().get();
		} catch (Exception e) {
			
		}
		return null;
	}
	
	public List<Cliente> getClientes(){
		try {
			return template.findAll(Cliente.class);
		} catch (Exception e) {
			
		}
		return null;
	}
	
	public boolean delete(String id) {
		try {
			Query query = new Query(Criteria.where("_id").is(id));
			template.remove(query, Cliente.class);
			return true;
		} catch (Exception e) {
		}
		return false;
	}
	
	public boolean update(Cliente cliente) {
		try {
			Cliente old = getCliente(cliente.getId());
			BeanUtils.copyProperties(cliente, old);
			template.save(old);
			return true;
		} catch (Exception e) {
			log.info("" +e);
		}
		return false;
	}
}
