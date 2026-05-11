package com.rytways.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.rytways.model.GroupCategory;
import com.rytways.repository.MeetingCategoryRepo;
import com.rytways.specifications.MeetingCategorySpecs;

@Service
public class MeetingCategoryService {

	@Autowired
	private MeetingCategoryRepo meetingCategoryRepo;

	public GroupCategory addNewGroup(GroupCategory groupCategory) {

		return meetingCategoryRepo.save(groupCategory);
	}

	public List<GroupCategory> getAllMeetingCategory() {
		return meetingCategoryRepo.findAll();
	}

	public String deleteMeetingCategory(int groupCategory) {

		meetingCategoryRepo.deleteById(groupCategory);
		return "Successfully Deleted";
	}

	public List<GroupCategory> searchItems(GroupCategory group) {
		Sort sort = Sort.by(Sort.Direction.ASC, "meetingGroupId");

		Specification spec1 = MeetingCategorySpecs.meetingCategory(group.getMeetingCategory());
		Specification spec2 = MeetingCategorySpecs.status(group.getStatus());
		Specification spec = Specification.where(spec1).and(spec2);
		List<GroupCategory> meetingCategory = meetingCategoryRepo.findAll(spec, sort);

		return meetingCategory;

	}

}
