package client.giaodien;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import client.main.MainClient;

public class GiaoDienVongChoi extends Frame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MainClient mainClient;
	JLabel nhanTenNguoiChoi, nhanThoiGian, nhanDiemSo;
	int kichCoTen = 23, kichCoThoiGian = 23, kichCoDiemSo = 23, kichCoNhap = 20, kichCoHienThi = 18;
	String font = "Arial";
	JTextField nhapDapAn;
	JTextArea hienThiDapAn;
	JButton guiDapAn;
	Border border = BorderFactory.createLineBorder(Color.ORANGE, 5);
	Border border2 = BorderFactory.createLineBorder(Color.DARK_GRAY, 20);
	String actionGui = "guidapan";
	public String dapAnTruoc = "";
	public boolean checkGuiDapAn;
	
	public GiaoDienVongChoi(MainClient mainClient) {
		super("Thắp Sáng Tri Thức - Client");
		this.mainClient = mainClient;
		this.addWindowListener(mainClient);
		this.setSize(1000, 600);
		this.addWindowListener(mainClient);
		this.add(taoPanelChinh());
		this.setIconImage(mainClient.iconChinh);
		this.checkGuiDapAn = false;
	}

	JPanel taoPanelChinh()
	{
		JPanel panel = new JPanel(new BorderLayout(10, 10));
		panel.setBackground(Color.DARK_GRAY);
		panel.add(taoPanelTop(), BorderLayout.NORTH);
		panel.add(taoPanelCenter(), BorderLayout.CENTER);
		panel.add(taoPanelBot(), BorderLayout.SOUTH);
		return panel;
	}
	
	JPanel taoPanelTop()
	{
		JPanel panel = new JPanel(new GridLayout(1, 2));
		//panel.setBackground(Color.LIGHT_GRAY);
		nhanTenNguoiChoi = new JLabel("TÊN NGƯỜI CHƠI;");
		nhanTenNguoiChoi.setBorder(border);
		nhanTenNguoiChoi.setFont(new Font("Times New Roman", 3, kichCoTen));
		panel.add(nhanTenNguoiChoi);
		
		JPanel panelRight = new JPanel(new GridLayout(1, 2));
		nhanDiemSo = new JLabel("ĐIỂM: 000");
		nhanDiemSo.setFont(new Font(font, Font.BOLD, kichCoDiemSo));
		nhanThoiGian = new JLabel("THỜI GIAN: 00");
		nhanThoiGian.setFont(new Font(font, Font.BOLD, kichCoThoiGian));
	
		panelRight.add(nhanDiemSo);
		panelRight.add(nhanThoiGian);
		panelRight.setBorder(border);
		panel.add(panelRight);
		return panel;
	}
	
	JPanel taoPanelCenter()
	{
		JPanel panel = new JPanel(new BorderLayout());
		hienThiDapAn = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(hienThiDapAn);

		hienThiDapAn.setFont(new Font("Arial", 0, kichCoHienThi));
		panel.setBorder(border2);
		panel.add(scrollPane, BorderLayout.CENTER);
		panel.add(scrollPane.getVerticalScrollBar(), BorderLayout.EAST);
		panel.add(scrollPane.getHorizontalScrollBar(), BorderLayout.SOUTH);
		return panel;
	}
	
	JPanel taoPanelBot()
	{
		JPanel panel = new JPanel(new BorderLayout(10, 0));
		nhapDapAn = new JTextField();
		nhapDapAn.setFont(new Font(font, Font.ITALIC, kichCoNhap));
		nhapDapAn.setActionCommand(actionGui);
		nhapDapAn.addActionListener(this);
		
		panel.add(nhapDapAn, BorderLayout.CENTER);
		
		guiDapAn = new JButton("Gửi Đáp Án");
		guiDapAn.setActionCommand(actionGui);
		guiDapAn.addActionListener(this);
		guiDapAn.setFont(new Font(font, Font.BOLD, kichCoNhap));
		panel.add(guiDapAn, BorderLayout.EAST);
		panel.setBackground(Color.DARK_GRAY);
		panel.setBorder(border2);
		return panel;
	}
	
	
	/*
	 * các hàm hiển thị
	 */
	
	public void hienThiThoiGian(String thoiGian)
	{
		checkGuiDapAn = true;
		nhanThoiGian.setText("THỜI GIAN: "+thoiGian);
	}
	
	public void hienThiDapAn(String tinNhan)
	{
		hienThiDapAn.append("\n" + tinNhan);
	}
	
	public void hienThiTenNguoiChuoi(String tenNguoiChoi)
	{
		nhanTenNguoiChoi.setText(tenNguoiChoi);
	}
	
	public void hienThi()
	{
		this.setVisible(true);
	}
	
	public void anDi()
	{
		this.setVisible(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		// TODO Auto-generated method stub
		if(ev.getActionCommand().equals(actionGui))
		{
			if(checkGuiDapAn)
				{
					System.out.println("check true");
					if(!dapAnTruoc.equals(nhapDapAn.getText()))
					{
						dapAnTruoc = nhapDapAn.getText();
						mainClient.guiTinDenServer("Tra loi: " + dapAnTruoc);
					}
				}
			else System.out.println("check false");
			
			return ;
		}
	}
}
