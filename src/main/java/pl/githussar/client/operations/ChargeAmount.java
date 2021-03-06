package pl.githussar.client.operations;
import org.apache.log4j.Logger;

import pl.githussar.client.http.ServiceFactory;
import pl.githussar.tx.Operation;

public class ChargeAmount implements Operation{

	static final Logger logger = Logger.getLogger(ChargeAmount.class);
	
	private Double transferAmount;
	
	private String accountNumber;

	private ServiceFactory serviceFactory = new ServiceFactory();
	
	public ChargeAmount(Double transferAmount, String accountNumber){
		this.transferAmount = transferAmount;
		this.accountNumber = accountNumber;
	}
	
	public static ChargeAmount createInstance(Double transferAmount, String accountNumber){
		return new ChargeAmount(transferAmount, accountNumber);
	}
	
	public Status prepareTransaction(String operationId){
		logger.debug("Execute prepareTransaction operationId:"+operationId);
		String uri = "http://localhost:8080/BankService/rest/startChargeAccount?operationId="
				+operationId+"&amount="+transferAmount+"&account"+accountNumber;
		return serviceFactory.callService(uri);
	}
	
	public Status commit(String operationId){
		logger.debug("Execute commit operationId:"+operationId);
		String uri = "http://localhost:8080/BankService/rest/commitChargeAccount?operationId="+operationId;
		return serviceFactory.callService(uri);
	}
	
	public Status rollback(String operationId){
		logger.debug("Execute rollback operationId:"+operationId);
		String uri = "http://localhost:8080/BankService/rest/rollbackChargeAccount?operationId="+operationId;
		return serviceFactory.callService(uri);
	}
	
	public Status revert(String operationId){
		logger.debug("Execute revert operationId:"+operationId);
		String uri = "http://localhost:8080/BankService/rest/revertChargeAccount?operationId="+operationId;
		return serviceFactory.callService(uri);
	}

}
