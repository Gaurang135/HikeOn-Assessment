package com.assessment.hikeOn.Controller;

import com.assessment.hikeOn.Dto.NewUserRequest;
import com.assessment.hikeOn.Exceptions.DuplicateUserException;
import com.assessment.hikeOn.Exceptions.RegisteredMailException;
import com.assessment.hikeOn.Exceptions.UserUnderageException;
import com.assessment.hikeOn.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/Register")
    public ResponseEntity<String> registerUser(@RequestBody NewUserRequest request){
        try {
            userService.registerUser(request);
        }catch (UserUnderageException | RegisteredMailException | DuplicateUserException e ){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("User Registered Successfully", HttpStatus.OK );
    }
}
