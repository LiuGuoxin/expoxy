package com.guoxin;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class RestoreEpoxyView extends JDialog {
	private JTextField textField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RestoreEpoxyView dialog = new RestoreEpoxyView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RestoreEpoxyView() {
		setBounds(100, 100, 530, 556);
		getContentPane().setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBounds(0, 0, 514, 58);
			getContentPane().add(panel);
			panel.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("New label");
			lblNewLabel.setBounds(24, 10, 103, 27);
			panel.add(lblNewLabel);
			
			JLabel label = new JLabel("New label");
			label.setBounds(155, 10, 103, 27);
			panel.add(label);
		}
		{
			JPanel panel_1 = new JPanel();
			panel_1.setBounds(0, 58, 514, 49);
			getContentPane().add(panel_1);
			panel_1.setLayout(null);
			
			JLabel label = new JLabel("New label");
			label.setBounds(24, 10, 103, 27);
			panel_1.add(label);
			
			textField = new JTextField();
			textField.setBounds(155, 10, 199, 27);
			panel_1.add(textField);
			textField.setColumns(10);
		}
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 106, 514, 373);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		
		
		table = new JTable();
		table.setBounds(0, 0, 514, 373);

		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"\u5E8F\u53F7", "\u80F6\u6C34\u53F7", "\u80F6\u6C34\u7C7B\u578B", "\u65F6\u95F4", "\u64CD\u4F5C"
			}
		));
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 514, 373);
		panel.add(scrollPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 478, 514, 40);
		getContentPane().add(panel_1);

	}
}
