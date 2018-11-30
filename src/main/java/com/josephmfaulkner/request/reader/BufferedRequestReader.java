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
			String requestMethod[] = methodHeader.split(" ");
			
			request.SetMethod(requestMethod[0]);
			request.SetResource(requestMethod[1]);
			request.SetProtocol(requestMethod[2]);
			
			System.out.println("<header>");
			String headerLine = reader.readLine();
			while( headerLine != null)
			{
				if(headerLine.isEmpty())
				{
					break;
				}
				System.out.println("	"+ headerLine);
				String header[] = headerLine.split(":");
				request.SetHeader(header[0], header[1]);
				
				headerLine = reader.readLine(); 
			}
			System.out.println("</header>");
			success = true; 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println("Error Parsing Request");
			success = false;
			e.printStackTrace();
		}
	}
}
