package io.badri.app.service.impl;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.badri.app.entity.Mail;
import io.badri.app.entity.User;
import io.badri.app.entity.VerificationToken;
import io.badri.app.repository.UserRepository;
import io.badri.app.repository.VerificationRepository;
import io.badri.app.service.MailService;
import io.badri.app.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Value("${spring.mail.username}") // read from properties file.
	private String mailFrom;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	private UserRepository userRepo;

	@Autowired
	public UserServiceImpl(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	@Autowired
	private MailService mailService;

	@Autowired
	private VerificationRepository verificationTokenRepository;

	@Override
	public void saveUser(User user) {

		User usrWithEmail = userRepo.findByEmail(user.getEmail());
		User usrWithUsername = userRepo.findByUsername(user.getUsername());

		// if returned null there is no user exists already
		if (usrWithEmail == null && usrWithUsername == null) {
			
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			userRepo.save(user);
			
			// send email with token
			String token = generateVerficationToken(user);

			Mail mail = new Mail();

			mail.setMailFrom(mailFrom);
			mail.setMailTo(user.getEmail());
			mail.setMailSubject("Verify email");
			mail.setMailContent("Activate the account by clicking this link below.");

			try {
				mailService.sendMail(mail, token);
			}

			catch (Exception e) {
				e.printStackTrace();
			}

		} else
			throw new RuntimeException("User with given email or username already exists.");
	}

	@Override
	@Transactional
	public boolean updateUser(User user, int id) {
		// User u = userRepo.findById(id);

		/*
		 * User u = userRepo.findById(id); if(u != null) {
		 * u.setFirstName(user.getFirstName()); u.setLastName(user.getLastName());
		 * u.setDob(user.getDob()); u.setEmail(user.getEmail());
		 * u.setUsername(user.getUsername()); // u.setId(id);
		 * System.out.println("updated id " + user.getId()); return true; }
		 */
		if (user != null) {
			user.setId(id);
			userRepo.save(user);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteUser(int id) {

		if (id != 0) {
			userRepo.deleteById(id);
			return true;
		}

		return false;
	}

	@Override
	public List<User> listUser() {
		return userRepo.findAll();
	}

	@Override
	public boolean getLoggedinUser(User user) {

		String uname = user.getUsername();
		String email = user.getEmail();
		String password = user.getPassword();

		if ((userRepo.findByUsername(uname) != null && userRepo.findByPassword(password) != null)
				|| (userRepo.findByPassword(password) != null && userRepo.findByEmail(email) != null)) {

			return true;
		}
		return false;
	}

	private String generateVerficationToken(User user) {
		String token = UUID.randomUUID().toString();
		VerificationToken verificationToken = new VerificationToken();
		verificationToken.setToken(token);
		verificationToken.setUser(user);
		verificationToken.setExpiryDate(Instant.now().plusSeconds(86400L)); //for 24 hours

		verificationTokenRepository.save(verificationToken);
		return token;
	}

	@Override
	@Transactional
	public void verifyAccount(String theToken) {

		VerificationToken verificationToken = verificationTokenRepository.findByToken(theToken);
		// verificationToken.orElseThrow(); applicable for
		// token's expiry date
		 
		if (verificationToken != null && verificationToken.getExpiryDate() != null) {
           
			fetchUserandEnable(verificationToken);
            
		}
		
		else throw new RuntimeException("Token expired, signup again.");
	}

	private void fetchUserandEnable(VerificationToken verificationToken) {

		String username = verificationToken.getUser().getUsername();

		User user = userRepo.findByUsername(username);

		user.setEnabled(true);

		userRepo.save(user);
		// verificationToken.setToken("");
		verificationTokenRepository.deleteByUser(user); //once enabled , delete that user from the database.
		// verificationTokenRepository.save(verificationToken);
	}
}
