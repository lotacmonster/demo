package server.giaodien;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import server.main.MainServer;
import server.socket.TaoServer;

public class GiaoDienChinh extends Frame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public MainServer mainServer;
	JTextArea hienThiTraLoi;
	JTextArea hienThiMayKetNoi;
	JTextField thietLapThoiGian;
	JLabel nguoiChoi1, nguoiChoi2, nguoiChoi3, nguoiChoi4;
	String actionChay = "run", actionCancel = "cancel", actionReset = "reset", actionDiem = "setdiem";
	JLabel thoiGian;
	JScrollPane cuonHienThiTraLoi, cuonHienThiMay;
	Border border = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 10);
	MenuBar menuBar = new MenuBar();
	Menu menuCaiDat, menuHelp;
	
	MenuItem itemUser, itemExit, itemHelp;
	public GiaoDienTuyChon giaoDienTuyChon; 
	int phut=0, giay=0;
	boolean daBatDau = false, daKetThuc = false, daReset = true;

	int thoiGianChay = 0;
	Timer timer;
	Image iconMain;
	
	public GiaoDienChinh(MainServer mainServer) 
	{
		// TODO Auto-generated constructor stub
		super("Thắp sáng tri thức - Server");
		this.mainServer = mainServer;
		this.taoMenuBar();
		this.setMenuBar(menuBar);
		this.add(taoPanelChinh());
		this.setSize(1000, 700);
		this.addWindowListener(mainServer);
		this.setIconImage(mainServer.iconChinh);
	}

	void taoMenuBar()
	{
		menuCaiDat = new Menu("Cài đặt");
		menuHelp = new Menu("Help?");
		menuBar.add(menuCaiDat);
		menuBar.add(menuHelp);
		
		itemUser = new MenuItem("Client-User");
		itemExit = new MenuItem("Exit");
		itemHelp = new MenuItem("Help");
		itemUser.addActionListener(this);
		itemExit.addActionListener(this);
		itemHelp.addActionListener(this);
		
		menuHelp.add(itemHelp);
		menuCaiDat.add(itemUser);
		menuCaiDat.add(itemExit);
	}
	JPanel taoPanelChinh()
	{
		JPanel panel = new JPanel(new BorderLayout(70, 70));
		panel.add(taoPanelCenter(), BorderLayout.CENTER);
		panel.add(taoPanelDiem(), BorderLayout.NORTH);
		return panel;
	}
	
	/*
	 * 
	 * panel để cài đặt thanh set điểm
	 */
	JPanel taoPanelDiem()
	{
		JPanel panel = new JPanel(new GridLayout(2, 4));
		panel.add(nguoiChoi1 = new JLabel(" "+mainServer.idDangNhap[0] + " = " + mainServer.tenNguoiChoi[0]));
		panel.add(nguoiChoi2 = new JLabel(" "+mainServer.idDangNhap[1] + " = " + mainServer.tenNguoiChoi[1]));
		panel.add(nguoiChoi3 = new JLabel(" "+mainServer.idDangNhap[2] + " = " +mainServer.tenNguoiChoi[2]));
		panel.add(nguoiChoi4 = new JLabel(" "+mainServer.idDangNhap[3] + " = " + mainServer.tenNguoiChoi[3]));
		nguoiChoi1.setFont(new Font("Arial", 3, 20));
		nguoiChoi2.setFont(new Font("Arial", 3, 20));
		nguoiChoi3.setFont(new Font("Arial", 3, 20));
		nguoiChoi4.setFont(new Font("Arial", 3, 20));

		return panel;
	}
	
	
	JPanel taoPanelCenter()
	{
		JPanel panel = new JPanel(new GridLayout(1, 2));
		panel.add(taoPanelTrai());
		panel.add(taoPanelPhai());
		return panel;
	}
	
	JPanel taoPanelPhai()
	{
		JPanel panel = new JPanel(new BorderLayout());
		hienThiTraLoi = new JTextArea();
		hienThiTraLoi.setFont(new Font("Arial", 0, 15));
		cuonHienThiTraLoi = new JScrollPane(hienThiTraLoi);
		panel.setBorder(border);
		panel.add(cuonHienThiTraLoi, BorderLayout.CENTER);
		panel.add(cuonHienThiTraLoi.getVerticalScrollBar(), BorderLayout.EAST);
		panel.add(cuonHienThiTraLoi.getHorizontalScrollBar(), BorderLayout.SOUTH);
		return panel;
	}
	
	
	JPanel taoPanelTrai()
	{
		JPanel panel = new JPanel(new GridLayout(2, 1));
		
		JPanel panelHienThiMay = new JPanel(new BorderLayout());
		
		/*
		 
		  */
		hienThiMayKetNoi = new JTextArea("Chưa có máy kết nối...");
		hienThiMayKetNoi.setFont(new Font("Times New Roman", 0, 15));
		cuonHienThiMay = new JScrollPane(hienThiMayKetNoi);
		panelHienThiMay.add(cuonHienThiMay, BorderLayout.CENTER);
		panelHienThiMay.add(cuonHienThiMay.getVerticalScrollBar(), BorderLayout.EAST);
		
		/*
		 * 
		 */
		panel.add(panelHienThiMay);
		panel.add(taoPanelThoiGian());
		return panel;
	}
	
	JPanel taoPanelThoiGian()
	{
		JPanel panel = new JPanel(new GridLayout(1, 2));
		
		JPanel panelTrai = new JPanel(new GridLayout(2, 1));
		thietLapThoiGian = new JTextField();
		thietLapThoiGian.setBorder(border);
		thietLapThoiGian.setActionCommand(actionChay);
		thietLapThoiGian.setText("10");
		thietLapThoiGian.addActionListener(this);
		thietLapThoiGian.setFont(new Font("Arial", Font.ITALIC, 50));
		
		thoiGian = new JLabel(" 00 ");
		thoiGian.setSize(300, 20);
		thoiGian.setBorder(border);
		thoiGian.setFont(new Font("Times New Roman", Font.BOLD , 50));
		panelTrai.setBorder(border);
		panelTrai.add(thoiGian);
		panelTrai.add(thietLapThoiGian);
		
		panel.add(panelTrai);
		
		JPanel panelPhai = new JPanel(new GridLayout(3, 1));
		panelPhai.setBorder(border);
		panelPhai.add(taoNut("Bắt Đầu.", actionChay));
		panelPhai.add(taoNut("Kết thúc", actionCancel));
		panelPhai.add(taoNut("Reset", actionReset));
		panel.add(panelPhai);
		
		return panel;
	}
	
	JLabel taoNhan(String ten)
	{
		JLabel label = new JLabel(ten);
		return label;
	}
	
	JButton taoNut(String tenNut, String action)
	{
		JButton nut = new JButton(tenNut);
		nut.setActionCommand(action);
		nut.addActionListener(this);
		return nut;
	}
	
	/*
	 * 
	 */
	
	public void hienThiTrangThaiMay(String tinNhan)
	{
		hienThiMayKetNoi.setText(tinNhan);
	}
	
	public void hienThiTraLoi(String tinNhan)
	{
		hienThiTraLoi.append(tinNhan);
	}
	
	public void xoaTraLoi()
	{
		hienThiTraLoi.setText("");
	}
	
	public void hienThiNguoiChuoi()
	{
		nguoiChoi1.setText(" "+mainServer.idDangNhap[0] + " = " + mainServer.tenNguoiChoi[0]);
		nguoiChoi2.setText(" "+mainServer.idDangNhap[1] + " = " + mainServer.tenNguoiChoi[1]);
		nguoiChoi3.setText(" "+mainServer.idDangNhap[2] + " = " + mainServer.tenNguoiChoi[2]);
		nguoiChoi4.setText(" "+mainServer.idDangNhap[3] + " = " + mainServer.tenNguoiChoi[3]);		
	}
	
	/*
	 * 
	 */
	public void hienThi()
	{
		this.setVisible(true);
	}
	
	public void anDi()
	{
		this.setVisible(false);
	}
	
	// xử lí đồng hồ
	
	private void chayDongHo(int thoiGian)
	{
		this.tangThoiGian();
		if(thoiGian == 0)return ;
		timer = new Timer();
		timer.purge();
		timer.schedule(new DongHoSuKien(this, thoiGian), 1000, 1000);
		
	}
	
	public void tangThoiGian()
	{
		giay++;
		
		String time=String.valueOf(giay);
		thoiGian.setText(time);
		if(phut == 0 && giay == 0) mainServer.guiTinNhanDenToanBo("Bat Dau: ");
		else mainServer.guiTinNhanDenToanBo("Thoi gian: " + time);
	}
	 
	public void inKetQua()
	{
		mainServer.guiTinNhanDenToanBo("Ket thuc: ");
		mainServer.guiBangRank();
	}
	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals(actionChay) && daReset)
		{
			daBatDau = true;
			daReset = false;
			daKetThuc = false;
			mainServer.taoThoiGianBatDau();
			if(thoiGianChay == 0)
				{
					Matcher matcher = Pattern.compile("\\d*").matcher(thietLapThoiGian.getText());
					if(matcher.matches())
					{
						thoiGianChay = Integer.parseInt(thietLapThoiGian.getText());
						phut = 0;
						giay = -1;
					}
					else 
					{
						JOptionPane.showMessageDialog(null, "Nhập lại thời gian");
						return ;
					}
				}
			
			if(thoiGianChay < 0)
				{
					thoiGianChay = 0;
				}
			chayDongHo(thoiGianChay);
			thoiGianChay = 0;
		}
		
		if(e.getActionCommand().equals(actionReset)&& daKetThuc)
		{
			daBatDau = false;
			daKetThuc = false;
			daReset = true;
			thoiGian.setText(" 00 ");
			mainServer.guiTinNhanDenToanBo("Reset: ");
			mainServer.resetBangRank();
		}
		
		if(e.getActionCommand().equals(actionCancel) && daBatDau)
		{
			daKetThuc = true;
			daReset = false;
			daBatDau = false;
			thoiGian.setText(thoiGian.getText()+"  Gián đoạn");
			timer.cancel();
			mainServer.guiTinNhanDenToanBo("Ket thuc: ");
		}
		
		if(e.getSource() == itemExit)
		{
			mainServer.exitChuongTrinh();
		}
		
		
		if(e.getSource() == itemUser)
		{
			this.anDi();
			giaoDienTuyChon= new GiaoDienTuyChon(this);
			giaoDienTuyChon.hienThi();
			return ;
		}
		
		if(e.getActionCommand().equalsIgnoreCase("Help"))
		{
			JOptionPane.showMessageDialog(null, "Mọi thắc mắc xin liên hệ: nc.dinh15t2@gmail.com");
			return ;
		}
		
		
	}
	
}



class DongHoSuKien extends TimerTask
{
	GiaoDienChinh giaoDienChinh;
	int tongThoiGian = 0;
	int demThoiGian;
	public DongHoSuKien(GiaoDienChinh giaoDienChinh, int tongThoiGian)
	{
		this.giaoDienChinh = giaoDienChinh;
		this.tongThoiGian = tongThoiGian;
		this.demThoiGian = 0;
	}
	
	
	@Override
	public void run()
	{
		demThoiGian++;
		Calendar calendar = Calendar.getInstance();
		System.out.println(calendar.get(Calendar.SECOND) + " " + calendar.get(Calendar.MILLISECOND));
		giaoDienChinh.tangThoiGian();
		if(demThoiGian >= tongThoiGian)
			{
				this.cancel();
				giaoDienChinh.inKetQua();
			}
		
		//if(giaoDienChinh.ngatThoiGian == false)this.cancel();
	}
}
