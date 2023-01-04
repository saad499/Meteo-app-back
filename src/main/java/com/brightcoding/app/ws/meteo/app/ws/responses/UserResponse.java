package com.brightcoding.app.ws.meteo.app.ws.responses;

import java.util.List;

public class UserResponse {

    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private Boolean admin;
    private List<AdressResponse> adresses;
    private ContactResponse contact;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public List<AdressResponse> getAdresses() {
        return adresses;
    }

    public void setAdresses(List<AdressResponse> adresses) {
        this.adresses = adresses;
    }

    public ContactResponse getContact() {
        return contact;
    }

    public void setContact(ContactResponse contact) {
        this.contact = contact;
    }
}
