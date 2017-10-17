package pl.githussar.client.mock;

import java.io.InputStream;

public class MarshallerMock {

	public AnswerMock unmarshal(InputStream response){
		return new AnswerMock("OK");
	}
}
