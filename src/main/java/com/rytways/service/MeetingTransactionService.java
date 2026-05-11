package com.rytways.service;

import java.io.UnsupportedEncodingException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.rytways.model.CreateTask;
import com.rytways.model.GroupCategory;
import com.rytways.model.MeetingMinutes;
import com.rytways.model.MeetingParticipants;
import com.rytways.model.MeetingStatus;
import com.rytways.model.MeetingTransaction;
import com.rytways.model.Users;
import com.rytways.repository.CreateTaskFromMeetingRepo;
import com.rytways.repository.MeetingCategoryRepo;
import com.rytways.repository.MeetingParticipantsRepo;
import com.rytways.repository.MeetingTransactionRepo;
import com.rytways.repository.MeetingTransactionStatus;
import com.rytways.repository.MinutesOfMeetingRepo;
import com.rytways.repository.UserRepository;
import com.rytways.specifications.MeetingTransactionSpecification;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MeetingTransactionService {
	
	 @Value("${spring.mail.username}")
	 private String sender;
	
	@Autowired
	private MeetingTransactionRepo repo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private MeetingTransactionStatus  MeetingTransactionStatusRepo;
	
	@Autowired
	private MinutesOfMeetingRepo minutesOfMeetingRepo;
	
	@Autowired
	private MeetingParticipantsRepo meetingParticipantsRepo;
	
	@Autowired
	private MeetingCategoryRepo meetingCategoryRepo;
	
	@Autowired
	private CreateTaskFromMeetingRepo createTaskFromMeetingRepo;
	
	@Autowired
	private JavaMailSender mailSender;	

	public MeetingTransaction createNewMeeting(MeetingTransaction meeting) {
		DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formatedDateTime=meeting.getDateOfMeeting().format(formatter);
		meeting=repo.save(meeting);
		GroupCategory category= meetingCategoryRepo.findById(meeting.getMeetingGroupId()).orElse(null);
		meeting.setCategory(category);
		Users user=userRepo.findById(meeting.getHostedBy()).orElse(null);
		meeting.setUser(user);
		return meeting;
	}

	public List<MeetingTransaction> getAllData() {

		return repo.findAll();
	}
	
	public List<MeetingTransaction> searchItems(MeetingTransaction group) {
	      
	      Sort sort = Sort.by(Sort.Direction.ASC, "meetingTransactionId");
			List<Specification<MeetingTransaction>> specs = new ArrayList<>();
			
			 if (group.getFromDate() != null) {
			        specs.add(MeetingTransactionSpecification.poFromDate(group.getFromDate()));
			    }
			 if (group.getToDate() != null) {
			        specs.add(MeetingTransactionSpecification.poTillDate(group.getToDate()));
			    }
			 if (group.getMeetingGroupId() > 0) {
			        specs.add(MeetingTransactionSpecification.meetingCategory(group.getMeetingGroupId()));
			    }
			 if (group.getAjenta() != null) {
			        specs.add(MeetingTransactionSpecification.status(group.getAjenta()));
			    }
			 
			  Specification<MeetingTransaction> combinedSpec = specs.stream()
			            .reduce(Specification::and)
			            .orElse(null);

			    return repo.findAll(combinedSpec, sort);
	   }

	//--------------Meeting Status-----------------------------------------

	public MeetingTransaction updateStatus(MeetingStatus stats) {
		
		DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formatedDateTime=stats.getDateOfMeeting().format(formatter);
		stats=MeetingTransactionStatusRepo.save(stats);
		MeetingTransaction transaction=repo.findById(stats.getMeetingTransactionId()).orElse(null);
		transaction.setMeetingStatus(stats.getMeetingStatus());
		transaction.setDateOfMeeting(stats.getDateOfMeeting());
		transaction = repo.save(transaction);
		return transaction;
	}
	
	public MeetingTransaction updateStatusData(int meetingTransactionId,MeetingTransaction dateOfMeeting) {
		MeetingTransaction trans=repo.findById(meetingTransactionId).orElse(null);
		DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formatedDateTime=dateOfMeeting.getDateOfMeeting().format(formatter);
		trans.setMeetingStatus(dateOfMeeting.getMeetingStatus());
	    trans.setDateOfMeeting(dateOfMeeting.getDateOfMeeting());
		repo.save(trans);
		return trans;
	}

	public MeetingStatus getMeetingStatusById(int meetingTransactionId) {
		return MeetingTransactionStatusRepo.findByMeetingTransactionId(meetingTransactionId);
	}

	//------------------MinutesOfMeeting------------------------------------------------
	
	public List<MeetingMinutes> getAllMinutesOfMeeting(int meetingTransactionId) {
		
		return minutesOfMeetingRepo.findByMeetingTransactionId(meetingTransactionId);
	}

	public MeetingMinutes createMinuteOfMeeting(MeetingMinutes min) {
		minutesOfMeetingRepo.save(min);
		MeetingTransaction trans=repo.findById(min.getMeetingTransactionId()).orElse(null);
		min.setMeetingTransaction(trans);
		return min;
	}

	public String deleteMinuteOfMeeting(int minutesId) {
		MeetingMinutes minutes=minutesOfMeetingRepo.findById(minutesId).orElse(null);
		minutes.setMeetingTransaction(null);
//		minutes.setUser(null);
	
		minutesOfMeetingRepo.delete(minutes);
		return "Successfully Deleted";
	}
	

	public String updateMinutesOfMeeting(int minutesId, MeetingMinutes meetingMinutes) {
		MeetingMinutes min=minutesOfMeetingRepo.findById(minutesId).orElse(null);
		min.setMinuteOfMeeting(meetingMinutes.getMinuteOfMeeting());
		min.setCheckItIsTask(meetingMinutes.getCheckItIsTask());
		minutesOfMeetingRepo.save(min);
		return "updated";
	}
	
	//--------------------------Meeting participants-----------------

	public List<MeetingParticipants> getAllParticipants(int meetingTransactionId) {
	
		System.out.println("par"+meetingParticipantsRepo.findByMeetingTransactionId(meetingTransactionId));
		return meetingParticipantsRepo.findByMeetingTransactionId(meetingTransactionId);
	}

	public MeetingParticipants createParticipants(MeetingParticipants meetingParticipants) {
		meetingParticipants=meetingParticipantsRepo.save(meetingParticipants);
		MeetingTransaction trans=repo.findById(meetingParticipants.getMeetingTransactionId()).orElse(null);
		meetingParticipants.setMeetingTransaction(trans);
		return meetingParticipants;
	}

	public MeetingParticipants updateParticipants(int participantsId, MeetingParticipants meetingParticipants) {
		meetingParticipants=meetingParticipantsRepo.save(meetingParticipants);
		return meetingParticipants;
	}
	
	public String deleteParticipants(int participantsId) {
		MeetingParticipants participants=meetingParticipantsRepo.findById(participantsId).orElse(null);
		participants.setMeetingTransaction(null);
		meetingParticipantsRepo.delete(participants);
		return "successfully deleted";
	}

	public String sendMail(int meetingTransactionId, List<MeetingMinutes> minutes) {
		List<MeetingParticipants> participants=meetingParticipantsRepo.findByMeetingTransactionId(meetingTransactionId);
		List<String> emailAddress=participants.stream().map(MeetingParticipants::getParticipantEmailId).collect(Collectors.toList());
		String Subject="Minutes Of Meeting";
		String Body=buildEmailBody(minutes);
		for(String mail:emailAddress)
		{
			try{
				sendEmail(mail,Subject,Body);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return "Successfully Mail Sended";
	}

	private void sendEmail(String mail, String subject, String body) throws UnsupportedEncodingException, MessagingException {
		 MimeMessage message = mailSender.createMimeMessage();              
		 MimeMessageHelper helper = new MimeMessageHelper(message,true,"UTF-8");
		 helper.setFrom(sender,"Rytways");
		 helper.setTo(mail);
		 helper.setSubject(subject);
		 helper.setText(body);
		mailSender.send(message);
	}

	private String buildEmailBody(List<MeetingMinutes> minutes) {
		StringBuilder body=new StringBuilder("Dear Participant,\n\nHere are the Minutes Of Meeting:\n\n");
		for(MeetingMinutes min:minutes)
		{
			body.append("- ").append(min.getMinuteOfMeeting()).append("\n");
		}
		body.append("\nBest Regards,\nMeeting Coordinator");
		return body.toString();
	}

	public MeetingTransaction getMeetingById(int meetingTransactionId) {
		return repo.findById(meetingTransactionId).orElse(null);
	}

	public CreateTask createTask(CreateTask meeting) {
		meeting =createTaskFromMeetingRepo.save(meeting);
		return meeting;
	}

	public List<MeetingTransaction> getAllMeetingByUserId(int userId) {
		List<MeetingTransaction> meetingg=repo.findByUserId(userId);
		Users user=userRepo.findById(userId).orElse(null);
		List<MeetingParticipants> participant=meetingParticipantsRepo.findAll();
		
		for(MeetingParticipants tran:participant) {
			  if(tran.getParticipantName().equals(user.getPersonName())) {
				  MeetingTransaction meet= tran.getMeetingTransaction();
				  meetingg.add(meet);
			  }
		}
		return meetingg;
	}

	public List<MeetingParticipants> getParticipants() {
		return meetingParticipantsRepo.findAll();
	}

	


}
