package com.xpanxion.rest.dto.bean;

public class UserBean {
	private long id;
    private String username;
    private String password;

    /**
     * Returns the id of this bean
     * 
     * @return the id the id of this bean
     */
    public long getId() {
        return this.id;
    }

    /**
     * Returns the userName of this bean
     * 
     * @return the userName of this bean
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Returns the password of this bean
     * 
     * @return the password of this bean
     */
    public String getPassword() {
        return this.password;
    }
    
    /**
     * Sets the id of this bean
     * 
     * @param id  the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Sets the userName of this bean 
     * 
     * @param username  the userName to set
     */
    public void setUsername(String userName) {
        this.username = userName;
    }
    
    /**
     * Sets the password of this bean 
     * 
     * @param password  the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}

