package com.rytways.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.rytways.model.DocsUpload;

@Repository
public interface DocsUploadRepo extends JpaRepository<DocsUpload,Integer>,JpaSpecificationExecutor<DocsUpload> {
	
	Optional<List<DocsUpload>> findByIdAndTypeIdOrderByUpdatedOnDesc(int poId,String type);

	
}
