package com.rytways.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.rytways.Categories.ActiveStatus;
import com.rytways.Categories.ActiveStatusConvertor;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table
public class ApprovalProcess extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
   // @GenericGenerator(name = "native", strategy = "native")
	// @Column(name = "orderId", updatable = false, nullable = false)
	  private Long approvalProcessId;
	
//  @Size(min=3, message="OrderNo must be at least 3 characters long")
    private String processName;
    
    private int isEditable=0;
	
	//@Range(min = 1, message="Quantity Must be greater or equal to 1")
 	private String approvalType;
 	
 	
 	@Convert(converter = ActiveStatusConvertor.class)
	//@NotBlank(message="Please Enter Sub Category")
 	//@Range(min = 1, max = 10, message="Please Select Sub Category")
 	private ActiveStatus includeCreator=ActiveStatus.No;
 	
 	private String description;
	
 	@EqualsAndHashCode.Exclude
	@ToString.Exclude
 	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "approval_process_id",nullable = false,insertable=true,updatable=true)
	private List<ApprovalAuthorities>  authorities=new ArrayList<>();	

}
	