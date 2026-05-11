package com.rytways.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.rytways.model.Documents;

@Repository
public interface DocumentsRepo extends JpaRepository<Documents, Long>, JpaSpecificationExecutor<Documents>{
	
	
	Optional<List<Documents>> findByEntryIdAndEntryTypeOrderByUpdatedOn(Long entryId,String entryType);
}
