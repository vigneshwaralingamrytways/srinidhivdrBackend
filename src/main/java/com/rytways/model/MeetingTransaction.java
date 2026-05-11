package com.rytways.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

@Getter
@Setter
@Entity
public class MeetingTransaction extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int meetingTransactionId;
    
    @Transient
    private LocalDate fromDate;
    
    @Transient
    private LocalDate toDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime dateOfMeeting;

    private int meetingGroupId;
    
    private String meetingMode;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "meetingGroupId",nullable = false,insertable =  false, updatable = false)
 	private GroupCategory category;
    
    private String meetingStatus="Scheduled";  

    private String ajenta;

    private String meetingInviteMessage;
    
    private Integer hostedBy;
    
    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "hostedBy", nullable = false, insertable = false, updatable = false)
	private Users user;
}
