package pl.githussar.client;

import java.util.ArrayList;
import java.util.List;

import pl.githussar.client.operations.ChargeAmount;
import pl.githussar.client.operations.TransferAmount;
import pl.githussar.tx.Operation;

public class BankTransferOperationBuilder {


	public static BankTransferOperationBuilder creatInstance(){
		return new BankTransferOperationBuilder();
	}
	
	public List<Operation> createBankTransferOperations(Double transferAmount
			, String fromAccount, String toAccount){
		
		ChargeAmount chargeAccount = ChargeAmount.createInstance(transferAmount, fromAccount);
		TransferAmount transferOperation = TransferAmount.createInstance(transferAmount, toAccount);
		List<Operation> operation = new ArrayList<Operation>();
		operation.add(chargeAccount);
		operation.add(transferOperation);
		return operation;
	}
	
}
