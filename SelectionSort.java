import java.util.Arrays;
import java.util.Scanner;
class SelectionSort
{
	public static void main(String arg[])
	{
		Scanner input=new Scanner(System.in);
		System.out.println("Enter the total number of elements in array");
		int length=input.nextInt();
		int[] array=new int[length];
		System.out.println("Enter the numbers");
		for(int i=0;i<length;i++)
		{
			array[i]=input.nextInt();
		}
		for(int i=0;i<(array.length-1);i++)
		{
			
			for(int j=i+1;j<array.length;j++)
			{
				if(array[i]>array[j])
				{
					int temp=array[i];
					array[i]=array[j];
					array[j]=temp;
				}
			}
		}
		System.out.println("final"+" :  "+Arrays.toString(array));
	}
}
