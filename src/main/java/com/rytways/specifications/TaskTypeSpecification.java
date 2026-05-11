package com.rytways.specifications;

import org.springframework.data.jpa.domain.Specification;

import com.rytways.model.TaskType;

public class TaskTypeSpecification {


	public static Specification<TaskType> taskTypeValue(String taskType) {
		return (root, query, builder) -> taskType == null || taskType.isEmpty() ? builder.conjunction()
				: builder.equal(root.get("taskType"), taskType);
	}
	
	public static Specification<TaskType> taskStatusValue(String status) {
		return (root, query, builder) -> status == null || status.isEmpty() ? builder.conjunction()
				: builder.equal(root.get("status"), status);
	}

}
