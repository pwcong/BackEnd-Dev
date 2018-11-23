package xyz.pwcong.ssoauthserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import xyz.pwcong.ssoauthserver.model.RoleName;
import xyz.pwcong.ssoauthserver.service.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(
                        "/auth/**",
                        "/oauth/**",
                        "/css/**",
                        "/js/**",
                        "/libs",
                        "/images/**",
                        "/fonts/**"
                ).permitAll()
                .antMatchers("/admin/**").hasRole(RoleName.ADMIN.name())
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login")
                .loginProcessingUrl("/auth/login")
                .permitAll()
                .and()
                .logout().logoutUrl("/auth/logout").permitAll();
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return userService;
    }
}
