package server.main;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.JOptionPane;

import server.giaodien.GiaoDienChinh;
import server.giaodien.GiaoDienTaoCong;
import server.socket.TaoServer;
import server.socket.ThreadServer;

public class MainServer extends WindowAdapter
{
	TaoServer taoServer = new TaoServer(this);
	GiaoDienTaoCong giaoDienTaoCong = new GiaoDienTaoCong(this);
	GiaoDienChinh giaoDienChinh = new GiaoDienChinh(this);
	
	Vector<ThreadServer> threadServers = new Vector<>();
	boolean checkThreadServers[] = new boolean[10];
	int soLuongThread = 0;
	ThreadServer threadHienThi;
	boolean checkThreadHienThi = false;
	boolean daGuiTin[] = new boolean[10];
	boolean daGuiTinHienThi = false;
	
	public String kieuDuLieu = "UTF-8";
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		MainServer mainServer = new MainServer();
		for(int i=0;i<10;i++)mainServer.checkThreadServers[i]=false;
		mainServer.batDau();
	}

	public void batDau()
	{
		giaoDienTaoCong.hienThi();
	}
	
	// liên quan đến giao diện chính
	public void hienThiGiaoDienChinh()
	{
		giaoDienChinh.hienThi();
	}
	
	public void suaTrangThaiMay()
	{
		String trangThai = "";
		for(int i=0; i<10; i++)
		{
			trangThai += "\nClient #" + i + " : " + (checkThreadServers[i]?"đã kết nối":"chưa kết nối") + " = " + (daGuiTin[i]?"OK":"Fail");
		}
		
		trangThai += "\n\nHiển Thị :" + (checkThreadHienThi?"đã kết nối":"chưa kết nối") + " = " + (daGuiTinHienThi?"OK":"Fail");
		giaoDienChinh.hienThiTrangThaiMay(trangThai);
	}
	//xử lí liên quan đến socket
	
	public boolean taoServer(int port)
	{
		boolean res = taoServer.taoServer(port);
		taoServer.start();
		return res;
	}
	
	
	public void addThreadServer(ThreadServer thread)
	{
		if(soLuongThread < 10)
		{
			boolean kt = false;
			
			for(int i=0; i<threadServers.size(); i++)
				if(checkThreadServers[i] == false)
				{
					checkThreadServers[i] = true;
					threadServers.setElementAt(thread, i);
					threadServers.elementAt(i).thietLapID(i);
					threadServers.elementAt(i).start();
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
		suaTrangThaiMay();
	}
	
	public void xuLiTinNhan(String tinNhan, int idClient)
	{
		
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
		if(checkThreadServers[idClient])
		{
			threadServers.elementAt(idClient).guiDuLieu(tinNhan);
			daGuiTin[idClient] = true;
			return true;
		}
		daGuiTin[idClient] = false;
		return false;
	}
	
	
	public void guiTinNhanDenToanBo(String tinNhan)
	{
		guiTinNhan(tinNhan, 28);
		
		for(int i=0;i<10;i++)
			guiTinNhan(tinNhan, i);
		
		suaTrangThaiMay();
	}
	
	//bắt sự kiện
	public void exitChuongTrinh()
	{
		int hoi;
		hoi=JOptionPane.showConfirmDialog(null, "Bạn có muốn thoát chương trình không?",null, JOptionPane.YES_NO_OPTION);
        if (hoi == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
	}
	
	@Override
	public void windowClosing(WindowEvent e)
	{
		 	exitChuongTrinh();
	}

}
