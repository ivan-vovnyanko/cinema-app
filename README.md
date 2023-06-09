# Cinema App 🎥

***An application that mimics the internal structure of the cinema service.***

Written **backend part** of the application.<br>
For implementation, such *popular frameworks* as ***Hibernate*** and ***Spring(security, webmvc, orm, context)*** were used.
All *errors* and *major changes* in the database are recorded by the ***logger(log4j v2).***
For the reliability of the application, **tests** have been made. 
Tests made with support by **junit 5** , **local database** and **Mockito component.**
Application authentication works on ***jwt keys.***
You can use the application by sending requests through ***Postman.***
Below is how to use the app ***:)*** <br>

***

## Functional ⚙️

To use, you need to define a ***role.***<br>

If you are a ***USER***, then you will be able **to view the available sessions**, **choose the ones you like** and **add them to your cart** and at the end **make an order.**<br> 
If you are an ***ADMIN***, you will be able **to edit halls**, **add and remove movies**, and **create sessions for your clients** to sign up for.

### 1. User 🙂

To start using the service, you only need **to register.**
You must enter ***a valid email*** and ***password*** *(at least 8 characters).*
As soon as you do this, you will automatically be given the ***user role.***
What *features* do you have? 
By specifying **the movie** you need and **the date** you want to go to, you will be given ***a list of all available sessions.*** 
If you are interested in any of the sessions, you can ***add it to your cart.***
When you're ready, you can ***complete the purchase*** *(the app is simple, so all you need to do is make a specific **http request**).*
You will also have the opportunity to view all your **completed purchases**,
which store information about ***tickets** and ***the date the purchase was made.***

### 2. Admin 🛠️

Now let's talk about the ***admin*** role.


*Initially*, we have **1 admin account** with static login data. 
What can our *admin* do? 
It can ***add new movies*** for *our cinema.*
To *create a movie*, you first need **to name it** and **give it a description.**
We also have the ability **to create halls** for viewing.
**A hall** can have **a minimum of 10 seats** and also needs **a description.** 
With at least 1 movie and 1 hall, we can **create viewing sessions!** 
To do this, we select **the desired film**, **the desired hall** and **the date with the time** when the session will take place. 
Also, *our admin* can view information about **all created films, halls and sessions.** 


Look like that's it)

## Launch 🚀

1. Download the code from <a href="https://github.com/ivan-vovnyanko/hospital/archive/refs/heads/develop.zip">**GitHub**</a> and save it to your computer.
2. Now we are **setting up your local database based on MySql:**
    - **Install MySql** on your device, you can find information on how to set it up during installation on the network
      (<a href="https://dev.mysql.com/downloads/installer/">***Windows***</a> or <a href="https://dev.mysql.com/doc/refman/8.0/en/macos-installation.html">***MacOS***</a>).
    - Open the workbench and create a new schema called ***cinema_db***
    - Now you need **to set up a configuration file** for connecting to the database *(no need to create tables, hibernate will do it for you).*
      Open the **db.properties** file in the **src/main/resources** package and change the following fields:
        - In field ***db.url*** you must enter **the address of your local database.**
          *Usually, such links are the same for everyone and you do not need to change them.*
        - In field ***db.user*** you must enter **the login that you specified when installing mysql.**
        - In field ***db.password*** you must enter **the password that you specified when installing mysql.**
3. Now you need **to set up your Tomcat:**
    - <a href="https://tomcat.apache.org/download-90.cgi#9.0.72">***Download Tomcat***</a> from open resources. I have version ***9.0.72***, I advise you to use it.
    - Open the application launch options and select **Local Tomcat** as the plugin to launch.
      Select **the folder with your Tomcat plugin** in the settings.
    - Leave the ***Context path*** field blank.
4. Now click on the ***Run*** button or the hotkey combination ***Shift + F10.***
   If you see the line below **in the console**, then we have ***successfully launched the program.***


5. The logger does not need to be configured, it is done in advance.
>❗ Please note that log files **may not be saved in the project folder.** The log file may be located in your **TomCat folder.**

## 💻 Used technologies
+ <a style="color:#ffed3a; text-decoration: none; font-size: 22px; font-weight: bold;" href="https://blogs.apache.org/maven/entry/apache-maven-compiler-plugin-version">Hibernate Core</a>
+ <a style="color:#ffed3a; text-decoration: none; font-size: 22px; font-weight: bold;" href="https://mvnrepository.com/artifact/org.hibernate/hibernate-validator/6.1.6.Final">Hibernate Validator</a>
+ <a style="color:#ffed3a; text-decoration: none; font-size: 22px; font-weight: bold;" href="https://mvnrepository.com/artifact/org.springframework/spring-webmvc/5.3.25">Spring WebMvc</a>
+ <a style="color:#ffed3a; text-decoration: none; font-size: 22px; font-weight: bold;" href="https://mvnrepository.com/artifact/org.springframework/spring-context/5.3.25">Spring Context</a>
+ <a style="color:#ffed3a; text-decoration: none; font-size: 22px; font-weight: bold;" href="https://auth0.com/blog/spring-security-overview/">Spring Security</a>
+ <a style="color:#ffed3a; text-decoration: none; font-size: 22px; font-weight: bold;" href="https://blogs.apache.org/maven/entry/apache-maven-compiler-plugin-version">Apache Maven Compiler Plugin</a>
+ <a style="color:#ffed3a; text-decoration: none; font-size: 22px; font-weight: bold;" href="https://mvnrepository.com/artifact/org.apache.maven.plugins/maven-war-plugin/3.3.2">Apache Maven WAR Plugin</a>
+ <a style="color:#ffed3a; text-decoration: none; font-size: 22px; font-weight: bold;" href="https://blogs.apache.org/maven/entry/apache-maven-checkstyle-plugin-version1">Apache Maven Checkstyle Plugin</a>
+ <a style="color:#ffed3a; text-decoration: none; font-size: 22px; font-weight: bold;" href="https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-core/2.19.0">Apache Maven Log4j V.2 Plugin</a>
+ <a style="color:#ffed3a; text-decoration: none; font-size: 22px; font-weight: bold;" href="https://mvnrepository.com/artifact/javax.servlet/javax.servlet-api/4.0.1">JavaServlet API</a>
+ <a style="color:#ffed3a; text-decoration: none; font-size: 22px; font-weight: bold;" href="https://mvnrepository.com/artifact/mysql/mysql-connector-java/8.0.22">MySQL Connector Java</a>
+ <a style="color:#ffed3a; text-decoration: none; font-size: 22px; font-weight: bold;" href="https://mvnrepository.com/artifact/javax.servlet/jstl/1.2">Java JSTL</a>
+ <a style="color:#ffed3a; text-decoration: none; font-size: 22px; font-weight: bold;" href="https://www.tutorialspoint.com/guice/guice_field_injection.htm#:~:text=Injection%20is%20a%20process%20of,the%20field%20of%20an%20object.">Dependency Injection With Field Injection (Injector)</a>
+ <a style="color:#ffed3a; text-decoration: none; font-size: 22px; font-weight: bold;" href="https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api/5.9.2">JUnit Jupiter API</a>
+ <a style="color:#ffed3a; text-decoration: none; font-size: 22px; font-weight: bold;" href="https://mvnrepository.com/artifact/org.mockito/mockito-core/5.1.1">Mockito API</a>


