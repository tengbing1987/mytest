package com.wondersgroup.socket.client.sender;

import java.io.IOException;

import com.wondersgroup.socket.SocketWrapper;

public interface Sendable {
	
	public byte getSendType();

	public void sendContent(SocketWrapper socketWrapper) throws IOException;
}
