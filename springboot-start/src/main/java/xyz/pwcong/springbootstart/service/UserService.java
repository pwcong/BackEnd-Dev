package xyz.pwcong.springbootstart.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;
import xyz.pwcong.springbootstart.model.Role;
import xyz.pwcong.springbootstart.model.User;

import java.util.Set;

public interface UserService extends UserDetailsService {

    @Transactional
    public User register(String username, String password, Set<Role> roles) throws Exception;

    public User login(String username, String password) throws Exception;

    @Transactional
    public User initAdminAccount(String password) throws Exception;


}
