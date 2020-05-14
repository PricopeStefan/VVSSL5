package ubb.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;

@DefaultUrl("https://hackme.pricope-stefan.com/index.php?page=source-viewer.php")
public class SourceViewerPage extends PageObject {
    @FindBy(id = "id_file_select")
    private WebElementFacade dropdown_files;

    @FindBy(name = "source-file-viewer-php-submit-button")
    private WebElementFacade submit_button;

    public List<String> get_available_files() {
        System.out.println("SIZE = " + dropdown_files.findElements(By.tagName("option")).size());

        return dropdown_files.findElements(By.tagName("option")).stream()
                .map( element -> element.getText())
                .collect(Collectors.toList());
    }

    public void view_source_of(String file) {
        Select drpCountry = new Select(dropdown_files);
        drpCountry.selectByVisibleText(file);
        submit_button.click();
    }
}
