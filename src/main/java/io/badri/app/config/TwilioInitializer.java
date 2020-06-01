package io.badri.app.config;

import org.slf4j.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.twilio.Twilio;


@Configuration
public class TwilioInitializer {

	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(TwilioInitializer.class);
	
	private TwilioConfig twilioConfig;
	
	@Autowired
	public TwilioInitializer(TwilioConfig twilioConfig) {
		this.twilioConfig = twilioConfig;
		
		Twilio.init(
		 twilioConfig.getAccountSid(),
		 twilioConfig.getAuthToken()
		);
		logger.info("twilio Initialized with ..... " + twilioConfig.getAccountSid());
	}	
	
}
