package sever.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

import sever.main.MainSever;

public class TaoSever extends Thread{
	ServerSocket listener=null;
	MainSever main;
	int id=0;
	public TaoSever(MainSever main) {
		// TODO Auto-generated constructor stub
		this.main=main;
		
	}
	
	public boolean taoPort(int port)
	{
		try
		{
			listener = new ServerSocket(port);
		}
		catch(IOException ex)
		{
			JOptionPane.showMessageDialog(null, "Khong tao duoc cong!");
			return false;
		}
		return true;
	}

	public void run()
	{
		try
		{
			while(true)
				{
					Socket socket = listener.accept();
					main.thread.addElement(new ThreadSocket(socket, main, main.thread.size()));
					main.thread.elementAt(main.thread.size()-1).start();
					JOptionPane.showMessageDialog(null, "Co mot may ket noi den");
				}
		}catch(IOException ex)
		{
			JOptionPane.showMessageDialog(null, "Loi ket noi client!");
		}
		finally
		{
			
		}
	}
	
	public void close()
	{
		try
		{
			listener.close();
		}catch(IOException ex) {}
	}
}
