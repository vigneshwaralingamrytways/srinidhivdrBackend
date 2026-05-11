package com.rytways.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class EmployeeDetailsForOrgChart extends BaseEntity{
	
	@Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int empId;
	
	private String employeeName;
	
	private LocalDate dob;
	
	private int chartId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "chartId",nullable = false,insertable =  false, updatable = false)
	private OrganizationChart organizationChart;

}
