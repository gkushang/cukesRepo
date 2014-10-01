package com.cukesrepo.service.login;

import com.cukesrepo.domain.User;
import com.cukesrepo.exceptions.ProjectNotFoundException;
import com.cukesrepo.exceptions.UserNotFoundException;
import com.google.common.base.Optional;

import java.util.Map;

/**
 * Created by maduraisamy on 1/22/14.
 */
public interface LoginService {
     public Optional<User> login(String userName, String password) throws UserNotFoundException;
    public void addUser(Map<String, String[]> parameterMap);
}
