package pl.githussar.client;
import java.util.List;

import org.apache.log4j.Logger;

import pl.githussar.client.operations.ChargeAmount;
import pl.githussar.tx.Operation;
import pl.githussar.tx.TransactionsManager;

public class BankServiceCaller {

	static final Logger logger = Logger.getLogger(ChargeAmount.class);
	
	private Double amount;
	
	private String accountFrom;
	
	private String accountTo;
	
	public BankServiceCaller(Double amount, String accountFrom, String accountTo){
		this.amount = amount;
		this.accountFrom = accountFrom;
		this.accountTo = accountTo;
	}
	
	public Operation.Status executeOperation(){
		if (!checkParametrs()){
			return Operation.Status.ERROR;
		}
		BankTransferOperationBuilder transferOperation = BankTransferOperationBuilder.creatInstance();
		List<Operation> operations = transferOperation.createBankTransferOperations(amount, accountFrom, accountTo);
		
		TransactionsManager transactionManager = new TransactionsManager();
		Operation.Status result = transactionManager.processOperations(operations);
		
		logger.debug("Bank transfer finished with status:"+result);
		return result; 
	}
	
	private boolean checkParametrs(){
		return amount != null && accountFrom != null && accountTo != null;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getAccountFrom() {
		return accountFrom;
	}

	public void setAccountFrom(String accountFrom) {
		this.accountFrom = accountFrom;
	}

	public String getAccountTo() {
		return accountTo;
	}

	public void setAccountTo(String accountTo) {
		this.accountTo = accountTo;
	}
}
