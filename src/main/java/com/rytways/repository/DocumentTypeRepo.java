package com.rytways.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.rytways.model.DocumentTypeMaster;
import com.rytways.model.SubFolderMaster;

@Repository
public interface DocumentTypeRepo extends JpaRepository<DocumentTypeMaster, Long>,JpaSpecificationExecutor<DocumentTypeMaster>{
	List<DocumentTypeMaster> findByStatus(String status);

	List<SubFolderMaster> findByDocumentTypeId(Long documentTypeId);
}
