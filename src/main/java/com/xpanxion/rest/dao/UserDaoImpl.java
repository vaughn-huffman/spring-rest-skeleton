package com.xpanxion.rest.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.xpanxion.rest.dto.entity.UserEntity;
import com.xpanxion.rest.dto.bean.UserBean;


@Repository
public class UserDaoImpl implements UserDao {
	public static final String SERVER_URI = "http://localhost:8080/";
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<UserEntity> getAllUsers() {
		RestTemplate restTemplate = new RestTemplate();
		List<UserEntity> userEntities = new ArrayList<UserEntity>();
				
        //we can't get List<UserBean> because JSON converter doesn't know the type of
        //object in the list and hence convert it to default JSON object type LinkedHashMap
        List<LinkedHashMap> users = restTemplate.getForObject(SERVER_URI+"/users", List.class);
        for(LinkedHashMap map : users){
            UserEntity userEntity = new UserEntity();
            userEntity.setId(((Long) map.get("id")).longValue());
            userEntity.setUsername((String) map.get("username"));
            userEntity.setPassword((String) map.get("password"));
            userEntities.add(userEntity);
        }
		return userEntities;
	}

	
	public UserEntity getUser(long id) {
		RestTemplate restTemplate = new RestTemplate();
		UserBean user = restTemplate.getForObject(SERVER_URI+"/user/{id}", UserBean.class, id);
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(user, userEntity);
		return userEntity;
	}

	
	public UserEntity addUser(UserEntity user) {
		RestTemplate restTemplate = new RestTemplate();
		UserBean userBean;
		UserEntity userEntity = new UserEntity();
		userBean = restTemplate.postForObject(SERVER_URI+"/user", user, UserBean.class);
		BeanUtils.copyProperties(userBean, userEntity);
		return userEntity;
	}

	
	public UserEntity updateUser(long id, UserEntity user) {
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("username", "Bob");
		map.add("password", "pw");
		RestTemplate restTemplate = new RestTemplate();
		UserBean userBean = restTemplate.getForObject(SERVER_URI+"/user/{id}", UserBean.class, 2);
		System.out.println("Original: user: Name="+user.getUsername()+",Password="+user.getPassword());
		user.setPassword("updatedpassword");
		restTemplate.put(SERVER_URI+"/user/{id}", user, user.getId());
		System.out.println("Updated: user: Name="+user.getUsername());
		return null;
	}

	
	public UserEntity deleteUser(long id) {
		RestTemplate restTemplate = new RestTemplate();
		UserEntity userEntity = this.getUser(id);
		restTemplate.delete(SERVER_URI+"/user/{id}", UserBean.class, id);
		return userEntity;
	}

}
