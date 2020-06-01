package io.badri.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.mem.InMemoryUsersConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.connect.web.SessionUserIdSource;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;

@Configuration
public class SocialConfig implements SocialConfigurer {

	@Value("${spring.social.facebook.app-id}")
	private String appId;
	@Value("${spring.social.facebook.app-secret}")
	private String appSecret;

	@Override
	public void addConnectionFactories(final ConnectionFactoryConfigurer cfConfig, final Environment env) {
		cfConfig.addConnectionFactory(new FacebookConnectionFactory(appId, appSecret));
	}

	@Override
	public UserIdSource getUserIdSource() {
		return new SessionUserIdSource();
	}

	@Override
	public UsersConnectionRepository getUsersConnectionRepository(
			final ConnectionFactoryLocator connectionFactoryLocator) {
		return new InMemoryUsersConnectionRepository(connectionFactoryLocator);
	}

	@Bean
	public ConnectController connectController(ConnectionFactoryLocator connectionFactoryLocator,
			ConnectionRepository connectionRepository) {
		return new ConnectController(connectionFactoryLocator, connectionRepository);
	}
}