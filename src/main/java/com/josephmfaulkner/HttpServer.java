package com.josephmfaulkner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.josephmfaulkner.request.Request;
import com.josephmfaulkner.response.Response;
import com.josephmfaulkner.request.reader.BufferedRequestReader;
import com.josephmfaulkner.response.writer.PrintResponseWriter;

public class HttpServer {
	
	private HttpServer instance;
	private boolean instanceSet = false;
	private int errorCount = 0; 
	
	private int listeningPort;
	private ServerSocket server;
	private Socket socketConnection;
	
	private BufferedReader socketRequest;
	private PrintWriter socketResponse;
	
	private RequestHandler requestHandler;
	
	HttpServer(int listeningPort)
	{
		this.listeningPort = listeningPort;
		this.requestHandler = new RequestHandler();
	}
	
	public HttpServer server(int listeningPort) {
		if(!this.instanceSet) {
			this.instance = new HttpServer(listeningPort);
		}
		return this.instance;
	}
	
	public HttpServer server() {
		return this.instance;
	}
	
	public void start() throws IOException
	{
		this.server = new ServerSocket(this.listeningPort);
		System.out.println(String.format("Server Running on Port: %d",this.listeningPort));
		
		while(true)
		{
			try 
			{
				this.socketConnection = server.accept();
				this.socketRequest = new BufferedReader(new InputStreamReader(socketConnection.getInputStream()));
				this.socketResponse = new PrintWriter(socketConnection.getOutputStream());
		
				Request request = new Request(new BufferedRequestReader(this.socketRequest));
				Response response = new Response(new PrintResponseWriter(this.socketResponse));
				
				requestHandler.handleRequest(request,response);
				
				this.socketConnection.close();
				this.socketRequest.close();
				this.socketResponse.close();
			} 
			catch(Exception exception)
			{
				exception.printStackTrace();
				errorCount++;
				if(errorCount > Configuration.ERRORS_ALLOWED)
				{
					System.err.println("Stopping Server");
					break; 
				}
			}
		}	
	}
}
