package com.xpanxion.rest.dto.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



/**
 * UserEntity Pojo
 * 
 * Represents items from the user table.
 * Exposes one named query that returns all entities from the table
 * 
 * @author vhuffman
 *
 */
@Entity
@Table(name = "users")
public class UserEntity { 
    	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id")
    	private long id;
    	
    	@Column(name = "username")
	    private String username;
    	
    	@Column(name = "password")
	    private String password;
	    
    	public UserEntity() {}
    	
		/**
	     * Returns the Id of the entity. This is the primary key. 
	     * 
	     * @return the id of the entity
	     */
	    public long getId() {
	        return this.id;
	    }

	    /**
	     * Returns the entities userName field
	     * 
	     * @return the userName field. 
	     */
	    public String getUsername() {
	        return this.username;
	    }
	    
	    /**
	     * Returns the entities password field
	     * 
	     * @return the password field. 
	     */
	    public String getPassword() {
	        return this.password;
	    }

	    /**
	     * Sets the entity's id. 
	     * 
	     * @param id  the id to set
	     */
	    public void setId(long id) {
	        this.id = id;
	    }

	    /**
	     * Set's the entity's userName field. 
	     * 
	     * @param text  the userName to set
	     */
	    public void setUsername(String value) {
	        this.username = value;
	    }
	    
	    /**
	     * Set's the entity's text field. 
	     * 
	     * @param text  the password to set
	     */
	    public void setPassword(String value) {
	        this.password = value;
	    }
	}
