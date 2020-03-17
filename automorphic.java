import java.util.Scanner;
class AutomorphicNumber
{
	public static void main (String arg[])
	{
		Scanner input=new Scanner(System.in);
		System.out.println("Enter the number");
		long number= input.nextInt();
		long temp=number;
		long sqnum=number*number;
		
		int count=0;
		
		while(temp>0)
		{
		count+=1;
		temp/=10;
		
		}
		long squareLastDigits = sqnum%(long)Math.pow(10,count); //to find the last digits of squarenumber .
		if(squareLastDigits==number){
			System.out.println(number+" is Automorphic Number");
		}
		else{
			System.out.println(number+" is not an Automorphic Number");
		}
	}
}
