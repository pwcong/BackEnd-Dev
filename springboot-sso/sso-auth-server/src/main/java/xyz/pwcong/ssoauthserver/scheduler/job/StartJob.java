package xyz.pwcong.ssoauthserver.scheduler.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Configurable
@EnableScheduling
public class StartJob {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Scheduled(cron = "0 0/1 * * * ?")
    public void run() {
        logger.info("StartJob say \"Hello World!\"");
    }
}
