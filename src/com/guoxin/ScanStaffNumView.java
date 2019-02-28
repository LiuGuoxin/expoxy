package com.guoxin;

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

public class ScanStaffNumView extends JDialog implements BarCodeReciever, Procceser {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblNewLabel;
	private int method = 0;
	private int scanStep = 0;
	private String scanText;
	private BarcodeProducter barcodeProducter;
	private RestoreExpoxyView restoreExpoxyView;
	private boolean canRecieveBarCode = false;
	private JTextField textField_1;
	private Sql sql;
	private Staff staff;
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
	public ScanStaffNumView(JFrame owner,  boolean modal,
			BarcodeProducter barcodeProducter, RestoreExpoxyView restoreExpoxyView,Sql sql) {
		super(owner, "请扫描工号", modal);
		this.sql = sql;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 786, 223);
		setResizable(false);
		setLocationRelativeTo(owner);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 750, 174);
		contentPane.add(panel);
		panel.setLayout(null);

		lblNewLabel = new JLabel("工号");
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
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("宋体", Font.PLAIN, 37));
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(191, 101, 549, 70);
		panel.add(textField_1);
		
		JLabel label = new JLabel("姓名");
		label.setVerticalAlignment(SwingConstants.CENTER);
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("宋体", Font.PLAIN, 37));
		label.setBounds(10, 101, 171, 70);
		panel.add(label);
		this.barcodeProducter = barcodeProducter;
		this.restoreExpoxyView = restoreExpoxyView;
		
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

	@Override
	public void recieveBarcode(String barCode) {
		// TODO Auto-generated method stub
		// String s = barCode.toString();
		if (!canRecieveBarCode)
		return;
		this.textField.setText(barCode);
		if(!Staff.isStaff(barCode)) {
			textField_1.setText("你扫的不是工号！");
			scanStep = 0;
			return;
		}
		
		staff = sql.search_staff_From_DataBase(barCode);
		if(staff!=null){
			textField_1.setText(staff.name);
		}else{
			textField_1.setText("没找到这个人");
			scanStep = 0;
			return;
		}
		if (scanStep == 0) {
			scanText = barCode;
			scanStep++;
		} else if (scanStep == 1) {
			if (barCode.equals(scanText)) {
				scanStep--;
				// TODO 这里对下一个视图进行传值
				restoreExpoxyView.setOperator(staff);
				dispose();
				restoreExpoxyView.setProccesingMethod(method);
//				barcodeProducter.ChangeReciever(restoreExpoxyView);

			} else {
				scanText = barCode;
			}
		}
	}

	private void restoreDefault() {
		canRecieveBarCode = false;
		method = 0;
		scanStep = 0;
		scanText ="";
		textField.setText("");
		textField_1.setText("");
		staff = null;
//		lblNewLabel.setText("");
//		System.out.println("SNV 初始化");
//		barcodeProducter.stopListen();
	}

	@Override
	public void setProccesingMethod(int method) {
		// TODO Auto-generated method stub
		barcodeProducter.ChangeReciever(this);
		this.method = method;
		canRecieveBarCode = true;
/*		switch (method) {
		case MainView.expoxy_Storage: 
		
			break;
		case MainView.expoxy_Unfreeze:

			break;
		case MainView.expoxy_Use:

			break;
		case MainView.expoxy_CallBack:

			break;

		default:
			break;
		}*/
		this.setVisible(true);
	}
}
