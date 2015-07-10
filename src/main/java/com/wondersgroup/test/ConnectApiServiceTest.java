package com.wondersgroup.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;


public class ConnectApiServiceTest {
	
	@Test
	public void TestUtils () {
		BufferedReader in = null;
		String result = "";
		URL url;
		try {
			String urlStr = "http://10.1.64.104:8099/company/delete/wonder";
			url = new URL(urlStr);
			HttpURLConnection  connection = (HttpURLConnection) url.openConnection();
			// 设置通用的请求属性
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type","text/html; charset=UTF-8");
			// 建立实际的连接
			connection.connect();
//       // 获取所有响应头字段
//       Map<String, List<String>> map = connection.getHeaderFields();
//       // 遍历所有的响应头字段
//       for (String key : map.keySet()) {
//           System.out.println(key + "--->" + map.get(key));
//       }
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
			System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			 System.out.println("发送POST请求出现异常 "+ e);
	         e.printStackTrace();
		}finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
	}
}
