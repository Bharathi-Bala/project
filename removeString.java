import java.util.Arrays;
import java.util.Scanner;
 class RemoveWordFromSentence
{
  public static void main(String ar[])
  {
    Scanner input=new Scanner(System.in);
    System.out.println("Enter the Sentence  ?");
    String sentence=input.nextLine();
    System.out.println("Enter the word/letter to be removed ?");
    String remove=input.nextLine();
    

    if(sentence.contains(remove))
    {
      String output="";
      String resArr[];
      resArr=sentence.split(remove);
      for(int i=0;i<resArr.length;i++)
      {
        output+=resArr[i];
      }
      System.out.println(output);
    }
    else
    {
      System.out.println("The Sentence didn't contain the word to be removed !");
    }


    //another way using replace;
    String replace=sentence.replace(remove,"");
    System.out.println(replace);
  }
}
