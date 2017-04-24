package cn.com.gf.demo.pages;

/**
 * Created by Administrator on 2017/4/23.
 */
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class CreateUpdateBookPage {
//    private WebDriver driver = null;
//    public CreateUpdateBookPage(WebDriver driver) {
//        this.driver = driver;
//    }
    @FindBy(how = How.ID, using = "window-1081_header_hd-textEl")
    public WebElement windowHeader;
    @FindBy(how = How.NAME, using = "id")
    public WebElement bookId;
    @FindBy(how = How.NAME, using = "name")
    public WebElement bookName;
    @FindBy(how = How.NAME, using = "author")
    public WebElement bookAuthor;
    @FindBy(how = How.NAME, using = "year")
    public WebElement bookYear;
    @FindBy(how = How.NAME, using = "digest")
    public WebElement bookDigest;
    @FindBy(how = How.XPATH, using = "//span[@class='x-btn-inner x-btn-inner-center' and contains(text(),'提交')]")
    public WebElement btnSubmit;
    @FindBy(how = How.ID, using = "button-1060-btnIconEl")
    public WebElement btnReset;
    @FindBy(how = How.ID, using = "messagebox-1001-displayfield-inputEl")
    public WebElement popUpMsg;
    @FindBy(how = How.ID, using = "button-1005-btnIconEl")
    public WebElement btnOK;
}
