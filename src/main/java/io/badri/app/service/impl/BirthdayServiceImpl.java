package io.badri.app.service.impl;

import java.time.LocalDate;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;

import io.badri.app.config.TwilioConfig;
import io.badri.app.entity.User;
import io.badri.app.repository.UserRepository;
import io.badri.app.service.BirthdayService;

@Service
@EnableScheduling
public class BirthdayServiceImpl implements BirthdayService {

	private static Logger logger = LoggerFactory.getLogger(BirthdayServiceImpl.class);

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private TwilioConfig twilioConfig;

	@PostConstruct 
	public List<User> loadUsers() {

//	int year = LocalDate.now().getYear(); 
//	int month = LocalDate.now().getMonthValue();
//	int day = LocalDate.now().getDayOfMonth();

		List<User> userListByDateofBirth = userRepo.findByDob(LocalDate.now()); // user lists .parse(year + "-" + month
																				// + "-" + day)

		// test : User user1 = userRepo.findById(4);
		// userRepo.findAll();

		logger.info(" ----------------- what is returned by findByDob() method ? ------------------- "
				+ userListByDateofBirth + " ----- ");
		return userListByDateofBirth;

	}

	@Override
	@Scheduled(cron ="0 8 * * * *") // runs every 8:00 am every day and sends birthday to user if they have.
	public void sendBirthdayWish() {

		List<User> users = loadUsers(); // gives users [list] whose birthday is today
       
		for (int i = 0; i < users.size(); i++) {
			MessageCreator creator = Message.creator(new PhoneNumber(users.get(i).getPhoneNumber()),
					new PhoneNumber(twilioConfig.getTrialNumber()), "Happy Birthday " +
			       users.get(i).getFirstName() + " stay blessed !!");

			       creator.create(); // sends the message
		}
	}

}
