package View;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class ViewPadrao extends JFrame{

	public ViewPadrao() {
		
		this.setSize(1000, 700);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.NORMAL);
		this.getContentPane().setBackground(Color.PINK);
		ImageIcon logoLoja = new ImageIcon("images/A3BB1CBD-0992-4041-B1A1-1E085460F0D0.png");
		this.setIconImage(logoLoja.getImage());
		this.getContentPane().setLayout(null);
		
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {}	
	}
}
