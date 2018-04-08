package day06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务器应用程序
 * @author Administrator
 *
 */
public class Server {
	//运行在服务器的Socket
 private ServerSocket server;
 /**
  * 构造方法，用于初始化服务端
 * @throws IOException 
  */
 public Server() throws IOException{
		 try {
			 /*
			  * 创建ServerSocket时需要指定服务器端口
			  */
			 System.out.println("初始化服务端");
			server=new ServerSocket(8088);
			System.out.println("服务端初始化完毕");
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	
 }
 
 /**
  * 服务端开始工作的方法
  */
 public void start(){
	 try{
		 /*
		  * ServerSocket的accept方法
		  * 用于监听8088端口，等待客户端的连接
		  * 该方法是一个阻塞方法，直到一个
		  * 客户端连接，否则该方法一直阻塞。
		  * 若一个客户端连接了，会返回该客户端的
		  * Socket
		  */
		 System.out.println("等待客户端连接...");
		 Socket socket=server.accept();
		 /*
		  * 通过socket获取远端的地址信息
		  * 对于服务端而言，远端就是客户端
		  */
		 InetAddress address
		    =socket.getInetAddress();
		 //获取远端计算机的IP地址
		 String ha=address.getHostAddress();
		 //address.getCanonicalHostName();
		 
		 int port=socket.getPort();
		 System.out.println(
				 ha+":"+port+"客户端连接了");
		 socket.getInetAddress();
		 
		 System.out.println("客户端连接了");
		 
		 /*
		  * 通过刚刚连上的客户端的Socket获取
		  * 输入流，来读取客户端发送过来的信息
		  */
		 InputStream in
		   =socket.getInputStream();
		 /*
		  * 将字节输入流包装为字符输出流，这样
		  * 可以指定编码集来读取每一个字符
		  */
		 InputStreamReader isr
		   =new InputStreamReader(
				   in,"utf-8");
		 /*
		  * 将字符流转换为缓冲字符输入流
		  * 这样就可以以行为单位读取字符串
		  */
		 BufferedReader br
		    =new BufferedReader(isr);
		 
		 //读取客户端发送过来的一行字符串
		 /*
		  * 读取客户端发送过来的信息这里
		  * windows与linux存在一定的差异：
		  * linux:当客户端与服务器断开连接后
		  *    我们通过输入流会读取null
		  *    但这里合乎逻辑的，因为缓冲流的readLine()方法若返回null就
		  *    表示无法通过该流再读取到信息。
		  *    参考之前服务文本文件的判断。
		  * 
		  * windows:当客户端与服务端断开连接后
		  *        readLine()方法会抛出异常。
		  */
		 String message=br.readLine();
		 while((message=br.readLine())!=null){
		 
		 System.out.println(
				 "客户端说："+message);
		 }
	 }catch(Exception e){
		 e.printStackTrace();
	 }
 }
 
 public static void main(String[] args){
	 Server server;
	 try {
		server=new Server();
		server.start();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println("服务器初始化失败");
	}
 }
 /**
  * 服务端中的一个线程，用于与某个客户端交互。
  * 使用线程的目的是使用服务端可以处理多客户端了
  * @author Administrator
  *
  */
 class ClientHandler implements Runnable{
	//当前线程处理的客户端的Socket
	 private Socket socket;
	 /**
	  * 根据给定的客户端的Socket,创建线程体
	  */
	 public ClientHandler(Socket socket){
		 this.socket=socket;
	 }
	 /**
	  * 该线程会将当前Socket中的输入流获取
	  * 用来循环读取客户端发送过来的消息。
	  */
	 public void run(){
		 
	 }
 }
}
