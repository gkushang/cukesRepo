package com.cukesrepo.service.user;

import com.cukesrepo.domain.User;
import com.cukesrepo.exceptions.UserNotFoundException;
import com.cukesrepo.repository.user.UserRepository;
import com.google.common.base.Optional;
import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by maduraisamy on 12/30/13.
 */
public class UserServiceImpl implements UserService{
    private final UserRepository _userRepository;

    @Autowired
    public UserServiceImpl
            (
                    UserRepository userRepository
            ) {

        Validate.notNull(userRepository, "userRepository cannot be null");

        _userRepository = userRepository;
    }

    @Override
    public List<User> getUsers() {
        return _userRepository.getUsers();
    }

    @Override
    public Optional<User> getUserByName(String UserName) throws  UserNotFoundException {
        return _userRepository.getUserByName(UserName);
    }

    @Override
    public Optional<User> getUserByNameAndPwd(String userName, String password) {
        return _userRepository.getUserByNameAndPwd(userName,password);
    }
}
