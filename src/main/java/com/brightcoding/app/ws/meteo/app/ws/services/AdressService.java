package com.brightcoding.app.ws.meteo.app.ws.services;

import com.brightcoding.app.ws.meteo.app.ws.shared.dto.AdressDto;

import java.util.List;

public interface AdressService {

    List<AdressDto> getAllAdresses(String email);

    AdressDto craeteAddress(AdressDto adress, String email);
}
