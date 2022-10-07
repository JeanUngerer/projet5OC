package com.SafetyNet.Alerts;

import com.SafetyNet.Alerts.liveDatas.service.LiveDatas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SafetyNetAlertsApplication {


	static LiveDatas liveDatas;

	@Autowired
	public SafetyNetAlertsApplication(LiveDatas liveDatas){
		this.liveDatas = liveDatas;
	}

	public static void main(String[] args) {

		SpringApplication.run(SafetyNetAlertsApplication.class, args);

		liveDatas.getInitialDatas();
	}

}
