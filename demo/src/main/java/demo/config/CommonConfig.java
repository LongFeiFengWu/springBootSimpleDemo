package demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "common")
public class CommonConfig {

	private String keyForAES;

	private String verifyCodeStr;

	private String verifyCodeName;

	private boolean verifyCodeSwitch;

	private boolean regSwitch;

	public String getKeyForAES() {
		return keyForAES;
	}

	public void setKeyForAES(String keyForAES) {
		this.keyForAES = keyForAES;
	}

	public String getVerifyCodeStr() {
		return verifyCodeStr;
	}

	public void setVerifyCodeStr(String verifyCodeStr) {
		this.verifyCodeStr = verifyCodeStr;
	}

	public String getVerifyCodeName() {
		return verifyCodeName;
	}

	public void setVerifyCodeName(String verifyCodeName) {
		this.verifyCodeName = verifyCodeName;
	}

	public boolean isVerifyCodeSwitch() {
		return verifyCodeSwitch;
	}

	public void setVerifyCodeSwitch(boolean verifyCodeSwitch) {
		this.verifyCodeSwitch = verifyCodeSwitch;
	}

	public boolean isRegSwitch() {
		return regSwitch;
	}

	public void setRegSwitch(boolean regSwitch) {
		this.regSwitch = regSwitch;
	}
}
