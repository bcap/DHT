package me.bcap.dht.message.request;

import java.io.Serializable;
import java.util.SortedMap;

public class FindNodeRequest extends IdentifierRequest implements Serializable {

	protected static final long serialVersionUID = IdentifierRequest.serialVersionUID + 1L;

	public FindNodeRequest() {
		super();
	}
	
	public void addToStringProperties(SortedMap<String, Object> propertiesMap) {
		super.addToStringProperties(propertiesMap);
	}

}
