package client.giaodien;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import client.main.MainClient;


public class GiaoDienChatRoom extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JTextArea tinNhanDen;
	JTextField tinNhanDi;
	String actionSend = "send";
	MainClient mainClient;
	JTextField idClient;
	Calendar calendar;
	String time;
	public GiaoDienChatRoom(MainClient main) {
		// TODO Auto-generated constructor stub
		super("Chat TSTT");
		mainClient=main;
		this.add(taoPanelChinh());
		this.setSize(700, 500);
		this.addWindowListener(main);
	}
	
	JPanel taoPanelChinh()
	{
		JPanel panel= new JPanel(new BorderLayout());
		panel.add(taoPanelCenter(), BorderLayout.CENTER);
		panel.add(taoPanelChat(), BorderLayout.SOUTH);
		panel.add(taoPanelTop(), BorderLayout.NORTH);
		return panel;
	}
	
	JPanel taoPanelTop()
	{
		JPanel panel = new JPanel();
		panel.add(taoNhan("Gui tin nhan den server: "));
		return panel;
	}
	
	JPanel taoPanelCenter()
	{
		JPanel panel = new JPanel();
		tinNhanDen = new JTextArea(30, 50);
		panel.add(tinNhanDen);
		return panel;
	}
	
	JPanel taoPanelChat()
	{
		JPanel panel = new JPanel();
		tinNhanDi = new JTextField(20);
		panel.add(taoNhan("Tin nhan: "));
		panel.add(tinNhanDi);
		panel.add(taoNut("Gui", actionSend));
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
	
	public void hienThi()
	{
		this.setVisible(true);
	}
	
	public void thoat()
	{
		this.setVisible(false);
	}
	
	public void hienThiTinNhan(String tinNhan)
	{
		//if(tinNhanDen.getText().length()>50)tinNhanDen.setText("");
		tinNhanDen.setText(tinNhanDen.getText()+"\n"+tinNhan);
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		// TODO Auto-generated method stub
		if(ev.getActionCommand().equals(actionSend))
		{
			mainClient.guiTinNhan(tinNhanDi.getText());
			tinNhanDen.setText(tinNhanDen.getText()+"\nBan: "+tinNhanDi.getText());
			tinNhanDi.setText("");
		}
	}
	

}
