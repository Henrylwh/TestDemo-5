# TestDemo
Web 测试方案
测试框架主要采用Selenium+Maven+TestNG.
测试代码采用分层的方案。测试代码四层：
base文件夹内定义了一组基础的独立工具类，包括启动selenium webdriver, TestCaseListener以及一些utils.
pages文件夹定义的是web各页面的元素
flows文件夹定义的是操作各页面元素的方法
tests文件夹定义的是测试用例

resources\suiteXmlFile文件夹下的是TestNG的xml文件。
xml文件定义了两个参数，browserType 和url，当需要切换浏览器和测试地址时，只需要更改这两个参数的值。当需要执行不同的测试集，比如冒烟测试或者回归测试，只需要修改xml文件的groups.
BookManageTest中定义了两个测试集：SMOKE和REGRESSION。SMOKE包括新增记录，修改记录，删除记录。在新增，修改，删除之后都会查询记录列表，验证是否操作成功。REGRESSION包括取消新增，新增重复记录，修改ID不存在记录，取消删除记录。
测试的执行。
可以在IDE直接执行resources\suiteXmlFile\testng.xml.或者通过mvn clean test命令执行测试。测试结果在文件夹target\surefire-reports。当测试失败时，会生成截图存到screenshot文件夹。测试结果例子请参考WebTestReport.html

接口测试方案
接口测试采用postman+newman
环境准备：安装postman和Node.JS, 接下来安装newman. 为了提高安装速度，先在nodejs执行npm config set registry https://registry.npm.taobao.org, 之后执行npm install -g newman安装newman
测试用例包括正常的新增，查询，更新，删除，另外包括新增重复记录，更新ID不存在记录，删除ID不存在记录等。在测试过程中定义了环境变量URL，以及全局变量BookId.
测试的执行。可以在postman直接执行collection。或者在命令行执行：
newman run TestDemo.postman_collection.json -g globals.postman_globals.json -e QASite.postman_environment.json --reporters cli,html,json,junit --reporter-json-export jsonOut.json --reporter-junit-export xmlOut.xml --reporter-html-export htmlOut.html