package com.rytways.specifications;

import org.springframework.data.jpa.domain.Specification;

import com.rytways.model.TaskSubType;
import com.rytways.model.TaskType;

public class TaskSubTypeSpecification {

	public static Specification<TaskSubType> taskType(Integer taskTypeId) {
		return (root, query, builder) -> taskTypeId == null || taskTypeId < 0 ? builder.conjunction()
				: builder.equal(root.get("taskTypeId"), taskTypeId);
	}
	
	public static Specification<TaskSubType> taskSubType(String taskSubType) {
		return (root, query, builder) -> taskSubType == null || taskSubType.isEmpty() ? builder.conjunction()
				: builder.equal(root.get("taskSubType"), taskSubType);
	}
	
	public static Specification<TaskSubType> status(String status) {
		return (root, query, builder) -> status == null || status.isEmpty() ? builder.conjunction()
				: builder.equal(root.get("status"), status);
	}
}
