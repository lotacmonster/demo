package sever.giaodien;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GiaoDienVongChoi extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField soMot, soHai, soBa, soBon;
	String actionReset="reset";
	String []listID = {"#1", "#2", "#3", "#4"};
	JLabel tenCauHoi;
	String cauHoiSo="Cau hoi: ";
	public GiaoDienVongChoi() {
		// TODO Auto-generated constructor stub
		//super("Vong choi");
		this.add(taoPanelChinh());
		this.setSize(400, 500);
		//this.setVisible(true);
	}
	
	
	JPanel taoPanelChinh()
	{
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(taoPanelTieuDe(), BorderLayout.NORTH);
		panel.add(taoPanelCenter(), BorderLayout.CENTER);
		panel.add(taoPanelReset(), BorderLayout.SOUTH);
		return panel;
	}
	
	JPanel taoPanelTieuDe()
	{
		JPanel panel = new JPanel();
		tenCauHoi = new JLabel(cauHoiSo);
		panel.add(tenCauHoi);
		return panel;
	}
	
	JPanel taoPanelCenter()
	{
		JPanel panel = new JPanel(new GridLayout(4, 1));
		soMot = new JTextField();
		soHai = new JTextField();
		soBa = new JTextField();
		soBon = new JTextField();
		panel.add(soMot);
		panel.add(soHai);
		panel.add(soBa);
		panel.add(soBon);
		return panel;
	}
	
	JPanel taoPanelReset()
	{
		JPanel panel = new JPanel();
		panel.add(taoNut("Reset", actionReset));
		return panel;
	}
	
	JButton taoNut(String tenNut, String action)
	{
		JButton nut= new JButton(tenNut);
		nut.setActionCommand(action);
		nut.addActionListener(this);
		return  nut;
	}
	
	JLabel taoNhan(String tenNhan)
	{
		JLabel label = new JLabel(tenNhan);
		label.setFont(new Font("Times New Roman", 1, 18));
		return label;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		// TODO Auto-generated method stub
		
	}
}
