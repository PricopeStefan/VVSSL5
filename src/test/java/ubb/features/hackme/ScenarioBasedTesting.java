package ubb.features.hackme;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import ubb.pages.HTMLStoragePage;
import ubb.steps.serenity.BabySteps;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@RunWith(SerenityRunner.class)
public class ScenarioBasedTesting {
    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @Steps
    public BabySteps pepega;

    @Test
    public void scenario_add_pair_web_storage() {
        /* Testing the login functionality - first step in the scenario */
        pepega.go_to_home_page();
        pepega.navigate_to_login();
        pepega.enter_user_creds("admin", "adminpass");
        webdriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        assert pepega.check_logout_button_exists();
        assert pepega.is_cookie_present("username");

        /* Testing the page containing web storage vulnerabilities */
        pepega.navigate_to_web_storage();

        List<HTMLStoragePage.HTMLStorageEntry> initial_entries = pepega.get_storage_entries_from_table();
        assert initial_entries.size() > 0; // by default o sa fie mai mult de 0 chestii salvate deja
        for (HTMLStoragePage.HTMLStorageEntry entry : initial_entries) {
            System.out.println(entry);
        }

        pepega.enter_storage_pair_with_type("hello", "world", HTMLStoragePage.StorageType.LOCAL);
        List<HTMLStoragePage.HTMLStorageEntry> modified_entries = pepega.get_storage_entries_from_table();

        assert modified_entries.size() == initial_entries.size() + 1;
        assert modified_entries.get(modified_entries.size() - 1).equals(new HTMLStoragePage.HTMLStorageEntry("hello", "world", HTMLStoragePage.StorageType.LOCAL));

        /* TESTING THE SUCCESFULL LOGOUT */
        pepega.do_logout();
        assert !pepega.is_cookie_present("username");
    }

    @Test
    public void scenario_view_file_source() {
        /* Testing the login functionality - first step in the scenario */
        pepega.go_to_home_page();
        pepega.navigate_to_login();
        pepega.enter_user_creds("admin", "adminpass");
        webdriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        assert pepega.check_logout_button_exists();
        assert pepega.is_cookie_present("username");


        pepega.navigate_to_source_viewer_page();
        webdriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        List<String> files = pepega.get_available_source_files();
        for (String file :files)
            System.out.println(file);

        Random rand = new Random();
        pepega.view_source_of_file(files.get(rand.nextInt(files.size())));

        webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        /* TESTING THE SUCCESFULL LOGOUT */
        pepega.do_logout();
        assert !pepega.is_cookie_present("username");
    }
}
