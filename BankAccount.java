import java.io.Serializable;
import java.util.*;
public class BankAccount implements Serializable {
    //Creating global Scanner object
    public static Scanner scr = new Scanner(System.in);

    @Override
    public String toString() {
        return "Name: " + name +
            "\nAccount No: " + uid +
            "\nType: " + type +
            "\nBalance: tk" + balance +
            "\nAddress: " + address;
    }

     public String uid;
     String name;
    String address;
    String type;
    double balance;
     static int no_of_trans = 0;

    //Default Constructor
    BankAccount(){
        uid=null;
        name=address=type=null;
    }

    BankAccount(String name,String address,String type,double balance){
        this.uid = UUID.randomUUID().toString(); 
        this.name = name;
        this.address = address;
        this.type = type;
        this.balance = balance;
    }


    void transfer(BankAccount receiver) {
        System.out.print("Enter amount to transfer : tk");
        double amt = Double.parseDouble(scr.nextLine());
        if (amt <= this.balance) {
            this.balance -= amt;
            receiver.balance += amt;
            System.out.println("Transfer Successful......");
            no_of_trans++;
        } else {
            System.out.println("Insufficient Balance!!!!!!");
        }
    }

    //Deposit amount in account
    void deposit(){
        System.out.println("");
        System.out.println("Account No : "+this.uid);
        System.out.println("Name : "+this.name);
        System.out.print("Enter amount to be Deposited : tk");
        double blnc = Double.parseDouble((scr.nextLine()));
        this.balance = this.balance + blnc;
        System.out.println("");
        System.out.println("Amount Credited Successfully...");
        System.out.println("");
        no_of_trans++;
    }

    //Withdraw from account
    void withdraw(){
        System.out.println("");
        System.out.println("Account No : "+this.uid);
        System.out.println("Name : "+this.name);
        System.out.print("Enter amount to be Withdrawn : tk");
        double blnc = Double.parseDouble((scr.nextLine()));
        this.balance = this.balance - blnc;
        System.out.println("");
        System.out.println("Amount Debited Successfully...");
        System.out.println("");
        no_of_trans++;
    }

    //Change address of depositer
    void changeAddress(){
        System.out.println("");
        System.out.println("Account No : "+this.uid);
        System.out.println("Name : "+this.name);
        System.out.print("Enter New Address : ");
        this.address = scr.nextLine();
        System.out.println("");
        System.out.println("Address Successfully Changed...");
        System.out.println("");
    }
    
    void checkBalance() {
        System.out.println("Account No : " + this.uid);
        System.out.println("Available Balance : tk" + this.balance);
    }

}