package org.cap.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.cap.entities.BankDetails;
import org.cap.entities.Transaction;
import org.springframework.stereotype.Repository;

/**
 * marking with @Repository is similar to @Component but used for Dao implementation classes,
 * spring will keep instance of DetailsDaoImpl and the object will be kept in bean factory
 *
 */
@Repository
public class BankDaoImpl implements BankDao {

    @PersistenceContext
    private EntityManager em;		//EntityManager object creation

    public BankDaoImpl(){
    }

	

	public BankDetails addCustomer(BankDetails customer) {	//Method to add account details into database
		// TODO Auto-generated method stub
		customer=em.merge(customer);
		return customer;
	}

 
	public BankDetails accountBalance(int accno) {
	 
		return em.find(BankDetails.class,  accno);	//Method for Balance Enquiry
	}

	public BankDetails amountDeposit(int accno, int amount) {	//Depositing / crediting the amount into an acoount 
		// TODO Auto-generated method stub
		BankDetails cust=em.find(BankDetails.class, accno);
		int accbal=cust.getAccBal();
		if(accbal<0)
		{
		return cust;	
		}
		else
		{
		int upbal=accbal+amount;
		cust.setAccBal(upbal);
		em.merge(cust);			//updating
		
		
		BankDetails cust1=em.find(BankDetails.class, accno);
		Transaction ts=new Transaction();		//updating the transaction deatils to Transaction Table Database
		ts.setType("Deposit");
		ts.setAmountTransferred(amount);
		ts.setAccountNo(accno);
		em.persist(ts);
		
		
		return cust1;
	}
		}

	public BankDetails amountWithdraw(int accno, int amount) throws Exception {	//withdrawing amount from account
		// TODO Auto-generated method stub
		BankDetails cust=em.find(BankDetails.class, accno);
		int accbal=cust.getAccBal();
	if(accbal<amount)
	{
	return cust;
	}
	else
	{
		int upbal=accbal-amount;
		cust.setAccBal(upbal);
		em.merge(cust);		//updating
		
		BankDetails cust1=em.find(BankDetails.class, accno);
		Transaction ts=new Transaction();		//updating the transaction deatils to Transaction Table Database
		ts.setType("Withdraw");
		ts.setAmountTransferred(amount);
		ts.setAccountNo(accno);
		em.persist(ts);
		
		return cust1;
	}
	}

	public BankDetails amountTransfer(int accno1, int accno2, int amount) {
		// TODO Auto-generated method stub
		BankDetails c1=em.find(BankDetails.class,accno1 );
        int am1=c1.getAccBal();
        if(am1<amount)
    	{
    	return c1;
    	}
        else {
        	
     
        int uam1=am1-amount;
        c1.setAccBal(uam1);
        
        BankDetails c2=em.find(BankDetails.class,accno2 );
        int am2=c2.getAccBal();
        int uam2=am2+amount;
        c2.setAccBal(uam2);
        
        em.merge(c1);
        em.merge(c2);
        
        System.out.println("Money Successfully transfered");
        BankDetails c4=em.find(BankDetails.class,accno2 );
    	//updating the transaction deatils to Transaction Table Database
        Transaction ts1=new Transaction();
        ts1.setType("Recieved From  "+ accno1);
        ts1.setAmountTransferred(amount);
        ts1.setAccountNo(accno2);
        em.persist(ts1);
        
        BankDetails c3=em.find(BankDetails.class,accno1 );
    	//updating the transaction deatils to Transaction Table Database
        Transaction ts=new Transaction();
        ts.setType("Transfered to "+ accno2);
        ts.setAmountTransferred(amount);
        ts.setAccountNo(accno1);
        em.persist(ts);
        
		return c3;
        }
	}



	@Override
	//Printing All Transaction History about an particular account
	public List<Transaction> printTransactions(int accno) {
		TypedQuery<Transaction> query = em.createQuery("select t from Transaction t where t.accountNo="+accno, Transaction.class);
		return query.getResultList();
		
	 
	}

    
}
