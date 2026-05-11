package com.rytways.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Task extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private int taskId;

	@Transient
	private LocalDate fromDate;

	@Transient
	private LocalDate toDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate startDate;

	private String description;
	
	private int clientId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "clientId", nullable = false, insertable = false, updatable = false)
	private ClientMaster clientMaster;
	
	private Integer createBy;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "createBy", nullable = false, insertable = false, updatable = false)
	private Users created;

	private Integer owner;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "owner", nullable = false, insertable = false, updatable = false)
	private Users user;

	private LocalDate expectedEndDate;

	private String status = "YetToStart";

	private int taskTypeId;

	private int taskSubTypeId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "taskTypeId", nullable = false, insertable = false, updatable = false)
	private TaskType taskTypeClass;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "taskSubTypeId", nullable = false, insertable = false, updatable = false)
	private TaskSubType taskSubTypeClass;

	private LocalDate actualEndDate;

	private String itemOne;
	private String itemTwo;
	private String itemThree;
	private String itemFour;
	private String itemFive;
	private String itemSix;
	private String itemSeven;
	private String itemEight;
	private String itemNine;
	private String itemTen;
	private String itemEleven;
	private String itemTwelve;
	private String itemThirteen;
	private String itemFourteen;
	private String itemFifteen;
	private LocalDate itemSixteen;
	private LocalDate itemSeventeen;
	private String itemRemarks;
}
