package com.cukesrepo.service.login;

import com.cukesrepo.domain.User;
import com.cukesrepo.exceptions.UserNotFoundException;
import com.cukesrepo.repository.user.UserRepository;
import com.google.common.base.Optional;
import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by maduraisamy on 1/22/14.
 */
@Service
public class LoginServiceImpl implements LoginService {

    private final UserRepository _userRepository;

    @Autowired
    public LoginServiceImpl
            (
                    UserRepository userRepository
            ) {

        Validate.notNull(userRepository, "userRepository cannot be null");

        _userRepository = userRepository;
    }

    @Override
    public Optional<User> login(String userName, String password) throws UserNotFoundException {

        Optional<User> userOptional = _userRepository.getUserByName(userName);

        if(userOptional.get().getPassword().equals(password))
            return userOptional;

        throw new UserNotFoundException("Invalid username or password");
    }

    @Override
    public void addUser(Map<String, String[]> parameterMap) {
        _userRepository.addUser(parameterMap);

    }
}
