package clienthienthi.giaodien;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import clienthienthi.main.HienThiMain;

public class GiaoDienHienThi extends Frame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HienThiMain hienThiMain;
	String user =  "  #USER";
	String time00 = " 00 ";
	String answer = "   ANSWER";
	JLabel tenNguoi1 =new JLabel(user), tenNguoi2 = new JLabel(user), tenNguoi3 = new JLabel(user), tenNguoi4 = new JLabel(user);
	JLabel thoiGian1 = new JLabel(time00), thoiGian2 = new JLabel(time00), thoiGian3 = new JLabel(time00), thoiGian4 = new JLabel(time00);
	JLabel dapAn1 = new JLabel(answer), dapAn2 = new JLabel(answer), dapAn3 = new JLabel(answer), dapAn4 = new JLabel(answer);
	JLabel thoiGian, nhanAnh;
	Border borderTenNguoi = BorderFactory.createLineBorder(Color.BLACK, 5);
	Border borderThoiGian = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 5);
	Border borderDongHo = BorderFactory.createLineBorder(Color.BLUE, 10);
	Border borderAnh = BorderFactory.createLineBorder(Color.ORANGE, 15);
	int chieuRong, chieuCao;
	
	public GiaoDienHienThi(HienThiMain hienThiMain) 
	{
		super("Thắp Sáng Tri Thức - Hiển Thị.");
		this.hienThiMain = hienThiMain;
		this.setSize(1000, 700);
		this.setIconImage(hienThiMain.iconChinh);
		this.addWindowListener(hienThiMain);
		this.add(taoPanelChinh());
		//this.setBackground(Color.GRAY);
	}
	
	
	JPanel taoPanelChinh()
	{
		JPanel panel = new JPanel(new BorderLayout(0, 10));
		panel.add(taoPanelTrai(), BorderLayout.WEST);
		panel.add(taoPanelCenter(), BorderLayout.CENTER);
		return panel;
	}
	
	JPanel taoPanelTrai()
	{
		JPanel panel = new JPanel(new GridLayout(2, 1));
		
		//chieuRong = Math.min(this.getHeight()*9/20, this.getHeight()/5);
		nhanAnh = new JLabel();
		nhanAnh.setIcon(new ImageIcon(hienThiMain.iconChinh.getScaledInstance(300, 300, Image.SCALE_SMOOTH)));
		nhanAnh.setBorder(borderAnh);
		panel.add(nhanAnh);
		
		thoiGian = new JLabel(" 00 ");
		thoiGian.setBorder(borderDongHo);
		thoiGian.setFont(new Font("Arial", Font.BOLD, 200));
		panel.add(thoiGian);
		
		return panel;
	}
	
	JPanel taoPanelCenter()
	{
		JPanel panel = new JPanel(new GridLayout(4, 1));
		
		panel.add(taoPanelCon(tenNguoi1, thoiGian1, dapAn1));
		panel.add(taoPanelCon(tenNguoi2, thoiGian2, dapAn2));
		panel.add(taoPanelCon(tenNguoi3, thoiGian3, dapAn3));
		panel.add(taoPanelCon(tenNguoi4, thoiGian4, dapAn4));
		panel.setBorder(borderTenNguoi);
		return panel;
	}
	
	
	JPanel taoPanelCon(JLabel tenNguoi, JLabel thoiGian, JLabel dapAn)
	{
		JPanel panel = new JPanel(new BorderLayout());
		
		tenNguoi.setFont(new Font("Times New Roman", Font.ITALIC, 30));
		tenNguoi.setBorder(borderTenNguoi);
		panel.add(tenNguoi, BorderLayout.NORTH);
		
		thoiGian.setFont(new Font("Arial", Font.BOLD, 35));
		thoiGian.setBorder(borderThoiGian);
		panel.add(thoiGian, BorderLayout.WEST);
		
		dapAn.setFont(new Font("Times New Roman", 3, 25));
		dapAn.setBorder(borderThoiGian);
		panel.add(dapAn, BorderLayout.CENTER);
		
		panel.setBorder(borderTenNguoi);
		return panel;
	}
	
	
	/*
	 * hàm xử lí
	 */
	public void hienThiThoiGian(String thoiGian)
	{
		this.thoiGian.setText(thoiGian);
	}
	
	public void resetNhan()
	{
		hienThiNhan1(" #USER NAME 1", " 00 ", " ANSWER...");
		hienThiNhan2(" #USER NAME 2", " 00 ", " ANSWER...");
		hienThiNhan3(" #USER NAME 3", " 00 ", " ANSWER...");
		hienThiNhan4(" #USER NAME 4", " 00 ", " ANSWER...");
		hienThiThoiGian(" 00 ");
	}
	
	
	public void hienThiNhan1(String tenNguoi, String thoiGian, String dapAn)
	{
		tenNguoi1.setText("  "+tenNguoi);
		thoiGian1.setText(" " +thoiGian);
		dapAn1.setText("  "+dapAn);
	}
	
	public void hienThiNhan2(String tenNguoi, String thoiGian, String dapAn)
	{
		tenNguoi2.setText("  " +tenNguoi);
		thoiGian2.setText(" " +thoiGian);
		dapAn2.setText("  " +dapAn);
	}
	
	public void hienThiNhan3(String tenNguoi, String thoiGian, String dapAn)
	{
		tenNguoi3.setText("  " +tenNguoi);
		thoiGian3.setText(" " +thoiGian);
		dapAn3.setText("  " +dapAn);
	}
	
	public void hienThiNhan4(String tenNguoi, String thoiGian, String dapAn)
	{
		tenNguoi4.setText("  " +tenNguoi);
		thoiGian4.setText(" " +thoiGian);
		dapAn4.setText("  " +dapAn);
	}
	/*
	 * hàm hiển thị
	 */
	public void hienThi()
	{
		this.setVisible(true);
	}
	
	public void anDi()
	{
		this.setVisible(false);
	}

}
