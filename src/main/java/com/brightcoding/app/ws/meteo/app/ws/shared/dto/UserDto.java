package com.brightcoding.app.ws.meteo.app.ws.shared.dto;

import java.io.Serializable;
import java.util.List;

public class UserDto implements Serializable {

    private static final long serialVersionUID = -5039688889507344180L;

    private long id;
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Boolean admin;
    private String encryptedPassword;
    private String emailVerificationRoken;
    private Boolean emailVerificationStatus = false;
    private List<AdressDto> adresses;
    private ContactDto contact;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public String getEmailVerificationRoken() {
        return emailVerificationRoken;
    }

    public void setEmailVerificationRoken(String emailVerificationRoken) {
        this.emailVerificationRoken = emailVerificationRoken;
    }

    public Boolean isEmailVerificationStatus() {
        return emailVerificationStatus;
    }

    public void setEmailVerificationStatus(boolean emailVerificationStatus) {
        this.emailVerificationStatus = emailVerificationStatus;
    }

    public List<AdressDto> getAdresses() {
        return adresses;
    }

    public void setAdresses(List<AdressDto> adresses) {
        this.adresses = adresses;
    }


    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public ContactDto getContact() {
        return contact;
    }

    public void setContact(ContactDto contact) {
        this.contact = contact;
    }
}
