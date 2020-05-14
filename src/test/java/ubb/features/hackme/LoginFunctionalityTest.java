package ubb.features.hackme;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Issue;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Pending;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.Qualifier;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.springframework.core.annotation.Order;
import ubb.steps.serenity.BabySteps;

import java.util.concurrent.TimeUnit;


@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom("src/test/resources/invalid_passwords.csv")
public class LoginFunctionalityTest {
    public String password;
    public String shouldPass;
    @Qualifier
    public String getQualifier() {
        return password;
    }

    @Managed(uniqueSession = true)
    public WebDriver webdriver;
    @Steps
    public BabySteps pepega;

    @Test
    @Order(1)
    public void test_login_with_passwords() {
        pepega.go_to_home_page();
        pepega.navigate_to_login();

        pepega.enter_user_creds("admin", getPassword());
        System.out.println("TESTING " + getPassword() + " passsable = " + getShouldPass());
        if (shouldPass.equals("1")) {
            webdriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            assert pepega.check_logout_button_exists();
            assert pepega.is_cookie_present("username");
        }
        else
            assert !pepega.is_cookie_present("username");
    }



    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getShouldPass() {
        return shouldPass;
    }
    public void setShouldPass(String shouldPass) {
        this.shouldPass = shouldPass;
    }
}
