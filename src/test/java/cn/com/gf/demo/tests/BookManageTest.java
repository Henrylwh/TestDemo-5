package cn.com.gf.demo.tests;

import cn.com.gf.demo.base.utils.SeleniumUtil;
import cn.com.gf.demo.flows.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import cn.com.gf.demo.pages.Homepage;
import org.testng.annotations.Test;
import cn.com.gf.demo.base.utils.Logger;
import cn.com.gf.demo.base.utils.Util;
import org.testng.annotations.AfterMethod;

/**
 * Created by Administrator on 2017/4/22.
 */
public class BookManageTest {
    private static final Logger logger = Logger.getLogger(BookManageTest.class);

    private WebDriver driver;

    @Parameters({"browserType", "url"})
    @BeforeMethod(alwaysRun = true, description = "Start driver", enabled = true)
    public void openBookList(String browserType, String url) throws Exception {
        driver = SeleniumUtil.createDriver(browserType);
        logger.info("Start Selenium Webdriver");
        driver.manage().window().maximize();
        driver.get(url);
        logger.info("Open test site");
        Homepage homepage = new Homepage();
        HomepageFlow homepageFlow = new HomepageFlow(driver);
        homepageFlow.clickTree();
        Thread.sleep(2000);
    }

    @AfterMethod(alwaysRun = true)
    public void closeDriver() {
        driver.close();
        driver.quit();
    }


    @Test(groups = {"SMOKE", "REGRESSION"}, description = "Add new book", enabled = true)
    public void createBookTest() {
        Util util = new Util();
        int beforeRowSize;
        int afterRowSize;
        String randomNo = String.valueOf(util.randomNo());
        String randomYear = String.valueOf(util.randomYear());
        logger.info("Add book test started");
        BookListFlow bookListFlow = new BookListFlow(driver);
        CreateUpdateBookFlow createUpdateBookFlow = new CreateUpdateBookFlow(driver);
        beforeRowSize = bookListFlow.rowSize();
        logger.info("The total book number before add is " + beforeRowSize);
        bookListFlow.clickCreate();
        createUpdateBookFlow.inputBookId(randomNo);
        createUpdateBookFlow.inputBookName("BookName" + randomNo);
        createUpdateBookFlow.inputBookAuthor("BookAuthor" + randomNo);
        createUpdateBookFlow.inputBookYear(randomYear);
        createUpdateBookFlow.inputBookDigest("BookDigest" + randomNo);
        createUpdateBookFlow.clickBtnSubmit();
        Assert.assertEquals(createUpdateBookFlow.getPopUpMsg(), "添加书籍成功");
        createUpdateBookFlow.clickBtnOk();
        afterRowSize = bookListFlow.rowSize();
        logger.info("The total book number after add is " + afterRowSize);
        Assert.assertEquals(afterRowSize, beforeRowSize + 1);
        Assert.assertTrue(bookListFlow.listContains("BookDigest" + randomNo));
        logger.info("Add book test finished");
    }

    @Test(groups = {"REGRESSION"}, description = "Test reset button of add book window", enabled = true)
    public void createBookResetTest() {
        Util util = new Util();
        String randomNo = String.valueOf(util.randomNo());
        String randomYear = String.valueOf(util.randomYear());
        logger.info("Create book reset button test start");
        BookListFlow bookListFlow = new BookListFlow(driver);
        CreateUpdateBookFlow createUpdateBookFlow = new CreateUpdateBookFlow(driver);
        bookListFlow.clickCreate();
        createUpdateBookFlow.inputBookId(randomNo);
        createUpdateBookFlow.inputBookName("BookName" + randomNo);
        createUpdateBookFlow.inputBookAuthor("BookAuthor" + randomNo);
        createUpdateBookFlow.inputBookYear(randomYear);
        createUpdateBookFlow.inputBookDigest("BookDigest" + randomNo);
        createUpdateBookFlow.clickBtnReset();
        Assert.assertEquals(createUpdateBookFlow.getBookNoValue(), "");
        logger.info("Reset button test finishd");
    }

    @Test(groups = {"REGRESSION"}, description = "Add duplicate book", enabled = true)
    public void createDuplicateTest() {
        String bookId;
        Util util = new Util();
        logger.info("Add duplicate book test start");
        BookListFlow bookListFlow = new BookListFlow(driver);
        CreateUpdateBookFlow createUpdateBookFlow = new CreateUpdateBookFlow(driver);

        bookId = createUpdateBookFlow.createBook();
        String randomNo = String.valueOf(util.randomNo());
        String randomYear = String.valueOf(util.randomYear());
        logger.info("First book is created successfully");

        bookListFlow.clickCreate();
        createUpdateBookFlow.inputBookId(bookId);
        createUpdateBookFlow.inputBookName("BookName" + randomNo);
        createUpdateBookFlow.inputBookAuthor("BookAuthor" + randomNo);
        createUpdateBookFlow.inputBookYear(randomYear);
        createUpdateBookFlow.inputBookDigest("BookDigest" + randomNo);
        createUpdateBookFlow.clickBtnSubmit();
        Assert.assertEquals(createUpdateBookFlow.getPopUpMsg(), "errorNo:1,errorInfo:该id已存在。");
        logger.info("Add duplicate book test finish");
    }

    @Test(groups = {"SMOKE", "REGRESSION"}, description = "Update book", enabled = true)
    public void UpdateTest() {
        int beforeRowSize;
        int afterRowSize;
        Util util = new Util();
        String randomNo = String.valueOf(util.randomNo());
        String randomYear = String.valueOf(util.randomYear());
        logger.info("Update book test start");
        BookListFlow bookListFlow = new BookListFlow(driver);
        CreateUpdateBookFlow createUpdateBookFlow = new CreateUpdateBookFlow(driver);

        bookListFlow.selectBook(createUpdateBookFlow.createBook());
        logger.info("Book to be updated is created successfully");
        beforeRowSize = bookListFlow.rowSize();

        bookListFlow.clickUpdate();

        createUpdateBookFlow.inputBookName("BookName" + randomNo);
        createUpdateBookFlow.inputBookAuthor("BookAuthor" + randomNo);
        createUpdateBookFlow.inputBookYear(randomYear);
        createUpdateBookFlow.inputBookDigest("BookDigest" + randomNo);
        createUpdateBookFlow.clickBtnSubmit();
        Assert.assertEquals(createUpdateBookFlow.getPopUpMsg(), "更新成功");
        createUpdateBookFlow.clickBtnOk();
        afterRowSize = bookListFlow.rowSize();
        Assert.assertEquals(afterRowSize, beforeRowSize);
        Assert.assertTrue(bookListFlow.listContains("BookDigest" + randomNo));
        logger.info("Update book test finished");
    }

    @Test(groups = {"REGRESSION"}, description = "Update book when the id is not exist", enabled = true)
    public void UpdateIdNotExistTest() {

        Util util = new Util();
        String randomNo = String.valueOf(util.randomNo());
        String randomYear = String.valueOf(util.randomYear());
        logger.info("Update book test start");
        BookListFlow bookListFlow = new BookListFlow(driver);
        CreateUpdateBookFlow createUpdateBookFlow = new CreateUpdateBookFlow(driver);

        bookListFlow.selectBook(createUpdateBookFlow.createBook());

        bookListFlow.clickUpdate();

        createUpdateBookFlow.inputBookId("BookId" + randomNo);
        createUpdateBookFlow.inputBookName("BookName" + randomNo);
        createUpdateBookFlow.inputBookAuthor("BookAuthor" + randomNo);
        createUpdateBookFlow.inputBookYear(randomYear);
        createUpdateBookFlow.inputBookDigest("BookDigest" + randomNo);
        createUpdateBookFlow.clickBtnSubmit();
        Assert.assertEquals(createUpdateBookFlow.getPopUpMsg(), "errorNo:1,errorInfo:该id不存在。");
        logger.info("Update book test finished");
    }

    @Test(groups = {"SMOKE", "REGRESSION"}, description = "Update book", enabled = true)
    public void DeleteTest() {
        int beforeRowSize;
        int afterRowSize;
        String bookId;
        Util util = new Util();
        logger.info("Delete book test start");
        BookListFlow bookListFlow = new BookListFlow(driver);
        CreateUpdateBookFlow createUpdateBookFlow = new CreateUpdateBookFlow(driver);
        DeleteBookFlow deleteBookFlow = new DeleteBookFlow(driver);

        bookId = createUpdateBookFlow.createBook();
        logger.info("Book to be deleted is created successfully");
        bookListFlow.selectBook(bookId);
        beforeRowSize = bookListFlow.rowSize();
        logger.info("The total book number before delete is " + beforeRowSize);

        bookListFlow.clickDelete();
        Assert.assertEquals(deleteBookFlow.getPopUpMsg(), "请谨慎操作，你真的要删除吗？");
        deleteBookFlow.clickYesBtn();
        Assert.assertEquals(deleteBookFlow.getPopUpMsg(), "删除书籍成功！");
        deleteBookFlow.clickOKBtn();
        afterRowSize = bookListFlow.rowSize();
        logger.info("The total book number after delete is " + afterRowSize);
        Assert.assertEquals(afterRowSize, beforeRowSize - 1);
        Assert.assertFalse(bookListFlow.listContains(bookId));
        logger.info("Delete book test finished");
    }

    @Test(groups = {"SMOKE", "REGRESSION"}, description = "Update book", enabled = true)
    public void CancelDeleteTest() {
        int beforeRowSize;
        int afterRowSize;
        String bookId;
        Util util = new Util();
        logger.info("Cancel delete book test start");
        BookListFlow bookListFlow = new BookListFlow(driver);
        CreateUpdateBookFlow createUpdateBookFlow = new CreateUpdateBookFlow(driver);
        DeleteBookFlow deleteBookFlow = new DeleteBookFlow(driver);

        bookId = createUpdateBookFlow.createBook();
        logger.info("Book to be deleted is created successfully");
        bookListFlow.selectBook(bookId);
        beforeRowSize = bookListFlow.rowSize();
        logger.info("The total book number before delete is " + beforeRowSize);

        bookListFlow.clickDelete();
        Assert.assertEquals(deleteBookFlow.getPopUpMsg(), "请谨慎操作，你真的要删除吗？");
        deleteBookFlow.clickNoBtn();
        afterRowSize = bookListFlow.rowSize();
        logger.info("The total book number after cancel delete is " + afterRowSize);
        Assert.assertEquals(afterRowSize, beforeRowSize);
        Assert.assertTrue(bookListFlow.listContains(bookId));
        logger.info("Cancel delete book test finished");
    }


}
