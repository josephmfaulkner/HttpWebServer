package com.josephmfaulkner.response;

import java.util.SortedMap;
import java.util.TreeMap;

import com.josephmfaulkner.response.Response;
import com.josephmfaulkner.response.writer.ResponseWriter;

public class Response {
	private ResponseWriter writer;
	
	private int status;
	private SortedMap<String,String> headers;
	private String body;
	
	public Response(ResponseWriter writer)
	{
		this.status = 200; 
		this.writer = writer;
		this.headers = new TreeMap<>(); 
	}
	public void setStatus(int i) {
		this.status = i; 	
	}
	
	private String statusHeader() {
		return "HTTP/1.1 " + this.status + " OK";
	}
	
	public void setHeader(String headerKey, String value) {
		this.headers.put(headerKey, value);
	}
	
	public void setBody(String body) { 
		this.body = body;
	}
	
	public void send() {
		writer.outline(statusHeader());
		
		for(String headerKey: headers.keySet())
		{
			String headerValue = headers.get(headerKey);
			String headerFormatted = String.format("%s:%s", headerKey, headerValue);
			writer.outline(headerFormatted);
		}
		
		writer.outline("");
		
		writer.out(this.body);
		
		writer.flush();
	}
	
}
