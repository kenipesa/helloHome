# Hello Home <a name="top"></a>

### Table of Contents
* [About](#about)
* [Members](#members)
* [Project Links](#pLinks)
* [File Structure](#files)
* [Resources](#resources)

### About <a name="about"></a>
helloHome  
This site is designed to help users who are currently planning on moving by providing budgeting help, a map view of neighborhoods in a city searched by the user and a link to zillow’s page of information about the given neighborhood that includes median home values, the market type and much more.


### Members <a name="members"></a>
* Kevin Couture
* Nicholas Paro
* Peter Lee
* Sapana Poudel

### Project Links <a name="pLinks"></a>
* [Trello Board](https://trello.com/b/FJsnmXIV/kenipesa)
* [Wireframe](https://app.moqups.com/RQTQyO8RUn/view/page/ad64222d5)
* [Database Schema](./project-link-files/HelloHome.pdf)
* [Team Agreement](./project-link-files/team-agreement.md)
* [User Stories](./project-link-files/user-stories.md)

### File Structure <a name="files"></a>
* src/main/java/
    * [HelloHomeApplication](./src/main/java/com/kenipesa/helloHome/HelloHomeApplication.java)
    * config/
      * [UserDetailsServiceImpl](./src/main/java/com/kenipesa/helloHome/config/UserDetailsServiceImpl.java)
      * [WebSecurityConfig](./src/main/java/com/kenipesa/helloHome/config/WebSecurityConfig.java)
    * controllers/
      * [ApplicationUserController](./src/main/java/com/kenipesa/helloHome/controllers/ApplicationUserController.java)
      * [HomeController](./src/main/java/com/kenipesa/helloHome/controllers/HomeController.java)
      * [SearchController](./src/main/java/com/kenipesa/helloHome/controllers/SearchController.java)
    * libraries/
      * [FinanceCalculator](./src/main/java/com/kenipesa/helloHome/libraries/FinanceCalculator.java)
      * [Jsoup](./src/main/java/com/kenipesa/helloHome/libraries/Jsoup.java)
      * [ResultObj](./src/main/java/com/kenipesa/helloHome/libraries/ResultObj.java)
      * [ZillowAPILib](./src/main/java/com/kenipesa/helloHome/libraries/ZillowAPILib.java)
    * models/
      * [ApplicationUser](./src/main/java/com/kenipesa/helloHome/models/ApplicationUser.java)
      * [ApplicationUserRepository](./src/main/java/com/kenipesa/helloHome/models/ApplicationUserRepository.java)
      * [Expenses](./src/main/java/com/kenipesa/helloHome/models/Expenses.java)
      * [ExpensesRepository](./src/main/java/com/kenipesa/helloHome/models/ExpensesRepository.java)
      * [Searches](./src/main/java/com/kenipesa/helloHome/models/Searches.java)
      * [SearchesRepository](./src/main/java/com/kenipesa/helloHome/models/SearchesRepository.java)
* src/main/resources/
    * static/
      * [styles](./src/main/resources/static/styles.css)
    * templates/
      * [aboutUs](./src/main/resources/templates/aboutUs.html)
      * [addExpenses](./src/main/resources/templates/addExpenses.html)
      * [error](./src/main/resources/templates/error.html)
      * [index](./src/main/resources/templates/index.html)
      * [login](./src/main/resources/templates/login.html)
      * [profile](./src/main/resources/templates/profile.html)
      * [register](./src/main/resources/templates/register.html)
      * [results](./src/main/resources/templates/results.html)
      * [searches](./src/main/resources/templates/searches.html)
    * templates/fragments/
      * [aboutCard](./src/main/resources/templates/fragments/aboutCard.html)
      * [header](./src/main/resources/templates/fragments/header.html)
      * [searchResults](./src/main/resources/templates/fragments/searchResults.html)
* src/test/
  * java/
    * [HelloHomeApplicationTest](./src/test/java/com/kenipesa/helloHome/HelloHomeApplicationTest.java)

### Resources <a name="resources"></a>
* [Thymeleaf Conditionals](https://www.baeldung.com/spring-thymeleaf-conditionals)
* [Jsoup](https://jsoup.org/)
* [Jsoup Tutorial](https://www.tutorialspoint.com/jsoup/index.htm)
* [One-to-one Mapping](https://hellokoding.com/jpa-one-to-one-foreignkey-relationship-example-with-spring-boot-maven-and-mysql/)
* [Bootstrap Lux](https://bootswatch.com/lux/)
* [Bootstrap Grid](https://getbootstrap.com/docs/4.3/layout/grid/#all-breakpoints)
* [Bootstrap List](https://getbootstrap.com/docs/4.3/components/list-group/#flush)

**[Return to Top](#top)**
