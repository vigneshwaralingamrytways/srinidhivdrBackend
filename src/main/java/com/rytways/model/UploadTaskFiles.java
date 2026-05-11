package com.rytways.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UploadTaskFiles extends BaseEntity{
  
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	 @GenericGenerator(name = "native", strategy = "native")
	 private int fileId;
	 
	 private String remarks;
	 
	 @Transient
	 private MultipartFile files;
	 
	 private String fileName;
	 
	 private int taskId;
	 
	 @ManyToOne(fetch = FetchType.EAGER)
     @JoinColumn(name = "taskId", nullable = false, insertable = false, updatable = false)
	 private Task task;
	
	
}
