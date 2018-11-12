package demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "common")
public class CommonConfig {

	private String keyForAES;

	public String getKeyForAES() {
		return keyForAES;
	}

	public void setKeyForAES(String keyForAES) {
		this.keyForAES = keyForAES;
	}

}
