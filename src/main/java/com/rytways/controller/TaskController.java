package com.rytways.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rytways.model.AssignedToTask;
import com.rytways.model.Task;
import com.rytways.model.TaskMeetingStatus;
import com.rytways.model.TaskSubType;
import com.rytways.model.TaskType;
import com.rytways.model.UploadTaskFiles;
import com.rytways.repository.UploadTaskFileRepo;
import com.rytways.service.TaskService;

@RestController
@RequestMapping("/task")
public class TaskController {

	@Autowired
	private TaskService service;

	@Autowired
	private UploadTaskFileRepo fileRepo;
	
	@Value("${docReportUploadPath}")
	String path;

	@PostMapping("/getAllTask")
	public ResponseEntity<List<Task>> getAllTask() {
		return new ResponseEntity<List<Task>>(service.getAllTask(), HttpStatus.OK);
	}
	
	@PostMapping("/getAllTaskByUserId/{userId}")
	public ResponseEntity<List<Task>> getAllTaskByUserId(@PathVariable int userId) {
		return new ResponseEntity<List<Task>>(service.getAllTaskByUserId(userId), HttpStatus.OK);
	}

	@PostMapping("/createTask")
	public ResponseEntity<Task> createNewTask(@RequestBody Task task) {
		return new ResponseEntity<Task>(service.createNewTask(task), HttpStatus.OK);
	}

	@DeleteMapping("/deleteTask/{taskId}")
	public ResponseEntity<String> deleteTask(@PathVariable int taskId) {
		return new ResponseEntity<String>(service.deleteTask(taskId), HttpStatus.OK);
	}

	@PutMapping("/updateTask/{taskId}")
	public ResponseEntity<Task> updateTask(@PathVariable int taskId, @RequestBody Task task) {
		return new ResponseEntity<Task>(service.updateTask(taskId, task), HttpStatus.OK);
	}

	@PutMapping("/updateTaskStatus/{taskId}")
	public ResponseEntity<String> updateTaskStatus(@PathVariable int taskId, @RequestBody Task status) {
		return new ResponseEntity<String>(service.updateTaskStatus(taskId, status), HttpStatus.OK);
	}

	@PostMapping("/search")
	public ResponseEntity<List<Task>> searchOrder(@RequestBody Task task) {

		List<Task> matPrItems = service.searchItems(task);

		return new ResponseEntity<>(matPrItems, HttpStatus.OK);
	}
	// ------------------TaskStatus-----------------------

	@PostMapping("/status")
	public ResponseEntity<Task> taskStatus(@RequestBody TaskMeetingStatus task) {
		return new ResponseEntity<Task>(service.taskStatus(task), HttpStatus.OK);
	}

	@PostMapping("/getTaskStatus/{taskId}")
	public ResponseEntity<TaskMeetingStatus> getTaskSTats(@PathVariable int taskId) {
		return new ResponseEntity<TaskMeetingStatus>(service.getTaskStatus(taskId), HttpStatus.OK);
	}

	// -----------------AssignedTO------------------------
	@PostMapping("/getAllAssigned/{taskId}")
	public ResponseEntity<List<AssignedToTask>> getAllAssigned(@PathVariable int taskId) {
		return new ResponseEntity<List<AssignedToTask>>(service.getAllAssigned(taskId), HttpStatus.OK);
	}
	
	@PostMapping("/getAllAssignedByUserId/{userId}")
	public ResponseEntity<List<Task>> getAllAssignedByUserId(@PathVariable int userId) {
		return new ResponseEntity<List<Task>>(service.getAllAssignedByUserId(userId), HttpStatus.OK);
	}


	@PostMapping("/createUsers")
	public ResponseEntity<AssignedToTask> createUsers(@RequestBody AssignedToTask assignedToTask) {
		return new ResponseEntity<AssignedToTask>(service.createUsers(assignedToTask), HttpStatus.OK);
	}

	@PutMapping("/updateusers/{assignedToTaskId}")
	public ResponseEntity<String> updateUsers(@PathVariable int assignedToTaskId,
			@RequestBody AssignedToTask assignedToTask) {
		return new ResponseEntity<String>(service.updateUsers(assignedToTaskId, assignedToTask), HttpStatus.OK);
	}

	@DeleteMapping("/deleteAssigned/{assignedToTaskId}")
	public ResponseEntity<String> deleteAssigned(@PathVariable int assignedToTaskId) {
		return new ResponseEntity<String>(service.deleteAssigned(assignedToTaskId), HttpStatus.OK);
	}

	// ------------------Task Type-------------------------------

	@PostMapping("/getAllTaskType")
	public ResponseEntity<List<TaskType>> getAllTaskType() {
		return new ResponseEntity<List<TaskType>>(service.getAllTaskType(), HttpStatus.OK);
	}

	@PostMapping("/taskType/create")
	public ResponseEntity<TaskType> createTaskType(@RequestBody TaskType taskType) {
		return new ResponseEntity<TaskType>(service.createTaskType(taskType), HttpStatus.OK);
	}

	@PostMapping("/taskType/search")
	public ResponseEntity<List<TaskType>> searchOrderByTaskType(@RequestBody TaskType taskType) {

		List<TaskType> matPrItems = service.searchItemsByTaskType(taskType);

		return new ResponseEntity<>(matPrItems, HttpStatus.OK);
	}

	// ------------------Task SubType-------------------------------

	@PostMapping("/getAllTaskSubType")
	public ResponseEntity<List<TaskSubType>> getAllTaskSubType() {
		return new ResponseEntity<List<TaskSubType>>(service.getAllTaskSubType(), HttpStatus.OK);
	}

	@PostMapping("/getAllTaskSubTypeByTaskId/{taskTypeId}")
	public ResponseEntity<List<TaskSubType>> getAllTaskSubTypeByTaskId(@PathVariable int taskTypeId) {
		return new ResponseEntity<List<TaskSubType>>(service.getAllTaskSubTypeByTaskId(taskTypeId), HttpStatus.OK);
	}

	@PostMapping("/taskSubType/create")
	public ResponseEntity<TaskSubType> createTaskSubType(@RequestBody TaskSubType taskSubType) {
		return new ResponseEntity<TaskSubType>(service.createTaskSubType(taskSubType), HttpStatus.OK);
	}

	@PostMapping("/taskSubType/search")
	public ResponseEntity<List<TaskSubType>> searchOrderByTaskSubType(@RequestBody TaskSubType taskSubType) {

		List<TaskSubType> matPrItems = service.searchItemsByTaskSubType(taskSubType);

		return new ResponseEntity<>(matPrItems, HttpStatus.OK);
	}

	// --------------------------uploadFiles-----------------

	@PostMapping("/uploadFilesInTask")
	public ResponseEntity<List<UploadTaskFiles>> uploadFilesTask(@RequestParam("files") MultipartFile[] files,
			@RequestParam("taskId") Integer taskId, @RequestParam("remarks") String remarks) {
		return new ResponseEntity<List<UploadTaskFiles>>(service.uploadFilesTask(files, taskId, remarks), HttpStatus.OK);
	}

	@PostMapping("/getAllUploadFilesByTaskId/{taskId}")
	public ResponseEntity<List<UploadTaskFiles>> getAllUploadFilesByTaskId(@PathVariable int taskId) {
		return new ResponseEntity<List<UploadTaskFiles>>(service.getAllUploadFilesByTaskId(taskId), HttpStatus.OK);
	}

	@PostMapping("/deleteTaskFilesByFileId/{fileId}")
	public ResponseEntity<?> deleteEntity(@PathVariable int fileId) {
		UploadTaskFiles file=fileRepo.findById(fileId).orElse(null);
		File fi=new File(path+"taskFiles/"+file.getFileName());
		if(fi.exists()) {
			fi.delete();
		}
		fileRepo.deleteById(fileId);
		return new ResponseEntity<>("Entity deleted successfully", HttpStatus.OK);
	}
	
	@PostMapping("/download/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) throws IOException {
    	
        try {
            File file = new File(path+"taskFiles/"+fileName);                
            Path path = Paths.get(file.getAbsolutePath());
            ByteArrayResource resource = 
                   new ByteArrayResource(Files.readAllBytes(path));

            return ResponseEntity.ok().headers(this.headers(fileName))
                  .contentLength(file.length())
                    .contentType(MediaType
                     .parseMediaType("application/octet-stream"))
                 .body(resource);
        } catch (MalformedURLException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    private HttpHeaders headers(String name) {

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, 
                     "attachment; filename=" + name);
        header.add("Cache-Control", 
                     "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");
        return header;

    }

}
