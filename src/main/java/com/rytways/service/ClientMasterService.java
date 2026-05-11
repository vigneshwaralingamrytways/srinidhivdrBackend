package com.rytways.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rytways.model.ClientMaster;
import com.rytways.repository.ClientMasterRepo;
import com.rytways.specifications.ClientMasterSpecification;

@Service
public class ClientMasterService {

	@Autowired
    private ClientMasterRepo clientMasterRepo;

	public List<ClientMaster> getAllClients() {
		return clientMasterRepo.findAll();
	}

	public ClientMaster createNewClient(ClientMaster client) {
		return clientMasterRepo.save(client);
	}

	public List<ClientMaster> searchClients(ClientMaster client) {
		Sort sort = Sort.by(Sort.Direction.ASC, "clientId");
		List<Specification<ClientMaster>> specs = new ArrayList<>();
		if (client.getClientName() != null && !client.getClientName().isEmpty()) {
			specs.add(ClientMasterSpecification.clientName(client.getClientName()));
		}
		if (client.getStatus() != null && !client.getStatus().isEmpty()) {
			specs.add(ClientMasterSpecification.status(client.getStatus()));
		}
		Specification<ClientMaster> combinedSpec = specs.stream().reduce(Specification::and).orElse(null);
		return clientMasterRepo.findAll(combinedSpec, sort);
	}

	public List<ClientMaster> readExcel(MultipartFile file) {
		List<ClientMaster> uploadData=new ArrayList<ClientMaster>();
		try(Workbook workbook=new XSSFWorkbook(file.getInputStream())){
			Sheet sheet=workbook.getSheetAt(0);
			for(Row row:sheet) {
				if(row.getRowNum()==0) continue;
				ClientMaster client=new ClientMaster();
				client.setClientName(row.getCell(0).getStringCellValue());
				uploadData.add(client);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return clientMasterRepo.saveAll(uploadData);
	}
}
