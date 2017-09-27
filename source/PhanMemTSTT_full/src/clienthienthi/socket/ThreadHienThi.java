package clienthienthi.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import clienthienthi.main.HienThiMain;

public class ThreadHienThi extends Thread
{
	HienThiMain hienThiMain;
	Socket socket;
	String svHost;
	int svPort;
	BufferedReader bufferedReader;
	BufferedWriter bufferedWriter;
	String tinNhanTuServer;
	
	public ThreadHienThi(HienThiMain hienThiMain) 
	{
		this.hienThiMain = hienThiMain;
	}
	
	public boolean taoKetNoi(String svHost, int svPort)
	{
		this.svHost = svHost;
		this.svPort = svPort;
		
		try
		{ 
			socket = new Socket(svHost, svPort);
			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(),	hienThiMain.kieuDuLieu));
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), hienThiMain.kieuDuLieu));
			JOptionPane.showMessageDialog(null, "Kết nối thành công");
			this.start();
			return true;
		}
		catch(UnknownHostException ex)
		{
			JOptionPane.showMessageDialog(null, "Mời nhập lại", "Lỗi kết nối", JOptionPane.ERROR_MESSAGE);
		}
		catch(IOException ex)
		{
			JOptionPane.showMessageDialog(null, "Mời nhập lại", "Lỗi kết nối", JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}
	
	public void guiTinDenServer(String tinNhan)
	{
		try
		{
			System.out.println("gui: "+tinNhan);
			bufferedWriter.write(tinNhan);
			bufferedWriter.newLine();
			bufferedWriter.flush();
		}
		catch(UnknownHostException ex)
		{
			hienThiMain.ketNoiLai();
		}
		catch(IOException ex)
		{
			hienThiMain.ketNoiLai();
		}
	}
	
	
	public void run()
	{
		try
			{
				while(true)
				{
					if((tinNhanTuServer = bufferedReader.readLine())!=null)
					{
						hienThiMain.xuLiTinNhan(tinNhanTuServer);
					}
				}
			}
			catch(UnknownHostException ex)
			{
				hienThiMain.ketNoiLai();
			}
			catch(IOException ex)
			{
				hienThiMain.ketNoiLai();
			}
		
		
	}
}
