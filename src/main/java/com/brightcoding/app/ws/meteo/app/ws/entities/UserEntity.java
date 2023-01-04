package com.brightcoding.app.ws.meteo.app.ws.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name="users")
public class UserEntity implements Serializable {

     private static final long serialVersionUID = -9019030687411552040L;

     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     private long id;

     @Column(nullable = false)
     private String userId;

     @Column(nullable = false, length = 50)
     private String firstName;

     @Column(nullable = false, length = 50)
     private String lastName;

     @Column(nullable = false, length = 120, unique = true)
     private String email;

     @Column(nullable = true)
     private Boolean admin = false;

     @Column(nullable = false)
     private String encryptedPassword;

     @Column(nullable = true)
     private String emailVerificationToken;

     @Column(nullable = false)
     private Boolean emailVerificationStatus = false;

     @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
     private List<AdressEntity> adresses;

     /*@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
     private ContactEntity contact;*/


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

     public String getEncryptedPassword() {
          return encryptedPassword;
     }

     public void setEncryptedPassword(String encryptedPassword) {
          this.encryptedPassword = encryptedPassword;
     }

     public String getEmailVerificationToken() {
          return emailVerificationToken;
     }

     public void setEmailVerificationToken(String emailVerificationToken) {
          this.emailVerificationToken = emailVerificationToken;
     }

     public Boolean getEmailVerificationStatus() {
          return emailVerificationStatus;
     }

     public void setEmailVerificationStatus(Boolean emailVerificationStatus) {
          this.emailVerificationStatus = emailVerificationStatus;
     }

     public List<AdressEntity> getAdresses() {
          return adresses;
     }

     public void setAdresses(List<AdressEntity> adresses) {
          this.adresses = adresses;
     }

     public Boolean getAdmin() {
          return admin;
     }

     public void setAdmin(Boolean admin) {
          this.admin = admin;
     }
}
