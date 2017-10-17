package pl.githussar.client.operations;
import org.apache.log4j.Logger;

import pl.githussar.client.http.ServiceFactory;
import pl.githussar.tx.Operation;

public class TransferAmount implements Operation {

	static final Logger logger = Logger.getLogger(ChargeAmount.class);
	
	private Double amount;
	
	private String accountNumber;

	private ServiceFactory serviceFactory = new ServiceFactory();
	
	public TransferAmount(Double transferAmount, String accountNumber){
		this.amount = transferAmount;
		this.accountNumber = accountNumber;
	}
	
	public static TransferAmount createInstance(Double transferAmount, String accountNumber){
		return new TransferAmount(transferAmount, accountNumber);
	}
	
	public Double getTransferAmount() {
		return amount;
	}

	public Status prepareTransaction(String operationId){
		logger.debug("Execute prepareTransaction operationId:"+operationId);
		String uri = "http://localhost:8080/BankService/rest/startTransferAmount?operationId="
				+operationId+"&amount="+amount+"&account"+accountNumber;
		return serviceFactory.callService(uri);
	}
	
	public Status commit(String operationId){
		logger.debug("Execute commit operationId:"+operationId);
		String uri = "http://localhost:8080/BankService/rest/commitTransferAmount?operationId="+operationId;
		return serviceFactory.callService(uri);
	}
	
	public Status rollback(String operationId){
		logger.debug("Execute rollback operationId:"+operationId);
		String uri = "http://localhost:8080/BankService/rest/rollbackTransferAmount?operationId="+operationId;
		return serviceFactory.callService(uri);
	}
	
	public Status revert(String operationId){
		logger.debug("Execute revert operationId:"+operationId);
		String uri = "http://localhost:8080/BankService/rest/revertTransferAmount?operationId="+operationId;
		return serviceFactory.callService(uri);
	}

}
