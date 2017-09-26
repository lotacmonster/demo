package server.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.swing.JOptionPane;

import server.main.MainServer;

public class ThreadServer extends Thread
{
	MainServer mainServer;
	Socket socket;
	int ID = 0;
	BufferedWriter bufferedWriter;
	BufferedReader bufferedReader;
	String tinNhanNhanDuoc;
	
	public ThreadServer(MainServer mainServer, Socket socket)
	{
		// TODO Auto-generated constructor stub
		this.mainServer = mainServer;
		this.socket = socket;
		
		try
		{
			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), mainServer.kieuDuLieu));
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), mainServer.kieuDuLieu));
		}
		catch(IOException ex)
		{
		
		}
	}
	
	public void thietLapID(int ID)
	{
		this.ID = ID;
	} 
	
	public void guiDuLieu(String tinNhan)
	{
		try
		{
			bufferedWriter.write(tinNhan);
			bufferedWriter.newLine();
			bufferedWriter.flush();
		}
		catch(IOException ex)
		{
			JOptionPane.showMessageDialog(null, "Lỗi gửi tin đến máy: "+ID);
		}
	}
	
	public void run()
	{
		try
		{
			while(true)
			{
				if((tinNhanNhanDuoc = bufferedReader.readLine())!=null)
				{
					mainServer.xuLiTinNhan(tinNhanNhanDuoc, ID);
				}
			}
		}
		catch(IOException ex)
		{
			JOptionPane.showMessageDialog(null, "Lỗi đọc dữ liệu: "+ID);
		}
	}
	
	public boolean ngatLuongDuLieu()
	{
		try
		{
			bufferedReader.close();
			bufferedWriter.close();
			socket.close();
			return true;
		}
		catch(IOException ex)
		{
			JOptionPane.showMessageDialog(null, "Ngắt luồng thất bại");
			return false;
		}
	}
	
	
	
}
