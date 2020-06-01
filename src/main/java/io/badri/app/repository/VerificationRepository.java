package io.badri.app.repository;

import java.time.Instant;

import org.springframework.data.jpa.repository.JpaRepository;

import io.badri.app.entity.User;
import io.badri.app.entity.VerificationToken;

public interface VerificationRepository extends JpaRepository<VerificationToken, Long> {

	VerificationToken findByToken(String token);

	void deleteByUser(User user);

	void findByExpiryDate(Instant time);
	
}
