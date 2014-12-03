package com.xpanxion.rest.dao;

import java.util.List;

import com.xpanxion.rest.dto.entity.UserEntity;

public interface UserDao {
		
		public List<UserEntity> getAllUsers();
		
		public UserEntity getUser(long userId);
		
		public UserEntity addUser(UserEntity user);
		
		public UserEntity updateUser(long userId, UserEntity user);
		
		public UserEntity deleteUser(long userId);

	}
