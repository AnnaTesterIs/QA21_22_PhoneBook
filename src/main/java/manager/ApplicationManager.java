package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
Logger logger = LoggerFactory.getLogger(ApplicationManager.class);
   // WebDriver wd;
    EventFiringWebDriver wd;
    HelperUser helperUser;
    HelperContact helperContact;
    String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }


    public void init(){
        if (browser.equals(BrowserType.CHROME)){
     //   wd = new ChromeDriver();
        wd = new EventFiringWebDriver(new ChromeDriver());
        logger.info("All tests run in Chrome Browser");
        } else if
        (browser.equals(BrowserType.FIREFOX)) {
            wd = new EventFiringWebDriver(new FirefoxDriver());
            logger.info("All tests run in Firefox Browser");
        }

        wd.manage().window().maximize();
        wd.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        wd.navigate().to("https://telranedu.web.app/home");
        logger.info("The link -->"  + wd.getCurrentUrl());
        helperUser = new HelperUser(wd);
        helperContact = new HelperContact(wd);
        wd.register(new ListenerWD());


    }

    public HelperContact getHelperContact(){
        return helperContact;


    }

    public HelperUser getHelperUser() {
        return helperUser;
    }

    public void stop(){
wd.quit();
    }
}
