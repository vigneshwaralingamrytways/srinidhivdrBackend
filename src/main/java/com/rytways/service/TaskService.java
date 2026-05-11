package com.rytways.service;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rytways.model.AssignedToTask;
import com.rytways.model.ClientMaster;
import com.rytways.model.Task;
import com.rytways.model.TaskMeetingStatus;
import com.rytways.model.TaskSubType;
import com.rytways.model.TaskType;
import com.rytways.model.UploadTaskFiles;
import com.rytways.model.Users;
import com.rytways.repository.AssignedToTaskRepo;
import com.rytways.repository.ClientMasterRepo;
import com.rytways.repository.TaskMeetingStatusRepo;
import com.rytways.repository.TaskRepository;
import com.rytways.repository.TaskSubTypeRepo;
import com.rytways.repository.TaskTypeRepo;
import com.rytways.repository.UploadTaskFileRepo;
import com.rytways.repository.UserRepository;
import com.rytways.specifications.TaskSpecification;
import com.rytways.specifications.TaskSubTypeSpecification;
import com.rytways.specifications.TaskTypeSpecification;

@Service
public class TaskService {

	@Value("${docReportUploadPath}")
	String path;
	
	@Autowired
	private TaskRepository repo;

	@Autowired
	private TaskMeetingStatusRepo statusRepo;

	@Autowired
	private AssignedToTaskRepo assignedRepo;

	@Autowired
	private TaskTypeRepo taskTypeRepo;

	@Autowired
	private TaskSubTypeRepo taskSubTypeRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private UploadTaskFileRepo uploadTaskFileRepo;
	
	@Autowired
	private ClientMasterRepo clientMasterRepo;

	public List<Task> getAllTask() {

		return repo.findAll();
	}

	public Task createNewTask(Task task) {
		task = repo.save(task);
		TaskType taskType = taskTypeRepo.findById(task.getTaskTypeId()).orElse(null);
		TaskSubType taskSubType = taskSubTypeRepo.findById(task.getTaskSubTypeId()).orElse(null);
		Users user = userRepo.findById(task.getOwner()).orElse(null);
		ClientMaster client=clientMasterRepo.findById(task.getClientId()).orElse(null);
		task.setClientMaster(client);
		task.setUser(user);
		task.setTaskTypeClass(taskType);
		task.setTaskSubTypeClass(taskSubType);
		return task;
	}

	public String deleteTask(int taskId) {
		repo.deleteById(taskId);
		return "Deleted";
	}

	public Task updateTask(int taskId, Task task) {
		Task t = repo.findById(taskId).orElse(null);
		t.setActualEndDate(task.getActualEndDate());
		t.setStartDate(task.getStartDate());
		t.setExpectedEndDate(task.getExpectedEndDate());
		t.setDescription(task.getDescription());
		t.setTaskTypeId(task.getTaskTypeId());
		t.setTaskSubTypeId(task.getTaskSubTypeId());
		t.setOwner(task.getOwner());
		t = repo.save(t);
		int taskTypeId = (int) (task.getTaskTypeId());
		int taskSubTypeId = (int) (task.getTaskSubTypeId());
		TaskType taskType = taskTypeRepo.findById(taskTypeId).orElse(null);
		TaskSubType taskSubType = taskSubTypeRepo.findById(taskSubTypeId).orElse(null);
		ClientMaster client=clientMasterRepo.findById(task.getClientId()).orElse(null);
		t.setClientMaster(client);
		t.setTaskTypeClass(taskType);
		t.setTaskSubTypeClass(taskSubType);
		t.setTaskTypeId(taskTypeId);
		t.setTaskSubTypeId(taskSubTypeId);
		return t;
	}

	public Task taskStatus(TaskMeetingStatus stats) {
		stats = statusRepo.save(stats);
		Task task = repo.findById(stats.getTaskId()).orElse(null);
		task.setStatus(stats.getStatus());
		task = repo.save(task);
		return task;
	}

	public String updateTaskStatus(int taskId, Task status) {
		Task task = repo.findById(taskId).orElse(null);
		task.setStatus(status.getStatus());
		String sta = status.getStatus();
		repo.save(task);
		return sta;
	}

	public TaskMeetingStatus getTaskStatus(int taskId) {
		TaskMeetingStatus stats = statusRepo.findByTaskId(taskId).orElse(null);
		return stats;
	}

	// --------------------------Assinged-----------------------

	public List<AssignedToTask> getAllAssigned(int taskId) {

		return assignedRepo.findByTaskId(taskId);
	}

	public AssignedToTask createUsers(AssignedToTask assignedToTask) {
		assignedToTask = assignedRepo.save(assignedToTask);
		Users user = userRepo.findById(assignedToTask.getUser()).orElse(null);
		Task task = repo.findById(assignedToTask.getTaskId()).orElse(null);
		assignedToTask.setUsers(user);
		assignedToTask.setTask(task);
		return assignedToTask;
	}

	public String updateUsers(int assignedToTaskId, AssignedToTask assignedToTask) {
		AssignedToTask task = assignedRepo.findById(assignedToTaskId).orElse(null);
		task.setUsers(assignedToTask.getUsers());
		task.setUserType(assignedToTask.getUserType());
		task.setTaskId(assignedToTask.getTaskId());
		assignedRepo.save(task);
		return "updated";
	}

	public String deleteAssigned(int assignedToTaskId) {
		AssignedToTask task = assignedRepo.findById(assignedToTaskId).orElse(null);
		assignedRepo.delete(task);
		return "deleted";
	}

	// ----------------TaskType--------------------------------

	public List<TaskType> getAllTaskType() {
		return taskTypeRepo.findAll();
	}

	public TaskType createTaskType(TaskType taskType) {
		taskTypeRepo.save(taskType);
		return taskType;
	}

	public List<TaskSubType> getAllTaskSubType() {

		return taskSubTypeRepo.findAll();
	}

	public TaskSubType createTaskSubType(TaskSubType taskSubType) {
		taskSubType = taskSubTypeRepo.save(taskSubType);
		TaskType taskType = taskTypeRepo.findById(taskSubType.getTaskTypeId()).orElse(null);
		taskSubType.setTaskType(taskType);
		return taskSubType;
	}

	public List<TaskSubType> getAllTaskSubTypeByTaskId(int taskTypeId) {

		return taskSubTypeRepo.findByTaskTypeId(taskTypeId);
	}

	public List<UploadTaskFiles> uploadFilesTask(MultipartFile[] files, Integer taskId, String remarks) {
		List<UploadTaskFiles> uploadFiles = new ArrayList<UploadTaskFiles>();
		String uploadDir=path+"taskFiles/";
		File directory= new File(uploadDir);
		if(!directory.exists()) {
			directory.mkdirs();
		}
		
		for (MultipartFile file : files) {
			File saveFile = new File(uploadDir + file.getOriginalFilename());

			try {
				file.transferTo(saveFile);
				UploadTaskFiles uploadFile = new UploadTaskFiles();
				uploadFile.setFileName(file.getOriginalFilename());
				uploadFile.setRemarks(remarks);
				uploadFile.setTaskId(taskId);
				uploadTaskFileRepo.save(uploadFile);
				Task tas=repo.findById(taskId).orElse(null);
				uploadFile.setTask(tas);
				uploadFiles.add(uploadFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return uploadFiles;

	}

	public List<UploadTaskFiles> getAllUploadFilesByTaskId(int taskId) {

		return uploadTaskFileRepo.getAllUploadedFilesByTaskId(taskId);
	}

	public List<Task> searchItems(Task task) {
		Sort sort = Sort.by(Sort.Direction.ASC, "taskId");
		List<Specification<Task>> specs = new ArrayList<>();
		if (task.getFromDate() != null) {
			specs.add(TaskSpecification.poFromDate(task.getFromDate()));
		}
		if (task.getToDate() != null) {
			specs.add(TaskSpecification.poTillDate(task.getToDate()));
		}
		if (task.getTaskTypeId() > 0) {
			specs.add(TaskSpecification.taskType(task.getTaskTypeId()));
		}
		if (task.getTaskSubTypeId() > 0) {
			specs.add(TaskSpecification.taskSubType(task.getTaskSubTypeId()));
		}
		if (task.getOwner() != null && task.getOwner() > 0) {
			specs.add(TaskSpecification.assignedTo(task.getOwner()));
		}
		if (task.getStatus() != null && !task.getStatus().isEmpty()) {
			specs.add(TaskSpecification.taskStatus(task.getStatus()));
		}

		Specification<Task> combinedSpec = specs.stream().reduce(Specification::and).orElse(null);

		return repo.findAll(combinedSpec, sort);
	}

	public List<TaskType> searchItemsByTaskType(TaskType taskType) {
		Sort sort = Sort.by(Sort.Direction.ASC, "taskTypeId");

		Specification spec1 = TaskTypeSpecification.taskTypeValue(taskType.getTaskType());
		Specification spec2 = TaskTypeSpecification.taskStatusValue(taskType.getStatus());

		Specification spec = Specification.where(spec1).and(spec2);

		List<TaskType> meetingCategory = taskTypeRepo.findAll(spec, sort);

		return meetingCategory;

	}

	public List<TaskSubType> searchItemsByTaskSubType(TaskSubType taskSubTypeValue) {
		Sort sort = Sort.by(Sort.Direction.ASC, "taskSubTypeId");

		Specification spec1 = TaskSubTypeSpecification.taskType((Integer) taskSubTypeValue.getTaskTypeId());
		Specification spec2 = TaskSubTypeSpecification.taskSubType(taskSubTypeValue.getTaskSubType());
		Specification spec3 = TaskSubTypeSpecification.status(taskSubTypeValue.getStatus());

		Specification spec = Specification.where(spec1).and(spec2).and(spec3);

		List<TaskSubType> meetingCategory = taskSubTypeRepo.findAll(spec, sort);

		return meetingCategory;

	}

	public List<Task> getAllTaskByUserId(int userId) {
		
		List<Task> createdBy = repo.findByCreateBy(userId);
		List<Task> participant = repo.findByOwner(userId);
        List<AssignedToTask> assigned=assignedRepo.findByUser(userId);
		
        Map<Integer, Task> uniqueTask=new LinkedHashMap<>();
        for(Task task:createdBy) {
        	uniqueTask.put(task.getTaskId(), task);
        }
        for(Task task:participant) {
        	uniqueTask.put(task.getTaskId(), task);
        }
        for(AssignedToTask assing:assigned) {
        	Task task=assing.getTask();
        	uniqueTask.put(task.getTaskId(), task);
        }
        
		return new ArrayList<>(uniqueTask.values());
	}

	public List<Task> getAllAssignedByUserId(int userId) {
		List<Task> participant = repo.findByOwner(userId);
		return participant;
	}

}
