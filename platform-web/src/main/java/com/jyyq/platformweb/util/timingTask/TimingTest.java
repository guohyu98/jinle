package com.jyyq.platformweb.util.timingTask;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TimingTest {

    @Scheduled(cron = "0 0/1 * * * ?")
    public void test(){
        System.out.println("---------执行定时任务-------");
    }

}
