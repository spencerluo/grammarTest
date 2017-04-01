package testcase;

import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import utils.Log;


public class NewTest {
  @Test
  public void f() {
	  Log.info("NewTest");
  }
  
  @Parameters("browser")
  @Test
  public void g(String browser) throws Exception{
	  Log.info("NewTest2");
  }
}
