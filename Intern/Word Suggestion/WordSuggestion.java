import java.io.*;
import java.util.*;
class MatchedWords
{
  int freq=0;
  String word="";
  MatchedWords(int f,String s)
  {
    freq=f;
    word=s;
  }
}

class myComparator implements Comparator<MatchedWords>
{
  public int compare(MatchedWords a,MatchedWords b) //comparing according to frequency
  {
    return b.freq-a.freq;
  }
}

class WordSuggestion
{
  public static void main(String[] args)
  throws IOException{
    FileReader fr=null;
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    try
    {
      System.out.println("Enter File Path: ");
      fr=new FileReader(br.readLine().trim()); //reading a file
    }
    catch(Exception e)
    {
      System.out.println("File Not Found !");
    }
    if(args.length==0)
    {
      System.out.println("No Argument Provided");
      return;
    }
    int k;
    ArrayList<Character> input=new ArrayList<Character>();
    ArrayList<ArrayList<Character>> al=new ArrayList<>();
    while((k=fr.read())!=-1)
    {
      if(k==10)
      {
        ArrayList<Character> dmy= (ArrayList<Character>)input.clone();
        //here we have used a dummy list through which we can append all the values of input in big list
        al.add(dmy);
        input.clear();
      }
      else
      {
        if(k>=65 && k<=90 || k>=97 && k<=122)
        {
        input.add((char)k);
        }
      }
    }

    String s=args[0];
    int[] arr=new int[123];
    // This array is used as hash so that every character in string match with index withour iterating
    Arrays.fill(arr,0);
    for(int i=0;i<s.length();i++)
    {
      if((int)s.charAt(i)>=95) //converting characters into lowercase
      arr[(int)s.charAt(i)-32]=arr[(int)s.charAt(i)-32]+1;
      else
      arr[(int)s.charAt(i)]=arr[(int)s.charAt(i)]+1;
     // ascii code is used as index here and value is 1 for every presend character
    }

    int count=0;
    ArrayList<MatchedWords> mainList=new ArrayList<MatchedWords>();
    // This arrayList is used to compare freq of words with given string
    for(int i=0;i<al.size();i++)
    {
      ArrayList<Character> ac=al.get(i); //comparing Dictionary words with input
      count=0;
      String string1="";
      for(int j=0;j<ac.size();j++)
      {
        if((int)ac.get(j)>=95)
        {
        if(arr[(int)ac.get(j)-32]>=1) // arr is used as hash to search in input string in O(1) time.
        count++;
        }
        else
        {
          if(arr[(int)ac.get(j)]>=1)
          count++;
        }
        string1+=ac.get(j)+"";
      }
      mainList.add(new MatchedWords(count,string1));
    }
    Collections.sort(mainList,new myComparator()); // calling comparator


    //Printing Strings with maximum matched characters
    StringBuffer sb=new StringBuffer();
    if(mainList.size()>5)
    {
    for(int i=0;i<5;i++)
    {
      MatchedWords obj=mainList.get(i);
      sb.append(obj.word+" ");
    }
    }
    else
    {
      for(int i=0;i<5;i++)
      {
        MatchedWords obj=mainList.get(i);
        sb.append(obj.word+" ");
      }
    }
    System.out.println(sb);
  }
}
