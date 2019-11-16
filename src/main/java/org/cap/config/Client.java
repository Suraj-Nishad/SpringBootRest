package org.cap.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
 
public class Client {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(Client.class, args);
	}
}

 
 
//Main Application java file used to run the complete application using Spring Boot 
//@SpringBootApplication  annotation is used


/*
Steps for linking pringBoot Application with Angular by developing a WebPage

--> Initially The SpringBoot Application is to be developed  using required methods / jars / classes
--> Required Operations are to be performed in Dao Class and return the required values/ objects
---> Using RestController :-> Generate the URL's using @PostMapping (for adding into DB), @GetMapping (for Retrieving the Data From DB), @PutMapping (for Updating the Data in DB)
	return the data in form of user convinent either json/plaintext using produces and consumes attributes.
---> a server port number is to be provided to use the data at different applications for example the server port number is 1234
	(http://localhost:1234/)-->This link acts as home page for springBoot Application

**Using Angular Web Application server acts as an interface to run both the applications at a time**

---> @crossOrigin("http://localhost:4200/") -->This Annotation is used in Repository Bean class to combine with Angular Web Page

localhost:4200 is the deafult address for angular webpage.

--->a Service.ts componenet to be created 
	the Sservice component should be developed in such a way that it should request the data from server http://localhost:1234/
	
	for Example:
	
	 public createAccount(bank){
      const headers =new HttpHeaders().set('Content_Type', 'text/plain ;charset=utf-8');
    return this.http.post("http://localhost:1234/add/account",bank,  { headers, responseType: 'text'});
  }
  
  the above method written in service.ts component used to create an account
  
-->put and get methods are used in angular to get data from json format files or servers

-->similary develop the code for all required methods generate url's.
-->Add required components and routerlinks in angular project
--->Design the front end using links and perfom the operations
-->The input required should be entered and output should be displayed on webpage 
which is retrieved from springBoot Application.

*/
	
