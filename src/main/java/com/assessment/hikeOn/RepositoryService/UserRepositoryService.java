package com.assessment.hikeOn.RepositoryService;

import com.assessment.hikeOn.Entities.User;
import com.assessment.hikeOn.Exceptions.DuplicateUserException;
import com.assessment.hikeOn.Exceptions.RegisteredMailException;
import com.assessment.hikeOn.Repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRepositoryService implements IUserRepositoryService{

    private final IUserRepository userRepository;

    @Autowired
    public UserRepositoryService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(User user) {
        validateRequest(user);
        user.setId(getID());
        userRepository.save(user);
    }

    public Integer getID(){
        List<User> allUsers = userRepository.findAll();
        return allUsers.isEmpty() ? 1: allUsers.stream().mapToInt(User::getId).max().orElse(0) + 1;
    }

    public void validateRequest(User user) {
        if(userRepository.getUserByEmail(user.getEmail()).isPresent()){
            throw new RegisteredMailException("The mail provided is already linked with existing account.");
        }
        List<User> allUsers = userRepository.findAll();

        if(allUsers.contains(user)){
            throw new DuplicateUserException("The user is already Registered in System.");
        }
    }

}
