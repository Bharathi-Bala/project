import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
class MaximumNumber
{
	public static void main(String arg[])
	{
		Scanner input=new Scanner(System.in);
		int num=input.nextInt();
		int temp=num;
		ArrayList<Integer> numbers=new ArrayList<Integer>();
		while(temp>0)
		{
			int rem=temp%10;
			numbers.add(rem);
			temp/=10;
		}
		
		Collections.sort(numbers);
		Collections.reverse(numbers);
		
		int result=0;
		for(int a:numbers)
		{
			result=result*10+a;
		}
		
		System.out.println("Maximum Number that can be obtained from the given number : "+result);
	}
}
