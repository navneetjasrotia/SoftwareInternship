import java.io.*;
import java.util.*;
import java.lang.*;
class Permutation
{
 static StringBuffer sb=new StringBuffer();
 public static void checkPermutation(int ind,ArrayList<ArrayList<Character>> al,char[] arr)
 {
   if(ind==al.size())//base case
   {
     for(int j=0;j<arr.length;j++)
     {
      if(j!=arr.length-1)
      {
      sb.append(arr[j]+"");
      }
      else
      {
      sb.append(arr[j]+", ");
      }
     }
     return;
   }
   ArrayList<Character> ac=al.get(ind); // here we get character arraylist from main list
   for(int i=0;i<ac.size();i++)   // loop is used to get permutation for all the values present in the list
   {
     arr[ind]=ac.get(i);   //storing values in array so that at the base we can append all values into stringbuffer
     checkPermutation(ind+1,al,arr);  //ind+1 to get next arraylist in the recursion
   }
 }

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

     int k;
     ArrayList<ArrayList<Character>> al=new ArrayList<ArrayList<Character>>(); //biglist
     ArrayList<Character> input=new ArrayList<Character>();

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

    char[] arr=new char[al.size()]; // array is used to input elements in recursion eg. tree nodes
    checkPermutation(0,al,arr); // Here we have used tree recursion
    sb.setLength(sb.length()-2); // -2 is used to remove extra ", " from stringbuffer at the end
    System.out.println(sb);
 }
}
