package myCurl;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.URL;

public class MyCurl {

	public MyCurl() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(args.length==0) {
			System.err.println("Usage: Arguements is null");
			return;
		}
		String urlString= args[0];
		try {
			URL url= new URL(urlString);
			String protocol= url.getProtocol();
			String host= url.getHost();
			int port= url.getPort()!=-1?url.getPort():url.getDefaultPort();
			String path= url.getPath();
			System.out.println("Connecting to"+" "+host);
			
			try(Socket socket= new Socket(host,port)){
				OutputStreamWriter out= new OutputStreamWriter(socket.getOutputStream());
				BufferedReader reader= new BufferedReader(new InputStreamReader(socket.getInputStream()));
				 String request = "GET " + path + " HTTP/1.1\r\n" +
                         "Host: " + host + "\r\n" +
                         "Accept: */*\r\n" +
                         "Connection: close\r\n" +
                         "\r\n";
				 out.write(request);
				 out.flush();
				 String line;
				 while((line=reader.readLine())!=null) {
					 System.out.println(line);
				 }
			}
			System.out.println("Sending request GET to"+" "+path+" "+"HTTP1/1");
			System.out.println("Host: " + host);
	        System.out.println("Accept: */*");
	        
	        
		}catch(Exception e) {
			System.out.println("Exception occured"+" "+e.getMessage());
		}

	}

}
