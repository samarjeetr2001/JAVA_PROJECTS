package javaProject;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JRadioButton;


import java.sql.Connection;

import javax.swing.*;
import java.sql.*;


class aftlog extends JFrame implements ActionListener 
{
	JButton b1,b2,b3,b4,b5;
	userData temp = new userData();
	aftlog()
	{
		//temp = da;
		
		b1=new JButton("Withdrawal");
		b2=new JButton("Deposite");
		b3=new JButton("Check Balance");
		b4=new JButton("Request for Loan");
		b5=new JButton("Pay EMI");
		
		add(b1);
		add(b2);
		add(b3);
		add(b4);
		add(b5);
		
		setLayout(new FlowLayout());
		setVisible(true);
		setSize(250,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource()==b1)
		{
			SWithdrawal sw=new SWithdrawal(temp.balance);
			temp.balance=sw.getBala();
			
		}
		else if(ae.getSource()==b2)
		{
			SDeposit sd=new SDeposit(temp);
		}
		else if(ae.getSource()==b3)
		{
			
		}
		else if(ae.getSource()==b4)
		{
			
		}
		else if (ae.getSource()==b5)
		{
			
		}
	}
}



 class invalidMobileNo extends JFrame implements ActionListener{
	long mob_no;
	JLabel l1,l2;
	JButton b;
	JTextField t;
	invalidMobileNo()
	{
		l1=new JLabel("Invalid MOBILE NUMBER! ");
		l2= new JLabel("Please Try Again ");
		t=new JTextField(20);
		b= new JButton("close");
		add(l1);
		add(l2);
		add(t);
		add(b);
		setLayout(new FlowLayout());
		setVisible(true);
		setSize(250,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		b.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		logreg lore = new logreg();
		
	}
	 public long getmob_no()
	{
		return mob_no;
	}
}



 public class logreg extends mainWala implements ActionListener
 {
	 //userData temp,temp1;
	 JTextField t1,t2;
	 JButton b1,b2;
	 JLabel l,l1,l2,l3;
	 JFrame j;
	logreg()
	{
		j= new JFrame("WELCOME TO V-BANK");
		l=new JLabel("WELCOME TO V-BANK");
		t1=new JTextField(20);
		t2=new JTextField(20);
		b1=new JButton("Login");
		b2=new JButton("Create Account");
		l1=new JLabel("ACCOUNT NUMBER : \n");
		l2=new JLabel("PASSWORD       : \n");
		l3=new JLabel("or");
		j.add(l);
		j.add(l1);
		j.add(t1);
		j.add(l2);
		j.add(t2);
		j.add(b1);
		j.add(l3);
		j.add(b2);
		j.setLayout(new FlowLayout());
		j.setVisible(true);
		j.setSize(250,400);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		b1.addActionListener(this);
		b2.addActionListener(this);
	}
	
	
public void actionPerformed(ActionEvent ae)
{

	if(ae.getSource()==b1)
	{
		try{Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/banksystem","root","");
		Statement stmt=con.createStatement();
		String sql ="Select * from userdata where ACCOUNTNO='"+t1.getText()+"' and PASSWORD='"+t2.getText()+"'";
		ResultSet rs= stmt.executeQuery(sql);
		if(rs.next())
			{JOptionPane.showMessageDialog(null,"login successfully...");
			aftlog lod = new  aftlog();
			}
		
			else
			JOptionPane.showMessageDialog(null,"login Unsuccessfully...");
		con.close();
	}catch(Exception e)
	{
		System.out.print(e);
	}
		
		
	}
	else if (ae.getSource()==b2)
	{
		register r1 = new register();
		
	}
}
}
	




class register extends mainWala implements ActionListener
{
	public long acc_no;
	public String name;
	public double balance,loan_amount=0,trans=0;
    String pass;
	public String mob_no;
	JFrame j;
	JTextField t1,t2,t3 ;
	JLabel l,l1,l2,l3;
	JButton b;
	register()
	{
		j=new JFrame("REGISTRATION FORM");
		
		l= new JLabel("REGISTRATION FORM                               ");
		l1=new JLabel("Name : ");
		l2=new JLabel("Mobile No. : ");
		l3=new JLabel("Set PASSWORD : ");
		t1=new JTextField(20);
		t2=new JTextField(20);
		t3=new JTextField(20);
		b= new JButton("Submit");
		j.add(l);
		j.add(l1);
		j.add(t1);
		j.add(l2);
		j.add(t2);
		j.add(l3);
		j.add(t3);
		j.add(b);
		j.setLayout(new FlowLayout());
		j.setVisible(true);
		j.setSize(250,400);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		b.addActionListener(this);
		
				
	}
	
	
	public void actionPerformed(ActionEvent ae)
	{
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/banksystem","root","");
			Statement stmt=con.createStatement();
			String sql1="select max(ACCOUNTNO) from userdata";
			PreparedStatement pst1=con.prepareStatement(sql1);
			ResultSet rs1=pst1.executeQuery();
			acc_no= Long.parseLong(rs1.getString("max(ACCOUNTNO)"));
			acc_no=acc_no+1;
			String sql ="INSERT INTO `userdata`(`id`,`NAME`, `ACCOUNTNO`, `PASSWORD`,`MOBILENO`,`GENDER`) VALUES ('2','"+ t1.getText() +"','"+acc_no+ "','"+t3.getText()+"','"+t2.getText()+"','')";
			PreparedStatement pst= con.prepareStatement(sql); 
			
			pst.execute(sql);
			pst.close();
			//if(rs.next())
				//JOptionPane.showMessageDialog(null,"login successfully...");
			//else
				//JOptionPane.showMessageDialog(null,"login Unsuccessfully...");
			con.close();
		}catch(Exception e1)
		{
			System.out.print(e1);
		}
		
		 getAccountNo ac = new getAccountNo(acc_no);
	
	}
}
public class userData 
{
	public long acc_no;
	public String name;
	public double balance,loan_amount=0,trans=0;
    long pin;
	public long mob_no;
	int count1=0;
	public userData()
	{
	}
	
	
	public userData(long acc_no,String name,long mob_no,long pin)
	{
		
		this.acc_no=acc_no;
		this.name=name;
		this.acc_no=acc_no;
		this.pin=pin;
		
		long copymobno=mob_no;
		int count= 0;
		

		while(copymobno!=0) 
		{
			copymobno=copymobno/10;
			count++;
		}
		if(count<9 || count>10)
		{	invalidMobileNo imn= new invalidMobileNo ();
			mob_no=imn.getmob_no();
			
		}
		balance=0;
		
		
		
		if(pin>9999)
	      {
			invalidPin inpin=new invalidPin();
			
			
	      }
		if ( pin<=9999 && (count==10))
		{
			getAccountNo accno = new getAccountNo(acc_no);
		}
		
	}}	
public class reqLOAn extends JFrame implements ActionListener
{
	JLabel l1;
	JButton b;
	reqLOAn()
	{
		b=new JButton("close");
		l1=new JLabel("Bank received your Request");
		add(l1);
		add(b);
		setLayout(new FlowLayout());
		setVisible(true);
		setSize(250,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		b.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		
	}
}

 class SDeposit extends JFrame implements ActionListener
{
	 userData temp;
	 String s="20";    
	 JTextField t1;
		JButton b1;
		JLabel l1,l2;
		SDeposit(userData da)
		{
			temp=da;
			
			t1=new JTextField(10);
			b1=new JButton("Deposit");
			l1= new JLabel("Enter Amount : ");
			//l2 = new JLabel(s);
			add(l1);
			add(t1);
			add(b1);
			//add(l2);
			
			setLayout(new FlowLayout());
			setVisible(true);
			setSize(250,400);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			b1.addActionListener(this);
		}
		
		public void actionPerformed(ActionEvent ae)
		{
			long depo = Long.parseLong(t1.getText());
			temp.balance=temp.balance + depo;
			
			suffDeposit suff = new suffDeposit();
		}
}
 class suffDeposit extends JFrame implements ActionListener
 {
 	JLabel l1;
 	JButton b;
 	suffDeposit()
 	{
 		b=new JButton("close");
 		l1= new JLabel("TRANSACTION SUCCESSFULL");
 		add(l1);
 		add(b);
 		
 		setLayout(new FlowLayout());
 		setVisible(true);
 		setSize(250,400);
 		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 		b.addActionListener(this);
 	}
 	
 	public void actionPerformed(ActionEvent ae)
	{
 		logreg lore = new logreg();
	}

 }
 class SWithdrawal extends JFrame implements ActionListener
{
	JTextField t1;
	JButton b1;
	JLabel l1;
	userData temp;
	double bala1;
	SWithdrawal(double bala)
	{
		bala1= bala;
		//temp=da;
		
		t1=new JTextField(10);
		b1=new JButton("Withdrawal");
		l1= new JLabel("Enter Amount : ");
		
		add(l1);
		add(t1);
		add(b1);
		
		setLayout(new FlowLayout());
		setVisible(true);
		setSize(250,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		b1.addActionListener(this);
	}
	public void actionPerformed(ActionEvent ae)
	{
		long wid=Long.parseLong(t1.getText());
		if(bala1>=wid)
		{
			suffWithdrawal suff = new suffWithdrawal();
			bala1=bala1-wid;
			
		}
		else if (temp.balance<wid)
		{
			insuffWithdrawal insuff=new insuffWithdrawal();
		}
	}
	double getBala() {
		return bala1;
	}
}

class insuffWithdrawal extends JFrame implements ActionListener
{
	JButton b;
	JLabel l1;
	insuffWithdrawal()
	{
		b= new JButton("close");
		l1= new JLabel("INSUFFICIENT BALANCE");
		add(l1);
		add(b);
		
		setLayout(new FlowLayout());
		setVisible(true);
		setSize(250,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		b.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		logreg lore = new logreg();
	}
}
class suffWithdrawal extends JFrame implements ActionListener
{
	JLabel l1;
	JButton b;
	suffWithdrawal()
	{
		b= new JButton("close");
		l1= new JLabel("TRANSACTION SUCCESSFULL");
		add(l1);
		add(b);
		
		setLayout(new FlowLayout());
		setVisible(true);
		setSize(250,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		b.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		logreg lore = new logreg();
	}
}
class SBalance extends JFrame implements ActionListener
{
	JLabel l1;
	JButton b;
	SBalance()
	{
		b= new JButton("close");
		l1= new JLabel("Balance :");
		add(l1);
		add(b);
		setLayout(new FlowLayout());
		setVisible(true);
		setSize(250,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		b.addActionListener(this);
	}
	
	
	
	
	public void actionPerformed(ActionEvent e) 
	{
	
	}
}

public class getAccountNo extends JFrame implements ActionListener {

	JLabel l1,l2;
	JButton b;
	getAccountNo(long acc_no)
	{
		
		l1=new JLabel("your account number is ");
		l2= new JLabel(Long.toString(acc_no));
		b=new JButton("close");
		add(l1);
		add(l2);
		add(b);
		
		setLayout(new FlowLayout());
		setVisible(true);
		setSize(250,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		b.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		logreg lore = new logreg();
	}
}public class invalidPin extends JFrame implements ActionListener
{
	JLabel l1,l2;
	JTextField t;
	JButton b;
	invalidPin()
	{
		
		l1=new JLabel("Invalid Pin  :");
		//t= new JTextField(20);
		b=new JButton("close");
		
		add(l1);
		add(t);
		add(b);
		
		setLayout(new FlowLayout());
		setVisible(true);
		setSize(250,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		b.addActionListener(this);
	}
	public void actionPerformed(ActionEvent ae)
	{
		logreg lore = new logreg();
	}
	
}
public class mainWala  
{
	static long acc_no=250101000;
	userData data[] = new userData[10];
	int j=0;
	int k=-1;
	int hakinahi=0;

	public static void main(String[] args) 
	{
		
		 
			logreg lore = new logreg();
			
	}
	
}
	