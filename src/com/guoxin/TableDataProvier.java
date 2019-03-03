package com.guoxin;

import java.sql.Timestamp;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class TableDataProvier implements Procceser {

	private DefaultTableModel tableModel;
	private JTable table;
	private int method;
	private String[] tital;
	private static final String[] storageTital = { "序号", "序列号", "型号", "" };
	private static final String[] unfreezeTital = { "序号", "序列号", "型号", "入库时间", "" };
	private static final String[] expoxyUseTital = { "序号", "序列号", "型号", "解冻时间", "解冻时长", "状态", "产线", "" };
	private static final String[] expoxyCallBackTital = { "序号", "序列号", "型号", "使用时间", "使用时长", "产线", "" };

	public void setReciver(JTable table) {
		this.table = table;
	}

	public void update() {

	}

	public void initial(JTable table) {

	}

	public void addData(Expoxy expoxy, int num) {
		String[] content = null;
		switch (method) {
		case MainView.expoxy_Storage:
			content = new String[4];
			content[0] = num + "";
			content[1] = expoxy.sierelNum;
			content[2] = expoxy.type;

			break;
		case MainView.expoxy_Unfreeze:
			content = new String[5];
			content[0] = num + "";
			content[1] = expoxy.sierelNum;
			content[2] = expoxy.type;
			content[3] = expoxy.strorageDate.toString();
			break;
		case MainView.expoxy_Use:
			content = new String[8];
			content[0] = num + "";
			content[1] = expoxy.sierelNum;
			content[2] = expoxy.type;
			content[3] = expoxy.unfreezeDate.toString();
			content[4] = (System.currentTimeMillis() - expoxy.unfreezeDate.getTime()) / (3600 * 1000) + "时"
					+ ((System.currentTimeMillis() - expoxy.unfreezeDate.getTime()) % (3600 * 1000)) / (60 * 1000)
					+ "分";

			content[5] = "解冻完成";
			

			content[6] = expoxy.place;

			break;
		case MainView.expoxy_CallBack:
			content = new String[7];
			content[0] = num + "";
			content[1] = expoxy.sierelNum;
			content[2] = expoxy.type;
			content[3] = expoxy.useDate.toString();
			content[4] = (System.currentTimeMillis() - expoxy.useDate.getTime()) / (3600 * 1000) + "时"
					+ ((System.currentTimeMillis() - expoxy.useDate.getTime()) % (3600 * 1000)) / (60 * 1000) + "分";
			content[5] = expoxy.place;
			break;

		default:
			break;
		}
		tableModel.addRow(content);
	}

	public void searchFromDatabase() {

	}

	@Override
	public void setProccesingMethod(int method) {
		// TODO Auto-generated method stub
		this.method = method;

		switch (method) {
		case MainView.expoxy_Storage:
			tital = storageTital;
			break;
		case MainView.expoxy_Unfreeze:
			tital = unfreezeTital;
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
		setTableWidth(0, 32);
		setTableWidth(1, 200);
		setTableWidth(2, 45);
		if (method >= MainView.expoxy_Use) {
			setTableWidth(3, 160);
			setTableWidth(4, 80);
			setTableWidth(5, 90);
			setTableWidth(5, 50);
		}
	}

	private void setTableWidth(int col, int width) {
		table.getColumnModel().getColumn(col).setPreferredWidth(width);
		table.getColumnModel().getColumn(col).setMaxWidth(width);
		table.getColumnModel().getColumn(col).setMinWidth(width);
	}
}
