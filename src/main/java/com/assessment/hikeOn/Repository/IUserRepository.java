package com.assessment.hikeOn.Repository;

import com.assessment.hikeOn.Entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends MongoRepository<User,Integer> {
    Optional<User> getUserByEmail(String mail);

}
