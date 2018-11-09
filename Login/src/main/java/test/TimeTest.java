package test;

import java.io.BufferedReader;
import java.io.DataOutput;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.StringBufferInputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.locks.ReentrantLock;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import org.junit.Test;
import org.xml.sax.helpers.DefaultHandler;

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;
import tt.entity.User;


public class TimeTest {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		ServerSocket s = new ServerSocket(8189);
		while(true){
			Socket incoming = s.accept();
			Runnable r =new ThreadSocket(incoming);
			Thread t = new Thread(r);
			t.start();
		}
		//InputStream in = incoming.getInputStream();
		//InputStreamReader is = new InputStreamReader(in);
		
		//InputStream in = new FileInputStream(new File("C:\\Users\\admin\\Desktop\\支付api.txt"));
		/*StringBuffer text=new StringBuffer();
		int c;
		while((c=in.read())!=-1&&!String.valueOf((char)c).equals("A")){
			System.out.print((char)c);
			text.append((char)c);
		}
		System.out.println(text.toString());*/
		//OutputStream ou = incoming.getOutputStream();
		//ou.write("hello".getBytes());
	}

	public static class ThreadSocket implements Runnable{
		private Socket incoming;
		public ThreadSocket(Socket incoming){
			this.incoming=incoming;
		}
		public void run() {
			// TODO Auto-generated method stub
			try {
				InputStream in = incoming.getInputStream();
				StringBuffer text=new StringBuffer();
				int c;
				while((c=in.read())!=-1&&!String.valueOf((char)c).equals("A")){
					System.out.print((char)c);
					text.append((char)c);
				}
				System.out.println(text.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	@Test
	public void test1() throws Exception{
		URL url = new URL("https://esa.un.org/unpd/wpp/");
		URLConnection con = url.openConnection();
		String name = "admin";
		String password = "123";
		String input = name+":"+password;
		BASE64Encoder encode = new BASE64Encoder();
		String coding = encode.encode(input.getBytes());
		//con.setRequestProperty("Authorization", "basic"+coding);
		con.connect();
		Map<String,List<String>> headers = con.getHeaderFields();
		for(Map.Entry<String,List<String>> header:headers.entrySet()){
			for(String value:header.getValue()){
				System.out.println(header.getKey()+":"+header.getValue());
			}
		}
		StringBuffer document = new StringBuffer();  
		BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));  
        String line = null;  
        while ((line = reader.readLine()) != null)  
          document.append(line + "\n");  
        reader.close();  
        System.out.println(document.toString());
	}
	
	@Test
    public void getDocumentAt() {  
        StringBuffer document = new StringBuffer();  
        try {  
            URL url = new URL("http://www.baidu.com");  
            URLConnection conn = url.openConnection();  
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));  
      
            String line = null;  
            while ((line = reader.readLine()) != null)  
              document.append(line + "\n");  
            reader.close();  
        } catch (MalformedURLException e) {  
            System.out.println("Unable to connect to URL: " );  
        } catch (IOException e) {  
            System.out.println("IOException when connecting to URL: ");  
        }  
        System.out.println(document.toString());  
    }  
	
	
	
}
