
# BDD Framework

Behavior Driven Development (BDD) Framework enables software testers to complete test scripting in plain English. BDD mainly focuses on the behavior of the product and user acceptance criteria.To implement BDD Framework we have used Cucumber. Cucumber is one of the best tools used to develop in the BDD Framework.It uses Gherkin, an ordinary language parser, that permits writing scripts in English that can be easily understood. It is used to run the acceptance tests written in BDD.

We have developed this Mobile Automation Framework in BDD style for easy understanding. 


## Content

- [Highlights](#Highlights)
- [Prerequisites](#Prerequisites)
- [Maven Dependencies](#Maven-Dependencies)
- [Project Hierarchy](#Project-Hierarchy)
- [Environment Configuration](#Environment-Configuration)
- [Browsers Supported](#Browsers-Supported)
- [Page Object Model Implementation](#Page-Object-Model-Implementation)
- [Running Tests](#Running-Test)
- [Report Generation](#Report-Generation)


## Highlights

Let's see what are the features that you will get in this Framework.

- Latest version of Appium (as on 23rd December, 2024), i.e. 9.3.0.
- JUnit Framework.
- Support BDD style using Cucumber, easy to read feature files for all stakeholder, written in plain english.
- The Framework is built on Maven, hence all the dependencies are automatically downloaded and no need to do any manual set up.
- Followed Page Object Model that provides easy initialization of WebElements using Page Factory design pattern.
- Multi-Platform support (Android, iOS) for execution.
- Easy to configure parameters from properties file.
- Generation of JSON report post execution.
- Generation of Cucumber Report.
- Various reusable methods available such as Click, Selection of dropdown, Enter value in textbox etc.
- Enabling log4j2 for logging framework.
- Ability to attach screenshots in the report.
- Having option of storing videos as per demand. 

## Prerequisites

- JDK 11 or above installed on the machine. Please note JAVA_HOME should be set.
- Maven installed. Please note M2_HOME should be set.
- Download Node.js from https://nodejs.org/en/download/prebuilt-installer and install.
- Download Appium Server using the command npm install -g appium. 
- Install Android Studio from https://developer.android.com/studio.
- Once Android Studio is downloaded, run the Android Studio, create an empty project, and then go to Tools -> SDK Manager -> SDK Tools. Check the option Android SDK Platform-Tools. Click Apply and then press Ok.

![image](https://github.com/user-attachments/assets/85ab7030-b398-46bc-bf7a-06cb59e09b44)

- Eclipse/Intellij or other IDE to import the project. Recommended Intellij as it has inbuilt support for Cucumber.
- Set the PATH variable ANDROID_HOME. Usually it's C:\Users\<user_id>\AppData\Local\Android\Sdk in Windows Laptop.
- Add SDK Tools, SDK Platform Tools in PATH variable. Usually it's C:\Users\rakes\AppData\Local\Android\Sdk\tools\bin, C:\Users\rakes\AppData\Local\Android\Sdk\tools, C:\Users\rakes\AppData\Local\Android\Sdk\platform-tools.
- Internet access to download the dependencies from Maven Central Repository.
## Maven Dependencies
Maven Dependencies are mentioned in the pom.xml of a maven project. We are using below important dependencies in this project.

- Cucumber - 7.20.1
- Appium - 9.3.0 
- JUnit - 4.13.2

Please check pom.xml for other dependencies.

## Project Hierarchy

Please find below the project structure. You may not be able to understand everything at this moment, don't worry, everything will be explained in detail later.

- src/test/java

           org.examples.definitions - Step Definition files of the Cucumber Feature files.
           org.examples.pageObjects - Page objects for each web page to be automated.
           org.examples.reusablemethods - store all the reusable methods such as click, enter text , dropdown selection etc. 
           org.examples.runner - Sample TestNG runner for execution and debugging purpose.
           org.examples.utils - Utility to set up WebDriver using Dependency Injection.

- src/test/resources

           environments - properties file for each environments containing parameters specific to an environment.
           features - store cucumber feature files. 
           cucumber.properties - to define the cucumber properties such as report publishing etc.  
           extent.properties - define the properties to generate extent reports.  
           global.properties - kept for future enhancement to define something at the global level. 

## Environment Configuration

In real project we deal with multiple environments such as Dev, Integration, QA, UAT, Production etc. All these environments usually have multiple URLs, Database Parameters etc. To handle this we need to create properties file for each environment and put it into **src/test/resources/environments** folder. As of now only **URL** and **BROWSER** - these 2 parameters must be mentioned in each environment properties file.

We need to pass the environment in which we would like to run our tests in Maven Command **(For Example -Denv = "INTG1")**, in code we read the corresponding properties file in **BaseTest.java** inside the utils folder.

**Note :** If we pass **-Denv="INTG1"** in the Maven Command line for an example, then there must be a properties file name **INTG1.properties** present in **src\test\resources\environments** folder.
## Browsers Supported

As of now the framework can support Chrome, Firefox and Edge browsers. We need to define the browser in the **environment.properties** file as mentioned in the previous section. Valid values for BROWSER are:

         CHROME - to execute tests on Chrome Browser.
         FIREFOX - to execute tests on Firefox Browser. 
         EDGE - to execute tests on Edge Browser. 
         RANDOM - if we mention the browser as RANDOM, then the test will run on any of these above browsers.

**Note :** Please note with latest Selenium version (we are using 4.13), there is no need of drivers to launch the browsers.

## Page Object Model Implementation

Let's discuss how we have implemented Page Object Model in this framework. As mentioned in the project structure we have a package named **"pageObjects"** under **src/test/java**. In Page Onject Model we should create one Java Class for each Page and capture all the WebElements of the Page. Now, surely we can perform various oprarations on that Page. All these operations should be defined as Java Method in the same class.

For Example we have a Login Page in which we have User ID and Password as textbox and a Submit button. In this case we should create a java class, let's say LoginPage.java. We should capture below WebElements:

- Identifier (id,name,xpath etc. ) for User ID text box.
- Identifier for Password text box.
- Identifier for Submit button.

And below three java methods to define the operations.

- method to enter value in User ID.
- method to enter value in Password.
- method to click on Submit button.

Now the question is how shall we initialize the Page Object. Ideally it should be done from the Step Definition file. To give you a background, Step Definition file is a Java class in which we should write code for each step that is written in the Cucumber Feature File.

In a Page Object java class, we initialize the WebElements within its constructor. Please find below the sample code.

        public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

So, when we create an object of this class LoginPage.java, the constructor is getting called automatically and using the PageFactory, all the WebElements are initialized.

Page Object Mode is a big topic to cover, if you would like to know more, you may visit [here](https://www.guru99.com/page-object-model-pom-page-factory-in-selenium-ultimate-guide.html).

## Running Test

There are 2 ways to execute a test. Using a TestNG Runner class or using Maven command line.

To run with a TestNG Runner class we can use the runner file named **CucumberRunnerTests.java** in **src/test/java/org/example/runner**. 

To run using Maven Command, here is an example for you. Let's explain each one of them in details.

    clean test -Dcucumber.filter.tags=@ValidCredentials -Denv="ORANGEHRM" 

- clean : It's a Maven Phase with which at the beginning the target folder of the framework will be cleaned.
- test : It's a validation phase of Maven to build the project. It includes compilation of all the java files and then execute the test.
- -Denv : As mentioned in the [Environment Configuration](#Environment-Configuration) section, we need to pass the environment in which we need to execute the test. Please note "-D" is mandatory to define a system parameter. We can access this parameter using **System.getProperty("env")** .
- -Dtag : Define the tag that you want to run from the Cucumber feature files.

**Note :** Please note that default environment (i.e. if not provided any) can be set against variable **DEFAULT_ENVIRONMENT** in **BaseTest.java** file. 

## Report Generation

Below are the reporting options available:

- Cucumber latest version comes with default report that is hosted on Cloud. After the execution a URL will be generated which can be shared across team members. It's hosted on cloud and can be controlled using cucumber.properties file. We have set a value as **cucumber.publish.enabled=true** inside it. If you don't want to get it published, then simply put this parameter as false. 
- Maven surefire plugin generates another report inside target/Surefire suite folder, it comes with an emailable report as well. 
- We are generating extent report as well in our project which is controlled from CucumberRunnerTests.java file inside src/test/java/org/example/runner. 
- Cucumber.json would also be generated inside target/cucumber-reports/ folder. As this is a JSON file, many third party report generation tool uses it as input to generate report.
- Cucumber HTML report will also be generated in target/cucumber-report-html folder.
