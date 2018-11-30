package com.josephmfaulkner;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Set;

import com.josephmfaulkner.request.Request;
import com.josephmfaulkner.response.Response;

public class RequestHandler {
	
	private String formatResource(String resource)
	{
		try 
		{
			return resource.substring(1).replaceAll("/", File.separator);
		}
		catch(Exception e)
		{
			return "";
		}
	
	}
	
	private boolean fileExists(String filePath)
	{
		File tmpDir = new File(filePath);
		return tmpDir.exists();
	}
	
	private void matchResource(String resourceURL, Response response)
	{
		String workingDir = System.getProperty("user.dir");
		String requestedResource = formatResource(resourceURL);
		if(requestedResource.isEmpty())
		{
			requestedResource = "index.html";
		}
		String resourceFilePath = String.format("%s%s%s%s%s", workingDir, File.separator, "public_html", File.separator, requestedResource);
		
		System.out.println("File:"+resourceFilePath);
		
		if(!fileExists(resourceFilePath))
		{
			System.out.println("No such file (404)");
			response.setStatus(404);
			response.setBody("");
			return;
		}
		
		FileInputStream fis;
		try {
			fis = new FileInputStream(resourceFilePath);

			StringBuffer fileContent = new StringBuffer("");
	
			byte[] buffer = new byte[1024];
			int n  = 0;
	
			while ((n = fis.read(buffer)) != -1) 
			{ 
			  fileContent.append(new String(buffer, 0, n)); 
			}
			
			response.setBody(fileContent.toString());
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
	}
	
	public void handleRequest(Request request, Response response)
	{
		if(!request.Success())
		{
			response.setStatus(401);
			response.send();
			return; 
		}
		
		/*
		StringBuilder headerString = new StringBuilder();
		Set<String> headers = request.HeaderKeys();
		for(String headerKey: headers)
		{
			String headerValue = request.GetHeader(headerKey);
			headerString.append(headerKey+":"+headerValue+"</br>");
		}		
		*/
		matchResource(request.Resource(), response);
		
		//response.setBody(String.format("<html><head><title>HttpServerApp</title></head><body><h1>Welcome to the HttpServerApplication</h1><p> %s </p></body></html>",headerString.toString()));
		
		response.send();
	}
}

