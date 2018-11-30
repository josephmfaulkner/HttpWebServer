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
		this.body = "";
		this.writer = writer;
		this.headers = new TreeMap<>(); 
	}
	
	public void setStatus(int i) {
		this.status = i; 	
	}
	
	private String statusMessage(int statusCode){
		switch(statusCode)
		{
			case 200: 
				return "OK";
			case 404: 
				return "Not Found";
			default:
				return "";
		}
	}
	
	private String statusHeader() {
		return String.format("HTTP/1.1 %d %s", this.status, statusMessage(this.status));
	}
	
	public void setHeader(String headerKey, String value) {
		this.headers.put(headerKey, value);
	}
	
	public void setBody(String body) { 
		this.body = body;
	}
	
	public void send() {
		writer.outline(statusHeader());
		System.out.println(String.format("RESPONSE: %s",statusHeader()));
		for(String headerKey: headers.keySet())
		{
			String headerValue = headers.get(headerKey);
			String headerFormatted = String.format("%s:%s", headerKey, headerValue);
			System.out.println(String.format("RESPONSE HEAD: %s",headerFormatted));
			writer.outline(headerFormatted);
		}
		
		writer.outline("");
		System.out.println(String.format("RESPONSE BODY:\n%s\n",this.body));
		writer.out(this.body);
		
		writer.flush();
	}
	
}
