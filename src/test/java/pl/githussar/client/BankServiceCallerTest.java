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
@PrepareForTest({ChargeAmount.class, TransferAmount.class})
public class BankServiceCallerTest {

	@Mock
	private ChargeAmount chargeAmount;
	
	@Mock
	private TransferAmount transferAmount;
	
	@Before
	public void setUp(){
		PowerMockito.mockStatic(ChargeAmount.class);
		PowerMockito.mockStatic(TransferAmount.class);
		Mockito.when(ChargeAmount.createInstance(Matchers.anyDouble(), Matchers.anyString())).thenReturn(chargeAmount);
		Mockito.when(TransferAmount.createInstance(Matchers.anyDouble(), Matchers.anyString())).thenReturn(transferAmount);
	}
	
	@Test
	public void testFlow(){
		//given
		
		//when
		BankServiceCaller bankServiceCaller = new BankServiceCaller(100.0, "A", "B");
		Operation.Status status = bankServiceCaller.executeOperation();
		
		//then
		Assert.assertEquals(Operation.Status.ERROR, status);
	}
}
