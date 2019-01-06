

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Button;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Epoxy {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Epoxy window = new Epoxy();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Epoxy() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 1062, 406);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1046, 268);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		table = new JTable();
		table.setBounds(-487, -53, 1046, 176);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"\u5E8F\u53F7", "\u80F6\u6C34\u53F7", "\t\u80F6\u6C34\u578B\u53F7", "\u89E3\u51BB\u65F6\u95F4", "\u89E3\u51BB\u4EBA", "\u4E0A\u7EBF\u65F6\u95F4", "\u4E0A\u7EBF\u4EBA", "\u56DE\u6536\u622A\u6B62\u65F6\u95F4", "\u56DE\u6536\u5012\u8BA1\u65F6", "\u56DE\u6536\u4EBA"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(36);
		table.getColumnModel().getColumn(1).setPreferredWidth(169);
		table.getColumnModel().getColumn(1).setMinWidth(169);
		table.getColumnModel().getColumn(2).setPreferredWidth(67);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 10, 1026, 218);
		panel.add(scrollPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 267, 1046, 101);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton = new JButton("解冻胶水");
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 33));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(Cfg.input_Staff_num == null) {
					Cfg.input_Staff_num = new Input_Staff_Num(frame,"请扫描工�?",true);
					Cfg.input_Staff_num.setVisible(true);
				}else {
					Cfg.input_Staff_num.setVisible(true);
				}
				
			}
		});
		btnNewButton.setBounds(129, 10, 176, 77);
		panel_1.add(btnNewButton);
		
		JButton button = new JButton("胶水上线");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button.setFont(new Font("宋体", Font.PLAIN, 33));
		button.setBounds(434, 10, 176, 77);
		panel_1.add(button);
		
		JButton button_1 = new JButton("胶水回收");
		button_1.setFont(new Font("宋体", Font.PLAIN, 33));
		button_1.setBounds(739, 10, 176, 77);
		panel_1.add(button_1);
	}
}
