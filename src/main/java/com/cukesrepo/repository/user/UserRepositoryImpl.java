package com.cukesrepo.repository.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cukesrepo.domain.User;
import com.cukesrepo.exceptions.UserNotFoundException;
import com.cukesrepo.repository.scenario.ScenarioRepository;
import com.google.common.base.Optional;
import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by maduraisamy on 12/23/13.
 */
@Repository
public  class UserRepositoryImpl implements UserRepository {

    private final MongoTemplate _mongoTemplate;
    private static final Logger LOG = LoggerFactory.getLogger(ScenarioRepository.class);

    private List<User> _users = new ArrayList<User>();


    @Autowired
    public UserRepositoryImpl
            (

                    MongoTemplate mongoTemplate
            ) {


        Validate.notNull(mongoTemplate, "mongoTemplate cannot be null");

        _mongoTemplate = mongoTemplate;
    }

    @Override
    public List<User> getUsers() {
        LOG.info("Querying db to get all the users");

        _users = _mongoTemplate.find(new Query(), User.class);

        return _users;
    }


    @Override
    public Optional<User> getUserByName(String userName) throws  UserNotFoundException {
        Query query = new Query(Criteria.where(User.NAME).is(userName));

        User user = _mongoTemplate.findOne(query, User.class);

        LOG.info("User '{}' found from db", userName);

        Optional<User> userOptional = Optional.fromNullable(user);

        if (userOptional.isPresent())
            return userOptional;

        throw new UserNotFoundException("User '" + userName + "' not found in db");
    }


    @Override
    public Optional<User> getUserByNameAndPwd(String userName, String password) {

        Query query = new Query((Criteria.where(User.NAME).is(userName)).and(User.PASSWORD).is(password));

        LOG.info("Get User for userName '{}' and password '{}'", userName, password);

        Optional<User> userOptional = Optional.fromNullable(_mongoTemplate.findOne(query, User.class));

        return userOptional;

    }
    @Override
    public void addUser(Map<String, String[]> parameterMap) {

        User user = new User();
        user.setUserName(parameterMap.get("name")[0]);
        user.setPassword(parameterMap.get("password")[0]);
        user.setEmail(parameterMap.get("email")[0]);
        user.setRole(parameterMap.get("role")[0]);

        LOG.info("Adding User '{}'", user.getUserName());

        _mongoTemplate.findAndRemove(new Query(Criteria.where(User.NAME).is(parameterMap.get("name")[0])), User.class);
        _mongoTemplate.insert(user);

    }
}

