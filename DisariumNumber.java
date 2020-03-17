import java.util.Scanner;
class DisariumNumber
{
	public static void main(String arg[])
  {
    Scanner input=new Scanner(System.in);
    int number=input.nextInt();
    int temp=number;
    double position=0;
    int temp1=number;//for counting no of digits
    while(temp1>0){
		position+=1;
		temp1/=10;	
    }
   
    int sum=0;
    while(temp>0)
    {
      
      double rem=temp%10;
      double val=Math.pow(rem,position);
      sum+=val;
      position-=1;
      temp=temp/10;
    }
    if(sum==number)
    {
      System.out.println("It is a DisariumNumber ! ");
    }
    else
    {
      System.out.println("It is not a DisariumNumber ! ");
    }
  }
}
