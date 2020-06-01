package io.badri.app.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;

import io.badri.app.config.TwilioConfig;
import io.badri.app.entity.SmsRequest;
import io.badri.app.service.SmsService;

@Service
public class SmsServiceImpl implements SmsService {

	private static final Logger logger = LoggerFactory.getLogger(SmsServiceImpl.class);
	@Autowired
	private TwilioConfig twilioConfig;
	
	@Override
	public void sendRequest(SmsRequest smsRequest) {

   //to, from , message
	MessageCreator creator = 	Message.creator(
				          new PhoneNumber(smsRequest.getPhoneNumber()),
				          new PhoneNumber(twilioConfig.getTrialNumber()), 
			              smsRequest.getMessage()
			            );
	
	creator.create(); //sends the message
	logger.info("Sms sent to --- [ " + smsRequest.getPhoneNumber() + " ] -----");
	
	}
}
