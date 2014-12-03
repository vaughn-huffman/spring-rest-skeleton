package com.xpanxion.rest.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xpanxion.rest.dao.UserDao;
import com.xpanxion.rest.dto.bean.UserBean;
import com.xpanxion.rest.service.UserService;
import com.xpanxion.rest.dto.entity.UserEntity;

@Transactional
@Service
public class UserServiceImpl implements UserService {
	private UserDao userDao;
	
    /**
     * Return all of the available User beans. 
     * 
     * @return all of the User beans
     */
	public List<UserBean> getUsers() {
		List<UserEntity> userItems = this.userDao.getAllUsers();
        List<UserBean> output = new ArrayList<UserBean>();
        for (UserEntity entity : userItems) {
        	UserBean bean = new UserBean();
        	convertUserEntityToBean(entity, bean);
            output.add(bean);

        }
        return output;
    }
	
    /**
     * Return a specific User 
     * 
     * @return a specific User by id
     */
	public UserBean getUser(long userId) {
		return convertUserEntityToBean(this.userDao.getUser(userId), new UserBean());
	}
	
	public UserBean addUser(UserBean user) {
		UserEntity entity = this.userDao.addUser(convertUserBeanToEntity(user, new UserEntity()));
		return convertUserEntityToBean(entity, new UserBean());
		
/*		UserEntity entity = new UserEntity();
		UserEntity savedEntity = new UserEntity();
		UserBean savedUser = new UserBean();
		BeanUtils.copyProperties(user, entity);
		savedEntity = this.userDao.addUser(entity);
		BeanUtils.copyProperties(savedEntity, savedUser);
		return savedUser;*/
	}

	public UserBean updateUser(long userId, UserBean user) {
		UserEntity entity = this.userDao.updateUser(userId, convertUserBeanToEntity(user, new UserEntity()));
		return convertUserEntityToBean(entity, new UserBean());
	}

	public UserBean updateUser(long userId, String userPassword) {
		UserBean user = new UserBean();
		user.setPassword(userPassword);
		UserEntity entity = this.userDao.updateUser(userId, convertUserBeanToEntity(user, new UserEntity()));
		return convertUserEntityToBean(entity, user);
	}
	
	public UserBean deleteUser(long userId) {
		UserEntity entity = this.userDao.deleteUser(userId);
		return convertUserEntityToBean(entity, new UserBean());
	}
	
	
    /**
     * Sets the UserDao for this service to use
     * 
     * @param dao the dao for this service to use. 
     */
    @Resource
    public void setUserDao(UserDao dao) {
        this.userDao = dao;
    }
    
    private UserBean convertUserEntityToBean(UserEntity entity, UserBean user) {
    	BeanUtils.copyProperties(entity, user);
        return user;
    }

    private UserEntity convertUserBeanToEntity(UserBean user, UserEntity entity) {
    	BeanUtils.copyProperties(user, entity);
        return entity;
    }

}
