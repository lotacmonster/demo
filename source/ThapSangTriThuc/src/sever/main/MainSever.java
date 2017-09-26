package sever.main;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.JOptionPane;

import sever.giaodien.GiaoDienChatServer;
import sever.giaodien.GiaoDienLogin;
import sever.giaodien.GiaoDienVongChoi; 
import sever.socket.TaoSever;
import sever.socket.ThreadSocket;

public class MainSever extends WindowAdapter{
	GiaoDienLogin gdLogin = new GiaoDienLogin(this);
	GiaoDienVongChoi gdVong = new GiaoDienVongChoi();
	GiaoDienChatServer gdChat = new GiaoDienChatServer(this);
	public Vector <ThreadSocket> thread = new Vector<ThreadSocket>();
	String[] IDPass= {"Login: abc-xyz","Login: dinh-cong"};
	int sizeOfIDPass=2;
	TaoSever severSocket= new TaoSever(this);
	public static void main(String[] args) {
		new MainSever().batDau();
	}
	
	public void batDau()
	{
		gdLogin.hienThi();
	}
	
	
	public void taoSocket(int port)
	{
		
		if(severSocket.taoPort(port))
		{
			JOptionPane.showMessageDialog(null, "Da tao cong ket noi", "Thong bao", JOptionPane.OK_OPTION);
			severSocket.start();
			gdLogin.thoat();
			gdChat.hienThi();
			
		}
	}
	

	public void xuLiLenhTuClient(String lenh, int idClient)
	{
		System.out.println(idClient + " "+lenh);
		if(lenh.startsWith("Login: "))
		{
			for(int i=0;i<sizeOfIDPass;i++)
				if(IDPass[i].equals(lenh))
				{
					thread.elementAt(idClient).phatLenhDenClient("Server: LoginOK");
					System.out.println("login ok");
					return ;
				}
			thread.elementAt(idClient).phatLenhDenClient("Server: LoginNO");
			System.out.println("login no");
			return ;
		}
		
		if(lenh.startsWith("TinNhan: "))
		{
			gdChat.hienThiTinNhan(lenh, idClient);
			return ;
		}
	//	thread.elementAt(idClient).phatLenhDenClient("Server: LoginOK");
	}
	
	
	public void guiTinNhan(String tinNhan, int id)
	{
		thread.elementAt(id).phatLenhDenClient("TinNhan: "+tinNhan);
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
