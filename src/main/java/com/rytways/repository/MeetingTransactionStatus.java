package com.rytways.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rytways.model.MeetingStatus;

@Repository
public interface MeetingTransactionStatus extends JpaRepository<MeetingStatus, Integer>{

	MeetingStatus findByMeetingTransactionId(@Param("meetingTransactionId") int meetingTransactionId);
}
