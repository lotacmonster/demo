package client.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import client.main.MainClient;

public class ThreadClient extends Thread{

	MainClient mainClient;
	Socket socket;
	String svHost;
	int svPort;
	BufferedReader bufferedReader;
	BufferedWriter bufferedWriter;
	String tinNhanTuServer;
	
	public ThreadClient(MainClient mainClient) {
		this.mainClient = mainClient;
	}

	public boolean taoKetNoi(String svHost, int svPort)
	{
		this.svHost = svHost;
		this.svPort = svPort;
		
		try
		{ 
			socket = new Socket(svHost, svPort);
			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), mainClient.kieuDuLieu));
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), mainClient.kieuDuLieu));
			JOptionPane.showMessageDialog(null, "Kết nối thành công");
			return true;
		}
		catch(UnknownHostException ex)
		{
			JOptionPane.showMessageDialog(null, "Mời nhập lại", "Lỗi kết nối", JOptionPane.ERROR_MESSAGE);
		}
		catch(IOException ex)
		{
			JOptionPane.showMessageDialog(null, "Mời nhập lại", "Lỗi kết nối", JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}
	
	public void guiTinDenServer(String tinNhan)
	{
		try
		{
			bufferedWriter.write(tinNhan);
			bufferedWriter.newLine();
			bufferedWriter.flush();
		}
		catch(UnknownHostException ex)
		{
			
		}
		catch(IOException ex)
		{
			
		}
	}
	
	
	public void run()
	{
		try
		{
			while(true)
			{
				if((tinNhanTuServer = bufferedReader.readLine())!=null)
				{
					mainClient.xuLiTinNhan(tinNhanTuServer);
				}
			}
		}
		catch(UnknownHostException ex)
		{
			
		}
		catch(IOException ex)
		{
			
		}
	}
}
