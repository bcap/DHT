package me.bcap.dht.server.handler;

import java.util.ArrayList;
import java.util.List;

import me.bcap.dht.message.request.IdentifierRequest;
import me.bcap.dht.message.request.Request;
import me.bcap.dht.message.response.FindNodeResponse;
import me.bcap.dht.message.response.Response;
import me.bcap.dht.node.Bucket;
import me.bcap.dht.node.Contact;
import me.bcap.dht.node.Identifier;
import me.bcap.dht.node.Node;

public class FindNodeRequestHandler extends RequestHandler {

	public Response handleImpl(Node node, Request request) {
		IdentifierRequest idReq = (IdentifierRequest) request;
		int nodeIndex = node.getBucketIndex(node);
		int bucketIndex = node.getBucketIndex(idReq.getIdentifier());
		//Direction tells us the direction where to move to get closer to the node itself
		//Eventually if the buckets are not full the window will keep moving an getting nodes
		//from another buckets, warping aroung the edges of the buckets array
		int direction = nodeIndex > bucketIndex ? 1 : -1;
		
		List<Contact> contacts = new ArrayList<Contact>();
		
		for(int visitedBuckets = 0; contacts.size() < Bucket.MAX_SIZE && visitedBuckets < Identifier.LENGTH; visitedBuckets++) {
			Bucket bucket = node.getBucket(bucketIndex);
			contacts.addAll(bucket.getContacts());
			bucketIndex = circularMod(bucketIndex + direction, Identifier.LENGTH);
		}
		
		FindNodeResponse response = new FindNodeResponse();
		response.setContacts(contacts);
		return response;
	}
	
	private int circularMod(int x, int y) {
		return ((x % y) + y ) % y;
	}
}
