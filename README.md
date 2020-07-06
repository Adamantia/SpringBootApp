## SpringBootApp

An MVC Java Springboot banking app. 


 ![](https://github.com/Adamantia/SpringBootApp/blob/master/couch/src/main/resources/static/images/index_screenshot.JPG)
 
 
 ## App highlight features / functionallity  
 
 - A user can register a private or company account
 - Forms have validations both front end (thymeleaf) and back end (including custom hibernate validations and checks for valid entry fields)
 - Every account has a unique IBAn. The method for Iban generation follows International Bank Account number rules found [here](https://nl.wikipedia.org/wiki/International_Bank_Account_Number)
 - A user can make a transaction to an existing client of the bank and see their transaction overview in their personal page.
 - A company account admin user can add user to the account and define their access privileges.
 - A user can have up to 5 bank account numbers assigned to their account.
 
 
## Built With
 - Java 11
 - Spring Framework 5
 - Springboot 2.1.7
 - Maven 3.3
 - MySQL 8.0.15
 - Thymeleaf 3.0.11
 - Lombok 1.18.10
 - Spring Data JPA
 - Hibernate
 - Bootstrap 


## Contribution

To contribute please follow these steps:

- Fork the repo
- Create a new branch (git checkout -b improve-branch)
- Add and commit your changes (git commit -am 'your message')
- Push your branch (git push origin improve-branch)
- Create a Pull Request

## Licence
[MIT](https://choosealicense.com/licenses/mit/)


