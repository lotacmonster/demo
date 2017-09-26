package client.giaodien;

import javax.swing.JFrame;

import client.main.MainClient;

public class GiaoDienDangNhap extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MainClient mainClient;
	public GiaoDienDangNhap(MainClient mainClient) {
		// TODO Auto-generated constructor stub
		super("Thắp sáng tri thức - Client");
		this.mainClient = mainClient;
		this.setSize(300, 200);
		this.addWindowListener(mainClient);
	}

	public void hienThi()
	{
		this.setVisible(true);
	}
	
	public void anDi()
	{
		this.setVisible(false);
	}
}
