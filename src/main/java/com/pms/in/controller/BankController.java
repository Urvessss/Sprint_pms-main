package com.pms.in.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pms.in.entities.BankDetails;
import com.pms.in.service.BankService;

@RestController
public class BankController {

	private static final Logger LOG = LoggerFactory.getLogger(AbstractUserController.class);
	
	@Autowired
	private BankService bankService;
	
	@GetMapping("/getbankbyid/{accno}")
	public ResponseEntity<BankDetails> getBankById(@PathVariable(name = "accno") Long accno) {
		LOG.info("getBankById");
		BankDetails ban = bankService.getBankDetails(accno); // line 
		HttpHeaders headers = new HttpHeaders();
//		headers.add("message", "This employee is available in the database.");
		headers.add("successCode", "10");
		LOG.info(headers.toString());
		ResponseEntity<BankDetails> response = new ResponseEntity<BankDetails>(ban, headers, HttpStatus.OK);
		return response;
	}

	
	// http://localhost:8082/addbankdetails
	@PostMapping("/addbankdetails")
	public ResponseEntity<BankDetails> addBank(@RequestBody BankDetails bankDetails) {
		LOG.info("Controller add Bank");
		BankDetails ban = bankService.addBank(bankDetails);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Bank added successfully.");
		LOG.info(headers.toString());
		ResponseEntity<BankDetails> response = new ResponseEntity<BankDetails>(ban, headers, HttpStatus.CREATED);
		return response;
	}
	
	// http://localhost:8082/updatebankdetails
		@PutMapping("/updatebankdetails")
		public ResponseEntity<BankDetails> updateBank(@RequestBody BankDetails bankDetails) {
			LOG.info("Controller updateBank");
			BankDetails ban = bankService.updateBankDetails(bankDetails);
			HttpHeaders headers = new HttpHeaders();
			headers.add("message", "Bank updated successfully.");
			LOG.info(headers.toString());
			ResponseEntity<BankDetails> response = new ResponseEntity<BankDetails>(ban, headers, HttpStatus.OK);
			return response;
		}
		
		// http://localhost:8082/deletebankbyid
		@DeleteMapping("/deletebankbyid/{accno}")
		public ResponseEntity<BankDetails> deleteBankById(@PathVariable Long accno) {
			LOG.info("deleteBankById");
			bankService.deleteBankDetails(accno);
			HttpHeaders headers = new HttpHeaders();
			headers.add("message", "This bank is available in the database and deleted.");
			LOG.info(headers.toString());
			ResponseEntity<BankDetails> response = new ResponseEntity<BankDetails>(headers, HttpStatus.OK);
			return response;
		}
}