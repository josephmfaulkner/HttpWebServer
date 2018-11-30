package com.josephmfaulkner;

import java.io.IOException;

public class Main {
	
	public static void main(String[] args) {
		int listeningPort = 7500; 
		HttpServer server = new HttpServer(listeningPort);
		try {
			server.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
	/*
		// TODO Auto-generated method stub

		try {
			ServerSocket server = new ServerSocket(3067);
			//server.setSoTimeout(10000);
		while(true) {
				System.out.println("Listening: "+ server.getLocalPort());
				
				Socket socket = server.accept();
				BufferedReader request = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter response = new PrintWriter(socket.getOutputStream());
				
				String requestLine = request.readLine();
				while(requestLine != null)
				{
					if(requestLine.isEmpty())
					{
						break;
					}
					System.out.println(requestLine);
					requestLine = request.readLine(); 
				}
				
				System.out.println("HeaderDone: ");
				
				
				response.println("HTTP/1.1 200 OK");
				response.println("Content-Type: text/html");
				response.println("");
				
				response.println("<html>");
				
					response.println("<head>");
						response.println("<title>");
							response.println("Hello World");
						response.println("</title>");
					response.println("</head>");
					
					response.println("<body>");
						response.println("<h1>");
							response.println("Hello World!");
						response.println("</h1>");
					response.println("</body>");
							
				response.println("</html>");
				
				response.println("");
				
				response.flush();
				
				request.close();
				response.close();
								
				
				socket.close();
				//server.close();
			} 
		
	} catch (IOException e) {
		
		
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

}
*/
