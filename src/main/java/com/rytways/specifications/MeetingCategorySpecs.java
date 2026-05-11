package com.rytways.specifications;

import org.springframework.data.jpa.domain.Specification;

import com.rytways.model.GroupCategory;

public class MeetingCategorySpecs {

	public static Specification<GroupCategory> meetingCategory(String meetingCategory) {
		return (root, query, builder) -> meetingCategory == null || meetingCategory.isEmpty() ? builder.conjunction()
				: builder.equal(root.get("meetingCategory"), meetingCategory);
	}
	
	public static Specification<GroupCategory> status(String status) {
		return (root, query, builder) -> status == null || status.isEmpty() ? builder.conjunction()
				: builder.equal(root.get("status"), status);
	}
}
