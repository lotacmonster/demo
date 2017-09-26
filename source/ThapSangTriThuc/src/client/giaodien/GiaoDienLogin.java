package client.giaodien;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import client.main.MainClient;

public class GiaoDienLogin extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField nhapID, nhapPass;
	String actionLogin="login";
	MainClient mainClient;
	public GiaoDienLogin(MainClient main) {
		// TODO Auto-generated constructor stub
		super("TSTT");
		this.add(taoPanelChinh());
		this.setSize(350, 300);
		this.mainClient=main;
		this.addWindowListener(main);
	}
	
	JPanel taoPanelChinh()
	{
		JPanel panel = new JPanel(new GridLayout(4 , 1));
		panel.add(taoPanelTieuDe());
		panel.add(taoPanelID());
		panel.add(taoPanelPass());
		panel.add(taoPanelLogin());
		return panel;
	}
	
	JPanel taoPanelTieuDe()
	{
		JPanel panel = new JPanel();
		panel.add(taoNhan("Moi dang nhap:"));
		return panel;
	}
	
	JPanel taoPanelID()
	{
		JPanel panel = new JPanel(new GridLayout(1, 2));
		panel.add(taoNhan("Nhap ID: "));
		nhapID = new JTextField(10);
		panel.add(nhapID);
		return panel;
	}
	
	JPanel taoPanelPass()
	{
		JPanel panel = new JPanel(new GridLayout(1, 2));
		panel.add(taoNhan("Nhap Pass: "));
		nhapPass = new JTextField(10);
		panel.add(nhapPass);
		return panel;
	}
	
	
	JPanel taoPanelLogin()
	{
		JPanel panel = new JPanel();
		panel.add(taoNut("Login", actionLogin));
		return panel;
	}
	
	private JLabel taoNhan(String tenNhan)
	{
		JLabel label= new JLabel(tenNhan);
		return label;
	}
	
	private JButton taoNut(String tenNut, String actionButton)
	{
		JButton button = new JButton(tenNut);
		button.setActionCommand(actionButton);
		button.addActionListener(this);
		return button;
	}
	
	
	public void hienThi()
	{
		this.setVisible(true);
	}
	
	public void thoat()
	{
		this.setVisible(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals(actionLogin))
		{
			mainClient.dangNhap(nhapID.getText(), nhapPass.getText());
		}

	}
}
