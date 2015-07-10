package com.wondersgroup.socket.client.processor;

import static com.wondersgroup.socket.Commons.findSendableClassByOrder;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import com.wondersgroup.socket.SocketWrapper;
import com.wondersgroup.socket.client.exceptions.NoOptionException;
import com.wondersgroup.socket.client.sender.Sendable;

public class LineProcessor {
	
private String []tokens;
	
	private Sendable sendable;
	
	public LineProcessor(String line) throws Exception {
		line = preLine(line).trim();
		if(line.trim().length() == 0) {//没有任何操作
			throw new NoOptionException();
		}
		tokens = line.trim().split("\\s+");
		String firstToken = tokens[0];
		Class <?>clazz = findSendableClassByOrder(firstToken);
		try {
			sendable = (Sendable)clazz.getConstructor(String[].class)
				.newInstance(new Object[] {tokens});
		}catch(InvocationTargetException e) {
			throw (Exception)e.getCause();
		}
	}
	
	public void sendContentBySocket(SocketWrapper socketWrapper) throws IOException {
		if(sendable != null && sendable.getSendType() > 0) {
			socketWrapper.write(sendable.getSendType());//发送类型
			sendable.sendContent(socketWrapper);
		}
	}
	
	private String preLine(String line) {
		if(line == null) return "";
		if(line.startsWith(">")) return line.substring(1);
		return line;
	}
}
