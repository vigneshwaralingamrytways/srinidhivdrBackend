package com.rytways.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rytways.model.Approval;
import com.rytways.repository.ApprovalRepository;
import com.rytways.repository.UserRepository;

import net.sf.jasperreports.engine.JasperReport;

@Service
public class ApprovalService {

	@Autowired
	ApprovalRepository approvalRepository;

	
	
	@Autowired
	UserRepository userRepo;

	
	

	JasperReport jasperReport;

//	@Value("${jrxmlfilePathToInputSheetpm1}")
//	private String filePathpm1;
//
//	@Value("${jrxmlfilePathToInputSheetpm2}")
//	private String filePathpm2;
//
//	@Value("${jrxmlfilePathToInputSheetpm3}")
//	private String filePathpm3;
//
//	@Value("${jrxmlfilePathToInputSheetpm4}")
//	private String filePathpm4;
//
//	@Value("${jrxmlfilePathToInputSheetpm5}")
//	private String filePathpm5;
//
//	@Value("${pathofpdfReport}")
//	private String pathToPDF;
//	
//	@Value("${jrxmlfilePathToInputSheetpm6}")
//	private String filePathpm6;
//	
//	
//	@Value("${companyImageFilePath}")
//	private String imageLocation;
	

	public Approval saveApprovalDetails(Approval approval) {

		approval = approvalRepository.save(approval);

		return approval;
	}

	



}