package com.jrp.pma.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_accounts")
public class UserAccount {

	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="user_accounts_seq")
	private long useId;
	
	@Column(name="username")
	private String userName;
	
	private String email;
	
	private String password;
	
	private boolean enabled = true;

	public long getUseId() {
		return useId;
	}

	public void setUseId(long useId) {
		this.useId = useId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public UserAccount() {
	
	}
	
	
}
