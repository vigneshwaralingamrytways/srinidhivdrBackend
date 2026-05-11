package com.rytways.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Documents extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
   // @GenericGenerator(name = "native", strategy = "native")
	// @Column(name = "orderId", updatable = false, nullable = false)
	 private long docId;
   
    private String fileName;

    private String type;
    
    private String generatedFileName;
    
    private long entryId;
    
    private String entryType;

	private String remarks;
    
    @Transient
    private MultipartFile file;
   
 }
