package com.guoxin;

import java.sql.Timestamp;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class TableDataProvier implements Procceser{
	
	private DefaultTableModel tableModel;
	private JTable table;
	private int method;
	private String[] tital;
	private static final String[] storageTital = {"序号","胶水号","胶水型号"};
	private static final String[] unfreezeTital = {"序号","胶水号","胶水型号","入库时间"};
	private static final String[] expoxyUseTital= {"序号","胶水号","胶水型号","解冻\n开始时间","解冻时长","是否解冻完成","使用产线"};
	private static final String[] expoxyCallBackTital = {"序号","胶水号","胶水型号","使用时间","使用时长"};
	
	
	public void setReciver(JTable table){
		this.table= table;

	}
	
	public void update() {
		
	}
	
	public void initial(JTable table) {
		
	}
	
	public void addData(Expoxy expoxy,int num) {
		String[] content;
		switch (method) {
		case MainView.expoxy_Storage: 
			content = new String[3];
			content[0] = num+"";
			content[1] = expoxy.sierelNum;
			content[2]= expoxy.type;
			tableModel.addRow(content);
			break;
		case MainView.expoxy_Unfreeze:
			content = new String[4];
			content[0] = num+"";
			content[1] = expoxy.sierelNum;
			content[2]= expoxy.type;
			content[3]= expoxy.strorageDate.toString();
			tableModel.addRow(content);
			break;
		case MainView.expoxy_Use:

			break;
		case MainView.expoxy_CallBack:

			break;

		default:
			break;
		}
		
	}
	
	public void searchFromDatabase() {
		
	}

	@Override
	public void setProccesingMethod(int method) {
		// TODO Auto-generated method stub
		this.method = 	 method;

		switch (method) {
		case MainView.expoxy_Storage: 
			tital = storageTital;
			break;
		case MainView.expoxy_Unfreeze:
			tital =unfreezeTital;
			break;
		case MainView.expoxy_Use:
			tital = expoxyUseTital;
			break;
		case MainView.expoxy_CallBack:
			tital = expoxyCallBackTital;
			break;
		default:
			break;
		}
		setTital();
	}
	
	private void setTital() {
		tableModel = new DefaultTableModel(null, tital);
		table.setModel(tableModel);
		table.getColumnModel().getColumn(0).setPreferredWidth(36);
		table.getColumnModel().getColumn(0).setMinWidth(36);
		table.getColumnModel().getColumn(0).setMaxWidth(36);
	}
	
}
