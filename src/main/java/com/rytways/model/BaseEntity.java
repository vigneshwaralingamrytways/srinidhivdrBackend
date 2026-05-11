package com.rytways.model;


import java.time.LocalDateTime;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
	//@CreatedDate
	//@LastModifiedDate
    private LocalDateTime updatedOn = LocalDateTime.now();
    
   // @CreatedBy
    //@LastModifiedBy
    private Integer updatedBy=1;
    
    
    private LocalDateTime createdOn;
    
    // @CreatedBy
     //@LastModifiedBy
     private Integer createdBy=1;
}
