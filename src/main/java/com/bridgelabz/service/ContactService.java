package com.bridgelabz.service;

import com.bridgelabz.dto.ContactDTO;
import com.bridgelabz.model.Contact;
import com.bridgelabz.repository.ContactRepository;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public Contact addContact(ContactDTO contactDTO) {
        Contact contact = new Contact(contactDTO);
        return contactRepository.save(contact);
    }

    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    public Optional<Contact> getContactById(Long id) {
        return contactRepository.findById(id);
    }

    public Optional<Contact> updateContact(Long id, ContactDTO contactDTO) {
        return contactRepository.findById(id).map(existingContact -> {
            existingContact.setName(contactDTO.getName());
            existingContact.setEmail(contactDTO.getEmail());
            existingContact.setPhone(contactDTO.getPhone());
            return contactRepository.save(existingContact);
        });
    }

    public boolean deleteContact(Long id) {
        if (contactRepository.existsById(id)) {
            contactRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
