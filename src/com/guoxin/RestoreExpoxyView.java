package com.guoxin;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

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
	private TableDataProvier tableDataProvier =new TableDataProvier();
	private Sql sql;
	private Staff staff;
	private JTextField textField_1;
	private ArrayList<Expoxy> expoxies = new ArrayList<Expoxy>();
	private ArrayList<String> seiralNums = new ArrayList<String>();
	private int scanStep = 0;
	private String scanText;
	
	
	
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
			BarcodeProducter barcodeProducter,Sql sql) {
		super(owner, "", modal);
		this.sql =sql;
		setBounds(100, 100, 530, 556);
		setResizable(false);
		setLocationRelativeTo(owner);
		getContentPane().setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBounds(0, 0, 514, 58);
			getContentPane().add(panel);
			panel.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("作业员");
			lblNewLabel.setBounds(24, 10, 103, 27);
			panel.add(lblNewLabel);
			
			label = new JLabel("New label");
			label.setBounds(155, 10, 103, 27);
			panel.add(label);
		}
		{
			JPanel panel_1 = new JPanel();
			panel_1.setBounds(0, 58, 514, 83);
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
			
			textField_1 = new JTextField();
			textField_1.setEditable(false);
			textField_1.setColumns(10);
			textField_1.setBounds(155, 47, 199, 27);
			panel_1.add(textField_1);
		}
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 142, 514, 337);
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
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 514, 373);
		panel.add(scrollPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 478, 514, 40);
		getContentPane().add(panel_1);
		tableDataProvier.setReciver(table);
		this.barcodeProducter = barcodeProducter;
	}

	@Override
	public void setProccesingMethod(int method) {
		// TODO Auto-generated method stub
		this.method = method;
		canRecieveBarCode = true;
		barcodeProducter.ChangeReciever(this);
		tableDataProvier.setProccesingMethod(method);
		this.setVisible(true);

	}

	@Override
	public void recieveBarcode(String barCode) {
		// TODO Auto-generated method stub
		if (!canRecieveBarCode)
		return;
		textField_1.setText("");
		textField.setText(barCode);
		if(!Expoxy.isExpoxy(barCode)) {
			textField_1.setText("别随便拿一个二维码忽悠我！");
			scanStep = 0;
			return;
		}	
		Expoxy expoxy;
		expoxy = sql.search_expoxy_From_expoxyStorage_by_sierelNum(barCode);
		if(expoxy!=null) {
			System.out.println(expoxy.strorageDate.toString());
			textField_1.setText("这个胶水已存在");
			scanStep = 0;
			return;
		}
		if (scanStep == 0) {
			scanText = barCode;
			scanStep++;
		} else if (scanStep == 1) {
			if (barCode.equals(scanText)) {
				addExpoxy(expoxy);
				scanStep--;
			} else {
				scanText = barCode;
			}
		}
	}

	private void restoreDefault() {
		// TODO Auto-generated method stub
		method = 0;
		scanStep = 0;
		scanText ="";
		canRecieveBarCode = false;
		barcodeProducter.stopListen();
		staff = null;
		textField_1.setText("");
		expoxies.clear();
		seiralNums.clear();
		System.out.println("closed");
	}

	public void setOperator(Staff staff) {
		this.staff = staff;
		// TODO Auto-generated method stub
		label.setText(staff.name);
	}
	
	private void addExpoxy(Expoxy expoxy) {
		switch (method) {
		case MainView.expoxy_Storage:
			if(seiralNums.contains(scanText)) {
				return;
			}
			seiralNums.add(scanText);
			Expoxy tmpExpoxy = new Expoxy(scanText, new Timestamp(System.currentTimeMillis()), staff, Expoxy.STORAGED);
			expoxies.add(tmpExpoxy);			
			tableDataProvier.addData(tmpExpoxy, expoxies.indexOf(tmpExpoxy)+1);
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
		
	}
}
