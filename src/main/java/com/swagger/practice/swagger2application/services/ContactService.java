package com.swagger.practice.swagger2application.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swagger.practice.swagger2application.model.Contact;
import com.swagger.practice.swagger2application.repositories.ContactRepository;

@Service
public class ContactService {

	@Autowired
	private ContactRepository contactRepository;
	
	public List<Contact> listAll() {
		List<Contact> contacts = new ArrayList<>();
		this.contactRepository.findAll().forEach(contacts::add);
		return contacts;
	}
	
	public Optional<Contact> getById(Long id) {
		return contactRepository.findById(id);
	}
	
	public Contact saveOrUpdate(Contact contact) {
		return contactRepository.save(contact);
	}
	
	public void delete(Long id) {
		contactRepository.deleteById(id);
	}
	
	
}
