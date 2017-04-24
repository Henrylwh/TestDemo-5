package cn.com.gf.demo.flows;

/**
 * Created by Administrator on 2017/4/23.
 */
import cn.com.gf.demo.base.utils.Util;
import cn.com.gf.demo.pages.CreateUpdateBookPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.apache.log4j.Logger;

public class CreateUpdateBookFlow {
    private static final Logger logger = Logger.getLogger(BookListFlow.class);
    private WebDriver driver = null;
    private CreateUpdateBookPage factory = null;
    public CreateUpdateBookFlow(WebDriver driver) {
        this.driver = driver;
        factory = PageFactory.initElements(driver, CreateUpdateBookPage.class);
    }
    public String getWindowTitle(){
        return factory.windowHeader.getText();
    }
    public void inputBookId(String bookNo){
        factory.bookId.click();
        factory.bookId.clear();
        factory.bookId.sendKeys(bookNo);
    }
    public void inputBookName(String bookTitle){
        factory.bookName.click();
        factory.bookName.clear();
        factory.bookName.sendKeys(bookTitle);
    }
    public void inputBookYear(String bookYear){
        factory.bookYear.click();
        factory.bookYear.clear();
        factory.bookYear.sendKeys(bookYear);
    }
    public void inputBookAuthor(String bookAuthor){
        factory.bookAuthor.click();
        factory.bookAuthor.clear();
        factory.bookAuthor.sendKeys(bookAuthor);
    }
    public void inputBookDigest(String bookSummary){
        factory.bookDigest.click();
        factory.bookDigest.clear();
        factory.bookDigest.sendKeys(bookSummary);
    }
    public void clickBtnSubmit(){
        Actions action = new Actions(driver);
        action.moveToElement(factory.btnSubmit).click().perform();
    }
    public void clickBtnReset(){
        factory.btnReset.click();
    }
    public String getPopUpMsg(){
        return factory.popUpMsg.getText();
    }
    public void clickBtnOk(){
        factory.btnOK.click();
    }

    public String getBookNoValue(){
        return factory.bookId.getAttribute("value");
    }
    public String createBook() {
        Util util=new Util();
        String randomNo=String.valueOf(util.randomNo());
        String randomYear=String.valueOf(util.randomYear());
        BookListFlow bookListFlow=new BookListFlow(driver);
        CreateUpdateBookFlow createUpdateBookFlow =new CreateUpdateBookFlow(driver);
        bookListFlow.clickCreate();
        createUpdateBookFlow.inputBookId(randomNo);
        createUpdateBookFlow.inputBookName("BookName"+randomNo);
        createUpdateBookFlow.inputBookAuthor("BookAuthor"+randomNo);
        createUpdateBookFlow.inputBookYear(randomYear);
        createUpdateBookFlow.inputBookDigest("BookDigest"+randomNo);
        createUpdateBookFlow.clickBtnSubmit();
        createUpdateBookFlow.clickBtnOk();
        logger.info("New book inserted");
        return randomNo;
    }
}
