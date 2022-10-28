package com.iot.pi.five.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MyServiceImpl implements MyService
{
	@Async
	@Override
	public void send()
	{
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Finished process.");
	}
	
}
