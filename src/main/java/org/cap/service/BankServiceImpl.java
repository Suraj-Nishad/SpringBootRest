package org.cap.service;

import java.util.List;

import javax.transaction.Transactional;

import org.cap.dao.BankDao;
import org.cap.entities.BankDetails;
import org.cap.entities.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * marking with @Service ,@Service is similar to @Component but used for service implementations
 *  Spring will create object of DetailsServiceImpl class and will keep in bean factory
 */
@Service
@Transactional
public class BankServiceImpl implements BankService {


    /**
     * spring will inject with object of DetailsDaoImpl class because @Autowired is mentioned here
     */
    @Autowired
    private BankDao dao;

    public BankDao getDao(){
        return dao;
    }

    public void setDao(BankDao dao){
        this.dao=dao;
    }

    //Service class methods  calls the dao class methods

	public BankDetails addCustomer(BankDetails customer) {
		// TODO Auto-generated method stub
		return dao.addCustomer(customer);
	}

 

	public BankDetails accountBalance(int accno) {
 
		return dao.accountBalance(accno);
	}

	public BankDetails amountDeposit(int accno, int amount) {
		// TODO Auto-generated method stub
	 
		return dao.amountDeposit(accno,amount);
	}

	public BankDetails amountWithdraw(int accno, int amount) throws Exception {
		// TODO Auto-generated method stub
		 
		return dao.amountWithdraw(accno,amount);
	}

	public BankDetails amountTransfer(int accno1, int accno2, int amount) {
		// TODO Auto-generated method stub
		
		return dao.amountTransfer(accno1,accno2,amount);
	}

	@Override
	public List<Transaction> printTransactions(int accno) {
		 
		return dao.printTransactions(accno);
	}

	
	
   
}











