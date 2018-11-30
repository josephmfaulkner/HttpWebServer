package com.josephmfaulkner.request;

import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import com.josephmfaulkner.request.reader.RequestReader;

public class Request {
	private RequestReader reader;
	
	private boolean success;
	public boolean Success() { return this.success; }
	
	private String method;
	public void   SetMethod(String method) {this.method = method;}
	public String Method() {return method;}
	
	private String resource;
	public void   SetResource(String resource) {this.resource = resource;}
	public String Resource() {return resource;}
	
	private String protocol;
	public void   SetProtocol(String protocol) {this.protocol = protocol;}
	public String Protocol() {return protocol;}

	private SortedMap<String,String> headers;
	public void SetHeader(String headerKey, String headerValue) {
		this.headers.put(headerKey, headerValue);
	}
	public String GetHeader(String headerKey) {
		return this.headers.get(headerKey); 
	}
	public Set<String> HeaderKeys(){
		return this.headers.keySet();
	}
	
	public boolean HasHeader(String headerKey)
	{
		return headers.containsKey(headerKey);
	}
	
	
	public Request(RequestReader reader) {
		this.success = false; 
		this.reader = reader;
		this.headers = new TreeMap<>();
		this.reader.parseRequest(this);
		this.success = true; 
	}
	
}
