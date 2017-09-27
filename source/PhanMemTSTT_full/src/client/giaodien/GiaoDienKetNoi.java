package client.giaodien;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import client.main.MainClient;
import client.socket.ThreadClient;


public class GiaoDienKetNoi extends Frame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MainClient mainClient;
	JTextField nhapPort, nhapHost;
	String actionKetNoi = "ketnoi";
	ThreadClient threadClient;
	
	public GiaoDienKetNoi(MainClient mainClient) 
	{
		super("Thắp sáng tri thức - Client");
		this.mainClient = mainClient;
		this.setSize(300, 200);
		this.add(taoPanelChinh());
		this.addWindowListener(mainClient);
		this.threadClient = mainClient.threadClient;
		this.setIconImage(mainClient.iconChinh);
	}

	// tạo giao diện
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
		panel.add(taoNhan("Kết nối đến máy chủ: "));
		return panel;
	}
	
	JPanel taoPanelBot()
	{
		JPanel panel = new JPanel();
		panel.add(taoNut("Kết Nối.", actionKetNoi));
		return panel;
	}
	
	JPanel taoPanelTrai()
	{
		JPanel panel = new JPanel(new GridLayout(2, 1));
		panel.add(taoNhan("Nhap Host: "));
		panel.add(taoNhan("Nhập Port: "));
		return panel;
	}
	
	JPanel taoPanelCenter()
	{
		JPanel panel = new JPanel(new GridLayout(2, 1));
		
		nhapHost = new JTextField();
		nhapPort = new JTextField();
		nhapHost.setText("localhost");
		nhapPort.setText("7777");
		panel.add(nhapHost);
		panel.add(nhapPort);
		
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
	public void hienThi()
	{
		this.setVisible(true);
	}
	/*******
	 * 
	 * 
	 *******/
	
	public void anDi()
	{
		this.setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals(actionKetNoi))
		{
			Matcher matcher = Pattern.compile("\\d*").matcher(nhapPort.getText());
			if(matcher.matches())
			{
				mainClient.ketNoiMayChu(nhapHost.getText(), Integer.parseInt(nhapPort.getText()));
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Nhập port chưa đúng định dạng");
			}
		}
	}
}
