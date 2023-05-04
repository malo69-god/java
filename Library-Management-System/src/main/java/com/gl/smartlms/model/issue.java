package com.gl.smartlms.model;

import java.io.Serializable;


import java.util.Date;



import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;


import jakarta.validation.constraints.NotNull;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "issue")
@Data
public class issue implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@JsonFormat(pattern="dd-MM-yyyy")
	@NotNull
	@Column(name = "issue_date")
	private Date issueDate;
	
	@Column(name = "notes")
	private String notes;
	
	
	@JsonFormat(pattern="dd-MM-yyyy")
	@Column(name = "expected_return_date")
	private Date expectedReturnDate;
	
	@Column(name = "returned")
	private Integer returned;
	

	
	

}
