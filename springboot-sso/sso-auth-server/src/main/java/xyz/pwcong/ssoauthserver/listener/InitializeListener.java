package xyz.pwcong.ssoauthserver.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import xyz.pwcong.ssoauthserver.service.UserService;

@Configuration
public class InitializeListener implements ApplicationListener<ContextRefreshedEvent> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @Value("${admin.password}")
    private String adminPassword;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initAdminAccount();
    }

    private void initAdminAccount() {
        logger.info("Start init admin account");
        try {
            userService.initAdminAccount(adminPassword);
            logger.info("Success to init admin account");
        } catch (Exception e) {
            logger.error("Fail to init admin account", e);
        }
    }

}