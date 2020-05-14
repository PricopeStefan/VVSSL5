package ubb.features.hackme;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import ubb.steps.serenity.BabySteps;

@RunWith(SerenityRunner.class)
public class LogoutFunctionalityTest {
    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @Steps
    public BabySteps pepega;


    @Test
    public void test_logout_valid() {
        pepega.go_to_home_page();
        pepega.navigate_to_login();
        pepega.enter_user_creds("admin", "adminpass");

        pepega.do_logout();
        assert !pepega.is_cookie_present("username");
    }

    @Test
    public void test_logout_invalid() {
        pepega.go_to_home_page();

        //vrem ca butonu de logout sa nu existe in cazu asta si sa nu putem face logout
        assert !pepega.check_logout_button_exists();
    }
}
