package com.wondersgroup.nio.client;

import java.io.FileOutputStream;
import java.io.IOException;

import static com.wondersgroup.socket.Commons.*;

import com.wondersgroup.nio.FileUtils;
import com.wondersgroup.socket.SocketWrapper;

public class ClientMain {
	
	public static void main(String []args) throws IOException {
		for(int i = 0 ; i < 10 ; i ++) {
			final String num = String.valueOf(i);
			new Thread(String.valueOf(i)) {
				public void run() {
					SocketWrapper socketWrapper = null;
					FileOutputStream stream = null;
					try {
						stream = new FileOutputStream("d:/data/temp/log.txt");
						socketWrapper = new SocketWrapper("localhost" , 8888);
					
						println("已经连接上服务器端.....");
						socketWrapper.write("我是线程：" + num , DEFAULT_MESSAGE_CHARSET);
						byte []buffer = new byte[8192];
						int len = socketWrapper.read(buffer);
						while(len > 0) {
							println("我是线程：" + num + " 我接受到数据，长度为：" + len);
							stream.write(buffer , 0 , len);
							len = socketWrapper.read(buffer);
						}
						//System.in.read();
					}catch(Exception e) {
						e.printStackTrace();
					}finally {
						FileUtils.closeStream(stream , socketWrapper);
					}
				}
			}.start();
		}
		
	}
		
}
