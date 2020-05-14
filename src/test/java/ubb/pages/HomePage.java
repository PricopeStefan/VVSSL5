package ubb.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;

@DefaultUrl("https://hackme.pricope-stefan.com/index.php")
public class HomePage extends PageObject {
    @FindBy(linkText="Login/Register")
    private WebElementFacade login_hyperlink;

    public void navigate_to_login() {
        login_hyperlink.click();
    }

    @FindBy(name = "username")
    private WebElementFacade username_input;
    @FindBy(name = "password")
    private WebElementFacade password_input;

    public void enter_user_creds(String username, String password) {
        username_input.typeAndTab(username);
        password_input.typeAndEnter(password);
    }

    @FindBy(linkText = "Logout")
    private WebElementFacade logout_hyperlink;
    public void do_logout() {
        logout_hyperlink.click();
    }
}
