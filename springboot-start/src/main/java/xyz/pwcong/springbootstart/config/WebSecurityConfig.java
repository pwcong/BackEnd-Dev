package xyz.pwcong.springbootstart.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import xyz.pwcong.springbootstart.model.RoleName;
import xyz.pwcong.springbootstart.service.UserService;

import java.io.PrintWriter;

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
        http.
                authorizeRequests()
                .antMatchers(
                        "/auth/**",
                        "/css/**",
                        "/js/**",
                        "/images/**",
                        "/fonts/**"
                ).permitAll()
                .antMatchers("/admin/**").hasRole(RoleName.ADMIN.name())
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .formLogin().loginPage("/login")
                .successHandler((request, response, authentication) -> {

                    response.setContentType("application/json; charset=utf-8");

                    PrintWriter printWriter = response.getWriter();
                    printWriter.write("{\"code\": 0, \"msg\": \"Login successfully\"}");
                    printWriter.flush();
                    printWriter.close();

                })
                .failureHandler((request, response, exception) -> {
                    response.setContentType("application/json; charset=utf-8");

                    PrintWriter printWriter = response.getWriter();
                    printWriter.write("{\"code\": 1, \"msg\": \"" + exception.getMessage() + "\"}");
                    printWriter.flush();
                    printWriter.close();
                })
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
