package com.rytways.controller;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.modelmapper.internal.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rytways.exception.UserNotFoundException;
import com.rytways.helper.RandomPasswordGenerator;
import com.rytways.model.Users;
import com.rytways.service.PasswordResetService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/reset-password")
public class PasswordResetController {

	@Value("${spring.mail.username}")
	private String sender;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private PasswordResetService passwordResetService;

	@Autowired
	public PasswordResetController(JavaMailSender mailSender, PasswordResetService passwordResetService) {

		this.mailSender = mailSender;
		this.passwordResetService = passwordResetService;
	}

	@PostMapping("/forgot_password")
	public ResponseEntity<String> requestPasswordReset(@RequestBody Map<String, String> requestBody,
			HttpServletRequest request) {

		String userName = requestBody.get("userName");
		System.out.println("userName =>=>=>=>=>=> " + userName);
		// String token = RandomString.make(30);
		int passwordLength = 10; // Adjust the length as needed
		String generateRandomPassword = RandomPasswordGenerator.generateRandomPassword(passwordLength);

		// String generateRandomPassword="Admin@123";

		System.out.println("Generated Password: " + generateRandomPassword);

		try {

			/*
			 * passwordResetService.updateResetPasswordToken(token, email); String
			 * resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token=" +
			 * token; System.out.println("Click the LInk:"+resetPasswordLink); //
			 * sendEmail(email, resetPasswordLink);
			 * 
			 */
			String userEmail = passwordResetService.fetchUserEmailByUserName(userName);
			System.out.println("userEmail =>=.=>=> " + userEmail);
			String updatedPassword = passwordResetService.updatePassword(userEmail, generateRandomPassword);
			System.out.println("updatedPassword =>=>=>=>=>=> " + updatedPassword);
			sendEmailWithPassword(userEmail, generateRandomPassword);
		} catch (UserNotFoundException ex) {
			ex.printStackTrace();
		} catch (UnsupportedEncodingException | MessagingException ex) {
			ex.printStackTrace();
		}

		return ResponseEntity.ok("Password reset email sent successfully.");
	}

	// -------------------------------------------

	@PostMapping("/change_password")
	public ResponseEntity<String> requestChangeReset(@RequestBody Users user) {

		String token = RandomString.make(30);
		boolean passwordChanged = false;

		try {
			passwordChanged = passwordResetService.changePassword(user);
			// sendEmailWithPassword(user.getEmail(),updatedPassword);
		} catch (UserNotFoundException ex) {
			ex.printStackTrace();
		}
		if (passwordChanged) {
			return ResponseEntity.ok("Password Reset Successfully");
		} else {
			return ResponseEntity.badRequest().body("Unable to Reset Password");
		}

	}

	public void sendEmailWithPassword(String recipientEmail, String updatedPassword)
			throws MessagingException, UnsupportedEncodingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

		helper.setFrom(sender, "Srinidhi VDR");
		helper.setTo(recipientEmail);

		String subject = "Here's the new updated password";

		String content = "<p>Hello,</p>"
				+ "<p>You have requested to use the new password for Srinidhi VDR Application.</p>" + "<p>"
				+ updatedPassword + "</p>" + "<br>" + "<p>Ignore this email if you do remember your password, "
				+ "or you have not made the request.</p>";

		helper.setSubject(subject);

		helper.setText(content, true);

		mailSender.send(message);
	}

}
