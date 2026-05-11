package com.rytways.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rytways.model.MeetingTransaction;

@Repository
public interface MeetingTransactionRepo extends JpaRepository<MeetingTransaction, Integer>,JpaSpecificationExecutor<MeetingTransaction>{

	MeetingTransaction findByMeetingGroupId(int meetingGroupId);

	@Query(value="select * from meeting_transaction where hosted_by=:userId",nativeQuery = true)
	List<MeetingTransaction> findByUserId(int userId);
}
