package com.mase2.mase2_project.test.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;




@Stateless
@LocalBean
public class UtilsDAO {

    @PersistenceContext
    private EntityManager entityManager;
    
	public void deleteTable(){
		entityManager.createQuery("DELETE FROM MccMnc").executeUpdate();
		entityManager.createNativeQuery("ALTER TABLE mcc_mnc AUTO_INCREMENT=1")
		.executeUpdate();
	}
	
	public void deleteTableEventCause(){
		entityManager.createQuery("DELETE FROM EventCause").executeUpdate();
		entityManager.createNativeQuery("ALTER TABLE event_cause AUTO_INCREMENT=1")
		.executeUpdate();		
	}

	public void deleteTableBaseData(){
		entityManager.createQuery("DELETE FROM BaseData").executeUpdate();
		entityManager.createNativeQuery("ALTER TABLE base_data AUTO_INCREMENT=1").executeUpdate();
	}

	public void deleteTableFailureClass(){
		entityManager.createQuery("DELETE FROM FailureClass").executeUpdate();
		entityManager.createNativeQuery("ALTER TABLE failure_class AUTO_INCREMENT=1").executeUpdate();	
	}
	
	
	public void deleteTableUe(){
		entityManager.createQuery("DELETE FROM Ue").executeUpdate();		
	}
	
	public void deleteUserTable() {
		entityManager.createQuery("DELETE FROM User").executeUpdate();
	}
	
	public HttpHeaders getHttpHeaders() {
		return new HttpHeaders() {
			
			@Override
			public MultivaluedMap<String, String> getRequestHeaders() {
				//MultivaluedMap<String, String>new ArrayList<>();
				//request.add("michal:pass");
				return null;
			}
			
			@Override
			public List<String> getRequestHeader(String arg0) {
				List<String> request = new ArrayList<>();
				request.add("Basic admin@mase2.ie:5777f605aff3362ca976c1ca0dffffe4");
				return request;
			}
			
			@Override
			public MediaType getMediaType() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Locale getLanguage() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Map<String, Cookie> getCookies() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public List<MediaType> getAcceptableMediaTypes() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public List<Locale> getAcceptableLanguages() {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}
}
