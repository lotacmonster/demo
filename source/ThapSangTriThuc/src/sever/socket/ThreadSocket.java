package sever.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;
import javax.swing.JOptionPane;

import sever.main.MainSever;

public class ThreadSocket extends Thread{

	MainSever mainSever;
	Socket socket=null;
	int ID;
	BufferedReader is;
	BufferedWriter os;
	
	String lenhTuClient;
	
	public ThreadSocket(Socket socket, MainSever main, int ID) {
		// TODO Auto-generated constructor stub
		this.mainSever=main;
		this.socket=socket;
		this.ID=ID;
	}
	
	
	public void phatLenhDenClient(String lenh)
	{
		try
		{
			os.write(lenh);
			os.newLine();
			os.flush();
		}
		catch(IOException ex)
		{
			
		}
	}
	
	//dong tat ca cac cong
	public void close()
	{
		try
		{
			os.close();
			is.close();
			socket.close();
		}
		catch(IOException ex)
		{
		
		}
	}
	// nhan lenh tu client
	public void run()
	{
		try
		{

			is = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			os = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
			
			while(true)
			{
				if((lenhTuClient=is.readLine())!=null)
				{
					mainSever.xuLiLenhTuClient(lenhTuClient, ID);
				}
			}
		}
		catch(IOException ex)
		{
			JOptionPane.showMessageDialog(null, "Loi vao ra tai: "+ ID);
		}
		finally
		{
			close();
		}
	}
	
	
}
