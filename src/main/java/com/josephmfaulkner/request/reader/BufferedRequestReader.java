package com.josephmfaulkner.request.reader;

import java.io.BufferedReader;
import java.io.IOException;

import com.josephmfaulkner.request.Request;

public class BufferedRequestReader implements RequestReader{

	private BufferedReader reader; 
	private boolean success;
	
	public boolean Success() { return this.success; }
	
	public BufferedRequestReader(BufferedReader socketRequest) {
		success = false;
		reader = socketRequest;
	}

	@Override
	public void parseRequest(Request request) {
		try {
			String methodHeader    = reader.readLine();
			System.out.println(String.format("REQUEST: %s", methodHeader));
			
			if(methodHeader == null)
			{
				success = false; 
				return;
			}
			
			String requestMethod[] = methodHeader.split(" ");
			
			request.SetMethod(requestMethod[0]);
			request.SetResource(requestMethod[1]);
			request.SetProtocol(requestMethod[2]);
			
			String headerLine = reader.readLine();
			while( headerLine != null)
			{
				if(headerLine.isEmpty())
				{
					break;
				}
				String header[] = headerLine.split(":");
				request.SetHeader(header[0], header[1]);
				
				headerLine = reader.readLine(); 
			}
			success = true; 
		} catch (Exception e) {
			success = false;
		}
	}
}
