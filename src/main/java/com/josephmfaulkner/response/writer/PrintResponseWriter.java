package com.josephmfaulkner.response.writer;

import java.io.PrintWriter;


public class PrintResponseWriter implements ResponseWriter {
	
	private PrintWriter writer;
	
	public PrintResponseWriter(PrintWriter writer)
	{
		this.writer = writer;
	}

	@Override
	public void outline(String content) {
		writer.println(content);
	}

	@Override
	public void out(String content) {
		writer.print(content);
	}

	@Override
	public void flush() {
		writer.flush();
	}
	
}
