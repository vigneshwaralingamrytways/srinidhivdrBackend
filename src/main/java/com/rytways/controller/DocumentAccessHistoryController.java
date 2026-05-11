package com.rytways.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rytways.model.DocumentAccessHistory;
import com.rytways.service.DocumentAccessHistoryService;

@RestController
@RequestMapping("/documentAccess")
public class DocumentAccessHistoryController {

	@Autowired
	private DocumentAccessHistoryService documentAccessHistoryService;
	
	@PostMapping("/getAllDocumentAccess/{reportDocId}")
	public ResponseEntity<List<DocumentAccessHistory>> getAllDocumentAccess(@PathVariable Long reportDocId)
	{
		return new ResponseEntity<List<DocumentAccessHistory>>(documentAccessHistoryService.getAllDocumentAccess(reportDocId),HttpStatus.OK);
	}
}
