package com.rytways.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rytways.dto.IdDto;
import com.rytways.model.DocsUpload;
import com.rytways.repository.DocsUploadRepo;

@RestController
@RequestMapping("/docsUpload")
public class DocsUploadController {

	@Autowired
	private DocsUploadRepo docsRepo;

	@Value("${docReportUploadPath}")
	private String uploadPath;
//	private String uploadPath = "/downloads/downloads/SRIPATHI/Uploads/";

	public static String randomString(int length) {

		char[] ALPHANUMERIC = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

		StringBuilder random = new StringBuilder();

		for (int i = 0; i < length; i++) {
			int index = (int) (Math.random() * ALPHANUMERIC.length);
			random.append(ALPHANUMERIC[index]);
		}
		return random.toString();
	}

	@PostMapping("/uploadFile")
	public Map uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("id") int id,
			@RequestParam("remarks") String remarks, @RequestParam("typeId") String typeId) {
		DocsUpload invoiceDocs = new DocsUpload();
		try {

			File directory = new File(uploadPath);
			if (!directory.exists()) {
				directory.mkdirs();
			}
			// Save the file to the database
			invoiceDocs.setId(id);
			invoiceDocs.setRemarks(remarks);
			invoiceDocs.setTypeId(typeId);
			invoiceDocs.setFileName(file.getOriginalFilename());
			// Set other file attributes if needed
			System.out.println(invoiceDocs.getFileName());
			String extention = invoiceDocs.getFileName().split("\\.")[1];
			invoiceDocs.setType(extention);
			String generatedFileName = randomString(24) + "." + extention;

			invoiceDocs.setGeneratedFileName(generatedFileName);

			invoiceDocs = docsRepo.save(invoiceDocs);

			invoiceDocs = docsRepo.findById(invoiceDocs.getDocsId()).get();

			// Save the file to a directory if required
			file.transferTo(new File(uploadPath + invoiceDocs.getGeneratedFileName()));

			Map map = new HashMap();
			map.put("invoiceDocs", invoiceDocs);
			map.put("message", "File Uploaded Successfully");
			map.put("status", 1);
			Map retValues = new HashMap();
			;
			retValues.put("retValues", map);
			return retValues;

		} catch (Exception e) {
			e.printStackTrace();
			Map map = new HashMap();
			map.put("invoiceDocs", invoiceDocs);
			map.put("message", "File Uploaded UnSuccessfull");
			map.put("status", 0);
			Map retValues = new HashMap();
			;
			retValues.put("retValues", map);
			return retValues;
		}
	}

	@GetMapping("/download/{fileName}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) throws IOException {

		// String fileName = poQuote.getGeneratedFileName();
		// Load file as Resource
		Path filePath = Paths.get(uploadPath).resolve(fileName).normalize();
		try {

			String name = "";

			File file = new File(uploadPath + fileName);
			Path path = Paths.get(file.getAbsolutePath());
			ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

			return ResponseEntity.ok().headers(this.headers(fileName)).contentLength(file.length())
					.contentType(MediaType.parseMediaType("application/octet-stream")).body(resource);
			// Resource resource = new UrlResource(filePath.toUri());
			/*
			 * if (resource.exists()) { // Check if the file exists
			 * 
			 * // Set content type header HttpHeaders headers = new HttpHeaders();
			 * headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			 * headers.setContentDispositionFormData("attachment", fileName);
			 * 
			 * // Return the file as ResponseEntity return
			 * ResponseEntity.ok().headers(headers).body(resource); } else { return
			 * ResponseEntity.notFound().build(); }
			 */
		} catch (MalformedURLException e) {
			return ResponseEntity.notFound().build();
		}
	}

	private HttpHeaders headers(String name) {

		HttpHeaders header = new HttpHeaders();
		header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + name);
		header.add("Cache-Control", "no-cache, no-store, must-revalidate");
		header.add("Pragma", "no-cache");
		header.add("Expires", "0");
		return header;

	}

	@PostMapping("/deleteDocs")
	public ResponseEntity<?> deleteEntity(@RequestBody DocsUpload docs) {
		docsRepo.deleteById(docs.getDocsId());
		return new ResponseEntity<>("Entity deleted successfully", HttpStatus.OK);
	}

	@PostMapping("/getDocsById")
	public List<DocsUpload> uploadMultipleFiles(@RequestBody IdDto idDto) {

		Optional<List<DocsUpload>> poQuotes = docsRepo.findByIdAndTypeIdOrderByUpdatedOnDesc(idDto.getId(),
				idDto.getDocType());

		return poQuotes.get();
	}

}
