package server.giaodien;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class GiaoDienTuyChon extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GiaoDienChinh giaoDienChinh;
	public GiaoDienTuyChon(GiaoDienChinh giaoDienChinh) 
	{
		// TODO Auto-generated constructor stub
		super("Cài đặt:");
		this.giaoDienChinh = giaoDienChinh;
		this.setSize(500, 600);
		this.addWindowListener(new Close(this, giaoDienChinh));
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
		
	}
}

class Close extends WindowAdapter
{
	GiaoDienTuyChon giaoDienTuyChon;
	GiaoDienChinh giaoDienChinh;
	public Close(GiaoDienTuyChon giaoDienTuyChon, GiaoDienChinh giaoDienChinh)
	{
		this.giaoDienTuyChon = giaoDienTuyChon;
		this.giaoDienChinh = giaoDienChinh;
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		giaoDienTuyChon.anDi();
		giaoDienChinh.hienThi();
	}
}