package client.main;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import client.giaodien.GiaoDienDangNhap;
import client.giaodien.GiaoDienKetNoi;
import client.giaodien.GiaoDienVongChoi;
import client.socket.ThreadClient;

public class MainClient extends WindowAdapter {
	GiaoDienDangNhap giaoDienDangNhap = new GiaoDienDangNhap(this);
	GiaoDienKetNoi giaoDienKetNoi = new GiaoDienKetNoi(this);
	GiaoDienVongChoi giaoDienVongChoi = new GiaoDienVongChoi(this);
	public ThreadClient threadClient = new ThreadClient(this);
	public String kieuDuLieu = "UTF-8";
	
	public static void main(String[] args)
	{
		MainClient mainClient = new MainClient();
		mainClient.batDau();
	}
	
	public void batDau()
	{
		giaoDienKetNoi.hienThi();
	}
	 
	public void ketNoiMayChu(String svHost, int svPort)
	{
		if(threadClient.taoKetNoi(svHost, svPort))
		{
			threadClient.start();
			giaoDienKetNoi.anDi();
			giaoDienDangNhap.hienThi();
		}
	}
	//xử lí lệnh
	
	public void xuLiTinNhan(String tinNhan)
	{
		System.out.println(tinNhan);
		JOptionPane.showMessageDialog(null, tinNhan);
	}
	// sự kiện thoát
	
	public void exitChuongTrinh()
	{
		int thoat;
		thoat = JOptionPane.showConfirmDialog(null, "Bạn có muốn thoát khỏi chương trình?", null, JOptionPane.YES_NO_OPTION);
		if(thoat == JOptionPane.YES_OPTION)
		{
			System.exit(1);
		}
	}
	
	@Override
	public void windowClosing(WindowEvent e)
	{
		exitChuongTrinh();
	}
}
