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
public class AssignedToTask extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private int assignedToTaskId;

	private Integer user;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user", nullable = false, insertable = false, updatable = false)
	private Users users;

	private String userType;

	private int taskId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "taskId", nullable = false, insertable = false, updatable = false)
	private Task task;
}
