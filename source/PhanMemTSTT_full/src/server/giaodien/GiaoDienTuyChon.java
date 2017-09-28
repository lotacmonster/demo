package server.giaodien;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GiaoDienTuyChon extends Frame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GiaoDienChinh giaoDienChinh;
	JTextField nhapNguoiChoi, nhapID, nhapPass;
	JTextField nhapIDHienThi, nhapPassHienThi;
	JTextField nhapLinkFileLog;
	JButton nutNguoiChoi, nutHienThi, nutFile;
	String[] thuTu = {"user 1", "user 2", "user 3", "user 4"};
	JComboBox comboBox;
	public GiaoDienTuyChon(GiaoDienChinh giaoDienChinh) 
	{
		// TODO Auto-generated constructor stub
		super("Cài đặt:");
		this.giaoDienChinh = giaoDienChinh;
		this.setSize(500, 600);
		this.addWindowListener(new Close(this, giaoDienChinh));
		this.add(taoPanelChinh());
	}

	JPanel taoPanelChinh()
	{
		JPanel panel = new JPanel(new GridLayout(2, 1));
		panel.add(taoPanel());
		panel.add(taoPanelFile());
		return panel;
	}
	
	JPanel taoPanel()
	{
		JPanel panel = new JPanel(new GridLayout(1, 2));
		panel.add(taoPanelTaiKhoan());
		panel.add(taoHienThi());
		return panel;
	}
	
	JPanel taoPanelTaiKhoan()
	{
		JPanel panel = new JPanel(new FlowLayout());
		comboBox = new JComboBox(thuTu);
		//comboBox.setBounds(30, 10, 40, 10);
		comboBox.addActionListener(this);
		panel.add(comboBox);
		
		nhapNguoiChoi = new JTextField(giaoDienChinh.mainServer.tenNguoiChoi[0]);
		nhapID = new JTextField(giaoDienChinh.mainServer.idDangNhap[0]);
		nhapPass = new JTextField(giaoDienChinh.mainServer.passDangNhap[0]);
		nutNguoiChoi = new JButton("Chỉnh sửa");
		nutNguoiChoi.addActionListener(this);
		
		panel.add(nhapNguoiChoi);
		panel.add(nhapID);
		panel.add(nhapPass);
		panel.add(nutNguoiChoi);
		
		return panel;
	}
	
	JPanel taoHienThi()
	{
		JPanel panel = new JPanel(new FlowLayout());
		nhapIDHienThi = new JTextField(giaoDienChinh.mainServer.idHienThi);
		nhapPassHienThi = new JTextField(giaoDienChinh.mainServer.passHienThi);
		nutHienThi = new JButton("Chỉnh sửa hiển thị");
			
		nutHienThi.addActionListener(this);
		panel.add(nhapIDHienThi);
		panel.add(nhapPassHienThi);
		panel.add(nutHienThi);
		return panel;
	}
	
	JPanel taoPanelFile()
	{
		JPanel panel = new JPanel(new FlowLayout());
		
		nhapLinkFileLog = new JTextField(giaoDienChinh.mainServer.linkFile);
		nutFile = new JButton("Tạo lại file");
		
		nutFile.addActionListener(this);
		
		panel.add(nhapLinkFileLog);
		panel.add(nutFile);
		return panel;
	}
	/*
	 * 
	 */
	
	
	/*
	 * 
	 */
	public void hienThi()
	{
		this.nhapLinkFileLog.setText(giaoDienChinh.mainServer.linkFile);
		this.setVisible(true);
	}
	
	public void anDi()
	{
		this.setVisible(false);
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == comboBox)
		{
			nhapNguoiChoi.setText(giaoDienChinh.mainServer.tenNguoiChoi[comboBox.getSelectedIndex()]);
			nhapID.setText(giaoDienChinh.mainServer.idDangNhap[comboBox.getSelectedIndex()]);
			nhapPass.setText(giaoDienChinh.mainServer.passDangNhap[comboBox.getSelectedIndex()]);
			return ;
		}
		
		if(e.getSource() == nutNguoiChoi)
		{
			giaoDienChinh.mainServer.tenNguoiChoi[comboBox.getSelectedIndex()] = nhapNguoiChoi.getText();
			giaoDienChinh.mainServer.idDangNhap[comboBox.getSelectedIndex()] = nhapID.getText();
			giaoDienChinh.mainServer.passDangNhap[comboBox.getSelectedIndex()] = nhapPass.getText();
			giaoDienChinh.hienThiNguoiChuoi();
			return ;
		}
		
		if(e.getSource() == nutHienThi)
		{
			giaoDienChinh.mainServer.idHienThi = nhapIDHienThi.getText();
			giaoDienChinh.mainServer.passHienThi = nhapPassHienThi.getText();
			return ;
		}
		
		if(e.getSource() == nutFile)
		{
			giaoDienChinh.mainServer.linkFile = nhapLinkFileLog.getText();
			giaoDienChinh.mainServer.taoLinkFile();
		}
	}
}

class Close extends WindowAdapter
{
	GiaoDienTuyChon giaoDienTuyChon;
	GiaoDienChinh giaoDienChinh;
	public Close(GiaoDienTuyChon giaoDienTuyChon, GiaoDienChinh giaoDienChinh)
	{
		this.giaoDienTuyChon = giaoDienTuyChon;
		this.giaoDienChinh = giaoDienChinh;
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		giaoDienTuyChon.anDi();
		giaoDienChinh.hienThi();
	}
}