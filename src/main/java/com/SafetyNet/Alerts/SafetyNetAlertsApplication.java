package com.SafetyNet.Alerts;

import com.SafetyNet.Alerts.liveDatas.service.DataInitiatior;
import com.SafetyNet.Alerts.liveDatas.service.LiveDatas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SafetyNetAlertsApplication {

	public static void main(String[] args) {

		SpringApplication.run(SafetyNetAlertsApplication.class, args);

		//dataInitiatior.getInitialDatas();
	}

}
