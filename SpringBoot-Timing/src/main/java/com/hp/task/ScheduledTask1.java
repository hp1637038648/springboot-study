package com.hp.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask1 {
	
	private int count = 0;
	
	@Scheduled(cron = "*/6 * * * * ?")
	public void process() {
		System.out.println("This is scheduler task runing" + (count++));
	}
}
