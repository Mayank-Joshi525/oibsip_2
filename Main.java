/*
  Project Name :-- Number Guessing Game
  Author :- Mayank Joshi
  email :- mayankjoshi5252@gmail.com
  Language Used :- Java
  IDE :- Intelij Idea

 */



import java.util.Random;
import java.util.Scanner;
class round1{                           // ROUND 1 CLASS
    protected static int points,count;
    protected boolean round1_clear;
    protected static String name;

    static void  seprate(){  // made to make beautiful prsentation
        System.out.print("---------------------------------------------------------------");
        System.out.print("---------------------------------------------------------------");
        System.out.print("---------------------------------------------------------------");
        System.out.println("\n");
    }

    boolean start_game(){
        Scanner sc=new Scanner(System.in);
        System.out.println("\n\n                  Random Guesser Game");
        System.out.println("                            -- Luck Is Random  \n\n");
        System.out.println("Enter the name of the user :-");  // input to take user name
        name= sc.nextLine();
        Random rand=new Random(); // object of random class
        int min,max;
        System.out.println("\nRange of Number must be greater than 55 \n");
        System.out.println("Starting Range:- ");
        min=sc.nextInt();
        System.out.println("Ending Range:- ");
        max=sc.nextInt();

        if(min>max){
            System.out.println("MAXIMUM IS LESS THAN MINIMUM");
            return false;
        }
        if(max-min<=55){
            System.out.println("RANGE IS TOO LOW.. IT MUST BE GREATER THAN 55 ");
            return false;
        }
        System.out.println("\n          Round 1\n Rules  of Round 1 :- \n1 :- Guess The Number Within The Range\n2 :- Total points in this Round is 10,With Each Wrong Guess 1 Point Will Be Deducted\n3 :- You Need To Have 5 Or More Points To Qualify For Round 2");
        int num=rand.nextInt(max-min+1)+min; // RANDOM NUMBER
        System.out.println("\nGUESS THE NUMBER ? GIVEN RANGE "+min+" to "+max);
        points=10;
        int guess;
        count=0;
        int flag=-1;
        while (count!=10){
            System.out.printf("\nGuess Number [Chance (%d) ] \n",(count+1));
            guess=sc.nextInt();
            int er=0;
            if(guess<min || guess >max){
                er++;
                System.out.println("THIS CHOICE IS INVALID ENTER WITHIN THE RANGE :- "+min+" "+max);
                System.out.println("POINTS DEDUCTED BY 2 ");
                points-=2;
                if (er==2){
                    System.out.println("MULTIPLE INVALID GUESSES");
                    break;
                }
            }
            else if(guess==num){
                flag=1;
                break;
            }
            else if (guess<num) {
                System.out.println("Hint :- Little Higher");

            } else if (guess>num) {
                System.out.println("Hint :- Little Lower");

            }

            count++;

        }
        points-=count;
        if(flag==-1){
            round1_clear=false;
            seprate();
            System.out.println("ALL CHANCES ARE OVER ,YOU DO NOT GOT A MATCH");
            System.out.println("THE NUMBER WAS "+num);
            System.out.println();
        }
        else {
            if(points>=5){
                round1_clear=true;   // WON ROUND 1
            }
            else {
                round1_clear=false;
            }
            seprate();
            System.out.println(name+" Round 1 Completed With Points "+points);
            System.out.println("\n");
        }

        return round1_clear;
    }

}

class round2 extends round1{                // ROUND 2 CLASS
    protected int min,max,chance;
    boolean is_winner;
    Random rand=new Random();
    void set(){
        min=rand.nextInt(500-1+1)+1;
        max=rand.nextInt(1000-501+1)+501;           // FOR RANDOM RANGE
        int luck=rand.nextInt(9-2+1)+2;
        chance=points+luck;



    }
    round2(){                       // CONSTRUCTOR
        set();
    }
    boolean game(){
        seprate();
        int this_game_pont=0;
        System.out.println("                                    ROUND 2");
        System.out.println("WELCOME TO ROUND 2 "+name);
        System.out.println("\nRules For Round 2");
        System.out.println("\n1 :- The Range Will Be Decided By The Computer Randomly Between 1 to 1000");
        System.out.println("2 :- Total Chances Will Be Given Randomly To User Between 2 To 9 This Will Be Added To Points Of Round 1 And That Will Be Final Chances For Round 2");
        System.out.println("3 :- Points Will Be Added To Points Of Round 1");
        System.out.println("4 :- You Need To Score Points Greater Or Equal Than Total Chnaces To Win The Game");
        System.out.println("5 :- With Each Wrong Guess 1 Points Will Be Deducted From Total Chances\n");
        System.out.println("\n\n BEST OF THE LUCK \n\n");
        Scanner sc=new Scanner(System.in);
        System.out.println("Congrats, 🎊 You Got "+chance+" Chances By Luck in this final round \n");
        System.out.printf("                    Need To Guess The Number in first %d Guesses ",chance-(chance-points));
        int ch=chance-(chance-points);
        System.out.println(" -- GIVEN RANGE "+min+" "+max+" -- \n");
        int num=rand.nextInt(max-min+1)+min;
        int flag=0;
        int i=1;
        while (ch!=0){
            System.out.println("\nGuess Number [ Chance -> "+(i++)+"] ");
            int guess=sc.nextInt();
            int er=0;
            if(guess<min || guess >max){
                er++;
                System.out.println("THIS CHOICE IS INVALID ENTER WITHIN THE RANGE :- "+min+" "+max);
                System.out.println("\nPOINTS DEDUCTED BY 2 \n");
                points-=2;
                if (er==2){
                    System.out.println("\n MULTIPLE INVALID GUESSES \n");
                    break;
                }
            }
            else if(guess==num){
                flag=1;
                break;
            }
            else if (guess<num) {
                System.out.println("Hint :- Litter Higher");

            } else if (guess>num) {
                System.out.println("Hint :- Little Lower");

            }

            ch--;

        }
        i--;
        this_game_pont=chance-i;
        points+=this_game_pont;
        if (points>=(chance) && flag==1){

            is_winner=true;
        }

        else {
            System.out.println("\nThe Number Was "+num);
            System.out.println("\nGAME OVER !! \n");
            System.out.println("\n");
            points-=this_game_pont;
            is_winner=false;

        }
        return is_winner;
    }


}

public class Main {
    public static void main(String[] args) {
        round1 ob=new round1();         // OBJECT OF ROUND 1
        if(ob.start_game()){
            System.out.println("SUCCESSFULLY QUALIFIED FOR ROUND 2 ");
            round1.seprate();
            round2 obj=new round2();
            if (obj.game()){  // WON ROUND 1
                round1.seprate();
                System.out.println("Congratulations "+round1.name+" ✨🎉✨🎉 \n");      // WINNER
                System.out.println("You Won The Number Guessing Game With Points "+round1.points+" .............");
                round1.seprate();
            }
            else {
                round1.seprate();
                System.out.println("Better Luck Next Time Total Points You Scored "+round1.points+" ..... \n");
                round1.seprate();

            }

        }
        else {
            round2.seprate();
            System.out.println("\n\nNot Able To Qualify For Round 2 ,RETRY WITH ROUND 1 ONCE AGAIN .... \n\n");
            round1.seprate();
        }

    }
}