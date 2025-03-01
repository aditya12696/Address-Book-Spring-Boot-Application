package com.bridgelabz.dto;

public class ContactDTO {
    private String name;
    private String email;
    private String phone;

    // Constructors
    public ContactDTO() {}

    public ContactDTO(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    // Getters & Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}
