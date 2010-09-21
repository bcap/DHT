package com.github.bcap.dht.client.operation;

import com.github.bcap.dht.message.request.FindValueRequest;
import com.github.bcap.dht.message.response.Response;
import com.github.bcap.dht.node.Contact;
import com.github.bcap.dht.node.Identifier;

public class FindValue extends Operation<FindValueResult> {

	private Identifier key;

	public FindValue(Contact source, Identifier key) {
		super(source);
		this.key = key;
	}

	@Override
	protected void executeImpl() {
		FindValueRequest request = new FindValueRequest();
		request.setIdentifier(key);
		super.sendRequest(request);
	}
	
	@Override
	public void handleResponse(Response response) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleException(Exception exception) {
		// TODO Auto-generated method stub
		
	}

}
