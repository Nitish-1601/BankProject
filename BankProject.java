import java.util.*;
class Account
{
    private int bal;
    public Account(int bal)
    {
        this.bal=bal;
    }
    public boolean isSufficient(int w)
    {
        if(bal>=w && w>=0)
        return(true);
        else 
        return(false);
    }
    public void withdraw(int amt)
    {
        bal-=amt;
        System.out.println("withdrawl amount is "+amt);
        System.out.println("Current balance is "+bal);
        
    }
}
class Customer implements Runnable
{
    private Account acc;
    private String name;
    Customer(Account acc,String n)
    {
        this.acc=acc;
        name=n;
        
    }
    public void run()
    {
        Scanner in=new Scanner(System.in);
        synchronized(acc)
        {
        System.out.println(name+",Enter amt to be withdraw");
        int amt=in.nextInt();
        if(acc.isSufficient(amt))
        {
            System.out.println(name);
            acc.withdraw(amt);
        }
        else
        System.out.println("Insufficient Balance");
        }
    }
}
public class BankProject
{   
    public static void main(String[] args)
    {
        System.out.println("Initial Balance is Rs.1000");
        Account a1=new Account(1000);
        Customer c1=new Customer(a1,"Nick"),c2=new Customer(a1,"anshu");
        Thread t1=new Thread(c1);
        Thread t2=new Thread(c2);
        t1.start();
        t2.start();     
    }
}