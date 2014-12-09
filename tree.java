/*Class: Tree
 *This class builds a binary tree on top of an array
 *Methods are insertL, insertR, isLeaf
*/
import java.util.*;

public class tree
{
   protected int index; //mark place in tree
   String[] myArray = new String[200]; //array for tree
   
   //method to insert as left child of index 
   //parameters are index(int) and data(string)
   //void func()
   public void insertL(int num, String data) 
   {
      index = 2*num + 1;
      myArray[index]= data; 
   }
   //method to insert as right child of index 
   //parameters are index(int) and data(string)
   //void func()
   public void insertR(int num, String data)
   {
      
      index = 2*num + 2;
      
      myArray[index] = data;
   }
   //method to determine if array element is a leaf
   //parameter is index(int) 
   //returns true or false
   public boolean isLeaf(int num)
   {
      if((myArray[2*num+1]==null)&&(myArray[2*num+2]==null))
         return true;
      else
         return false;
   }
  
   
}//end class
