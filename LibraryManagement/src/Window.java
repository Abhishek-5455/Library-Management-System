import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.mysql.jdbc.ResultSet;

import net.proteanit.sql.DbUtils;

public class Window {

	private JFrame frmLibrarySystem;
	private JTextField txtBranch;
	private JTextField txtName;
	private JTextField txtReg;
	private JTextField txtBkName;
	private JTextField txtBkId;
	private JTextField searchRec;
	private Font font;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frmLibrarySystem.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Window() {
		initialize();
		connect();
		records();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLibrarySystem = new JFrame();
		frmLibrarySystem.setFont(new Font("Arial", Font.BOLD, 15));
		frmLibrarySystem.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\lenovo\\Pictures\\brain.png"));
		frmLibrarySystem.setTitle("Library System");
		frmLibrarySystem.setBounds(100, 100, 1269, 782);
		frmLibrarySystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLibrarySystem.getContentPane().setLayout(null);
		frmLibrarySystem.setLocationRelativeTo(null);;
		font = new Font("Yu Gothic", Font.BOLD, 25);
		
		JLabel lblNewLabel = new JLabel("Library");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBackground(Color.BLACK);
		font.deriveFont(45);
		lblNewLabel.setFont(new Font("Yu Gothic", Font.BOLD, 40));
		lblNewLabel.setBounds(497, 11, 243, 52);
		frmLibrarySystem.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Add", TitledBorder.CENTER, TitledBorder.TOP, null, Color.DARK_GRAY));
		panel.setBounds(10, 58, 562, 358);
		frmLibrarySystem.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel stdName = new JLabel("Student Name :");
		stdName.setFont(font);
		stdName.setBounds(10, 25, 215, 47);
		panel.add(stdName);
		
		JLabel stdReg = new JLabel("Reg. No : ");
		stdReg.setFont(font);
		stdReg.setBounds(10, 155, 215, 47);
		panel.add(stdReg);
		
		JLabel bkName = new JLabel("Book Name : ");
		bkName.setFont(font);
		bkName.setBounds(10, 226, 215, 47);
		panel.add(bkName);
		
		JLabel bkId = new JLabel("Book Id : ");
		bkId.setFont(font);
		bkId.setBounds(10, 291, 215, 47);
		panel.add(bkId);
		
		JLabel stdBranch = new JLabel("Branch / Year : ");
		stdBranch.setFont(font);
		stdBranch.setBounds(10, 97, 215, 47);
		panel.add(stdBranch);
		
		txtBranch = new JTextField();
		txtBranch.setFont(font);
		txtBranch.setBounds(235, 97, 317, 47);
		panel.add(txtBranch);
		txtBranch.setColumns(10);
		txtBranch.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		
		txtName = new JTextField();
		txtName.setBounds(235, 25, 317, 47);
		txtName.setFont(font);
		panel.add(txtName);
		txtName.setColumns(10);
		txtName.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		
		txtReg = new JTextField();
		txtReg.setBounds(235, 155, 317, 47);
		panel.add(txtReg);
		txtReg.setFont(font);
		txtReg.setColumns(10);
		txtReg.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		
		txtBkName = new JTextField();
		txtBkName.setBounds(235, 226, 317, 47);
		panel.add(txtBkName);
		txtBkName.setColumns(10);
		txtBkName.setFont(font);
		txtBkName.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		
		txtBkId = new JTextField();
		txtBkId.setBounds(235, 291, 317, 47);
		panel.add(txtBkId);
		txtBkId.setFont(font);
		txtBkId.setColumns(10);
		txtBkId.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "SEARCH", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 488, 562, 96);
		frmLibrarySystem.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("  Reg.  No : ");
		lblNewLabel_1.setFont(font);
		lblNewLabel_1.setBounds(10, 31, 177, 47);
		panel_1.add(lblNewLabel_1);
		
		searchRec = new JTextField();
		searchRec.setBounds(246, 31, 306, 47);
		panel_1.add(searchRec);
		searchRec.setColumns(10);
		searchRec.setFont(font);
		
		JButton addButton = new JButton("ADD");
		addButton.setBackground(Color.LIGHT_GRAY);
		addButton.setFont(font);
		addButton.setBounds(94, 427, 130, 50);
		frmLibrarySystem.getContentPane().add(addButton);
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = txtName.getText();
				String branch = txtBranch.getText();
				String reg = txtReg.getText();
				String bkName = txtBkName.getText();
				String id = txtBkId.getText();
				try {
					pre = con.prepareStatement("insert into info(stdName,branch,regNo,bkName,bkId) values(?,?,?,?,?)");
					pre.setString(1, name);
					pre.setString(2, branch);
					pre.setString(3, reg);
					pre.setString(4, bkName);
					pre.setString(5, id);
					pre.executeUpdate();
					records();
					JOptionPane.showMessageDialog(null, "Record Added!!!!");
					setNull();
					txtName.requestFocus();
				}catch(SQLException exp) {
					JOptionPane.showMessageDialog(null, "Wrong Credentials !!!!");
					setNull();
				}
			}
		});
		JButton clearButton = new JButton("CLEAR");
		clearButton.setBackground(Color.LIGHT_GRAY);
		clearButton.setFont(font);
		clearButton.setBounds(340, 427, 130, 50);
		frmLibrarySystem.getContentPane().add(clearButton);
		clearButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setNull();
				txtName.requestFocus();
			}
		});
		
		JButton exitButton = new JButton("EXIT");
		exitButton.setBackground(Color.LIGHT_GRAY);
		exitButton.setFont(font);
		exitButton.setBounds(442, 667, 226, 65);
		frmLibrarySystem.getContentPane().add(exitButton);
		exitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frmLibrarySystem.dispose();
				
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(580, 59, 663, 584);
		frmLibrarySystem.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setSurrendersFocusOnKeystroke(true);
		table.setBackground(Color.WHITE);
		
		JButton updateBtn = new JButton("UPDATE");
		updateBtn.setBackground(Color.LIGHT_GRAY);
		updateBtn.setBounds(46, 595, 170, 49);
		updateBtn.setFont(font);
		frmLibrarySystem.getContentPane().add(updateBtn);
		
		JButton deleteBtn = new JButton("DELETE");
		deleteBtn.setBackground(Color.LIGHT_GRAY);
		deleteBtn.setBounds(300, 595, 170, 49);
		deleteBtn.setFont(font);
		deleteBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 String id = searchRec.getText();
				 try {
					pre = con.prepareStatement("delete from info where regNo=?");
					pre.setString(1, id);
					pre.executeUpdate();
					records();
					JOptionPane.showMessageDialog(null,"Record Deleted !!!");
					searchRec.setText("");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		frmLibrarySystem.getContentPane().add(deleteBtn);
	}
	Connection con;
	PreparedStatement pre;
	java.sql.ResultSet rs;
	private JTable table;
	public void connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/library","root","");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public void records() {
		try {
			pre = con.prepareStatement("select * from info");
			rs = (ResultSet) pre.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch(Exception e) {
			System.out.println(e);
		}
	
	}
	public void setNull() {
		txtName.setText("");
		txtBkName.setText("");
		txtReg.setText("");
		txtBranch.setText("");
		txtBkId.setText("");
	}
}
