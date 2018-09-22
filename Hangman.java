package codeit; 

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Hangman {
  public static void main(String[] args) throws IOException {    
      String secretWord = choseWord(); 
      hangman(secretWord);
  }
  
  static void hangman(String secretWord)  {    
    Scanner s=new Scanner(System.in);
    
    int nguesses=8;
    int nletters=secretWord.length();
    int i=0;
    char guess;
    char[] lettersGuessed=new char[1000];    
    
    System.out.println("Welcome to the game Hangman!");      
    System.out.println("I am thinking of a word that is "+nletters+ " letters long.");
    System.out.println("-------------");  
    
    while (nguesses>0)
    { 
        System.out.println("You have "+nguesses+" guesses left");
        System.out.println("Available Letters: "+ getAvailableLetters(lettersGuessed));
        System.out.println("Please guess a letter: ");        
        
        guess=s.next().charAt(0);               
        
        if (!contains(guess,lettersGuessed))
        {       
                lettersGuessed[i]=guess;  
                i++;
                if (contains(guess, secretWord))
                    System.out.println("Good guess: " +getGuessedWord(secretWord, lettersGuessed));
                else
                { 
                    System.out.println("Oops! That letter is not in my word: " +getGuessedWord(secretWord, lettersGuessed));
                    nguesses--;
                }                              
        }
        else
            System.out.println("Oops! You've already guessed that letter: "+getGuessedWord(secretWord, lettersGuessed));
           
        System.out.println("-------------");
           
        if (isWordGuessed(secretWord, lettersGuessed))
          { 
            System.out.println("Congratulations, you won!");
            return;
          }           
    }   
    
    if (!isWordGuessed(secretWord, lettersGuessed))
       System.out.println("Sorry, you ran out of guesses. The word was "+secretWord+".");        
  }
  
  static boolean isWordGuessed(String secretWord, char[] lettersGuessed)
  {   
      boolean flag=true;
      int i=0;
      
      while(flag && i<secretWord.length())
      {
           flag=contains(secretWord.charAt(i),lettersGuessed);
           if(!flag)
                return flag;     
           i++;
      }
      
      return flag;
  }
  
  static String getGuessedWord(String secretWord,char[] lettersGuessed)
  {
      String s="";
      int i;
      
      for (i = 0; i < secretWord.length(); i++) 
      {
          if(contains(secretWord.charAt(i),lettersGuessed))
              s+=secretWord.charAt(i);
          else
              s+="_ ";
      }
      
      return s;
  }
  
  static String getAvailableLetters(char[] lettersGuessed)
  {
      String lowercase="abcdefghijklmnopqrstuvwxyz";
      String s="";
      
      for (int i = 0; i < lowercase.length(); i++)          
          if (!contains(lowercase.charAt(i),lettersGuessed))
            s+=lowercase.charAt(i);
      
      return s;
  }  
  
  static boolean contains(char secretWordLetter,char[] lettersGuessed)
  {
     for(char c:lettersGuessed)
        if(secretWordLetter==c)
             return true;

     return false;
  }  
  
  static boolean contains(char guessLetter,String secretWord)
  {
      for (int i = 0; i < secretWord.length(); i++) 
        if(guessLetter==secretWord.charAt(i))
             return true;
      
      return false;
  }  
  
  static String[] createWordList() throws IOException
  {
      BufferedReader reader = new BufferedReader(new FileReader(
              "E:\\Academic stuff\\CS Study Material\\e-books\\Computer\\Java\\HPES\\Assignment\\Hangman\\words.txt"));
      String line;
      String[] parts=null;     
              
      while ((line = reader.readLine()) != null) 
          parts = line.split("\\s");   
      
      return parts;
  }
  
  static String choseWord() throws IOException
  {
      int i=0;
      String[] words=createWordList();
      
      while(true)
       { 
           i=(int) (Math.random()*Math.pow(10,8));
           if(i<words.length)
               break;
       }
      
      return words[i];
  }
}
  
    
