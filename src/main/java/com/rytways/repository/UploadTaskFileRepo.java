package com.rytways.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rytways.model.UploadTaskFiles;

@Repository
public interface UploadTaskFileRepo extends JpaRepository<UploadTaskFiles, Integer>{

	List<UploadTaskFiles> getAllUploadedFilesByTaskId(int taskId);
}
