package com.rytways.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.Proxy;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@JsonNaming()
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernate_lazy_initializer", "handler"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Proxy(lazy = false)
public class DocsUpload  extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
   // @GenericGenerator(name = "native", strategy = "native")
	// @Column(name = "orderId", updatable = false, nullable = false)
	 private int docsId;
	

	private String fileName;

    private String type;
    
    private String generatedFileName;
    
	private int id;
	
	private String typeId;
	
	private String remarks;
    
    @Transient
    private MultipartFile file;	
}
