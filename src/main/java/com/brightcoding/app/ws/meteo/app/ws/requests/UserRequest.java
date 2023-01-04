package com.brightcoding.app.ws.meteo.app.ws.requests;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

public class UserRequest {

    @NotNull(message = "Ce champ ne doit pas etre null")
    @Size(min=3, message = "Ce champ doit avoir au moin 3 caractères")
    private String firstName;

    @NotNull(message = "Ce champ ne doit pas etre null")
    @Size(min=3, message = "Ce champ doit avoir au moin 3 caractères")
    private String lastName;

    @NotNull(message = "Ce champ ne doit pas etre null")
    @Email(message = "Ce champ doit respecter le formet email")
    private String email;

    @NotNull(message = "Ce champ ne doit pas etre null")
    @Size(min = 8, message = "Ce champ doit avoir au moins 8 caractères")
    @Size(max = 12, message = "Ce champ doit avoir maximum 12 caractères")
    @Pattern(regexp = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$", message = "Ce mot de passe doit avoir des lettres en Maj et en Miniscule et numéro")
    private String password;

    private Boolean admin;

    private List<AdressRequest> adresses;

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

    public List<AdressRequest> getAdresses() {
        return adresses;
    }

    public void setAdresses(List<AdressRequest> adresses) {
        this.adresses = adresses;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }


}
