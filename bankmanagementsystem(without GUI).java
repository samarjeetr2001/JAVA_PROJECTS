package bankmanagementsystem;

import java.util.Scanner;

public class bankSystem 
{ 
	static long acc_no=250101000;
//public static int i=0,n=1 ;


public static void main(String[] args) 
	{
	    int n=1,k=-1,hakinahi=0;
		userData data[] = new userData[10];//n=10
		bankerUse bankeruse=new bankerUse();
		
		//while(true)
		//{
			Scanner t = new Scanner(System.in);
		int j=0;
		
   
    	 for(int i=0;i<10;i++) //n=10
     {   //n++;
     System.out.println("\nWELCOME TO V-BANK\n");
    	 System.out.println("\nwant to login/create a account"+"\n press 1 for LOGIN \n 2 for create new account \n 3.for banker use");
         int ch=t.nextInt();
         //int j;
    	 if(ch==0)
         	break;
          if(ch==2)
        	  
          {   acc_no++;k++;
        	 data[k]=new userData(acc_no);
        	 
          }
         if(ch==1)
         {  if(k==-1)
         {
        	 System.out.println("^^please, create account");
        
         }
         else
         {
        	 System.out.println("enter account number");
        	long userAccount=t.nextLong(); 
        	System.out.println("enter 4 digit pin");
        	long userPin= t.nextLong();
    int checkkar=0;
        	try {	
        		for( int ra=0;ra<10;ra++) //n=10
        	
        	{       //System.out.println("ruk2");
        		if	(checkkar==0)
        		{
        			System.out.print(" ");
        		}
        		if (data[ra].acc_no==userAccount && data[ra].pin==userPin)  //ra=j
        		{//System.out.println("wait");
        		 checkkar=1;
        				 hakinahi=1;
        				j=ra;
        		 break;
        		}
        		
        	}
        		}catch(NullPointerException e)
        	{
        			System.out.println("^^invaid account number or pin^^");
        	}
        	if(hakinahi==1) 
        	{	System.out.println("^^hello :) ---- "+data[j].name);
         
        	System.out.println("\n\n1.Withdrawal\n2.Deposit\n3.Balance\n4.Request for loan\n5.pay EMI");
        	int choi=t.nextInt();
        	switch(choi)
        	{
        	case 1:{
        				System.out.println("----WITHDRAWAL----\n enter amount");
        				int wamo = t.nextInt();
        				
        				if(data[j].balance<wamo)
        					System.out.println("Insufficient balance");
        				else
        				{
        					//userData[i].trans=data[j].balance;
        					data[j].balance = data[j].balance - wamo;
        					System.out.println(" collect your cash");
        					System.out.println("remaining balance = "+ data[j].balance);
        				}
        				
        		   }break;
        	case 2:{
        				System.out.println("----DEPOSIT----\n enter amount");
        				int damo= t.nextInt();
        				data[j].trans=damo;
        				data[j].balance=data[j].balance + damo ;
        				//System.out.println("new balance = "+ data[j].balance);
        	       }break;
        	case 3:{
        		       System.out.println("new balance = "+ data[j].balance);
        	       }break;
        	case 4:{
        		         System.out.println("********bank received your request********");
        		        data[j]= bankeruse.loan(data[j]);      
        		   }break;
        	case 5:{
        		      if(data[j].balance<(data[j].loan_amount+(data[j].loan_amount*bankeruse.getROI())/100)/24)
							{
								System.out.println("Insufficient balance\nDue to Insufficient balanceyou have to pay FINE of 500 rupees");
								data[j].loan_amount=data[j].loan_amount+500;
							}
        		      else
        		      {
        		    	  System.out.println("your EMI is "+(data[j].loan_amount+(data[j].loan_amount*bankeruse.getROI())/100)/24);
        		    	  data[j].loan_amount=data[j].loan_amount-(data[j].loan_amount+(data[j].loan_amount*bankeruse.getROI())/100)/24;
        		    	  System.out.println("Transaction is completed");
        		    	  System.out.println("remaining loan amount is "+data[j].loan_amount);
        		      }
        	       }
        	}
        	//data[j].balance
        	
        	}	}
        	
        		}
         
         
         if(ch==3) 
         {    System.out.println("*********BANKER USE***********");
        	 int ret=bankeruse.login();
        	 if(ret==0)
        		 System.out.println("INVALID PASSWORD OR BANK NUMBER ");
        	 else
        	 {   
        		 System.out.println("want to change ROI if yes press 1 \nPRESENT ROI IS "+ bankeruse.getROI());
        		 int croi=t.nextInt();
        		 if(croi==1)
        			 bankeruse.changeROI();
        		 
        	 }	 
        	 }
         
	}

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
    
    public userData(long acc_no)
    {
        Scanner t=new Scanner(System.in);
        System.out.println("name: ");
        name = t.nextLine();
        System.out.println("mobile number: ");
        mob_no= t.nextLong();
        
        this.acc_no=acc_no;
        
        long copymobno=mob_no;
        int count= 0;
        
        
        while(copymobno!=0) 
        {
            copymobno=copymobno/10;
            count++;
        }
        if(count<9 || count>10)
        {   System.out.println("invalid mobile number"+"\n enter mobile number");
            mob_no=t.nextLong();
            copymobno=mob_no;
             
            while(copymobno!=0) 
            {
                copymobno=copymobno/10;
                count1++;
            }
            if(count1<9 || count1>10)
            {   System.out.println("invalid mobile number"+"\n enter mobile number");
                mob_no=t.nextLong();
            }
        }
        balance=0;
        System.out.println("*****  your account number is "+acc_no+"  *********");
        System.out.println("set 4 digit pin");
        
        pin= t.nextLong();
        if(pin>9999)
          {
            System.out.println("invalid pin"+"\nset pin");
            pin=t.nextLong();
            if(pin>9999)
              {
                System.out.println("invalid pin"+"\nset pin");
                pin=t.nextLong();
                
              }
          }
        
        
        
     }
    //for file
    /*public String getName() {
        return name;
    }
    public long getAC() {
        return acc_no;
    }*/
    

}
public class bankerUse extends bankSystem
{
    protected int ROI=12;
    final protected long bankNum=104713;
    protected long bankPw =18102 ;
    Scanner tt=new Scanner(System.in);
    
    int login()
    {
        System.out.println("Enter Bank code");
        long bankNumCopy=tt.nextLong();
        System.out.println("Enter Bank Password");
        long bankPwCopy=tt.nextLong();
        if((bankPwCopy==bankPw)&&(bankNumCopy==bankNum))
        {
            //int re= 1;
            return 1;
        }
        else 
        {
            //int re=0;
            return 0;
        }
    }
    
    int getROI()
    {
        return ROI;
    }
    
    void changeROI() {
        System.out.println("enter new ROI");
        ROI=tt.nextInt();
    }
    
    userData loan(userData bs)
    { 
        System.out.println("###Enter Amount you require###");
        long lamo=tt.nextLong();
                System.out.println( "$$$ Rate of interest is "+ROI);
    
    if((bs.loan_amount<10*bs.balance) &&(bs.trans>lamo))
    {
        System.out.println("loan approved \npress 1 to continue ");
        int lamoC=tt.nextInt();
        if(lamoC==1)
        {bs.loan_amount=lamo;
        System.out.println("####Total loan amount with interest is "+ (bs.loan_amount+(bs.loan_amount*ROI)/100));
        System.out.println("$$$$EMI for 24 months is "+(bs.loan_amount+(bs.loan_amount*ROI)/100)/24);
        }
    }
    else 
    {
        System.out.println("^^^^^Bank still having dicussion about your request^^^^^");
        
    }
    return bs;
}
}