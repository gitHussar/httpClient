package pl.githussar.client;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import pl.githussar.client.operations.ChargeAmount;
import pl.githussar.client.operations.TransferAmount;
import pl.githussar.tx.Operation;

@RunWith(PowerMockRunner.class)
@PrepareForTest({BankTransferOperationBuilder.class})
public class BankServiceCallerTest {

	@Mock
	private BankTransferOperationBuilder bankTransferOperationBuilder;
	
	@Before
	public void setUp(){
		PowerMockito.mockStatic(BankTransferOperationBuilder.class);
		BankTransferOperationBuilder bankTransferOperationBuilder = Mockito.mock(BankTransferOperationBuilder.class);
		Mockito.when(BankTransferOperationBuilder.creatInstance()).thenReturn(bankTransferOperationBuilder);
	}
	
	@Test
	public void shouldReturnErrorStatusIfOneOfEntryParamIsNull(){
		//given
		
		//when
		BankServiceCaller bankServiceCaller = new BankServiceCaller(100.0, "A", null);
		Operation.Status status = bankServiceCaller.executeOperation();
		
		//then
		Assert.assertEquals(Operation.Status.ERROR, status);
	}
}
