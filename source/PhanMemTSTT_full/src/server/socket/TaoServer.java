package server.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

import server.main.MainServer;

public class TaoServer extends Thread
{

	MainServer mainServer;
	ServerSocket serverSocket;
	Socket socket;
	int portServer;
	
	public TaoServer(MainServer mainServer)
	{
		// TODO Auto-generated constructor stub
		this.mainServer = mainServer;
	}

	
	//tạo cổng giao tiếp
	public boolean taoServer(int port)
	{
		portServer = port;
		
		try
		{
			serverSocket = new ServerSocket(portServer);
			JOptionPane.showMessageDialog(null, "Tạo cổng thành công");
			return true;
		}
		catch(IOException ex)
		{
			JOptionPane.showMessageDialog(null, "Tạo cổng thất bại!!!", null, JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	
	
	//lắng nghe máy khách kết nối
	public void run()
	{
		try
		{
			while(true)
			{
				socket = serverSocket.accept();
				JOptionPane.showMessageDialog(null, "Có máy kết nối đến: " + socket.getInetAddress() , "Thông báo", JOptionPane.QUESTION_MESSAGE);
				mainServer.addThreadServer(new ThreadServer(mainServer, socket));
			}
		}
		catch(IOException ex)
		{ 
			JOptionPane.showMessageDialog(null, "Lỗi khi kết nối client", "Thông báo", JOptionPane.WARNING_MESSAGE);
		}
	}
	
}
