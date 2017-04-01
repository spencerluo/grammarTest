package testcase;

import org.testng.annotations.Test;

import modules.LoginModule;
import utils.Locator;
import utils.Log;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterMethod;
import static utils.Actions.*;


public class LoginCase {
  @Test
  public void f() throws Exception {
	  LoginModule.login();
  }
  
  @BeforeMethod
  public void beforeMethod() throws Exception {
	  Locator.init();
	  Log.testStart();
	  openBrowser("chrome");
  }

  @AfterMethod(alwaysRun=true)
  public void afterMethod() {
	  quite();
	  Log.testEnd();
  }
  
}
