package com.rytways.specifications;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import com.rytways.model.MeetingTransaction;

public class MeetingTransactionSpecification {
	
	public static Specification<MeetingTransaction> poFromDate(LocalDate fromDate) {
		return (root, query, builder) -> fromDate == null ? builder.conjunction()
				: builder.greaterThanOrEqualTo(root.get("dateOfMeeting"), fromDate.atStartOfDay());
	}
	

	public static Specification<MeetingTransaction> poTillDate(LocalDate toDate) {
		return (root, query, builder) -> toDate == null ? builder.conjunction()
				: builder.lessThanOrEqualTo(root.get("dateOfMeeting"), toDate.atTime(23,59));
	}


	public static Specification<MeetingTransaction> meetingCategory(Integer meetingGroupId) {
		return (root, query, builder) -> meetingGroupId == null || meetingGroupId < 0 ? builder.conjunction()
				: builder.equal(root.get("meetingGroupId"), meetingGroupId);
	}
	
	public static Specification<MeetingTransaction> status(String ajenta) {
		return (root, query, builder) -> ajenta == null || ajenta.isEmpty() ? builder.conjunction()
				: builder.equal(root.get("ajenta"), ajenta);
	}
}
