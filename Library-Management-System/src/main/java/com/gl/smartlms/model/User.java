package com.gl.smartlms.model;

import java.io.Serializable;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;


import jakarta.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;


//mo args constructor name uname pass role
@Entity
@Data
@NoArgsConstructor
@Table(name ="user")
public class User  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@Column(name = "name")
	private String name;
	
	@NotNull
	@Column(name = "username")
	private String username;
	
	
	//@JsonIgnore
	@NotNull
	@Column(name = "password")
	private String password;
	
	@NotNull
	@Column(name = "active")
	private Integer active;
	
	@NotNull
	@Column(name = "role")
	private String role;
	
	@JsonFormat(pattern="dd-MM-yyyy")
	 @CreatedDate
	@Column(name = "created_date")
	private Date createdDate = new Date();
	
	@JsonFormat(pattern="dd-MM-yyyy")
	@Column(name = "last_Modified_Date")
	private Date lastModifiedDate;
	
	public User(@NotNull String name, @NotNull String username, @NotNull String password, @NotNull String role) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
		this.role = role;
	}
	

	
}
