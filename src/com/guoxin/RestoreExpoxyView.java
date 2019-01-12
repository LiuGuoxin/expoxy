package com.guoxin;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import barcode.BarcodeProducter;

public class RestoreExpoxyView extends JDialog implements BarCodeReciever,Procceser {
	private JTextField textField;
	private JTable table;
	private BarcodeProducter barcodeProducter;
	private int method = 0;
	private JLabel label ;
	private boolean canRecieveBarCode = false;
	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		try {
			RestoreExpoxyView dialog = new RestoreExpoxyView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
*/
	/**
	 * Create the dialog.
	 */
	public RestoreExpoxyView(JFrame owner,  boolean modal,
			BarcodeProducter barcodeProducter) {
		super(owner, "", modal);
		setBounds(100, 100, 530, 556);
		setResizable(false);
		setLocationRelativeTo(owner);
		getContentPane().setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBounds(0, 0, 514, 58);
			getContentPane().add(panel);
			panel.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("工号");
			lblNewLabel.setBounds(24, 10, 103, 27);
			panel.add(lblNewLabel);
			
			label = new JLabel("New label");
			label.setBounds(155, 10, 103, 27);
			panel.add(label);
		}
		{
			JPanel panel_1 = new JPanel();
			panel_1.setBounds(0, 58, 514, 49);
			getContentPane().add(panel_1);
			panel_1.setLayout(null);
			
			JLabel label = new JLabel("胶水号：");
			label.setBounds(24, 10, 103, 27);
			panel_1.add(label);
			
			textField = new JTextField();
			textField.setBounds(155, 10, 199, 27);
			textField.setEditable(false);
			panel_1.add(textField);
			textField.setColumns(10);
		}
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 106, 514, 373);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		
		
		table = new JTable();
		table.setBounds(0, 0, 514, 373);

/*		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"\u5E8F\u53F7", "\u80F6\u6C34\u53F7", "\u80F6\u6C34\u7C7B\u578B", "\u65F6\u95F4", "\u64CD\u4F5C"
			}
		));*/
		table.setModel(new TableData());
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 514, 373);
		panel.add(scrollPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 478, 514, 40);
		getContentPane().add(panel_1);
		
		this.barcodeProducter = barcodeProducter;
		this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				restoreDefault();
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	@Override
	public void setProccesingMethod(int method) {
		// TODO Auto-generated method stub
		this.method = method;
		canRecieveBarCode = true;
		barcodeProducter.ChangeReciever(this);
		switch (method) {
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
		}
		this.setVisible(true);
	}

	@Override
	public void recieveBarcode(String barCode) {
		// TODO Auto-generated method stub
		if (!canRecieveBarCode)
		return;
		System.out.println("由Restore发出"+barCode);
	}

	private void restoreDefault() {
		// TODO Auto-generated method stub
		method = 0;
		canRecieveBarCode = false;
		barcodeProducter.stopListen();
	}

	public void setOperator(String scanText) {
		// TODO Auto-generated method stub
		label.setText(scanText);
	}
}
