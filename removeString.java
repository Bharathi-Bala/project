import java.util.Arrays;
import java.util.Scanner;
 class RemoveString
{
  public static void main(String ar[])
  {
   
    Scanner input=new Scanner(System.in);
    System.out.println("Enter the Sentence  ?");
    String sentence=input.nextLine();
    System.out.println("Enter the word/letter to be removed ?");
    String remove=input.nextLine();
    
//using arrays
   
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
   
//using substring
   
    String result="";
    for(int i=0;i<sentence.length();i++){
      if(sentence.substring(i,i+remove.length()).equals(remove) && (i+remove.length()<=sentence.length()))
    {

        i=(i+remove.length()-1);
      
      }
      else{
        result+=sentence.substring(i,i+1);
      }
    }
    System.out.println("result  : "+result);

    // using replace;
    String replace=sentence.replace(remove,"");
    System.out.println(replace);
  }
}
