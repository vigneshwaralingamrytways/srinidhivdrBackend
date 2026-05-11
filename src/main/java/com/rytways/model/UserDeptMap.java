package com.rytways.model;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.rytways.Categories.ActiveStatus;
import com.rytways.Categories.ActiveStatusConvertor;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
public class UserDeptMap extends BaseEntity{
	
	@Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int userDeptMapId;
	
	//@NotBlank(message="Product Name must not be blank")
	private Integer userId;
	
	//@NotBlank(message="Process Name must not be blank")
	private Integer departmentId;
	
	//@NotBlank(message="Process Name must not be blank")
	
	//@NotBlank(message="Process Name must not be blank")
	//@Size(min=3,max=100, message="Customer Name must be between 3 to 100 characters")
	@Convert(converter = ActiveStatusConvertor.class)
	private ActiveStatus isActive=ActiveStatus.Yes;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "departmentId",nullable = false,insertable =  false, updatable = false)
 	private DepartmentMaster department;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude //,nullable = false,insertable =  false, updatable = false
	@JsonBackReference 
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", nullable = false,insertable =  false, updatable = false)
	// @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	//@JsonIgnoreProperties(value ="deliveries")
	private Users user;
}
