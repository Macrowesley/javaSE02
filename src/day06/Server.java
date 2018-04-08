package day06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ������Ӧ�ó���
 * @author Administrator
 *
 */
public class Server {
	//�����ڷ�������Socket
 private ServerSocket server;
 /**
  * ���췽�������ڳ�ʼ�������
 * @throws IOException 
  */
 public Server() throws IOException{
		 try {
			 /*
			  * ����ServerSocketʱ��Ҫָ���������˿�
			  */
			 System.out.println("��ʼ�������");
			server=new ServerSocket(8088);
			System.out.println("����˳�ʼ�����");
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	
 }
 
 /**
  * ����˿�ʼ�����ķ���
  */
 public void start(){
	 try{
		 /*
		  * ServerSocket��accept����
		  * ���ڼ���8088�˿ڣ��ȴ��ͻ��˵�����
		  * �÷�����һ������������ֱ��һ��
		  * �ͻ������ӣ�����÷���һֱ������
		  * ��һ���ͻ��������ˣ��᷵�ظÿͻ��˵�
		  * Socket
		  */
		 System.out.println("�ȴ��ͻ�������...");
		 Socket socket=server.accept();
		 /*
		  * ͨ��socket��ȡԶ�˵ĵ�ַ��Ϣ
		  * ���ڷ���˶��ԣ�Զ�˾��ǿͻ���
		  */
		 InetAddress address
		    =socket.getInetAddress();
		 //��ȡԶ�˼������IP��ַ
		 String ha=address.getHostAddress();
		 //address.getCanonicalHostName();
		 
		 int port=socket.getPort();
		 System.out.println(
				 ha+":"+port+"�ͻ���������");
		 socket.getInetAddress();
		 
		 System.out.println("�ͻ���������");
		 
		 /*
		  * ͨ���ո����ϵĿͻ��˵�Socket��ȡ
		  * ������������ȡ�ͻ��˷��͹�������Ϣ
		  */
		 InputStream in
		   =socket.getInputStream();
		 /*
		  * ���ֽ���������װΪ�ַ������������
		  * ����ָ�����뼯����ȡÿһ���ַ�
		  */
		 InputStreamReader isr
		   =new InputStreamReader(
				   in,"utf-8");
		 /*
		  * ���ַ���ת��Ϊ�����ַ�������
		  * �����Ϳ�������Ϊ��λ��ȡ�ַ���
		  */
		 BufferedReader br
		    =new BufferedReader(isr);
		 
		 //��ȡ�ͻ��˷��͹�����һ���ַ���
		 /*
		  * ��ȡ�ͻ��˷��͹�������Ϣ����
		  * windows��linux����һ���Ĳ��죺
		  * linux:���ͻ�����������Ͽ����Ӻ�
		  *    ����ͨ�����������ȡnull
		  *    ������Ϻ��߼��ģ���Ϊ��������readLine()����������null��
		  *    ��ʾ�޷�ͨ�������ٶ�ȡ����Ϣ��
		  *    �ο�֮ǰ�����ı��ļ����жϡ�
		  * 
		  * windows:���ͻ��������˶Ͽ����Ӻ�
		  *        readLine()�������׳��쳣��
		  */
		 String message=br.readLine();
		 while((message=br.readLine())!=null){
		 
		 System.out.println(
				 "�ͻ���˵��"+message);
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
		System.out.println("��������ʼ��ʧ��");
	}
 }
 /**
  * ������е�һ���̣߳�������ĳ���ͻ��˽�����
  * ʹ���̵߳�Ŀ����ʹ�÷���˿��Դ����ͻ�����
  * @author Administrator
  *
  */
 class ClientHandler implements Runnable{
	//��ǰ�̴߳���Ŀͻ��˵�Socket
	 private Socket socket;
	 /**
	  * ���ݸ����Ŀͻ��˵�Socket,�����߳���
	  */
	 public ClientHandler(Socket socket){
		 this.socket=socket;
	 }
	 /**
	  * ���̻߳Ὣ��ǰSocket�е���������ȡ
	  * ����ѭ����ȡ�ͻ��˷��͹�������Ϣ��
	  */
	 public void run(){
		 
	 }
 }
}
