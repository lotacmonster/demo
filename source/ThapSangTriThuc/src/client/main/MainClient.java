package client.main;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import client.giaodien.GiaoDienChatRoom;
import client.giaodien.GiaoDienKetNoi;
import client.giaodien.GiaoDienLogin;
import client.socket.SocketClient;

public class MainClient extends WindowAdapter{

	GiaoDienKetNoi gdKetNoi= new GiaoDienKetNoi(this);
	GiaoDienLogin gdLogin= new GiaoDienLogin(this);
	GiaoDienChatRoom gdChatRoom= new GiaoDienChatRoom(this);
	
	SocketClient socketClient= new SocketClient(this);
	String ID;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainClient mainClient=new MainClient();
		mainClient.batDau();
	}

	void batDau()
	{
		gdKetNoi.hienThi();
	}
	
	public void ketNoi(String host, int port)
	{
		if(socketClient.taoKetNoi(host, port))	showLogin();
		else exitSystem();
	}
	
	public void dangNhap(String id, String pass)
	{
		socketClient.guiLenhDenServer("Login: "+id+ "-"+pass);
	}
	
	
	public void showChatRoom()
	{
		gdLogin.thoat();
		gdChatRoom.hienThi();
	}
	
	public void showLogin()
	{
		gdKetNoi.thoat();
		gdLogin.hienThi();
	}
	
	public void showKetNoi()
	{
		gdKetNoi.hienThi();
		gdLogin.thoat();
	}
	
	public void xuLiLenhTuServer(String lenh)
	{
		System.out.println(lenh);
		if(lenh.equals("Server: LoginOK"))
		{
			showChatRoom();
			return ;
		}
		
		if(lenh.equals("Server: LoginNO"))
		{
			JOptionPane.showMessageDialog(null, "Sai mat khau hoac ID");
			return ;
		}
		
		if(lenh.startsWith("TinNhan: "))
		{
			gdChatRoom.hienThiTinNhan(lenh);
			return ;
		}
	}
	
	
	public void guiTinNhan(String tinNhan)
	{
		socketClient.guiLenhDenServer("TinNhan: "+tinNhan);
	}
	
	public void exitSystem()
	{
		System.exit(1);
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		super.windowClosing(e);
		exitSystem();
	}
}
