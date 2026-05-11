package com.rytways.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rytways.model.ApprovalAuthorities;
import com.rytways.model.ApprovalProcess;
import com.rytways.model.ApprovalsHistory;
import com.rytways.model.Users;
import com.rytways.repository.ApprovalAuthoritiesRepo;
import com.rytways.repository.ApprovalProcessRepo;
import com.rytways.repository.ApprovalsHistoryRepo;
import com.rytways.repository.UserRepository;


@Component
@Service
@Transactional
public class ApprovalHistoryService {
	@Autowired
	private ApprovalsHistoryRepo approvalHistRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	
	
	@Autowired
	private ApprovalAuthoritiesRepo approvalAuthRepo;
	
	@Autowired
	private ApprovalProcessRepo appProcessRepo;
	
	
	
	
	public Map saveHistoryApproval(ApprovalsHistory approvalHist){
		String entryStatus=null;
		String isSaved = null;
		int status = 0;
		try {
		Optional<ApprovalsHistory> appHistFromDb = approvalHistRepo.findById(approvalHist.getApprovalHistoryId());
		Optional<ApprovalAuthorities> approvalAuth = approvalAuthRepo.findById(appHistFromDb.get().getAuthorityId());
		if(appHistFromDb.isPresent()) {
			appHistFromDb.get().setIsApproved(approvalHist.getIsApproved());
			appHistFromDb.get().setIsCancelled(approvalHist.getIsCancelled());
			appHistFromDb.get().setApproverRemarks(approvalHist.getApproverRemarks());
			appHistFromDb.get().setApprovedBy(approvalHist.getApprovedBy());
			approvalHist= approvalHistRepo.save(appHistFromDb.get());
			if(approvalHist.getIsCancelled()==1) {
				approvalHistRepo.updateShowApproval(appHistFromDb.get().getAppType(),appHistFromDb.get().getId());
				entryStatus = "Cancelled";
				// Start
				
				if(appHistFromDb.get().getAppType().equals("Purchase Order")) {
					
				
			}
			if(approvalHist.getIsCancelled()==0) {
			//	List<ApprovalsHistory> appHistList = approvalHistRepo.findByIsApprovedAndAppTypeAndId(0,appHistFromDb.get().getAppType(),appHistFromDb.get().getId());
				entryStatus = approvalHist.getAuthoriy().getApprovalName();
				
				
				
				
				
				// Start
				
List<ApprovalsHistory> appHistList = approvalHistRepo.findByIsApprovedAndAppTypeAndId(0,appHistFromDb.get().getAppType(),appHistFromDb.get().getId());
				
				if(appHistList.size() == 0) {
					if(appHistFromDb.get().getAppType().equals("Purchase Request")) {
						
						
					}else {
						
					}
				}
				
			// End
			}
			System.out.println(entryStatus);
			
			isSaved = "Approved Sucessfully";
			status=1;
		}else {
			throw new Exception("Unable to save");
		}}
	
		}catch(Exception e){
			e.printStackTrace();
			isSaved = "Failed to Approve";
			status=0;
		}
		 Map map = new HashMap();
	     map.put("message",isSaved);
	     map.put("status",status);
	     Map retValues = new HashMap();;
	    retValues.put("retValues",map);
		return retValues;
    }
	
	
	public List<ApprovalsHistory> listHistoryApprovals(ApprovalProcess appPro){
		String isSaved = "";
		int status = 0;
		List<ApprovalsHistory> appHists = approvalHistRepo
				.findByAppTypeAndIdOrderByApprovalHistoryId(appPro.getProcessName(), appPro.getApprovalProcessId());
		
		try {
		for(ApprovalsHistory appHist : appHists) {
			if(appHist.getIsApproved()==1 || appHist.getIsCancelled()==1) {
				Optional<Users> user = userRepo.findById(appHist.getApprovedBy());
				if(user.isPresent()) {
					appHist.setApprovalPerson(user.get());
				}
			}
		}
	} catch(Exception e) {
		e.printStackTrace();
	}
        return appHists;
    }
	
	
    
}