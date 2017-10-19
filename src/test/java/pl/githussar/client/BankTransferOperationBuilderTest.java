package pl.githussar.client;

import java.util.List;

import javax.xml.bind.JAXBException;

import org.junit.Assert;
import org.junit.Test;

import pl.githussar.tx.Operation;

public class BankTransferOperationBuilderTest {

	@Test
	public void shouldContainTwoOperationForBankTransfer() throws JAXBException{
		//given
		
		//when
		BankTransferOperationBuilder transferOperations =  BankTransferOperationBuilder.creatInstance();
		List<Operation> operationList = transferOperations.createBankTransferOperations(100.0, "A", "B");
		
		//then
		Assert.assertEquals(operationList.size(), 2);
	}
}
