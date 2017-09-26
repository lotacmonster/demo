package client.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import client.main.MainClient;

public class SocketClient extends Thread{

	Socket clientSocket;
	BufferedReader is;
	BufferedWriter os;
	String ID;
	int port;
	String host;
	String lenhTuServer=null;
	MainClient mainClient;
	
	public SocketClient(MainClient main)
	{
		// TODO Auto-generated constructor stub
		mainClient=main;
	}
	
	// khoi tao ket noi den serve
	
	public boolean taoKetNoi(String svHost, int svPort)
	{
		try
		{
			clientSocket = new Socket(svHost, svPort);
			is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));
			os = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8"));
			JOptionPane.showMessageDialog(null, "Tao thanh cong duong truyen den server", "Thong bao", JOptionPane.OK_OPTION);
			this.start();
		}
		catch(UnknownHostException ex)
		{
			JOptionPane.showMessageDialog(null, "Khong tim thay Host", "Thong bao", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		catch(IOException ex)
		{
			JOptionPane.showMessageDialog(null, "Loi tao Stream", "Thong bao", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		return true;
	}
	
	// gui lenh den sever
	public void guiLenhDenServer(String lenh)
	{
		try
		{
			os.write(lenh);
			os.newLine();
			os.flush();
		}
		catch(UnknownHostException ex)
		{
			
		}
		catch(IOException ex)
		{
			
		}
	}
	
	
	// nhan lenh tu server

	public void run()
	{
		try
		{
			while(true)
			{
				if((lenhTuServer=is.readLine())!=null)	mainClient.xuLiLenhTuServer(lenhTuServer);
			}
		}
		catch(UnknownHostException ex)
		{
			JOptionPane.showMessageDialog(null, "Mat ket noi host!", "Thong bao", JOptionPane.ERROR_MESSAGE);
		}
		catch(IOException ex)
		{
			JOptionPane.showMessageDialog(null, "Loi tin hieu!", "Thong bao", JOptionPane.ERROR_MESSAGE);
		}
		finally
		{
			try
			{
				os.close();
				is.close();
				clientSocket.close();
			}
			catch(IOException ex)
			{
				
			}
		}
		
	}
	
}
