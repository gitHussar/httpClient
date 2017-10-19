package pl.githussar.client.http;

import static org.mockito.Mockito.when;

import java.io.InputStream;
import java.net.HttpURLConnection;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

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

import pl.githussar.client.mock.AnswerMock;
import pl.githussar.tx.Operation;

@RunWith(PowerMockRunner.class)
@PrepareForTest({JAXBContext.class})
public class ServiceFactoryTest {

	@Mock
	Unmarshaller unmarshaller;
	
	@Mock
	HttpConnector httpConnector;

	
	@Before
	public void setUp() throws Exception{
		HttpURLConnection connection = Mockito.mock(HttpURLConnection.class);
		InputStream inputStream = Mockito.mock(InputStream.class);
		when(httpConnector.connect(Matchers.anyString())).thenReturn(connection);
		when(connection.getInputStream()).thenReturn(inputStream);
		PowerMockito.mockStatic(JAXBContext.class);
		
		JAXBContext jContext = Mockito.mock(JAXBContext.class);
		when(JAXBContext.newInstance(Matchers.anyString())).thenReturn(jContext);
		when(jContext.createUnmarshaller()).thenReturn(unmarshaller);
	}
	
	@Test
	public void shouldReturnSuccessIfServiceStatusIsOK() throws JAXBException{
		//given
		AnswerMock answerMock = new AnswerMock("OK");
		Mockito.when(unmarshaller.unmarshal(Matchers.any(InputStream.class))).thenReturn(answerMock);
		
		//when
		ServiceFactory serviceFactory = new ServiceFactory();
		serviceFactory.setHttpConnector(httpConnector);
		
		Operation.Status status = serviceFactory.callService("http://host.pl/");
		
		//then
		Assert.assertEquals(Operation.Status.OK, status);
	}
	
	@Test
	public void shouldReturnErrprIfServiceStatusIsNotOK() throws JAXBException{
		//given
		AnswerMock answerMock = new AnswerMock("FAILED");
		Mockito.when(unmarshaller.unmarshal(Matchers.any(InputStream.class))).thenReturn(answerMock);
		
		//when
		ServiceFactory serviceFactory = new ServiceFactory();
		serviceFactory.setHttpConnector(httpConnector);
		Operation.Status status = serviceFactory.callService("http://host.pl/");
		
		//then
		Assert.assertEquals(Operation.Status.ERROR, status);
	}
}
