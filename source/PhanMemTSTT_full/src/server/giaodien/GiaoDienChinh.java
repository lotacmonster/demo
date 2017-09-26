package server.giaodien;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import server.main.MainServer;

public class GiaoDienChinh extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MainServer mainServer;
	JTextArea hienThiTraLoi;
	JTextArea hienThiMayKetNoi;
	JTextField thietLapThoiGian;
	String actionChay = "run", actionCancel = "cancel", actionTamDung = "tamdung";
	JLabel thoiGian;
	Border border = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
	JMenuBar menuBar = new JMenuBar();
	JMenu menuCaiDat, menuHelp;
	JMenuItem itemUser, itemPort, itemExit, itemHelp;
	GiaoDienTuyChon giaoDienTuyChon = new GiaoDienTuyChon(this);
	int phut=0, giay=0;
	boolean ngatThoiGian = false;
	int thoiGianChay = 0;
	
	public GiaoDienChinh(MainServer mainServer) 
	{
		// TODO Auto-generated constructor stub
		super("Thắp sáng tri thức.");
		this.mainServer = mainServer;
		this.taoMenuBar();
		this.setJMenuBar(menuBar);
		this.add(taoPanelChinh());
		this.setSize(1000, 700);
		this.addWindowListener(mainServer);
	}

	void taoMenuBar()
	{
		menuCaiDat = new JMenu("Cài đặt");
		menuHelp = new JMenu("Help?");
		menuBar.add(menuCaiDat);
		menuBar.add(menuHelp);
		
		itemUser = new JMenuItem("Client-User");
		itemPort = new JMenuItem("Tạo lại port");
		itemExit = new JMenuItem("Exit");
		itemHelp = new JMenuItem("Help");
		itemUser.addActionListener(this);
		itemPort.addActionListener(this);
		itemExit.addActionListener(this);
		itemHelp.addActionListener(this);
		
		menuHelp.add(itemHelp);
		menuCaiDat.add(itemUser);
		menuCaiDat.add(itemPort);
		menuCaiDat.add(itemExit);
	}
	JPanel taoPanelChinh()
	{
		JPanel panel = new JPanel(new GridLayout(1, 2));
		panel.add(taoPanelTrai());
		panel.add(taoPanelPhai());
		return panel;
	}
	
	JPanel taoPanelPhai()
	{
		JPanel panel = new JPanel(new BorderLayout(10, 10));
		hienThiTraLoi = new JTextArea();
		panel.setBorder(border);
		panel.add(hienThiTraLoi, BorderLayout.CENTER);
		return panel;
	}
	JPanel taoPanelTrai()
	{
		JPanel panel = new JPanel(new GridLayout(2, 1));
		hienThiMayKetNoi = new JTextArea("Chưa có máy kết nối...", 20, 20);
		hienThiMayKetNoi.setBorder(border);
		panel.add(hienThiMayKetNoi);
		panel.add(taoPanelThoiGian());
		return panel;
	}
	
	JPanel taoPanelThoiGian()
	{
		JPanel panel = new JPanel(new GridLayout(1, 2));
		
		JPanel panelTrai = new JPanel(new GridLayout(2, 1));
		thietLapThoiGian = new JTextField();
		thietLapThoiGian.setActionCommand(actionChay);
		thietLapThoiGian.setText("10");
		thietLapThoiGian.addActionListener(this);
		thoiGian = new JLabel("00 : 00");
		thoiGian.setAlignmentY(CENTER_ALIGNMENT);
		thoiGian.setSize(300, 20);
		
		panelTrai.setBorder(border);
		panelTrai.add(thoiGian);
		panelTrai.add(thietLapThoiGian);
		
		panel.add(panelTrai);
		
		JPanel panelPhai = new JPanel(new GridLayout(3, 1));
		panelPhai.add(taoNut("Bắt Đầu.", actionChay));
		panelPhai.add(taoNut("Tạm Dừng", actionTamDung));
		panelPhai.add(taoNut("Kết Thúc", actionCancel));
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
	
	
	public void hienThiTrangThaiMay(String tinNhan)
	{
		hienThiMayKetNoi.setText(tinNhan);
	}
	
	public void hienThiTraLoi(String tinNhan)
	{
		hienThiMayKetNoi.append("/n"+tinNhan);
	}
	
	public void xoaTraLoi()
	{
		hienThiTraLoi.setText("");
	}
	
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
		Timer timer = new Timer();
		timer.schedule(new DongHoSuKien(this, thoiGian), 1000, 1000);
	}
	
	public void tangThoiGian()
	{
		if(giay==60)
		{
			giay= 0;
			phut++;
		}
		else 
		{
			giay++;
		}
		
		String time="";
		time+=String.valueOf(phut/10);
		time+=String.valueOf(phut%10);
		time+=" : ";
		time+=String.valueOf(giay/10);
		time+=String.valueOf(giay%10);
		thoiGian.setText(time);
		mainServer.guiTinNhanDenToanBo("Thoi gian: " + time);
	}
	
	public void inKetQua()
	{
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals(actionChay))
		{
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
			ngatThoiGian = true;
			chayDongHo(thoiGianChay);
			thoiGianChay = 0;
		}
		
		if(e.getActionCommand().equals(actionCancel))
		{
			thoiGian.setText(thoiGian.getText()+"  Gián đoạn");
			ngatThoiGian = false;
		}
		
		if(e.getActionCommand().equals(actionTamDung))
		{
			JOptionPane.showMessageDialog(this, "Nút này chưa được cài đặt");
		}
		
		if(e.getSource() == itemExit)
		{
			mainServer.exitChuongTrinh();
		}
		
		if(e.getSource() == itemPort)
		{
			this.anDi();
			mainServer.batDau();
		}
		
		if(e.getSource() == itemUser)
		{
			this.anDi();
			giaoDienTuyChon.hienThi();
		}
		
		if(e.getActionCommand().equalsIgnoreCase("Help"))
		{
			JOptionPane.showMessageDialog(null, "Mọi thắc mắc xin liên hệ: nc.dinh15t2@gmail.com");
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
		giaoDienChinh.tangThoiGian();
		if(demThoiGian >= tongThoiGian)
			{
				this.cancel();
				giaoDienChinh.inKetQua();
			}
		if(giaoDienChinh.ngatThoiGian == false)this.cancel();
	}
}
