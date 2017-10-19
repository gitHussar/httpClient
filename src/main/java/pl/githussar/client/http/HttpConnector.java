package pl.githussar.client.http;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpConnector {

	public HttpURLConnection connect(String uri) throws IOException{
		URL url = new URL(uri);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Accept", "application/xml");
		return connection;
	}
	
}
