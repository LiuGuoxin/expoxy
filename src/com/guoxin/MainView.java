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
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;

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
	private Sql sql = new Sql();
	private ArrayList<Expoxy> expoxies = new ArrayList<>();
	private DefaultTableModel model = new DefaultTableModel(
			new Object[0][],
			new String[] {
				"\u5E8F\u53F7", "\u80F6\u6C34\u53F7", "\t\u80F6\u6C34\u578B\u53F7", "\u89E3\u51BB\u65F6\u95F4", "\u89E3\u51BB\u4EBA", "\u5730\u70B9", "\u4F7F\u7528\u65F6\u95F4", "\u66F4\u6362\u4EBA", "\u56DE\u6536\u622A\u6B62\u65F6\u95F4", "\u56DE\u6536\u5012\u8BA1\u65F6"
			}
		);
	private Timer timer= new Timer(true);
	private MTimerTask timerTask = new MTimerTask();
	private boolean canGo = true;
	private JTextField textField;
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
		updateFromDatabase();
		timer.schedule(timerTask, 1000, 1000);
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
		table.setModel(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(36);
		table.getColumnModel().getColumn(1).setPreferredWidth(169);
		table.getColumnModel().getColumn(1).setMinWidth(169);
		table.getColumnModel().getColumn(2).setPreferredWidth(67);
		table.getColumnModel().getColumn(8).setPreferredWidth(100);
		table.setEnabled(false);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.setRowHeight(25);
		setTableWidth(0, 36);
		setTableWidth(1, 150);
		setTableWidth(2, 60);
		setTableWidth(3, 130);
		setTableWidth(4, 50);
		setTableWidth(5, 50);
		setTableWidth(6, 130);
		setTableWidth(7, 50);
		setTableWidth(8, 130);
		
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
		panel_2.setLayout(null);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("宋体", Font.BOLD, 72));
		textField.setText("没有待回收的胶水");
		textField.setBounds(195, 10, 719, 80);
		panel_2.add(textField);
		textField.setColumns(10);
		
		
		barcodeProducter = new BarcodeProducter(sanNumView);
		barcodeProducter.startProduct();
		restoreExpoxyView = new RestoreExpoxyView(frame,  true,barcodeProducter,sql);
		sanNumView= new ScanStaffNumView(frame, true,barcodeProducter,restoreExpoxyView,sql);
		frame.addWindowFocusListener(new WindowFocusListener() {
			
			@Override
			public void windowLostFocus(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowGainedFocus(WindowEvent e) {
				// TODO Auto-generated method stub
				canGo = false;
				updateFromDatabase();
				canGo = true;
			}
		});
	}
	
	public void updateFromDatabase(){
		sql.search_All_Unused_Expoxy(expoxies);
		removeAll();
		updateAll();
	}
	
	public void removeAll(){
		while (model.getRowCount()>0){
			model.removeRow(0);
		}
	}
	
	public void updateAll(){
		if (expoxies.size()==0){
			textField.setText("没有待回收的胶水");
			return;
		}
		String[] content = new String[10];
			for (Expoxy expoxy:expoxies){
				long time  = 72*60*60*1000-(System.currentTimeMillis()- expoxy.unfreezeDate.getTime());
				content[0] = expoxies.indexOf(expoxy)+ 1+"";
				content[1] = expoxy.sierelNum;
				content[2] = expoxy.type;
				content[3] = expoxy.unfreezeDate.toString();
				content[4] = expoxy.unfreezer.name;
				content[5] = expoxy.place;
				content[6] = expoxy.useDate.toString();
				content[7] = expoxy.user.name;
				content[8] = new Timestamp(expoxy.unfreezeDate.getTime()+(60*60*72*1000)).toString();
				content[9]  =  time/(60*60*1000) + "时" +time%(60*60*1000)/(60*1000) +"分"; 
				model.addRow(content);
				
		}
			long time  = 72*60*60*1000-(System.currentTimeMillis()- expoxies.get(0).unfreezeDate.getTime());
			textField.setText(time/(60*60*1000) + "：" +time%(60*60*1000)/(60*1000) +"："+(time%(60*1000)/1000)); 
	}
	
	private void setTableWidth(int col, int width) {
		table.getColumnModel().getColumn(col).setPreferredWidth(width);
		table.getColumnModel().getColumn(col).setMaxWidth(width);
		table.getColumnModel().getColumn(col).setMinWidth(width);
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
	
	public class MTimerTask extends TimerTask{
	
		/*MainView mainView;
		
		public  MTimerTask(MainView mainView) {
			// TODO Auto-generated constructor stub
			this.mainView = mainView;
		}
		*/
		@Override
		public void run() {
			// TODO Auto-generated method stub
			if(!canGo){
				return;
			}
			removeAll();
			updateAll();
			frame.repaint();
		}
		
	}
}
