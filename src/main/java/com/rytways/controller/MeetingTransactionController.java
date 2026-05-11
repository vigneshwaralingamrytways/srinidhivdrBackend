package com.rytways.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rytways.model.CreateTask;
import com.rytways.model.MeetingMinutes;
import com.rytways.model.MeetingParticipants;
import com.rytways.model.MeetingStatus;
import com.rytways.model.MeetingTransaction;
import com.rytways.service.MeetingTransactionService;

@RestController
@RequestMapping("/meetingTransaction")
public class MeetingTransactionController {
	
	@Autowired
	private MeetingTransactionService meetingTransactionService;
	
	@PostMapping("/create")
	public ResponseEntity<MeetingTransaction> createNewMeeting(@RequestBody MeetingTransaction meeting){
		return new ResponseEntity<MeetingTransaction>(meetingTransactionService.createNewMeeting(meeting),HttpStatus.OK);
	}
	
	@PutMapping("/update/{meetingTransactionId}")
	public ResponseEntity<MeetingTransaction> updateStatusData(@PathVariable int meetingTransactionId,@RequestBody MeetingTransaction dateOfMeeting){
		
		return new ResponseEntity<MeetingTransaction>(meetingTransactionService.updateStatusData(meetingTransactionId,dateOfMeeting),HttpStatus.OK);
	}
	
	@PostMapping("/getAllMeeting")
	public ResponseEntity<List<MeetingTransaction>> getAllMeeting()
	{
		return new ResponseEntity<List<MeetingTransaction>>(meetingTransactionService.getAllData(),HttpStatus.OK);
	}
	
	@PostMapping("/getAllMeetingByUserId/{userId}")
	public ResponseEntity<List<MeetingTransaction>> getAllMeetingByUserId(@PathVariable int userId)
	{
		return new ResponseEntity<List<MeetingTransaction>>(meetingTransactionService.getAllMeetingByUserId(userId),HttpStatus.OK);
	}
	
	@GetMapping("/getMeetingById/{meetingTransactionId}")
	public ResponseEntity<MeetingTransaction> getMeetingById(@PathVariable int meetingTransactionId)
	{
		return new ResponseEntity<MeetingTransaction>(meetingTransactionService.getMeetingById(meetingTransactionId),HttpStatus.OK);
	}
	
	@PostMapping("/getMeetingStatusById/{meetingTransactionId}")
	public ResponseEntity<MeetingStatus> getMeetingStatusById(@PathVariable int meetingTransactionId)
	{
		return new ResponseEntity<MeetingStatus>(meetingTransactionService.getMeetingStatusById(meetingTransactionId),HttpStatus.OK);
	}
	
	@PostMapping("/search")
    public ResponseEntity<List<MeetingTransaction>> searchOrder(@RequestBody MeetingTransaction meeting){
	 		
			 	
		 	List<MeetingTransaction> matPrItems = meetingTransactionService.searchItems(meeting);
	       
	        return new ResponseEntity<>(matPrItems,HttpStatus.OK);
     }
	
	//----------------MeetingStatus----------------------------
	
	@PostMapping("/updateStatus")
	public ResponseEntity<MeetingTransaction> updateStatus(@RequestBody MeetingStatus stats )
	{
		return new ResponseEntity<MeetingTransaction>(meetingTransactionService.updateStatus(stats),HttpStatus.OK);
	}
	
	//-----------------MinutesOfMeeting---------------------------------------------------

	@PostMapping("/minutesOfMeeting/getAllMinutesOfMeeting/{meetingTransactionId}")
	public ResponseEntity<List<MeetingMinutes>> getAllMinutesOfMeeting(@PathVariable int meetingTransactionId)
	{
		return new ResponseEntity<List<MeetingMinutes>>(meetingTransactionService.getAllMinutesOfMeeting(meetingTransactionId),HttpStatus.OK);
	}
	
	@PostMapping("/minutesOfMeeting/create")
	public ResponseEntity<MeetingMinutes> createMinuteOfMeeting(@RequestBody MeetingMinutes min)
	{
	
		return new ResponseEntity<MeetingMinutes>(meetingTransactionService.createMinuteOfMeeting(min),HttpStatus.OK);
	}
	
	@DeleteMapping("/minutesOfMeeting/deleteById/{minutesId}")
	public ResponseEntity<String> deleteMinuteOfMeeting(@PathVariable int minutesId)
	{
		return new ResponseEntity<String>(meetingTransactionService.deleteMinuteOfMeeting(minutesId),HttpStatus.OK);
	}
	
	@PutMapping("/updateMinutesOfMeeting/{minutesId}")
	public ResponseEntity<String> updateMinutesOfMeeting(@PathVariable int minutesId,@RequestBody MeetingMinutes meetingMinutes){
		return new ResponseEntity<String>(meetingTransactionService.updateMinutesOfMeeting(minutesId,meetingMinutes),HttpStatus.OK);
	}
	
	//--------------Participants---------------
	
	@PostMapping("/getAllParticipants/{meetingTransactionId}")
	public ResponseEntity<List<MeetingParticipants>> getAllParticipants(@PathVariable int meetingTransactionId)
	{
		List<MeetingParticipants> paticipants=meetingTransactionService.getAllParticipants(meetingTransactionId);
	    return new ResponseEntity<List<MeetingParticipants>>(paticipants,HttpStatus.OK);
	}
	
	@PostMapping("/getParticipants")
	public ResponseEntity<List<MeetingParticipants>> getParticipants()
	{
		List<MeetingParticipants> paticipants=meetingTransactionService.getParticipants();
	    return new ResponseEntity<List<MeetingParticipants>>(paticipants,HttpStatus.OK);
	}
	
	@PostMapping("/createParticipants")
	public ResponseEntity<MeetingParticipants> createParticipants(@RequestBody MeetingParticipants meetingParticipants)
	{
		return new ResponseEntity<MeetingParticipants>(meetingTransactionService.createParticipants(meetingParticipants),HttpStatus.OK);
	}
	
	@PutMapping("/updateParticipants/{participantsId}")
	public ResponseEntity<MeetingParticipants> updateParticipants(@PathVariable int participantsId, @RequestBody MeetingParticipants meetingParticipants)
	{
		return new ResponseEntity<MeetingParticipants>(meetingTransactionService.updateParticipants(participantsId,meetingParticipants),HttpStatus.OK);
	}

	@DeleteMapping("/deleteParticipantsById/{participantsId}")
	public ResponseEntity<String> deleteParticipants(@PathVariable int participantsId)
	{
		return new ResponseEntity<String>(meetingTransactionService.deleteParticipants(participantsId),HttpStatus.OK);
	}
	
	@PostMapping("/sendMail/toParticipants/{meetingTransactionId}")
	public ResponseEntity<String> sendMail(@PathVariable int meetingTransactionId,@RequestBody List<MeetingMinutes> minutes){
		return new ResponseEntity<String>(meetingTransactionService.sendMail(meetingTransactionId,minutes),HttpStatus.OK);
	}
	
	//-----------------------
	
	@PostMapping("/createTask")
	public ResponseEntity<CreateTask> createTask(@RequestBody CreateTask meeting){
		return new ResponseEntity<CreateTask>(meetingTransactionService.createTask(meeting),HttpStatus.OK);
	}
	
}
