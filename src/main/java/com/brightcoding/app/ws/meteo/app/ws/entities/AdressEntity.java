package com.brightcoding.app.ws.meteo.app.ws.entities;

import com.brightcoding.app.ws.meteo.app.ws.shared.dto.UserDto;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name="adresses")
public class AdressEntity implements Serializable {

    private static final long serialVersionUID= 7150882924787817501L;

    @Id
    @GeneratedValue
    private long id;

    @Column(length = 30, nullable = false)
    private String adressId;

    @Column(length = 20, nullable = false)
    private String city;

    @Column(length = 20, nullable = false)
    private String country;

    @Column(length = 50, nullable = false)
    private String street;

    @Column(length = 7, nullable = false)
    private String postal;

    @Column(length = 20, nullable = false)
    private String type;

    @ManyToOne(targetEntity = UserEntity.class)
    @JoinColumn(name="users_id")
    private UserDto user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAdressId() {
        return adressId;
    }

    public void setAdressId(String adressId) {
        this.adressId = adressId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
