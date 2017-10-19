package pl.githussar.client.http;
import java.io.InputStream;
import java.net.HttpURLConnection;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import pl.githussar.client.mock.AnswerMock;
import pl.githussar.tx.Operation;

public class ServiceFactory {
	
	HttpConnector httpConnector = new HttpConnector();
	
	public Operation.Status callService(String uri){
		try { 
			HttpURLConnection connection = httpConnector.connect(uri);
			JAXBContext jContext = JAXBContext.newInstance("XML");
	        Unmarshaller unmarshaller = jContext.createUnmarshaller();
			InputStream xml = connection.getInputStream();
			AnswerMock answer = (AnswerMock)unmarshaller.unmarshal(xml);
			connection.disconnect();
			
			if ("OK".equals(answer.getStatus())){
				return Operation.Status.OK;
			} else {
				return Operation.Status.ERROR;
			}
		} catch (Exception e){
			return Operation.Status.ERROR;
		}
	}
	
	public HttpConnector getHttpConnector() {
		return httpConnector;
	}

	public void setHttpConnector(HttpConnector httpConnector) {
		this.httpConnector = httpConnector;
	}



}
