package com.rytways.model;

import javax.persistence.CascadeType;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.rytways.Categories.ActiveStatus;
import com.rytways.Categories.ActiveStatusConvertor;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
public class ApprovalAuthorities extends BaseEntity {
	
	@Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
	private int authorityId;
	
	
	//@NotBlank(message="LineNo must not be blank")
    //@Size(min=3, message="LineNo must be at least 3 characters long")
    private int seqNo;
	
	//@NotBlank(message="LineNo must not be blank")
    //@Size(min=3, message="LineNo must be at least 3 characters long")
    private int roleId;
	    
    private String approvalType;
    
    private String approvalName;
	
	private  int userId;
	
	private  int secondaryUserId;
	
	@Convert(converter = ActiveStatusConvertor.class)
	//@NotBlank(message="Please Enter Sub Category")
 	//@Range(min = 1, max = 10, message="Please Select Sub Category")
 	private ActiveStatus secondaryStatus=ActiveStatus.No;
	
	@Convert(converter = ActiveStatusConvertor.class)
	//@NotBlank(message="Please Enter Sub Category")
 	//@Range(min = 1, max = 10, message="Please Select Sub Category")
 	private ActiveStatus isActive=ActiveStatus.Yes;
	
	
	@Convert(converter = ActiveStatusConvertor.class)
	//@NotBlank(message="Please Enter Sub Category")
 	//@Range(min = 1, max = 10, message="Please Select Sub Category")
 	private ActiveStatus isDepartmentSpecific=ActiveStatus.Yes;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "roleId",nullable = false,insertable =  false, updatable = false)
 	private Roles role;
	
	@ManyToOne
 	private Users user;
	
	@ManyToOne
	private Users secondaryUser;
	
		
	@EqualsAndHashCode.Exclude
	@ToString.Exclude //,nullable = false,insertable =  false, updatable = false
	@JsonBackReference 
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "approval_process_id", nullable = false,insertable =  false, updatable = false)
	// @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	//@JsonIgnoreProperties(value ="deliveries")
	private ApprovalProcess approvalProcess;
	
}
