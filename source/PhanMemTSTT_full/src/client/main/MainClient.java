package client.main;

import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import client.giaodien.GiaoDienDangNhap;
import client.giaodien.GiaoDienKetNoi;
import client.giaodien.GiaoDienVongChoi;
import client.socket.ThreadClient;

public class MainClient extends WindowAdapter {
	ImageIcon imageIcon = new ImageIcon("image/LogoTSTT.png");
	public Image iconChinh = imageIcon.getImage();
	GiaoDienDangNhap giaoDienDangNhap = new GiaoDienDangNhap(this);
	GiaoDienKetNoi giaoDienKetNoi = new GiaoDienKetNoi(this);
	GiaoDienVongChoi giaoDienVongChoi = new GiaoDienVongChoi(this);
	public ThreadClient threadClient = new ThreadClient(this);
	public String kieuDuLieu = "UTF-8";

	
	public static void main(String[] args)
	{
		MainClient mainClient = new MainClient();
		mainClient.batDau();
		//mainClient.giaoDienVongChoi.hienThi();
	}
	
	public void batDau()
	{
		giaoDienKetNoi.hienThi();
	}
	
	/*
	 * kết nối đến máy chủ
	 */
	public void ketNoiMayChu(String svHost, int svPort)
	{
		if(threadClient.taoKetNoi(svHost, svPort))
		{
			//if(!threadClient.isAlive())threadClient.start();
			giaoDienKetNoi.anDi();
			giaoDienDangNhap.hienThi();
		}
	}
	
	/*
	 * Khối lênh đăng nhập
	 * 
	 */
	
	public void dangNhap(String id, String pass)
	{
		guiTinDenServer("DangNhap: "+ id +" "+ pass);
	}
	//xử lí lệnh
	
	public void xuLiTinNhan(String tinNhan)
	{
		System.out.println(tinNhan);
		//JOptionPane.showMessageDialog(null, tinNhan);
		
		if(tinNhan.startsWith("Login: "))
		{
			if(tinNhan.indexOf("OK")!=-1) 
			{
				giaoDienDangNhap.anDi();
				giaoDienVongChoi.hienThi();
			}
			else
			{
				JOptionPane.showMessageDialog(giaoDienDangNhap, "Sai ID hoặc mật khẩu");
			}
		}
		
		if(tinNhan.startsWith("Dap an: "))
		{
			giaoDienVongChoi.hienThiDapAn(tinNhan.substring(tinNhan.indexOf(" ", tinNhan.indexOf(" ") + 1) + 1));
			return ;
		}
		
		if(tinNhan.startsWith("Bat dau: "))
		{
			giaoDienVongChoi.hienThiThoiGian(" 00 ");
			return ;
		}
		
		if(tinNhan.startsWith("Thoi gian: "))
		{
			giaoDienVongChoi.hienThiThoiGian(tinNhan.substring(11));
			return ;
		}
		
		if(tinNhan.startsWith("Ket thuc: "))
		{
			giaoDienVongChoi.checkGuiDapAn = false;
			return ;
		}
		
		if(tinNhan.startsWith("Reset: "))
		{
			giaoDienVongChoi.hienThiThoiGian(" 00 ");
			giaoDienVongChoi.dapAnTruoc = "";
			giaoDienVongChoi.checkGuiDapAn = false;
			return ;
		}
		
		if(tinNhan.startsWith("Ten ID: "))
		{
			giaoDienVongChoi.hienThiTenNguoiChuoi(tinNhan.substring(8));
			return ;
		}
	}
	
	/*
	 * gui tin den server
	 */
	
	public void guiTinDenServer(String tinNhan)
	{
		threadClient.guiTinDenServer(tinNhan);
	}
	
	// xử lí ngoại lệ Socket
	
	public void ketNoiLai()
	{
		JOptionPane.showMessageDialog(null, "Gặp sự cố đường truyền,\n Hãy khởi động lại phần mềm?", "Lỗi", JOptionPane.ERROR_MESSAGE);
		exitChuongTrinh();
	}
	// sự kiện thoát
	
	public void exitChuongTrinh()
	{
		int thoat;
		thoat = JOptionPane.showConfirmDialog(null, "Bạn có muốn thoát khỏi chương trình?", null, JOptionPane.YES_NO_OPTION);
		if(thoat == JOptionPane.OK_OPTION)
		{
			System.exit(1);
		}
	}
	
	@Override
	public void windowClosing(WindowEvent e)
	{
		this.exitChuongTrinh();
		return ;
	}
}
