package org.cap.dao;

import java.util.List;

import org.cap.entities.BankDetails;
import org.cap.entities.Transaction;

public interface BankDao {

    
    public BankDetails addCustomer(BankDetails customer);
    
    
    
    public BankDetails accountBalance(int accno);
    
    public BankDetails amountDeposit(int accno,int amount);
    
    public BankDetails amountWithdraw(int accno,int amount) throws Exception;
    
    public BankDetails amountTransfer(int accno1,int accno2,int amount);
   
    public List<Transaction> printTransactions(int accno);

}
