package ubb.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@DefaultUrl("https://hackme.pricope-stefan.com/index.php?page=html5-storage.php")
public class HTMLStoragePage extends PageObject {
    public enum StorageType {
        LOCAL,
        SESSION
    }


    @FindBy(id = "idDOMStorageKeyInput") private WebElementFacade storage_key_input;
    @FindBy(id = "idDOMStorageItemInput") private WebElementFacade storage_value_input;
    @FindBy(xpath = "//input[@type='radio' and @value='Session']") private WebElementFacade storage_type_radio_session;
    @FindBy(xpath = "//input[@type='radio' and @value='Local']") private WebElementFacade storage_type_radio_local;
    @FindBy(xpath = "//input[@type='button' and @value='Add New']") private WebElementFacade add_new_pair_button;

    private Select dropdown;

    public void enter_storage_pair_with_type(String key, String value, StorageType pair_type) {
        storage_key_input.type(key);
        storage_value_input.type(value);

        if (pair_type == StorageType.LOCAL)
            storage_type_radio_local.click();
        else
            storage_type_radio_session.click();

        add_new_pair_button.click();
    }

    public List<HTMLStorageEntry> get_storage_entries_from_table() {
        WebElementFacade entries_table_body = find(By.id("idSessionStorageTableBody"));
        return entries_table_body.findElements(By.tagName("tr")).stream()
                .map( element -> {
                    String pair_key = element.findElements(By.tagName(("td"))).get(0).getText();
                    String pair_value = element.findElements(By.tagName(("td"))).get(1).getText();
                    StorageType pair_type = StorageType.valueOf(element.findElements(By.tagName(("td"))).get(2).getText().toUpperCase());
                    return new HTMLStorageEntry(pair_key, pair_value, pair_type);
                })
                .collect(Collectors.toList());
    }

    public static class HTMLStorageEntry {
        private String key;
        private String value;
        private StorageType type;

        public HTMLStorageEntry(String key, String value, StorageType type) {
            this.key = key;
            this.value = value;
            this.type = type;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public StorageType getType() {
            return type;
        }

        public void setType(StorageType type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "HTMLStorageEntry{" +
                    "key='" + key + '\'' +
                    ", value='" + value + '\'' +
                    ", type=" + type +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            HTMLStorageEntry that = (HTMLStorageEntry) o;
            return Objects.equals(key, that.key) &&
                    Objects.equals(value, that.value) &&
                    type == that.type;
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value, type);
        }
    }
}
