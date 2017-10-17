package pl.githussar.client.http;
import java.io.InputStream;
import java.net.HttpURLConnection;

import pl.githussar.client.mock.AnswerMock;
import pl.githussar.client.mock.MarshallerMock;
import pl.githussar.tx.Operation;

public class ServiceFactory {
	
	MarshallerMock marshaller = new MarshallerMock();
	
	HttpConnector httpConnector = new HttpConnector();
	
	public Operation.Status callService(String uri){
		try {
			HttpURLConnection connection = httpConnector.connect(uri);
			InputStream xml = connection.getInputStream();
			AnswerMock answer = marshaller.unmarshal(xml);
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

	public MarshallerMock getMarshaller() {
		return marshaller;
	}

	public void setMarshaller(MarshallerMock marshaller) {
		this.marshaller = marshaller;
	}

	public HttpConnector getHttpConnector() {
		return httpConnector;
	}

	public void setHttpConnector(HttpConnector httpConnector) {
		this.httpConnector = httpConnector;
	}

}
