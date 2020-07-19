package OnlineOrder;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;

public class Order extends JFrame {

	private JPanel contentPane;
	private JLabel lblMSG;
	private JTextArea textArea;
	private JButton btnSendOrder;
	private JButton btnCancel;

	private int quantity;
	private String orderedItems;
	private double totalPrice;
	private String specialRequest;
	private boolean orderStatus;
	private boolean option;
	private Date orderDate;
	private String orderNumber;

	public Order() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 719, 637);
		setTitle("Order Information");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		lblMSG = new JLabel("Order Info");
		
		textArea = new JTextArea();
		
		btnSendOrder = new JButton("Send Order");
		btnSendOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Your order has sent, thank you");
			}
		});
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Your order has cancelled, thank you");
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(28)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 611, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblMSG)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(145)
							.addComponent(btnSendOrder)
							.addGap(113)
							.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(48, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(49)
					.addComponent(lblMSG)
					.addGap(33)
					.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 355, GroupLayout.PREFERRED_SIZE)
					.addGap(42)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnSendOrder, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(34, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	public void setOrderInfo(String info) {
		textArea.setText(info);
	}
	
	public boolean cancelOrder() {
		return orderStatus;
	}
	
	public double getTotalPrice() {
		return totalPrice;
	}
	
	public String sendOrder() {
		return orderedItems;
	}
	
	public String getOrderInfo() {
		return orderedItems;
	}
	
	public String changeOrderStatus(String orderStatus) {
		return orderStatus;
	}
	
	public String filterOrderIngo(Date orderDate) {
		return orderedItems;
	}
	
	public String filterOrderIngo(String orderNumber) {
		return orderedItems;
	}
}
