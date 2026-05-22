package com.rytways.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rytways.model.DocumentTypeMaster;
import com.rytways.model.SubFolderMaster;

@Repository
public interface DocumentTypeRepo
		extends JpaRepository<DocumentTypeMaster, Long>, JpaSpecificationExecutor<DocumentTypeMaster> {
	List<DocumentTypeMaster> findByStatus(String status);

	List<SubFolderMaster> findByDocumentTypeId(Long documentTypeId);

	@Query("SELECT DISTINCT d FROM DocumentTypeMaster d "
			+ "JOIN DocumentUserMaster u ON d.documentTypeId = u.documentTypeId "
			+ "WHERE d.status = 'Active' AND u.userId = :userId " + "ORDER BY u.updatedOn ASC")
	List<DocumentTypeMaster> findActiveDocsByUserId(@Param("userId") Integer userId);
}
