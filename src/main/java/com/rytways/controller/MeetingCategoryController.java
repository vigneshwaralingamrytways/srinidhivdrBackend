package com.rytways.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rytways.model.GroupCategory;
import com.rytways.service.MeetingCategoryService;

@RestController
@RequestMapping("/meetingCategory")
public class MeetingCategoryController {

	@Autowired
	private MeetingCategoryService meetingCategoryService;

	@PostMapping("/getAllMeetingCategory")
	public ResponseEntity<List<GroupCategory>> getAllMeeting() {

		return new ResponseEntity<List<GroupCategory>>(meetingCategoryService.getAllMeetingCategory(), HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<GroupCategory> addNewGroup(@RequestBody GroupCategory groupCategory) {
		return new ResponseEntity<GroupCategory>(meetingCategoryService.addNewGroup(groupCategory), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{meetingGroupId}")
	public ResponseEntity<String> deleteMeetingCategory(@PathVariable int meetingGroupId) {
		return new ResponseEntity<String>(meetingCategoryService.deleteMeetingCategory(meetingGroupId), HttpStatus.OK);
	}

	@PostMapping("/search")
	public ResponseEntity<List<GroupCategory>> searchOrder(@RequestBody GroupCategory po) {

		List<GroupCategory> matPrItems = meetingCategoryService.searchItems(po);
		return new ResponseEntity<>(matPrItems, HttpStatus.OK);
	}
}
