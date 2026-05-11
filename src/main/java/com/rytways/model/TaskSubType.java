package com.rytways.model;

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
public class TaskSubType extends BaseEntity{

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
	private int taskSubTypeId;
	
	private int taskTypeId;
	    
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "taskTypeId",nullable = false,insertable =  false, updatable = false)
	private TaskType taskType;
	
	private String taskSubType;	
	
	private String status="0";
}
