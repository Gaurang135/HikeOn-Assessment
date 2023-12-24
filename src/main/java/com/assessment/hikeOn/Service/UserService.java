package com.assessment.hikeOn.Service;

import com.assessment.hikeOn.Dto.NewUserRequest;
import com.assessment.hikeOn.Entities.CustomerGroup;
import com.assessment.hikeOn.Entities.Occupation;
import com.assessment.hikeOn.Entities.User;
import com.assessment.hikeOn.RepositoryService.UserRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class UserService implements IUserService{

    private final UserRepositoryService userRepositoryService;

    @Autowired
    public UserService(UserRepositoryService userRepositoryService) {
        this.userRepositoryService = userRepositoryService;
    }

    @Override
    public void registerUser(NewUserRequest newUserRequest) {
        String  userName = newUserRequest.getName();
        String userMail = newUserRequest.getEmail();
        LocalDate dob = newUserRequest.getDateOfBirth();
        Occupation occupation = newUserRequest.getOccupation();
        CustomerGroup group;
        if(userMail.endsWith("@hikeon.tech")){
            group = CustomerGroup.HIKEON;
        } else {
            switch (occupation){
                case DEVELOPER -> group = CustomerGroup.DEVELOPER;
                case CHEF -> group = CustomerGroup.CHEF;
                default -> group = CustomerGroup.NA;
            }
        }

        User user = new User(userName,userMail,dob,occupation,group);
        userRepositoryService.registerUser(user);
    }
}
