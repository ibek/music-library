package com.pa165.mlib.service;

import com.pa165.mlib.dto.Role;
import com.pa165.mlib.dto.UserTO;
import com.pa165.mlib.exception.DuplicateException;
import java.util.List;

/**
 *
 * @author xbek
 */
public interface UserService {
    
    UserTO createNewUser(UserTO user, Role... groups) throws DuplicateException ;
    
    UserTO getUser(String name);
    
    UserTO updateUser(UserTO oldUser, UserTO newUser);
    
    boolean removeUser(UserTO user);
    
    List<UserTO> getAllUsers();
    
}
