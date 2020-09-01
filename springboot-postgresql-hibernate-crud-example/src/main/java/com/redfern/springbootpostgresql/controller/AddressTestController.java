package com.redfern.springbootpostgresql.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.redfern.springbootpostgresql.exception.ResourceNotFoundException;
import com.redfern.springbootpostgresql.model.AddressTest;
import com.redfern.springbootpostgresql.repository.AddressTestRepository;

@CrossOrigin(origins = "http://localhost:8080") //port 4200 in example
@RestController
@RequestMapping("/api/v1")
public class AddressTestController {

	@Autowired
	private AddressTestRepository addressTestRepository;
	
	@GetMapping("AddressTest")
	public List<AddressTest> getAllAddressTest() {
		return this.addressTestRepository.findAll();
	}
	
	//get employees by id
	@GetMapping("/AddressTest/{id}")
	public ResponseEntity<AddressTest> getAddressTestById(@PathVariable(value ="id") Long addressId)
			throws ResourceNotFoundException {
		AddressTest addressTest = addressTestRepository.findById(addressId)
				.orElseThrow(() -> new ResourceNotFoundException("Address not found for this id :: " + addressId));
		return ResponseEntity.ok().body(addressTest);
	}
	
	//save address
	@PostMapping("AddressTest")
	public AddressTest createAddressTest(@RequestBody AddressTest addressTest) {
		return this.addressTestRepository.save(addressTest);
	}
	//update address
	@PutMapping("AddressTest/{id}")
	public ResponseEntity<AddressTest> updateAddressTest(@PathVariable(value = "id") Long addressId,
			                                       @Valid @RequestBody AddressTest addressTestDetails) throws ResourceNotFoundException {
		
		AddressTest addressTest = addressTestRepository.findById(addressId)
				.orElseThrow(() -> new ResourceNotFoundException("Address not found for this id :: " + addressId));
		
		//addressTest.setEmail(addressTestRepository.getEmail());
		addressTest.setEmployeeId(addressTestDetails.getEmployeeId());
		addressTest.setStreetAddress1(addressTestDetails.getStreetAddress1());
		addressTest.setStreetAddress2(addressTestDetails.getStreetAddress2());
		addressTest.setCity(addressTestDetails.getCity());
		addressTest.setState(addressTestDetails.getCity());
		addressTest.setZipCode(addressTestDetails.getZipCode());
	
		return ResponseEntity.ok(this.addressTestRepository.save(addressTest));
	}
	
	//delete addressTest
	@DeleteMapping("AddressTest/{id}")
	public Map<String, Boolean> deleteAddressTest(@PathVariable(value = "id") Long addressId) throws ResourceNotFoundException {
		
		AddressTest addressTest = addressTestRepository.findById(addressId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + addressId));
		
		this.addressTestRepository.delete(addressTest);
		
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
