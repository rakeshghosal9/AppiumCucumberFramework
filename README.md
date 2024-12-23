
# BDD Framework

Behavior Driven Development (BDD) Framework enables software testers to complete test scripting in plain English. BDD mainly focuses on the behavior of the product and user acceptance criteria.To implement BDD Framework we have used Cucumber. Cucumber is one of the best tools used to develop in the BDD Framework.It uses Gherkin, an ordinary language parser, that permits writing scripts in English that can be easily understood. It is used to run the acceptance tests written in BDD.

We have developed this Mobile Automation Framework in BDD style for easy understanding. 


## Content

- [Highlights](#Highlights)
- [Prerequisites](#Prerequisites)
- [Maven Dependencies](#Maven-Dependencies)
- [Project Hierarchy](#Project-Hierarchy)
- [Understanding config.properties](#Understandgin-config.properties)
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

- JDK 11 or above installed on the machine. Please note **JAVA_HOME** should be set.
- Maven installed. Please note **M2_HOME** should be set.
- Download Node.js from **https://nodejs.org/en/download/prebuilt-installer** and install.
- Download Appium Server using the command **npm install -g appium**. 
- Install Android Studio from **https://developer.android.com/studio**.
- Once Android Studio is downloaded, run the Android Studio, create an empty project, and then go to **Tools -> SDK Manager -> SDK Tools**. Check the option Android SDK Platform-Tools. Click Apply and then press Ok.

![image](https://github.com/user-attachments/assets/85ab7030-b398-46bc-bf7a-06cb59e09b44)

- Eclipse/Intellij or other IDE to import the project. Recommended Intellij as it has inbuilt support for Cucumber.
- Set the PATH variable ANDROID_HOME. Usually it's **C:\Users\<user_id>\AppData\Local\Android\Sdk** in Windows Laptop.
- Add SDK Tools, SDK Platform Tools in PATH variable. Usually it's **C:\Users\rakes\AppData\Local\Android\Sdk\tools\bin, C:\Users\rakes\AppData\Local\Android\Sdk\tools, C:\Users\rakes\AppData\Local\Android\Sdk\platform-tools**.
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

           com.pages - Page object for each page of the App. 
           com.stepdefs - Step definition for the steps mentioned in the feature file. 
           com.runners - Sample TestNG runner for execution and debugging purpose.

- src/test/resources

           features - store cucumber feature files in this folder. 
           apps - keep the .apk and .ipa files in this folder.

- src/main/java

           com.utils - this folder contains various important files. We will go through each and everyone later.
    
- src/main/resources

           it contains config.properties and log4j2.xml files.  


## Understanding config.properties



## Page Object Model Implementation



## Running Test



## Report Generation


