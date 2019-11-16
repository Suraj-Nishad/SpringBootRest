 
package org.cap.controller;

import java.util.List;

import org.cap.entities.BankDetails;
import org.cap.entities.Transaction;
import org.cap.exception.AccountNotFoundException;
import org.cap.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController//@Controller
@CrossOrigin("http://localhost:4200")//
public class BankRestController {

    @Autowired
    private BankService detailsService; 	//Autowiring the child beans

    
    // Creating an Account spring 5.x annotations @PathParam
    @PostMapping(value = "/add/account",
            consumes = {"application/json"},
            produces = "application/json")
    public ResponseEntity<String> addCustomer(@RequestBody BankDetails customer) {
    	customer=detailsService.addCustomer(customer);
    	ResponseEntity<String> responseEntity = new ResponseEntity("Hello "+customer.getFirstName()+" "+customer.getLastName()+" \nYou have Successfully Registered to our Bank\n Your Account Number is : "+customer.getAccountNo(), HttpStatus.OK);
    	return responseEntity;
    }
	
	//Requesting to Show Balance using Id
	  @GetMapping(value = "/account/balance/{accountno}") 
	  public ResponseEntity<String> customerBalance(@PathVariable("accountno") int accno)
	  {
		  BankDetails b=detailsService.accountBalance(accno); 
		try {
		  int bal=b.getAccBal();
		  
		  if(b.getAccBal()>0) {
			  ResponseEntity<String>responseEntity = new ResponseEntity("Hello\n" +  b.getFirstName()+" "+b.getLastName()   +" \nYour Account balance is "+b.getAccBal(), HttpStatus.OK); return
					  responseEntity;
			}
		  else
		  {
			  ResponseEntity<String>responseEntity = new ResponseEntity("Hello\n" +  b.getFirstName()+" "+b.getLastName()   + "\n Enter valid amount greater than 0 ", HttpStatus.OK);
			  return responseEntity; 
		  }
		}
		catch (Exception e) {
			 ResponseEntity<String>responseEntity = new ResponseEntity("Enter Valid Id ", HttpStatus.OK);
			  return responseEntity; 
		}
		  }
	  
	  
	  //Depositing / Crediting the Amount into an Account using Account Id
	  @GetMapping(value = "/account/deposit/{accountno}/{amount}")
	  public ResponseEntity<String>customerDeposit(@PathVariable("accountno") int accountno
	  , @PathVariable("amount") int amount) 
	  { 
		 BankDetails b=detailsService.amountDeposit(accountno,amount); 
		 
		 if (b == null) {
				throw new AccountNotFoundException("Enter Valid Account Number");
			}
		 
		 else if(amount<0) {
			 ResponseEntity<String>responseEntity = new ResponseEntity("Hello\n" +  b.getFirstName()+" "+b.getLastName()   + "\n Enter valid amount greater than 0 ", HttpStatus.OK); return
					 responseEntity; 
		 }
		 
		  else
		  {
		 ResponseEntity<String>responseEntity = new ResponseEntity("Hello\n" +  b.getFirstName()+" "+b.getLastName()   + "\n Your Amount is Deposited Succesfully  with Amount " + amount +"\n"+ "Your Current Account Balance is \n" + b.getAccBal(), HttpStatus.OK); return
				 responseEntity;
		 }
		 
		 }
	  
	 //Debiting / Withdrawing the Amount from Account using Account Id
	  @GetMapping(value = "/account/withdraw/{accountno}/{amount}")
	  public ResponseEntity<String>customerWithdraw(@PathVariable("accountno") int accountno
	  , @PathVariable("amount") int amount) throws Exception { 
		  BankDetails b=detailsService.amountWithdraw(accountno,amount);
		  String msg="";
		  if (b == null) {
				throw new AccountNotFoundException("Enter Valid Account Number");
			}
		  else if(amount<0) {
				 ResponseEntity<String>responseEntity = new ResponseEntity("Hello\n" +  b.getFirstName()+" "+b.getLastName()   + "\n Enter valid amount greater than 0 ", HttpStatus.OK); return
						 responseEntity; 
			 }
		  else
		  {
			  if(b.getAccBal()<amount)
			  {
		 
				  msg="Your account has insufficient Balance please try again later";
			  
				  ResponseEntity<String> responseEntity = new ResponseEntity("Hello\n" +  b.getFirstName()+" "+b.getLastName()   +" "+   msg +" \n"+ "Your Current Account Balance is \n" + b.getAccBal(), HttpStatus.OK);
				  return responseEntity;
			  }
			  else
			  {
				  ResponseEntity<String> responseEntity = new ResponseEntity("Hello\n" +  b.getFirstName()+" "+b.getLastName()   + "\n Your Amount is Debited Succesfully  with Amount " + amount +" \n"+ "Your Current Account Balance is \n" + b.getAccBal(), HttpStatus.OK);
				  return responseEntity;
			  }
	  }
		}
	  
	  
	  // Fund Transfering From One Account to another Account using Their respective Account Id's
	  @GetMapping(value = "/account/transfer/{accountno1}/{accountno2}/{amount}")
	  public ResponseEntity<String>customerTransfer(@PathVariable("accountno1") int accountno1
	  ,@PathVariable("accountno2") int accountno2, @PathVariable("amount") int
	  amount) { 
		  BankDetails b=detailsService.amountTransfer(accountno1,accountno2,amount);
		  String msg="";
		  if (b == null) {
				throw new AccountNotFoundException("Enter Valid Account Number");
			}
		  else if(amount<0) {
				 ResponseEntity<String>responseEntity = new ResponseEntity("Hello\n" +  b.getFirstName()+" "+b.getLastName()   + "\n Enter valid amount greater than 0 ", HttpStatus.OK); return
						 responseEntity; 
			 }
		  else
		  {

			  if(b.getAccBal()<amount)
			  {
		 
				  msg="Your account has insufficient Balance please try again later";
			  
				  ResponseEntity<String> responseEntity = new ResponseEntity("	Hello\n" +b.getFirstName()+" "+b.getLastName() +"\n"+ msg + "\nYour Current Account Balance is " + b.getAccBal(), HttpStatus.OK);
				  return responseEntity;
			}
			  else
			  {
				  ResponseEntity<String> responseEntity = new ResponseEntity("	Hello\n" +b.getFirstName()+" "+b.getLastName() +"\n Your Amount is Transfered Succesfully to Account Number "+accountno2+"Succesfully\n" + "Your Current Account Balance is " + b.getAccBal(), HttpStatus.OK);
				  return responseEntity;
			  }
		 
		  }
		}
	  
	  
	  
	  
	  
	  
	 //Printing the Bank Statements or consider it as History of Transactions	 
	  @GetMapping("/bank/transactions/{id}") 
	  public List<Transaction>transactions(@PathVariable int id) throws Exception {
	  
	  if (detailsService.printTransactions(id) == null) { 
		  throw new AccountNotFoundException("Enter Valid Account Number"); 
		  }
	   return detailsService.printTransactions(id);
	  }
	  
	  //Exception
	  @ExceptionHandler(AccountNotFoundException.class)
	  public ResponseEntity userNotFound(AccountNotFoundException e) {
		  return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND); }
	 
 }   
    
    
    
    
    
  
