package com.guoxin;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import barcode.BarcodeProducter;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainView {

	private JFrame frame;
	private JTable table;
	public static ScanStaffNumView sanNumView;
	public static RestoreExpoxyView restoreExpoxyView;
	private static BarcodeProducter barcodeProducter;
	private static final int button_w = 130;
	private static final int button_h = 60;
	public static final int expoxy_Storage = 1; // 胶水入库窗口标识
	public static final int expoxy_Unfreeze = 2;// 胶水解冻窗口标识
	public static final int expoxy_Use = 3;// 胶水使用窗口标识
	public static final int expoxy_CallBack = 4;// 胶水回收窗口标识

			
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView window = new MainView();
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
	public MainView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 1062, 512);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1046, 463);
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
				"\u5E8F\u53F7", "\u80F6\u6C34\u53F7", "\t\u80F6\u6C34\u578B\u53F7", "\u89E3\u51BB\u65F6\u95F4", "\u89E3\u51BB\u4EBA", "\u4F7F\u7528\u5730\u70B9", "\u4F7F\u7528\u65F6\u95F4", "\u66F4\u6362\u4EBA", "\u56DE\u6536\u622A\u6B62\u65F6\u95F4", "\u56DE\u6536\u5012\u8BA1\u65F6"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(36);
		table.getColumnModel().getColumn(1).setPreferredWidth(169);
		table.getColumnModel().getColumn(1).setMinWidth(169);
		table.getColumnModel().getColumn(2).setPreferredWidth(67);
		table.getColumnModel().getColumn(8).setPreferredWidth(100);
		table.setEnabled(false);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 10, 1026, 218);
		panel.add(scrollPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 238, 1026, 79);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton = new JButton("解冻");
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 33));
		btnNewButton.addActionListener(new MActionListener(expoxy_Unfreeze));
		btnNewButton.setBounds(207, 10, button_w, button_h);
		panel_1.add(btnNewButton);
		
		JButton button = new JButton("上线");
		button.addActionListener(new MActionListener(expoxy_Use));
		button.setFont(new Font("宋体", Font.PLAIN, 33));
		button.setBounds(374, 10,  button_w, button_h);
		panel_1.add(button);
		
		JButton button_1 = new JButton("回收");
		button_1.addActionListener(new MActionListener(expoxy_CallBack));
		button_1.setFont(new Font("宋体", Font.PLAIN, 33));
		button_1.setBounds(541, 10,  button_w, button_h);
		panel_1.add(button_1);
		
		JButton button_2 = new JButton("入库");
		button_2.addActionListener(new MActionListener(expoxy_Storage));
		button_2.setFont(new Font("宋体", Font.PLAIN, 33));
		button_2.setBounds(37, 9, 133, 62);
		panel_1.add(button_2);
		
		JButton button_3 = new JButton("维护");
		button_3.setFont(new Font("宋体", Font.PLAIN, 33));
		button_3.setBounds(875, 10,  button_w, button_h);
		panel_1.add(button_3);
		
		JButton button_4 = new JButton("查询");
		button_4.setFont(new Font("宋体", Font.PLAIN, 33));
		button_4.setBounds(708, 10, button_w, button_h);
		panel_1.add(button_4);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 327, 1026, 100);
		panel.add(panel_2);
		
		
		barcodeProducter = new BarcodeProducter(sanNumView);
		barcodeProducter.startProduct();
		restoreExpoxyView = new RestoreExpoxyView(frame,  true,barcodeProducter);
		sanNumView= new ScanStaffNumView(frame, true,barcodeProducter,restoreExpoxyView);
	}
	

	private class MActionListener implements ActionListener{
		private int method ;
		public MActionListener(int method){
			this.method=method;
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			sanNumView.setProccesingMethod(method);
		}
		
	}
}
