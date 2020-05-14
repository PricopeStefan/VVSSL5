package ubb.steps.serenity;

import net.thucydides.core.annotations.Step;
import org.openqa.selenium.By;
import ubb.pages.HTMLStoragePage;
import ubb.pages.HomePage;
import ubb.pages.SourceViewerPage;

import java.util.List;

public class BabySteps {
    HomePage homePage;
    HTMLStoragePage htmlStoragePage;
    SourceViewerPage sourceViewerPage;

    @Step
    public void go_to_home_page() {
        homePage.open();
    }

    @Step
    public void navigate_to_login() {
        homePage.navigate_to_login();
    }

    @Step
    public void enter_user_creds(String username, String password) {
        homePage.enter_user_creds(username, password);
    }

    @Step
    public boolean is_cookie_present(String cookie_key) {
        return homePage.getDriver().manage().getCookieNamed(cookie_key) != null;
    }

    @Step
    public boolean check_logout_button_exists() {
        System.out.println("LOGOUT ELEMENT COUNT = " + homePage.getDriver().findElements(By.linkText("Logout")).isEmpty());
        return !homePage.getDriver().findElements( By.linkText("Logout") ).isEmpty();
    }

    @Step
    public void do_logout() {
        homePage.do_logout();
    }

    @Step
    public void navigate_to_web_storage() {
        htmlStoragePage.open();
    }

    @Step
    public void enter_storage_pair_with_type(String key, String value, HTMLStoragePage.StorageType pair_type) {
        htmlStoragePage.enter_storage_pair_with_type(key, value, pair_type);
    }

    @Step
    public List<HTMLStoragePage.HTMLStorageEntry> get_storage_entries_from_table() {
        return htmlStoragePage.get_storage_entries_from_table();
    }

    @Step
    public void navigate_to_source_viewer_page() {
        sourceViewerPage.open();
    }
    @Step
    public List<String> get_available_source_files() {
        return sourceViewerPage.get_available_files();
    }

    @Step
    public void view_source_of_file(String file) {
        sourceViewerPage.view_source_of(file);
    }
}
