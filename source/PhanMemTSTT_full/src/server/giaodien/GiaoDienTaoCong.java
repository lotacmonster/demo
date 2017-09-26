package server.giaodien;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import server.main.MainServer;

public class GiaoDienTaoCong extends JFrame implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MainServer mainServer;
	JTextField nhapPort;
	JButton nutTaoCong;
	String actionTaoCong = "taoCong";
	JLabel thongBao;
	
	public GiaoDienTaoCong(MainServer mainServer) 
	{
		// TODO Auto-generated constructor stub
		super("Thắp Sáng Tri Thức.");
		this.mainServer = mainServer;	
		this.add(taoPanelChinh());
		this.setSize(300, 200);
		this.addWindowListener(mainServer);
	} 
	
	JPanel taoPanelChinh()
	{
		JPanel panel = new JPanel(new GridLayout(3, 1));
		JLabel nhanTieuDe = new JLabel("Nhập cổng kết nối: ");
		nhapPort = new JTextField();
		nhapPort.addActionListener(this);
		panel.add(nhanTieuDe);
		panel.add(nhapPort);
		
		JPanel panelNut = new JPanel(new FlowLayout());
		nutTaoCong = new JButton("Tạo cổng.");
		nutTaoCong.setActionCommand(actionTaoCong);
		nutTaoCong.addActionListener(this);
		panelNut.add(nutTaoCong);
		thongBao = new JLabel("");
		panelNut.add(thongBao);
		
		panel.add(panelNut);
		return panel;
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
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		if(e.getSource()==nhapPort||e.getActionCommand().equals(actionTaoCong))
		{
			Matcher matcher = Pattern.compile("\\d*").matcher(nhapPort.getText());
			if(matcher.matches())
			{
				if(mainServer.taoServer(Integer.parseInt(nhapPort.getText())))
				{
					this.anDi();
					mainServer.hienThiGiaoDienChinh();
				}
			}
			else
			{
				thongBao.setText("Nhập sai port");
			}
		}
	}
}
