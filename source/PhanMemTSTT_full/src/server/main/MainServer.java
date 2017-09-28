package server.main;

import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import server.giaodien.GiaoDienChinh;
import server.giaodien.GiaoDienTaoCong;
import server.socket.TaoServer;
import server.socket.ThreadServer;

public class MainServer extends WindowAdapter implements Runnable
{
	ImageIcon imageIcon = new ImageIcon("image/LogoTSTT.png");
	public Image iconChinh = imageIcon.getImage();
	 public TaoServer taoServer = new TaoServer(this);
	GiaoDienTaoCong giaoDienTaoCong = new GiaoDienTaoCong(this);
	GiaoDienChinh giaoDienChinh;
	public String linkFile = "e:/log.txt";
	File file = null;
	FileWriter fileWriter = null;
	
	
	Vector<ThreadServer> threadServers = new Vector<>();
	boolean[] checkThreadServers;
	int gioiHanSoClient = 10;
	int[] idDangNhapTuongUng;
	int soLuongThread = 0;
	ThreadServer threadHienThi;
	boolean checkThreadHienThi = false;
	boolean daGuiTin[] = new boolean[10];
	boolean daGuiTinHienThi = false;
	public String kieuDuLieu = "UTF-8";
	
	public String[] idDangNhap = {"user01", "user02", "user03", "user04"};
	public int gioiHanSoID = 4;
	public String[] passDangNhap = {"pass01", "pass02", "pass03", "pass04"};
	public String[] tenNguoiChoi = new String[gioiHanSoID + 5];

	boolean[] tinhTrangDangNhap = {false, false, false, false, false , false};
	public long[] thoiGianCuoiCung = new long[gioiHanSoID +5];
	String[] cauTraLoi = new String[gioiHanSoID+5];
	
	public String idHienThi = "userhienthi";
	public String passHienThi = "passhienthi";
	boolean tinhTrangHienThi = false;
	Thread threadHienThiMay = new Thread(this);
	public int soCauHoi = 0;
	
	long thoiGianBatDau = 0;
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		MainServer mainServer = new MainServer();
		mainServer.batDau();
	}

	public MainServer()
	{

		checkThreadServers = new boolean[gioiHanSoClient];
		idDangNhapTuongUng = new int[gioiHanSoClient];
		for(int i=0;i<gioiHanSoClient;i++)checkThreadServers[i] = false;
		for(int i=0;i<gioiHanSoClient;i++)idDangNhapTuongUng[i] = gioiHanSoID;
		for(int i=0;i<gioiHanSoID;i++)
		{
			thoiGianCuoiCung[0] = 10000000000L;
			cauTraLoi[i] = "...";
		}
		
		tenNguoiChoi[0] = "Nguyen Van A";
		tenNguoiChoi[1] = "Nguyen Van B";
		tenNguoiChoi[2] = "Nguyen Van C";
		tenNguoiChoi[3] = "Nguyen Van D";
		taoLinkFile();
		giaoDienChinh  = new GiaoDienChinh(this);
	}
	
	public void taoLinkFile()
	{
		try
		{
			file = new File(linkFile);
			fileWriter = new FileWriter(file, true);
		}
		catch(IOException ex)
		{
			linkFile = JOptionPane.showInputDialog(null, "Không tìm thấy file log.txt,\nMời nhập lại link file,\n nhập NO để thoát.");
			if(linkFile.equals("NO"))exitChuongTrinh();
			file = new File(linkFile);
			taoLinkFile();
		}
	}
	
	public void batDau()
	{
		
		
		khoiChayThreadHienThiMay();
		giaoDienTaoCong.hienThi();
		//giaoDienChinh.giaoDienTuyChon.hienThi();

	}
	
	public void khoiChayThreadHienThiMay()
	{
		threadHienThiMay.start();
	}
	// liên quan đến giao diện chính
	public void hienThiGiaoDienChinh()
	{
		giaoDienChinh.hienThi();
		
	}
	
	public void suaTrangThaiMay()
	{
		String trangThai = "";
		for(int i=0; i<gioiHanSoClient; i++)
		{
			trangThai += "\nClient #" + i + " : " + (checkThreadServers[i]?"đã kết nối":"chưa kết nối") + " = " + (daGuiTin[i]?"OK":"Fail");
			trangThai += (idDangNhapTuongUng[i]<gioiHanSoID?("  " + idDangNhap[idDangNhapTuongUng[i]]) : "  None User");
		}
		
		trangThai += "\n\nHiển Thị :" + (checkThreadHienThi?"đã kết nối":"chưa kết nối") + " = " + (daGuiTinHienThi?"OK":"Fail");
		giaoDienChinh.hienThiTrangThaiMay(trangThai);
	}
	//xử lí liên quan đến socket
	
	public boolean taoServer(int port)
	{
		boolean res = taoServer.taoServer(port);
		//taoServer.start();
		return res;
	}
	
	
	public void addThreadServer(ThreadServer thread)
	{
		if(soLuongThread < gioiHanSoClient)
		{
			boolean kt = false;
			
			for(int i=0; i<threadServers.size(); i++)
				if(checkThreadServers[i] == false)
				{
					checkThreadServers[i] = true;
					threadServers.setElementAt(thread, i);
					threadServers.elementAt(i).thietLapID(i);
					threadServers.elementAt(i).start();
					soLuongThread++;
					kt = true;
				}
			if(!kt)
			{
				threadServers.addElement(thread);
				checkThreadServers[threadServers.size()-1] = true;
				threadServers.elementAt(threadServers.size()-1).thietLapID(threadServers.size()-1);
				threadServers.elementAt(threadServers.size()-1).start();
				kt = true;
			}	
		}
	}
	
	/*
	 * Loại một máy khỏi list
	 */
	
	public void loaiMay(int idClient)
	{
		if(idClient == 28)
		{
			checkThreadHienThi = false;
			tinhTrangHienThi = false;
			threadHienThi.ngatLuongDuLieu();
		}
		else
		{
			if(checkThreadServers[idClient]) soLuongThread--;
			checkThreadServers[idClient] = false;
			tinhTrangDangNhap[idDangNhapTuongUng[idClient]] = false;
			idDangNhapTuongUng[idClient] = gioiHanSoID;
			threadServers.elementAt(idClient).ngatLuongDuLieu();	
		}
	}
	
	
	public void xuLiTinNhan(String tinNhan, int idClient)
	{
		System.out.println(idClient + " " + tinNhan);
		if(tinNhan.startsWith("DangNhap: "))
		{
			int viTriCach1 = tinNhan.indexOf(" ");
			int viTriCach2 = tinNhan.indexOf(" ", viTriCach1 + 1);
			String id = tinNhan.substring(viTriCach1 + 1, viTriCach2);
			String pass = tinNhan.substring(viTriCach2 + 1);
			System.out.println(id + " " + pass);
			System.out.println(tinNhan);
			for(int i=0; i<gioiHanSoID; i++) 
			{
				if(idDangNhap[i].equals(id) && passDangNhap[i].equals(pass) && !tinhTrangDangNhap[i])
				{
					guiTinNhan("Login: OK", idClient);
					guiTinNhan("Ten ID: " + tenNguoiChoi[i], idClient);
					tinhTrangDangNhap[i] = true;
					idDangNhapTuongUng[idClient] = i;
					return ;
				}
			}
			
			if(idHienThi.equals(id)&& passHienThi.equals(pass) && !tinhTrangHienThi)
			{
				threadHienThi = threadServers.elementAt(idClient);
				threadHienThi.thietLapID(28);
				checkThreadHienThi = true;
				checkThreadServers[idClient] = false;
				tinhTrangHienThi = true;
				guiTinNhan("Login: OK", 28);
				return ;
			}
			
			guiTinNhan("Login: FALL", idClient);
			return ;
		}
		
		if(tinNhan.startsWith("Tra loi: "))
		{
			String hienThi = tinNhan.substring(tinNhan.indexOf(" ", tinNhan.indexOf(" " )+1)+1);
			String thoiGian = tinhToanThoiGian();
			thoiGianCuoiCung[idDangNhapTuongUng[idClient]] = tinhThoiGian();
			cauTraLoi[idDangNhapTuongUng[idClient]] = hienThi;
			
			guiTinNhan("Dap an: "+thoiGian + hienThi, idClient);
			String hienThiTraLoi = "\n #" + String.valueOf(idClient) + " : " + idDangNhap[idDangNhapTuongUng[idClient]]+ " " +thoiGian + hienThi;
			
			ghiFile("\n" + "-> Trả lời: " +hienThiTraLoi);
			
			giaoDienChinh.hienThiTraLoi(hienThiTraLoi);
			return ;
		}
	}
	
	
	public void guiBangRank()
	{
		boolean [] mang = {true, true, true, true,true};
		long max = 0;
		int vtmax = -1;
		max = 200000000000L;
		for(int i=0;i<4;i++)
			if(thoiGianCuoiCung[i]<max && mang[i])
			{
				max = thoiGianCuoiCung[i];
				vtmax = i;
			}
		guiTinNhan("#1: &" + tenNguoiChoi[vtmax] + "&" + tinhThoiGianTuongDoi(thoiGianCuoiCung[vtmax]) + "&" + cauTraLoi[vtmax] , 28);
		System.out.println(tenNguoiChoi[vtmax] + " " + tinhThoiGianTuongDoi(thoiGianCuoiCung[vtmax]));
		mang[vtmax] = false;
		
		max = 200000000000L;
		for(int i=0;i<4;i++)
			if(thoiGianCuoiCung[i]<max && mang[i])
			{
				max = thoiGianCuoiCung[i];
				vtmax = i;
			}
		guiTinNhan("#2: &" + tenNguoiChoi[vtmax] + "&" + tinhThoiGianTuongDoi(thoiGianCuoiCung[vtmax]) + "&" + cauTraLoi[vtmax] , 28);
		System.out.println(tenNguoiChoi[vtmax] + " " + tinhThoiGianTuongDoi(thoiGianCuoiCung[vtmax]));

		mang[vtmax] = false;
		
		max = 200000000000L;
		for(int i=0;i<4;i++)
			if(thoiGianCuoiCung[i]<max && mang[i])
			{
				max = thoiGianCuoiCung[i];
				vtmax = i;
			}
		guiTinNhan("#3: &" + tenNguoiChoi[vtmax] + "&" + tinhThoiGianTuongDoi(thoiGianCuoiCung[vtmax]) + "&" + cauTraLoi[vtmax] , 28);
		System.out.println(tenNguoiChoi[vtmax] + " " + tinhThoiGianTuongDoi(thoiGianCuoiCung[vtmax]));

		mang[vtmax] = false;
		
		max = 200000000000L;
		for(int i=0;i<4;i++)
			if(thoiGianCuoiCung[i]<max && mang[i])
			{
				max = thoiGianCuoiCung[i];
				vtmax = i;
			}
		guiTinNhan("#4: &" + tenNguoiChoi[vtmax] + "&" + tinhThoiGianTuongDoi(thoiGianCuoiCung[vtmax]) + "&" + cauTraLoi[vtmax] , 28);
		System.out.println(tenNguoiChoi[vtmax] + " " + tinhThoiGianTuongDoi(thoiGianCuoiCung[vtmax]));

		mang[vtmax] = false;
		
	}
	
	String tinhThoiGianTuongDoi(long thoiGian)
	{
		
		long thoiGianRes = thoiGian - thoiGianBatDau;
		System.out.println("Thoi gian tuong doi "+thoiGianRes + " " + thoiGian);
		if(thoiGianRes<= 0) thoiGianRes = 97000;
		String res = String.valueOf(thoiGianRes/1000) + "." + String.valueOf(thoiGianRes%1000);
		if(thoiGianRes > 60000) res = "ERR";
		return res;
	}
	
	public void resetBangRank()
	{
		for(int i=0; i<gioiHanSoID; i++)
		{
			thoiGianCuoiCung[i]=100000000000L;
			cauTraLoi[i] = "...";
		}
		thoiGianBatDau = 0;
	}
	
	public void taoThoiGianBatDau()
	{
		thoiGianBatDau = tinhThoiGian();
		String hienThi = "\n ********************\n Câu hỏi số #" +String.valueOf(soCauHoi++) + " : " + tinhToanThoiGian() + "\n";
		giaoDienChinh.hienThiTraLoi(hienThi);
		ghiFile(hienThi);
		System.out.println("thoi gian bat dau: " +  thoiGianBatDau);
	}
	
	public boolean guiTinNhan(String tinNhan, int idClient)
	{
		if(idClient == 28)
		{
			if(checkThreadHienThi)
				{
					threadHienThi.guiDuLieu(tinNhan);
					daGuiTinHienThi = true;
					return true;
				}
			daGuiTinHienThi = false;
			return false;
		}
		try
		{
		if(checkThreadServers[idClient])
		{
			threadServers.elementAt(idClient).guiDuLieu(tinNhan);
			daGuiTin[idClient] = true;
			return true;
		}
		daGuiTin[idClient] = false;
		}
		catch(ArrayIndexOutOfBoundsException ex)
		{
			JOptionPane.showMessageDialog(null, ex);
		}
		return false;
	}
	
	
	public void guiTinNhanDenToanBo(String tinNhan)
	{
		guiTinNhan(tinNhan, 28);
		
		for(int i=0;i<10;i++)
			guiTinNhan(tinNhan, i);
	}
	
	
	
	
	//cac ham xu li
	
	public String tinhToanThoiGian()
	{
		String thoiGian = "";
		Calendar calendar = Calendar.getInstance();
		String gio = String.valueOf(calendar.get(Calendar.HOUR));
		String phut = String.valueOf(calendar.get(Calendar.MINUTE));
		String giay = String.valueOf(calendar.get(Calendar.SECOND));
		String milis = String.valueOf(calendar.get(Calendar.MILLISECOND));

		thoiGian += "[ "+gio+":"+phut+":"+giay+"."+milis+" ] ";
		return thoiGian;
	}
	
	public long  tinhThoiGian()
	{
		Calendar calendar = Calendar.getInstance();
		long thoiGian = calendar.get(Calendar.HOUR);
		thoiGian = thoiGian * 60 + calendar.get(Calendar.MINUTE);
		thoiGian = thoiGian * 60 + calendar.get(Calendar.SECOND);
		thoiGian = thoiGian * 1000 + calendar.get(Calendar.MILLISECOND);
		return thoiGian;
	}
	
	public void ghiFile(String duLieu)
	{
		try
		{
			fileWriter = new FileWriter(file, true);
			fileWriter.write(duLieu);
			fileWriter.close();
		}
		catch(IOException ex)
		{
			JOptionPane.showMessageDialog(null, "Có lỗi xảy ra với file log");
			taoLinkFile();
			ghiFile(duLieu);
		}
	}
	
	//bắt sự kiện
		public void exitChuongTrinh()
		{
			int hoi;
			hoi=JOptionPane.showConfirmDialog(null, "Bạn có muốn thoát chương trình không?",null, JOptionPane.YES_NO_OPTION);
	        if (hoi == JOptionPane.YES_OPTION)
	        {
	        	try
	        	{
	        		fileWriter.close();
	        	}
	        	catch(IOException ex)
	        	{
	        		JOptionPane.showMessageDialog(null, "Lỗi đóng file");
	        	}
	            System.exit(0);
	        }
		}
		
	@Override
	public void windowClosing(WindowEvent e)
	{
		 	exitChuongTrinh();
	}

	@Override
	public void run() {
		while(true)
		{
			suaTrangThaiMay();
			//System.out.println("hien thi may");
			try
			{
				Thread.sleep(1000);
			}
			catch(InterruptedException ex)
			{
				JOptionPane.showMessageDialog(null, "Lỗi thread hiển thị máy");
			}
		}
	}
}



