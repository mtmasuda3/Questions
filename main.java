/*Matsutaka Masuda
 *CPSC-330
 *Dr. Anewalt
 *Project 2: Question Game
 *This program is a game of questions with the computer
*/

import java.util.*;

class main
{
   public static void main(String[] args)
   {      
      char response; //for yes and no input
      char animalLetter; //for a or an with questions
      int index = 0; //tree location index
      String question, animal;
      boolean gameOver = false;
      Scanner read = new Scanner(System.in); //scanner object for input
      tree myTree = new tree(); //declare tree object
      int count= 0; //track runs
      System.out.println("This is a game of questions with the computer.");
      System.out.println("Please enter a 'y' or 'n' for the yes or no questions.");
      
      while(!gameOver)//play game until quit
      {
         index = 0;//index of tree, reset to root after computer guesses or gives up
         if(count==0)//initial run sets root
         {
            System.out.println("\nOk.  Think of an animal.  Ready? ('y' to continue):");
            response = read.next().charAt(0);//read response
            if((response=='y')||(response=='Y'))//response equals yes
            {
               System.out.println("\nI give up. What is it? ");//computer gives up since no knowledge is stored
               animal = read.next();//read animal
               animalLetter = animal.charAt(0);//depending on animal name, use an or a for questions
               if((animalLetter=='a')||(animalLetter=='A')||(animalLetter=='e')||(animalLetter=='E')||(animalLetter=='i')||(animalLetter=='I')||(animalLetter=='o')||(animalLetter=='O')||(animalLetter=='u')||(animalLetter=='U'))
               {
                  System.out.println("\nWhat question would tell me it's an "+animal+"?");
               }
               else
               {
                  System.out.println("\nWhat question would tell me it's a " + animal+"?");
               }
               read.nextLine();
               question = read.nextLine();//read question
               myTree.myArray[0] = question;//insert root    
               myTree.insertR(index, animal);//insert animal            
            }
            else//exit
            {
               System.out.println("\nGame Over: Thanks for playing. ");
               gameOver = true;
            }
            count++;
         }//end count 0
         else//begin count >= 1
         {
            boolean inPlace = false;//to place animal and question in right tree place 
            System.out.println("\nWould you like to keep playing? ('y' or 'n')");//ask user if they want to continue playing
            response = read.next().charAt(0);//read response
            
            if((response=='y')||(response=='Y'))//response equals yes
            {            
               System.out.println("\nOk.  Think of an animal.  Ready? ('y' to continue):");
               response = read.next().charAt(0);
               if((response=='y')||(response=='Y'))//if response is yes to continue, enter inPlace while loop
               {                  
                  while(!inPlace)//continue until in right place
                  {                     
                     System.out.println(myTree.myArray[index]);//ask question
                     response = read.next().charAt(0);
                     if((response=='y')||(response=='Y'))//move index to right child
                     {
                        index = 2 * index + 2;
                     }
                     else //move index to left child
                     {
                        index = 2 * index + 1;
                     }
                     
                     if(myTree.myArray[index]==null)//if current index is empty, computer gives up and asks questions
                     {
                        System.out.println("\nI give up. What is it?");
                        animal = read.next();//read animal
                        animalLetter = animal.charAt(0);//for a or an for questions
                        if((animalLetter=='a')||(animalLetter=='A')||(animalLetter=='e')||(animalLetter=='E')||(animalLetter=='i')||(animalLetter=='I')||(animalLetter=='o')||(animalLetter=='O')||(animalLetter=='u')||(animalLetter=='U'))
                        {
                           System.out.println("\nWhat question would tell me it's an "+animal+"?");
                        }
                        else
                        {
                           System.out.println("\nWhat question would tell me it's a " + animal+"?");
                        }
                        read.nextLine();
                        question = read.nextLine();//read question                    
                        myTree.myArray[index] = question;//insert question
                        myTree.insertR(index, animal);//insert animal                        
                        inPlace = true;
                     }
                     else if(myTree.isLeaf(index))//if current index is a leaf, computer guess
                     {
                        if(myTree.myArray[index]!=null)//check to make sure that the leaf is not null
                        {
                           animalLetter = myTree.myArray[index].charAt(0);//a or an for computer guess
                           if((animalLetter=='a')||(animalLetter=='A')||(animalLetter=='e')||(animalLetter=='E')||(animalLetter=='i')||(animalLetter=='I')||(animalLetter=='o')||(animalLetter=='O')||(animalLetter=='u')||(animalLetter=='U'))
                           {
                              System.out.println("\nIs it an "+myTree.myArray[index]+"?");
                           }
                           else
                           {
                              System.out.println("\nIs it a "+myTree.myArray[index]+"?");
                           }
                           response = read.next().charAt(0);//read response
                           if((response=='y')||(response=='Y'))//if yes to guess, computer wins
                           {
                              System.out.println("\nI win!");
                              inPlace = true;
                           }
                           else//if no to guess, computer gives up and asks questions
                           {
                              String temp = myTree.myArray[index];//store animal name in temp
                              System.out.println("\nI give up. What is it?");
                              animal = read.next();//read animal
                              animalLetter = animal.charAt(0);//a or an for question
                              if((animalLetter=='a')||(animalLetter=='A')||(animalLetter=='e')||(animalLetter=='E')||(animalLetter=='i')||(animalLetter=='I')||(animalLetter=='o')||(animalLetter=='O')||(animalLetter=='u')||(animalLetter=='U'))
                              {
                                 System.out.println("\nWhat question would tell me it's an "+animal+"?");
                              }
                              else
                              {
                                 System.out.println("\nWhat question would tell me it's a " + animal+"?");
                              }
                              read.nextLine();
                              question = read.nextLine();//read question
                              myTree.myArray[index] = question;//insert recently read question where old animal name was
                              myTree.insertL(index, temp);//insert temp(old animal) as the left child
                              myTree.insertR(index, animal);//insert new animal as the right child
                              inPlace = true;//everything in place
                           }
                        }
                        else//keep searching for right place
                        {
                           inPlace = false;
                        }                        
                     }
                  }//end inPlace while loop    
               }
               else//exit
               {
                  System.out.println("\nGame Over: Thanks for playing. ");
                  gameOver = true;   
               }               
            }
            else//exit
            {
               System.out.println("\nGame Over: Thanks for playing. ");
               gameOver = true;
            }                      
         }
      }//end gameOver while loop
   
   }//end static void main()

}//end class main