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
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import barcode.BarcodeProducter;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 514, 373);
		panel.add(scrollPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 478, 514, 40);
		getContentPane().add(panel_1);
		
		JButton btnNewButton = new JButton("提交");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				submit();
			}
		});
		panel_1.add(btnNewButton);
		tableDataProvier.setReciver(table);
		this.barcodeProducter = barcodeProducter;
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowListener() {
			
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
			textField_1.setText("你扫的不胶水号");
			scanStep = 0;
			return;
		}	
		Expoxy expoxy = null;
		expoxy = sql.search_expoxy_From_expoxyStorage_by_sierelNum(barCode);
		switch (method) {
		case MainView.expoxy_Storage:
//			expoxy = sql.search_expoxy_From_expoxyStorage_by_sierelNum(barCode);
			if(expoxy!=null) {
				textField_1.setText("这个二维码已经使用过了，请更换二维码。");
				scanStep = 0;
				return;
			}else{
				textField_1.setText("这个二维码有效，再扫一次进行确认录入");
			}
			break;
		case MainView.expoxy_Unfreeze:
//			expoxy = sql.search_expoxy_From_expoxyStorage_by_sierelNum(barCode);
			if(expoxy==null){
				textField_1.setText("没有任何关于这瓶胶水的入库记录");
				scanStep = 0;
				return;
			}else if(expoxy.status == Expoxy.FREEZED){
				textField_1.setText("胶水正在解冻了，解冻时间：");
				scanStep = 0;
				return;
			}else if(expoxy.status == Expoxy.USED){
				textField_1.setText("胶水正在使用中，开始使用时间："+"生产线");
				scanStep = 0;
				return;
			}else if(expoxy.status == Expoxy.CALLBACKED){
				textField_1.setText("胶水已经在使用过了并已回收，回收时间");
				scanStep = 0;
				return;
			}else{
				textField_1.setText("再扫一次胶水号确认解冻");
			}
			break;
		case MainView.expoxy_Use:

			if(expoxy==null){
				textField_1.setText("没有任何关于这瓶胶水的入库记录");
				scanStep = 0;
				return;
			}else if(expoxy.status == Expoxy.STORAGED){
				textField_1.setText("胶水还没解冻，请先解冻胶水");
				scanStep = 0;
				return;
			}else if(expoxy.status == Expoxy.USED){
				textField_1.setText("胶水已经正在使用中，开始使用时间："+"生产线");
				scanStep = 0;
				return;
			}else if(expoxy.status == Expoxy.CALLBACKED){
				textField_1.setText("胶水已经在使用过了并已回收，回收时间");
				scanStep = 0;
				return;
			}else{
				textField_1.setText("再扫一次胶水号确认使用");
			}
			break;
		case MainView.expoxy_CallBack:
			if(expoxy==null){
				textField_1.setText("没有任何关于这瓶胶水的入库记录");
				scanStep = 0;
				return;
			}else if(expoxy.status == Expoxy.STORAGED){
				textField_1.setText("胶水还没解冻，请先解冻胶水");
				scanStep = 0;
				return;
			}else if(expoxy.status == Expoxy.FREEZED){
				textField_1.setText("胶水已经解冻，但还没有经过使用。");
				scanStep = 0;
				return;
			}else if(expoxy.status == Expoxy.CALLBACKED){
				textField_1.setText("胶水已经在使用并回收过了，回收时间："+"生产线");
				scanStep = 0;
				return;
			}else{
				textField_1.setText("再扫一次胶水号确认使用");
			}
			break;
		default:
			break;
		}
		

		if (scanStep == 0) {
			scanText = barCode;
			scanStep++;
		} else if (scanStep == 1) {
			if (barCode.equals(scanText)) {
				addExpoxy(expoxy);
				textField_1.setText("");
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
	}

	public void setOperator(Staff staff) {
		this.staff = staff;
		// TODO Auto-generated method stub
		label.setText(staff.name);
	}
	
	private void submit(){
		boolean success = false;
		String tmp = "";
		switch (method) {
		case MainView.expoxy_Storage:
			for(Expoxy e : expoxies){
				success = sql.storage(e);
			}
			tmp = "存储";

			break;
		case MainView.expoxy_Unfreeze:
			for(Expoxy e:expoxies){
				success = sql.ufreeze(e);
			}
			tmp = "解冻";
			break;
		case MainView.expoxy_Use:

			break;
		case MainView.expoxy_CallBack:

			break;

		default:
			break;
		}
		
		if(success){
			JOptionPane.showMessageDialog(this,"胶水"+tmp+"数据上传成功！");
		}else{
			JOptionPane.showMessageDialog(this,"胶水"+tmp+"数据上传失败！");
		}
		this.dispose();
	}
	

	
	private void addExpoxy(Expoxy expoxy) {
		if(seiralNums.contains(scanText)) {
			return;
		}
		seiralNums.add(scanText);
		switch (method) {
		case MainView.expoxy_Storage:

			Expoxy tmpExpoxy = new Expoxy(scanText, new Timestamp(System.currentTimeMillis()), staff, Expoxy.STORAGED);
			expoxies.add(tmpExpoxy);			
			tableDataProvier.addData(tmpExpoxy, expoxies.indexOf(tmpExpoxy)+1);
			break;
		case MainView.expoxy_Unfreeze:
			if(expoxies.contains(expoxy)){
				return;
			}
			expoxy.unfreeze(staff,new Timestamp(System.currentTimeMillis()));
			expoxies.add(expoxy);
			tableDataProvier.addData(expoxy, expoxies.indexOf(expoxy)+1);
			break;
		case MainView.expoxy_Use:
			if(expoxies.contains(expoxy)){
				return;
			}
			break;
		case MainView.expoxy_CallBack:
			if(expoxies.contains(expoxy)){
				return;
			}
			break;

		default:
			break;
		}
	}
}
