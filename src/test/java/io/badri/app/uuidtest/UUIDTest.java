package io.badri.app.uuidtest;

import java.time.Instant;
import java.util.UUID;

import org.junit.jupiter.api.Test;

public class UUIDTest {

	@Test
	public void getUUID() {
	UUID uuid = UUID.randomUUID();
	System.out.println("UUID " + uuid);
	
  }
}
