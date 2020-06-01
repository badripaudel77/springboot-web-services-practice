package io.badri.app.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.badri.app.entity.SmsRequest;
import io.badri.app.service.BirthdayService;
import io.badri.app.service.SmsService;

@RestController()
@RequestMapping("/users")
public class TwilioSmsController {

	@Autowired
	private SmsService smsService; 
	
	@Autowired
	private BirthdayService birthdayService;
	
	@PostMapping("/sms")
	public String sendSms(@RequestBody SmsRequest smsRequest) {
		
		smsService.sendRequest(smsRequest);
		
		return "sms sent successfully ! ";
	}
	
	@GetMapping("/sms/birthday")
	@ResponseBody
	public String sendBirthdaySms() {
		
		birthdayService.sendBirthdayWish();
		return "sms sent successfully ! ";
	}
}

