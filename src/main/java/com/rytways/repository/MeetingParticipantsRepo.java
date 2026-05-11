package com.rytways.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rytways.model.MeetingMinutes;
import com.rytways.model.MeetingParticipants;

@Repository
public interface MeetingParticipantsRepo extends JpaRepository<MeetingParticipants, Integer>{

	List<MeetingParticipants> findByMeetingTransactionId(int meetingTransactionId);
}
