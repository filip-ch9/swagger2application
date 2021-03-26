package com.swagger.practice.swagger2application.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swagger.practice.swagger2application.model.Contact;
import com.swagger.practice.swagger2application.services.ContactService;


@RestController
@RequestMapping(value = "/api")
public class AddressBookResourceRestController {
	
	@Autowired
	private ContactService contactService;
	
	@GetMapping("/{id}")
	public Optional<Contact> getContact(@PathVariable Long id) {
		return contactService.getById(id);
	}
	
	@GetMapping()
	public List<Contact> getAllContacts() {
		return contactService.listAll();
	}
	
	@PostMapping()
	public Contact addContact(@RequestBody Contact contact) {
		return contactService.saveOrUpdate(contact);
	}
	
	@DeleteMapping(value = "/{id}")
	public void deleteContact(@PathVariable("id") Long id) {
		contactService.delete(id);
	}
	
}
