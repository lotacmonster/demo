package clienthienthi.giaodien;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import clienthienthi.main.HienThiMain;

public class GiaoDienDangNhap extends Frame implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HienThiMain hienThiMain;
	JTextField nhapID, nhapPass;
	
	String actionDangNhap = "dangnhap";
	
	public GiaoDienDangNhap(HienThiMain hienThiMain) 
	{
		super("Thắp sáng tri thức - Hiển thị");
		this.setSize(300, 200);
		this.hienThiMain = hienThiMain;
		this.addWindowListener(hienThiMain);
		this.add(taoPanelChinh());
		this.setIconImage(hienThiMain.iconChinh);
	}

	JPanel taoPanelChinh()
	{
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(taoPanelTop(), BorderLayout.NORTH);
		panel.add(taoPanelBot(), BorderLayout.SOUTH);
		panel.add(taoPanelTrai(), BorderLayout.WEST);
		panel.add(taoPanelCenter(), BorderLayout.CENTER);
		return panel;
	}
	
	JPanel taoPanelTop()
	{
		JPanel panel = new JPanel();
		panel.add(taoNhan("Đăng nhập tài khoản máy chủ: "));
		return panel;
	}
	
	JPanel taoPanelBot()
	{
		JPanel panel = new JPanel();
		panel.add(taoNut("Đăng nhập.", actionDangNhap));
		return panel;
	}
	
	JPanel taoPanelTrai()
	{
		JPanel panel = new JPanel(new GridLayout(2, 1));
		panel.add(taoNhan("Nhập ID: "));
		panel.add(taoNhan("Nhập Pass: "));
		return panel;
	}
	
	JPanel taoPanelCenter()
	{
		JPanel panel = new JPanel(new GridLayout(2, 1));
		
		nhapID = new JTextField();
		nhapPass = new JTextField();
		panel.add(nhapID);
		panel.add(nhapPass);
		return panel;
	}
	///////
	JLabel taoNhan(String tenNhan)
	{
		JLabel label = new JLabel(tenNhan);
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
	 * Khối lênh hiển thị
	 */
	public void hienThi()
	{
		this.setVisible(true);
	}
	
	public void anDi()
	{
		this.setVisible(false);
	}
	
	/*
	 * khối lệnh bắt sự kiện
	 */
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals(actionDangNhap))
		{
			hienThiMain.dangNhap(nhapID.getText(), nhapPass.getText());
			return ;
		}
		
	}
	

}
