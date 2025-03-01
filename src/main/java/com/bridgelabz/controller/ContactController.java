package com.bridgelabz.controller;

import com.bridgelabz.entity.Contact;
import com.bridgelabz.repository.ContactRepository;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    // CREATE (POST)
    @PostMapping
    public ResponseEntity<Contact> addContact(@RequestBody Contact contact) {
        Contact savedContact = contactRepository.save(contact);
        return ResponseEntity.ok(savedContact);
    }

    // READ (GET all contacts)
    @GetMapping
    public ResponseEntity<List<Contact>> getAllContacts() {
        List<Contact> contacts = contactRepository.findAll();
        return ResponseEntity.ok(contacts);
    }

    // READ (GET by ID)
    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable Long id) {
        return contactRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // UPDATE (PUT by ID)
    @PutMapping("/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable Long id, @RequestBody Contact updatedContact) {
        return contactRepository.findById(id)
                .map(existingContact -> {
                    existingContact.setName(updatedContact.getName());
                    existingContact.setEmail(updatedContact.getEmail());
                    existingContact.setPhone(updatedContact.getPhone());
                    contactRepository.save(existingContact);
                    return ResponseEntity.ok(existingContact);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE (DELETE by ID)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteContact(@PathVariable Long id) {
        if (contactRepository.existsById(id)) {
            contactRepository.deleteById(id);
            return ResponseEntity.ok("Contact deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }
}
