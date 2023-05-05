package com.gl.smartlms.model;

import java.io.Serializable;




import jakarta.validation.constraints.NotNull;


import jakarta.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;




@Entity
@Table(name = "member")
@Data
@NoArgsConstructor
public class Member implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@NotEmpty(message = "*Please select member type")
	@NotNull(message = "please Select Member Type")
	@Column(name = "type")
	private String type;
	
	@NotEmpty(message = "*Please enter fisrt name")
	@NotNull(message = "*Please enter fisrt name")
	@Column(name = "first_name")
	private String firstName;
	
	@NotEmpty(message = "*Please enter middle name")
	@NotNull(message = "*Please enter middle name")
	@Column(name = "middle_name")
	private String middleName;
	
	@Column(name = "last_name")
	private String lastName;
	
	
	@NotEmpty(message = "*Please select gender")
	@NotNull(message = "*Please select gender")
	@Column(name = "gender")
	private String gender;
	
	
	@JsonFormat(pattern="dd-MM-yyyy")
	@NotNull(message = "*Please enter birth date")
	@Column(name = "date_of_birth")
	private Date dateOfBirth;
	
	
	@JsonFormat(pattern="dd-MM-yyyy")
	@Column(name = "joining_date")
	private Date joiningDate;
	
	@Column(name = "contact")
	private String contact;
	
	@Column(name = "email")
	private String email;
	
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Issue> issue;
	
	
	
	public Member(@NotNull String type, @NotNull String firstName, @NotNull String middleName, @NotNull String lastName,
			@NotNull String gender, @NotNull Date dateOfBirth, @NotNull Date joiningDate) {
		super();
		this.type = type;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.joiningDate = joiningDate;
	}
	
}
