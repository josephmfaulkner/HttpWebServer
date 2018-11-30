package com.josephmfaulkner.response.writer;

public interface ResponseWriter {

	void outline(String statusHeader);

	void out(String body);

	void flush();

}
