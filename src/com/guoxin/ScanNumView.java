package com.guoxin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;

public class ScanNumView extends JDialog {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblNewLabel ;
	private int function;
	public static final int expoxy_Storage = 1;	//胶水入库窗口标识
	public static final int expoxy_Unfreeze = 2;//胶水解冻窗口标识
	public static final int expoxy_Use = 3;//胶水使用窗口标识
	public static final int expoxy_CallBack = 4;//胶水回收窗口标识
	
	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SanStaffNumView frame = new SanStaffNumView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
	/**
	 * Create the frame.
	 */
	public ScanNumView(JFrame owner,String tital,String functionLabelText,boolean b) {
		super(owner,tital,b);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 637, 223);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 29, 587, 113);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblNewLabel = new JLabel(functionLabelText);
		lblNewLabel.setVerticalAlignment(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 37));
		lblNewLabel.setBounds(10, 21, 134, 70);

		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("宋体", Font.PLAIN, 37));
		textField.setBounds(154, 21, 409, 70);
		panel.add(textField);
		textField.setColumns(10);
	}
	
	public ScanNumView setFunction(String tital,String functionLabelText){
		this.setTitle(tital);
		lblNewLabel.setText(functionLabelText);
		return this;
	}
}
