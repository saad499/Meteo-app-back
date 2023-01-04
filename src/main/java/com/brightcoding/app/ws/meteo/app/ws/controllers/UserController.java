package com.brightcoding.app.ws.meteo.app.ws.controllers;

import com.brightcoding.app.ws.meteo.app.ws.exceptions.UserException;
import com.brightcoding.app.ws.meteo.app.ws.requests.UserRequest;
import com.brightcoding.app.ws.meteo.app.ws.responses.ErrorMessages;
import com.brightcoding.app.ws.meteo.app.ws.responses.UserResponse;
import com.brightcoding.app.ws.meteo.app.ws.services.UserService;
import com.brightcoding.app.ws.meteo.app.ws.shared.dto.UserDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users") //localhost
public class UserController {

    UserService userService;

    @GetMapping(path = "/{id}", produces ={ MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserResponse> getUser(@PathVariable String id){

        UserDto userDto = userService.getUserByUserId(id);

        UserResponse userResponse = new UserResponse();

        BeanUtils.copyProperties(userDto, userResponse);

        return new ResponseEntity<UserResponse>(userResponse,HttpStatus.OK);
    }

    @CrossOrigin(origins = {"*"})
    @GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public List<UserResponse> getAllUsers(@RequestParam(value = "page", defaultValue = "1")int page,@RequestParam(value = "limit", defaultValue = "2") int limit, @RequestParam(value = "search", defaultValue = "3") String search, @RequestParam(value = "status", defaultValue = "1") int status){

        List<UserResponse> usersResponse = new ArrayList<>();

        List<UserDto> users = userService.getUsers(page,limit, search, status);

        for(UserDto userDto: users){
            UserResponse user = new UserResponse();
            BeanUtils.copyProperties(userDto, user);

            usersResponse.add(user);
        }

        return usersResponse;
    }


    @PostMapping(consumes ={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE} ,
                 produces ={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )

    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest userRequest) throws Exception{

        if(userRequest.getFirstName().isEmpty()) throw new UserException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

        ModelMapper modelMapper = new ModelMapper();
        UserDto userDto = modelMapper.map(userRequest, UserDto.class);

        UserDto createUser = userService.createUser(userDto);

        UserResponse userResponse = modelMapper.map(createUser,UserResponse.class);

        return new ResponseEntity<UserResponse>(userResponse,HttpStatus.CREATED);
    }



    @PutMapping(path = "/{id}",
            consumes ={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE} ,
            produces ={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<UserResponse> updatetUser(@PathVariable String id, @RequestBody UserRequest userRequest){

        UserDto userDto = new UserDto();

        BeanUtils.copyProperties(userRequest, userDto);

        UserDto updateUser = userService.updateUser(id, userDto);

        UserResponse userResponse = new UserResponse();

        BeanUtils.copyProperties(updateUser,userResponse);

        return new ResponseEntity<UserResponse>(userResponse,HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable String id){

        userService.deleteUser(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
