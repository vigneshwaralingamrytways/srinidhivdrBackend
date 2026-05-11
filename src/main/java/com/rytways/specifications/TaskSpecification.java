package com.rytways.specifications;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import com.rytways.model.Task;
import com.rytways.model.TaskSubType;
import com.rytways.model.TaskType;

public class TaskSpecification {

	public static Specification<Task> poFromDate(LocalDate fromDate) {
		return (root, query, builder) -> fromDate == null ? builder.conjunction()
				: builder.greaterThanOrEqualTo(root.get("startDate"), fromDate);
	}
	

	public static Specification<Task> poTillDate(LocalDate toDate) {
		return (root, query, builder) -> toDate == null ? builder.conjunction()
				: builder.lessThanOrEqualTo(root.get("startDate"), toDate);
	}
	
	public static Specification<Task> taskType(Integer taskTypeId) {
		return (root, query, builder) -> taskTypeId == null  ? builder.conjunction()
				: builder.equal(root.get("taskTypeId"), taskTypeId);
	}
	
	public static Specification<Task> taskSubType(Integer taskSubTypeId) {
		return (root, query, builder) -> taskSubTypeId == null ? builder.conjunction()
				: builder.equal(root.get("taskSubTypeId"), taskSubTypeId);
	}
	
	public static Specification<Task> assignedTo(Integer owner) {
		return (root, query, builder) -> owner == null || owner < 0 ? builder.conjunction()
				: builder.equal(root.get("owner"), owner);
	}


	public static Specification<Task> taskStatus(String status) {
		return (root, query, builder) -> status == null || status.isEmpty() ? builder.conjunction()
				: builder.equal(root.get("status"), status);
	}

}
