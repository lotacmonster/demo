package sever.giaodien;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import sever.main.MainSever;

public class GiaoDienLogin extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField nhapPort;
	String actionConnect="ketnoi";
	MainSever mainSever;
	JTextField textPort= new JTextField();
	public GiaoDienLogin(MainSever main) {
		// TODO Auto-generated constructor stub
		this.add(taoPanelChinh());
		this.setSize(350,300);
		this.mainSever=main;
		this.addWindowListener(main);
		
		InetAddress inet;
		try
		{
			inet= InetAddress.getLocalHost();
			textPort.setText(inet.toString());
		}
		catch(UnknownHostException ex)
		{
			JOptionPane.showMessageDialog(null, "Khong tim thay IP may chu !");
			System.exit(1);
		}
		//this.setVisible(true);
	}
	
	public void hienThi()
	{
		this.setVisible(true);
	}
	
	public void thoat()
	{
		this.setVisible(false);
	}
	
	public void anFrame()
	{
		this.setVisible(false);
	}
	JPanel taoPanelChinh()
	{
		JPanel panel = new JPanel(new GridLayout(4, 1));
		panel.add(taoPanelTieuDe());
		panel.add(textPort);
		panel.add(taoPanelPort());
		panel.add(taoPanelConnect());
		return panel;
	}
	
	JPanel taoPanelTieuDe()
	{
		JPanel panel = new JPanel();
		panel.add(taoNhan("Nhap cong Port can tao: "));
		return panel;
	}
	
	JPanel taoPanelPort()
	{
		JPanel panel = new JPanel(new GridLayout(1, 2));
		panel.add(taoNhan("Nhap port: "));
		nhapPort = new JTextField(4);
		panel.add(nhapPort);
		return panel;
	}
	
	JPanel taoPanelConnect()
	{
		JPanel panel = new JPanel();
		panel.add(taoNut("Ket Noi", actionConnect));
		return panel;
	}
	
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
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		// TODO Auto-generated method stub
		if(ev.getActionCommand().equals(actionConnect))
		{
			mainSever.taoSocket(Integer.parseInt(nhapPort.getText()));
		}
	}
	
}
