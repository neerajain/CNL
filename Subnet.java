import java.util.Scanner;
import java.net.InetAddress;

class Subnet {
    public static void main(String [] args) {
        Scanner sc= new Scanner(System.in);
        System.out.print("Enter the ip address: ");
        String ip=sc.nextLine();
        String [] split_ip = ip.split("\\.");
        String [] split_bip = new String[4];
        StringBuilder bip = new StringBuilder();
        for(int i=0;i<4;i++) {
            split_bip[i]=appendZeroes(Integer.toBinaryString(Integer.parseInt(split_ip[i])));
            bip.append(split_bip[i]);
        }
        System.out.print("Enter the number of address: ");
        int n=sc.nextInt();
        int bits=(int)Math.ceil(Math.log(n)/Math.log(2));
        System.out.println("\nThe number of bits required: "+bits);
        int mask=32-bits;
        int total_address=(int)Math.pow(2,bits);
        System.out.println("Subnet mask is "+mask);
        System.out.println();
        System.out.print("Enter number of subnets you wish to form: ");
        int scount=sc.nextInt();
        int [] fbip =new int[32];
        for(int i=0;i<32;i++)
            fbip[i]=(int)bip.charAt(i)-48;
        for(int i=31;i>31-bits;i--)
            fbip[i] &=0;
        String [] fip ={"","","",""};
        for(int i=0;i<32;i++)
        {
            fip[i/8]=new String(fip[i/8]+fbip[i]);
        }
        int first_offset=0;
        int [] ipAddr =new int[4];
        System.out.print("GROUP 1\n");
        for(int i=0;i<4;i++)
        {
            System.out.print(ipAddr[i]=first_offset=Integer.parseInt(fip[i],2));
            if(i!=3)
                System.out.print(".");
        }
        int lbip[]=new int [32];
        for(int i=0;i<32;i++)
            lbip[i]=(int)bip.charAt(i)-48;
        for(int i=31;i>31-bits;i--)
            lbip[i]|= 1;
        String [] lip ={"","","",""};
        for(int i=0;i<32;i++)
            lip[i/8]=new String(lip[i/8]+lbip[i]);
        int [] ipLast =new int[4];
        System.out.print("-");
        for(int i=0;i<4;i++)
        {
            System.out.print(ipLast[i]=Integer.parseInt(lip[i],2));
            if(i!=3)
                System.out.print(".");
        }
        System.out.println();
        for(int j=1;j<scount;j++)
        {
            System.out.println("GROUP "+(j+1));
            System.out.print("");
            for(int i=0;i<4;i++)
            {
                if(i<3)
                {
                    System.out.print(ipAddr[i]+".");
                }
                else
                    System.out.print(ipAddr[i]=ipAddr[i]+total_address);
            }
            System.out.print("-");
            for(int i=0;i<4;i++)
            {
                if(i<3)
                {
                    System.out.print(ipLast[i]+".");
                }
                else
                    System.out.println(ipLast[i]=ipLast[i]+total_address);
            }
        }
        	System.out.println("\nEnter IP Address 1: ");
            String pingAddress = sc.next();
            System.out.println("\nEnter IP Address 2: ");
            String pingAddressTwo = sc.next();
            String[] pingTest = pingAddress.split("\\.",5);
            String[] pingTestTwo = pingAddressTwo.split("\\.");
            if(Integer.valueOf(pingTestTwo[3]) - Integer.valueOf(pingTest[3])<32) {
            	System.out.println("These addresses can ping eachother.");
            }
            
            else{
            	System.out.println("Addresses cannot ping eachother.");
            }
    }
    static String appendZeroes(String s)
    {
        String temp= new  String("00000000");
        return temp.substring(s.length())+ s;
    }
} 


