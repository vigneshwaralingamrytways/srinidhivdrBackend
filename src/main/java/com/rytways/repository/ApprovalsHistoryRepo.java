package com.rytways.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rytways.model.ApprovalsHistory;

@Repository
public interface ApprovalsHistoryRepo extends JpaRepository<ApprovalsHistory,Integer>,JpaSpecificationExecutor<ApprovalsHistory> {
	
		List<ApprovalsHistory> findByAppTypeAndIdOrderByApprovalHistoryId(String appType,Long id);
	
	
	 	@Modifying
	    @Query("UPDATE ApprovalsHistory ah SET ah.showApproval = 0 WHERE ah.appType= :appType and "
	    		+ "ah.id = :id and ah.isApproved=0")
	    void updateShowApproval(@Param("appType") String appType, @Param("id") Long id);
	 	
	 	List<ApprovalsHistory> findByIsApprovedAndAppTypeAndId(int isApproved,String appType,Long id);
	 	
	 	@Modifying
	    @Query("UPDATE ApprovalsHistory ah SET ah.isApproved = 0 WHERE ah.appType= :appType and "
	    		+ "ah.id = :id and ah.isApproved=1 and ah.authorityId>0")
	    void updateApprovalStatus(@Param("appType") String appType, @Param("id") Long id);
	 	
	 	
}
