package OnlineOrder;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.sql.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu extends JFrame {

	private JPanel contentPane;
	private JList<String> lstLeft;
	private JList<String> lstRight;
	private JButton btnOrder;
	private JButton btnCancel;
	private JCheckBox chkVIP;
	DefaultListModel<String> dlmRight = new DefaultListModel<String>();
	Item myMenu;

	/**
	 * Create the frame.
	 */
	public void loadData() {

		String cs = "jdbc:ucanaccess://c:\\temp\\finalfood2020.accdb";
		DefaultListModel<String> dlmLeft = new DefaultListModel<String>();
		ArrayList<String> list = new ArrayList<String>();

		try {
			Connection conn = DriverManager.getConnection(cs);
			Statement sql = conn.createStatement();
			ResultSet rs = sql.executeQuery("select itemname from fooditems");

			while (rs.next()) {
				list.add(rs.getString(1));
			}
			rs.close();

			Collections.sort(list);

			for (String item : list)
				dlmLeft.addElement(item);

			lstLeft.setModel(dlmLeft);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void itemSelect() {
		String selectedItem = lstLeft.getSelectedValue();
		dlmRight.addElement(selectedItem);
	}

	public void itemRemove() {
		int index = lstRight.getSelectedIndex();
		if (index != -1) {
			dlmRight.removeElementAt(index);
		} else {
			JOptionPane.showMessageDialog(null, "The list is empty");
		}
	}

	public void proceedOrder() {
		Order myOrder = new Order();
		myOrder.setVisible(true);
		setVisible(false);

		double price = 0, subTotal = 0, netTotal = 0;
		String preItem = "", curItem = "";
		Boolean firstRec = true;
		int count = 1;

		int rows = lstRight.getModel().getSize();
		String item = "";
		ArrayList<String> list = new ArrayList<String>();
		NumberFormat numC = NumberFormat.getCurrencyInstance();

		String fmt = "%-50s %-30s %20s %20s \n";
		System.out.printf(fmt, "Food items ordered", "Unit price", "Subtotal", "Net amt");
		String msg = String.format(fmt, "Food items ordered", "Unit price", "Subtotal", "Net amt");

		for (int i = 0; i < rows; i++) {
			item = dlmRight.getElementAt(i);
			list.add(item);
		}
		Collections.sort(list);

		for (String eachItem : list) {
			curItem = eachItem;
			String cs = "jdbc:ucanaccess://c:\\temp\\finalfood2020.accdb";

			if (firstRec) {
				count = 1;
				firstRec = false;
				preItem = curItem;
			} else {
				if (preItem.equals(curItem)) {
					++count;
				} else {
					myMenu = Item.valueOf(preItem);

					try {
						Connection conn = DriverManager.getConnection(cs);
						Statement sql = conn.createStatement();
						ResultSet rs = sql
								.executeQuery("select price from fooditems where itemname = '" + preItem + "'");

						while (rs.next()) {
							price = Double.parseDouble(rs.getString(1));
						}
						rs.close();

					} catch (Exception e) {
						e.printStackTrace();
					}
					msg += String.format(fmt, myMenu.getItemDesc() + " ( " + myMenu.getCalories() + " calories ) ",
							"x" + count + " " + numC.format(price) + " ==>", numC.format(count * price),
							numC.format(getDiscRate(preItem, price) * count));

					subTotal += count * price;
					netTotal += getDiscRate(preItem, price) * count;
					count = 1;
					preItem = curItem;
				}
			}
		}

		try {
			String cs = "jdbc:ucanaccess://c:\\temp\\finalfood2020.accdb";
			Connection conn = DriverManager.getConnection(cs);
			Statement sql = conn.createStatement();
			ResultSet rs = sql.executeQuery("select price from fooditems where itemname = '" + curItem + "'");
			myMenu = Item.valueOf(curItem);
			while (rs.next()) {
				price = Double.parseDouble(rs.getString(1));
			}
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		msg += String.format(fmt, myMenu.getItemDesc() + " ( " + myMenu.getCalories() + " calories ) ",
				"x" + count + " " + numC.format(price) + " ==>", numC.format(count * price),
				numC.format(getDiscRate(curItem, price) * count));
		subTotal += count * price;
		netTotal += getDiscRate(curItem, price) * count;

		msg += String.format(" ");
		msg += String.format(fmt, "Total amount:", "", numC.format(subTotal), numC.format(netTotal));
		msg += String.format("Please pay your employee : Hyeonju Kim");
		myOrder.setOrderInfo(msg);
	}

	public double getDiscRate(String item, double price) {
		double discRate = 0;

		Item myMenu1 = Item.valueOf(item);
		discRate = myMenu1.getDiscRate();

		if (chkVIP.isSelected()) {
			price = price - (price * discRate);
		}
		return price;
	}

	public void createEvents() {
		loadData();
		dlmRight.removeAllElements();
		lstRight.setModel(dlmRight);

		lstLeft.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				itemSelect();
			}
		});

		lstRight.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				itemRemove();
			}
		});

		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				proceedOrder();
			}
		});

		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dlmRight.removeAllElements();
			}
		});

	}

	public void setUp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 613, 576);
		setTitle("Menu");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel("Menu items");

		JScrollPane scrollPane = new JScrollPane();

		JLabel lblNewLabel_1 = new JLabel("Selected items");

		JScrollPane scrollPane_1 = new JScrollPane();

		btnOrder = new JButton("Order");

		JLabel lblNewLabel_2 = new JLabel("Contact : Hyeonju Kim(300316734)");

		chkVIP = new JCheckBox("Pick up");

		btnCancel = new JButton("Cancel");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addGap(28)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(
												scrollPane, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel)
										.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(btnOrder, GroupLayout.PREFERRED_SIZE, 90,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnCancel,
														GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)))
								.addGap(77)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel_1).addComponent(chkVIP).addComponent(scrollPane_1,
												GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE))))
				.addContainerGap(161, Short.MAX_VALUE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addGap(64)
				.addGroup(gl_contentPane
						.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel).addComponent(lblNewLabel_1))
				.addGap(18)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE))
				.addGap(49)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnOrder, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(chkVIP)
						.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
				.addGap(51).addComponent(lblNewLabel_2).addContainerGap(27, Short.MAX_VALUE)));

		lstRight = new JList<String>();

		lstRight.setFont(new Font("Courier New", Font.PLAIN, 18));
		scrollPane_1.setViewportView(lstRight);

		lstLeft = new JList<String>();

		lstLeft.setFont(new Font("Courier New", Font.PLAIN, 18));
		scrollPane.setViewportView(lstLeft);
		contentPane.setLayout(gl_contentPane);
	}

	public Menu() {
		setUp();
		createEvents();

	}
}
