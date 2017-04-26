package cn.com.gf.demo.flows;

/**
 * Created by Administrator on 2017/4/22.
 */
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import cn.com.gf.demo.pages.BookListPage;
import cn.com.gf.demo.base.utils.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import java.util.*;
public class BookListFlow {
    private static final Logger logger = Logger.getLogger(BookListFlow.class);
    private WebDriver driver = null;
    private BookListPage factory = null;
    public BookListFlow(WebDriver driver) {
        this.driver = driver;
        factory = PageFactory.initElements(driver, BookListPage.class);
    }
    public int rowSize(){
        List<WebElement> rows=factory.bookListTb.findElements(By.tagName("tr"));
        return rows.size();
    }
    public void clickCreate(){
        factory.btnCreate.click();
        logger.info("Click create add book button");
    }
    public void clickUpdate(){
        factory.btnUpdate.click();
        logger.info("Click update book button");
    }
    public void clickDelete(){
        factory.btnDelete.click();
        logger.info("Click delete book button");
    }
    public void clickRefresh(){
        factory.btnRefresh.click();
    }

    public boolean listContains(String string){
        for(WebElement tr : factory.bookListTb.findElements(By.tagName("tr"))){
            List<WebElement> tdList = tr.findElements(By.tagName("td"));
            for(WebElement td:tdList){
                if(td.getText().equals(string))
                 return true;
            }
                   }
        return false;
    }
    public void selectBook(String string){
        for(WebElement tr : factory.bookListTb.findElements(By.tagName("tr"))){
            List<WebElement> tdList = tr.findElements(By.tagName("td"));
            for(WebElement td:tdList) {
                if (td.getText().equals(string)){
                    tr.click();
                }
            }
        }
    }
}
