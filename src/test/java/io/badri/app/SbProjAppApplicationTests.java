package io.badri.app;

import java.time.Instant;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SbProjAppApplicationTests {

	@Test
	void contextLoads() {
		
	}
	
	@Test 
	void test() {
		System.out.println("time " + Instant.now().plusSeconds(86400L));
	}

}
