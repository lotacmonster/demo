package clienthienthi.main;

import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import clienthienthi.giaodien.GiaoDienDangNhap;
import clienthienthi.giaodien.GiaoDienHienThi;
import clienthienthi.giaodien.GiaoDienKetNoi;
import clienthienthi.socket.ThreadHienThi;


public class HienThiMain extends WindowAdapter{

	public ImageIcon icon = new ImageIcon("image/LogoTSTT.png");
	public Image iconChinh = icon.getImage();
	GiaoDienKetNoi giaoDienKetNoi = new GiaoDienKetNoi(this);
	GiaoDienDangNhap giaoDienDangNhap = new GiaoDienDangNhap(this);
	GiaoDienHienThi giaoDienHienThi = new GiaoDienHienThi(this);
	ThreadHienThi threadHienThi = new ThreadHienThi(this);
	public String kieuDuLieu = "UTF-8";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new HienThiMain();
	}

	public HienThiMain() 
	{
		giaoDienKetNoi.hienThi();
		//giaoDienDangNhap.hienThi();
		//giaoDienHienThi.hienThi();
	}
	
	
	/*
	 * 
	 */
	
	public void dangNhap(String id, String pass)
	{
		guiTinDenServer("DangNhap: "+ id +" "+ pass);
	}
	/*
	 * 
	 */
	public void taoKetNoi(String svHost, int svPort)
	{
		if(threadHienThi.taoKetNoi(svHost, svPort))
		{
			giaoDienKetNoi.anDi();
			giaoDienDangNhap.hienThi();
		}
	}
	
	public void guiTinDenServer(String tinNhan)
	{
		threadHienThi.guiTinDenServer(tinNhan);
	}
	
	
	public void xuLiTinNhan(String tinNhan)
	{
		if(tinNhan.startsWith("Login: "))
		{
			if(tinNhan.equals("Login: OK"))
			{
				giaoDienDangNhap.anDi();
				giaoDienHienThi.hienThi();
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Sai ID hoặc mật khẩu");
			}
			return ;
		}
		
		if(tinNhan.startsWith("Bat dau: "))
		{
			giaoDienHienThi.hienThiThoiGian(" " +tinNhan.substring(9));
			return ;
		}
		
		if(tinNhan.startsWith("Thoi gian: "))
		{
			giaoDienHienThi.hienThiThoiGian(" "+tinNhan.substring(11));
			return ;
		}
		
		if(tinNhan.startsWith("Ket thuc: "))
		{
			giaoDienHienThi.hienThiThoiGian(" 00 ");
			return ;
		}
		
		if(tinNhan.startsWith("Reset: "))
		{
			giaoDienHienThi.resetNhan();
			return ;
		}
		
		if(tinNhan.startsWith("#1: "))
		{
			int vt1 = tinNhan.indexOf("&");
			int vt2 = tinNhan.indexOf("&", vt1 + 1);
			int vt3 = tinNhan.indexOf("&", vt2 + 1);
			giaoDienHienThi.hienThiNhan1(tinNhan.substring(vt1 + 1, vt2), tinNhan.substring(vt2 + 1, vt3), tinNhan.substring(vt3 + 1));
			return ;
		}
		
		if(tinNhan.startsWith("#2: "))
		{
			int vt1 = tinNhan.indexOf("&");
			int vt2 = tinNhan.indexOf("&", vt1 + 1);
			int vt3 = tinNhan.indexOf("&", vt2 + 1);
			giaoDienHienThi.hienThiNhan2(tinNhan.substring(vt1 + 1, vt2), tinNhan.substring(vt2 + 1, vt3), tinNhan.substring(vt3 + 1));
			return ;
		}
		
		if(tinNhan.startsWith("#3: "))
		{
			int vt1 = tinNhan.indexOf("&");
			int vt2 = tinNhan.indexOf("&", vt1 + 1);
			int vt3 = tinNhan.indexOf("&", vt2 + 1);
			giaoDienHienThi.hienThiNhan3(tinNhan.substring(vt1 + 1, vt2), tinNhan.substring(vt2 + 1, vt3), tinNhan.substring(vt3 + 1));
			return ;
		}
		
		if(tinNhan.startsWith("#4: "))
		{
			int vt1 = tinNhan.indexOf("&");
			int vt2 = tinNhan.indexOf("&", vt1 + 1);
			int vt3 = tinNhan.indexOf("&", vt2 + 1);
			giaoDienHienThi.hienThiNhan4(tinNhan.substring(vt1 + 1, vt2), tinNhan.substring(vt2 + 1, vt3), tinNhan.substring(vt3 + 1));
			return ;
		}
	}
	/*
	 * 
	 */
	
	public void ketNoiLai()
	{
		JOptionPane.showMessageDialog(null, "Lỗi kết nối, \nHãy khởi động lại chương trình", "Thông báo", JOptionPane.ERROR_MESSAGE);
		exitChuongTrinh();
	}
	
	public void exitChuongTrinh()
	{
		int chon = JOptionPane.showConfirmDialog(null, "Bạn có muốn thoát chương trình?", null, JOptionPane.YES_NO_OPTION);
		if(chon == JOptionPane.YES_OPTION)
		{
			System.exit(1);
		}
	}
	
	public void windowClosing(WindowEvent e)
	{
		exitChuongTrinh();
	}
}
