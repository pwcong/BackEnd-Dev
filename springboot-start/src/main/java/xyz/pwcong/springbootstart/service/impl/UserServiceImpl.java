package xyz.pwcong.springbootstart.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import xyz.pwcong.springbootstart.model.Info;
import xyz.pwcong.springbootstart.model.Role;
import xyz.pwcong.springbootstart.model.RoleName;
import xyz.pwcong.springbootstart.model.User;
import xyz.pwcong.springbootstart.repository.UserRepository;
import xyz.pwcong.springbootstart.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User register(String username, String password, Set<Role> roles) throws Exception {
        logger.info("Try register new user with username \"" + username + "\"");

        User user = userRepository.findByUsername(username);

        if (user != null) {
            logger.info("Username \"" + username + "\" is in used");
            throw new Exception("Username \"" + username + "\" is in used");
        }

        user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRoles(roles);
        user.setAccountNonLocked(true);
        user.setAccountNonExpired(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);

        Info info = new Info();
        user.setInfo(info);

        userRepository.save(user);

        logger.info("User with username \"" + username + "\" has been registered successfully");

        return user;
    }

    @Override
    public User login(String username, String password) throws Exception {
        logger.info("Try login with username \"" + username + "\"");

        User user = userRepository.findByUsername(username);

        if (user != null) {
            logger.info("User with username \"" + username + "\" is not existed");
            throw new Exception("User with username \"" + username + "\" is not existed");
        }

        if (!passwordEncoder.encode(password).equals(user.getPassword())) {
            logger.info("User with username \"" + username + "\" login failed cause by incorrect password");
            throw new Exception("Incorrect password");
        }

        logger.info("User with username \"" + username + "\" login successfully");

        return user;
    }

    @Override
    public User initAdminAccount(String password) throws Exception {
        User admin = userRepository.findByUsername("admin");

        if (admin != null) {
            return admin;
        }

        Set<Role> roles = new HashSet<>();
        Role adminRole = new Role();
        adminRole.setName(RoleName.ADMIN);
        roles.add(adminRole);

        admin = register("admin", password, roles);

        return admin;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException("User is not found with username \"" + s + "\"");
        }
        return user;
    }
}
