package com.cukesrepo.repository.user;

import java.util.List;
import java.util.Map;

import com.cukesrepo.domain.User;
import com.cukesrepo.exceptions.UserNotFoundException;
import com.google.common.base.Optional;


public interface UserRepository
{

    public List<User> getUsers();

    public Optional<User> getUserByName(String userName) throws UserNotFoundException;

    public Optional<User> getUserByNameAndPwd(String userName, String password);

    public void addUser(Map<String, String[]> parameterMap);
}
