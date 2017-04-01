package modules;

import static utils.Actions.*;

public class LoginModule {

	public static void login() throws Exception {

		getUrl("http://study-miniblog-new.qa.netease.com/login");
		sendKeys("LoginPage", "Username", "spencer");
		sendKeys("LoginPage", "Password", "asdD1234");
		click("LoginPage", "Submit");
	}
}
