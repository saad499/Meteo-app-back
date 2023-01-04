package com.brightcoding.app.ws.meteo.app.ws.controllers;

import com.brightcoding.app.ws.meteo.app.ws.requests.AdressRequest;
import com.brightcoding.app.ws.meteo.app.ws.responses.AdressResponse;
import com.brightcoding.app.ws.meteo.app.ws.services.AdressService;
import com.brightcoding.app.ws.meteo.app.ws.shared.dto.AdressDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/adresses")
@AllArgsConstructor
public class AdressController {

    AdressService adressService;

    @GetMapping
    public ResponseEntity<List<AdressResponse>>getAdresses(Principal principal){
        List<AdressDto> adresses = adressService.getAllAdresses(principal.getName());

        Type listType = new TypeToken<List<AdressResponse>>() {}.getType();
        List<AdressResponse> adressResponses = new ModelMapper().map(adresses, listType);

        return new ResponseEntity<List<AdressResponse>>(adressResponses,HttpStatus.OK);
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<AdressResponse> StoreAdresse(@RequestBody AdressRequest adressRequest, Principal principal){

        ModelMapper modelMapper = new ModelMapper();

        AdressDto adressDto = modelMapper.map(adressRequest, AdressDto.class);
        AdressDto createAdress = adressService.craeteAddress(adressDto, principal.getName());

        AdressResponse newAddress = modelMapper.map(createAdress,AdressResponse.class);

        return new ResponseEntity<AdressResponse>(newAddress, HttpStatus.CREATED);
    }



}
