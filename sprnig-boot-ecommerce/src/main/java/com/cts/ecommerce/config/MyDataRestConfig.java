package com.cts.ecommerce.config;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

//import org.hibernate.mapping.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.cts.ecommerce.entity.Country;
import com.cts.ecommerce.entity.Product;
import com.cts.ecommerce.entity.ProductCategory;
import com.cts.ecommerce.entity.State;

import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer{
	
	
	private EntityManager entityManager;
	
	@Autowired
	public MyDataRestConfig(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
		
		RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config, cors);
		
		HttpMethod[] theUnsupportedActions = {HttpMethod.PUT,HttpMethod.POST,HttpMethod.DELETE};
		
		//disable HTTP methods for products (POST<PUT<DELETE)
		disableHttpMethods( Product.class, config, theUnsupportedActions);
		
		//disable HTTP methods for ProductCategory (POST, PUT, DELETE)
		disableHttpMethods( ProductCategory.class, config, theUnsupportedActions);
		
		//disable HTTP methods for Country (POST, PUT, DELETE)
		disableHttpMethods( Country.class, config, theUnsupportedActions);
		
		//disable HTTP methods for State (POST, PUT, DELETE)
		disableHttpMethods( State.class, config, theUnsupportedActions);
		
		//call internal helper method
		exposeIds(config);
	
	}


	private void disableHttpMethods(Class theClass, RepositoryRestConfiguration config, HttpMethod[] theUnsupportedActions) {
		config.getExposureConfiguration()
		.forDomainType(theClass)
		.withItemExposure((metdata,httpMethods)-> httpMethods.disable(theUnsupportedActions))
		.withCollectionExposure((metdata,httpMethods)->httpMethods.disable(theUnsupportedActions));
	}


	private void exposeIds(RepositoryRestConfiguration config) {
		//expose entity id
		
		// Retrieving  list of all entity classes from entity manager
		Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
		List<Class> entityClasses = new ArrayList<>();
		
		for(EntityType tempEntityType: entities) {
			entityClasses.add(tempEntityType.getJavaType());
		}
		
		// expose the entity ids for the array of entities/ domain type
		Class[] domainTypes = entityClasses.toArray(new Class[0]);
		config.exposeIdsFor(domainTypes);
	}
	
	
}
