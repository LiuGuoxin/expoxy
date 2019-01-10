package com.guoxin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import barcode.BarcodeProducter;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.SwingConstants;

public class ScanStaffNumView extends JDialog implements BarCodeReciever {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblNewLabel;
	private int function = 0;
	public static final int expoxy_Storage = 1; // 胶水入库窗口标识
	public static final int expoxy_Unfreeze = 2;// 胶水解冻窗口标识
	public static final int expoxy_Use = 3;// 胶水使用窗口标识
	public static final int expoxy_CallBack = 4;// 胶水回收窗口标识
	private int scanStep = 0;
	private String scanText;
	private BarcodeProducter barcodeProducter;

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { SanStaffNumView frame = new
	 * SanStaffNumView(); frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 */
	/**
	 * Create the frame.
	 */
	public ScanStaffNumView(JFrame owner, String tital, String functionLabelText, boolean b,
			BarcodeProducter barcodeProducter) {
		super(owner, tital, b);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 786, 223);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 29, 750, 113);
		contentPane.add(panel);
		panel.setLayout(null);

		lblNewLabel = new JLabel(functionLabelText);
		lblNewLabel.setVerticalAlignment(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 37));
		lblNewLabel.setBounds(10, 21, 171, 70);

		panel.add(lblNewLabel);

		textField = new JTextField();
		textField.setEditable(false);
		textField.setFont(new Font("宋体", Font.PLAIN, 37));
		textField.setBounds(191, 21, 549, 70);
		panel.add(textField);
		textField.setColumns(10);
		this.barcodeProducter = barcodeProducter;
		this.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub
				restoreDefault();
			}

			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	public ScanStaffNumView setFunction(String tital, String functionLabelText) {
		this.setTitle(tital);
		lblNewLabel.setText(functionLabelText);
		return this;
	}

	@Override
	public void recieveBarcode(StringBuilder barCode) {
		// TODO Auto-generated method stub
		String s = barCode.toString();
		this.textField.setText(s);
		if (scanStep == 0) {
			scanText = s;
			scanStep++;
		} else if (scanStep == 1) {
			if (s.equals(scanText)) {
				scanStep--;
				// TODO 这里对下一个视图进行传值
				System.out.println("设置值：" + s);
			} else {
				scanText = s;
			}
		}
	}

	public void restoreDefault() {
		scanStep = 0;
		textField.setText("");
	}
}
