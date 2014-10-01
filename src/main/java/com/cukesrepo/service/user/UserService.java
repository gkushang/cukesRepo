package com.cukesrepo.service.user;

import com.cukesrepo.domain.Project;
import com.cukesrepo.domain.User;
import com.cukesrepo.exceptions.ProjectNotFoundException;
import com.cukesrepo.exceptions.UserNotFoundException;
import com.google.common.base.Optional;

import java.util.List;

/**
 * Created by maduraisamy on 12/30/13.
 */
public interface UserService {

    public List<User> getUsers();

    public Optional<User> getUserByName(String UserName) throws UserNotFoundException, ProjectNotFoundException;

    public Optional<User> getUserByNameAndPwd(String userName, String password);
}


