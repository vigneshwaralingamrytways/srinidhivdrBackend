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

@Getter
@Setter
@Entity
public class MeetingParticipants extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int participantsId;

    private int meetingTransactionId;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "meetingTransactionId",nullable = false,insertable =  false, updatable = false)
    private MeetingTransaction meetingTransaction;

//    private Integer meetingUserMasterId;
    
    private String participantName;

    private String participantEmailId;
}
