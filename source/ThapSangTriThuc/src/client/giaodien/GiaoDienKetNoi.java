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

public class GiaoDienKetNoi extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1776680328259828704L;
	JTextField nhapHost, nhapPort;
	String actionConnect = "ketnoi";
	MainClient mainClient;
	public GiaoDienKetNoi(MainClient main) {
		// TODO Auto-generated constructor stub
		super("Ket noi may chu");
		this.add(taoPanelChinh());
		this.setSize(350, 300);
		this.mainClient=main;
		this.addWindowListener(main);
	}
	
	public void hienThi()
	{
		this.setVisible(true);
	}
	
	public void thoat()
	{
		this.dispose();
	}
	
	JPanel taoPanelChinh()
	{
		JPanel panel = new JPanel(new GridLayout(4, 1));
		panel.add(taoPanelTieuDe());
		panel.add(taoPanelHost());
		panel.add(taoPanelPort());
		panel.add(taoPanelConnect());
		return panel;
	}
	
	JPanel taoPanelTieuDe()
	{
		JPanel panel = new JPanel();
		panel.add(taoNhan("Nhap Host Sever:"));
		return panel;
	}
	
	JPanel taoPanelHost()
	{
		JPanel panel = new JPanel(new GridLayout(1, 2));
		panel.add(taoNhan("Nhap host sever: "));
		nhapHost = new JTextField(20);
		panel.add(nhapHost);
		return panel;
	}
	
	JPanel taoPanelPort()
	{
		JPanel panel = new JPanel(new GridLayout(1,	2));
		panel.add(taoNhan("Nhap port: "));
		nhapPort = new JTextField(4);
		panel.add(nhapPort);
		return panel;
	}
	
	JPanel taoPanelConnect()
	{
		JPanel panel = new JPanel();
		panel.add(taoNut("Ket noi.", actionConnect));
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
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		// TODO Auto-generated method stub
		if(ev.getActionCommand().equals(actionConnect))
		{
			//JOptionPane.showMessageDialog(this, "Dang ket noi" , "Thong bao", JOptionPane.CLOSED_OPTION);
			mainClient.ketNoi(nhapHost.getText(), Integer.parseInt(nhapPort.getText()));
		}
	}
} 