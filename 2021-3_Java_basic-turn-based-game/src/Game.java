import java.util.Scanner;

public class Game {
	
	Scanner myScanner = new Scanner(System.in);
	Scanner enterScanner = new Scanner(System.in);
	int playerHP;
	String playerName;
	String playerWeapon;
	int choice;
	int monsterHP;
	
	int silverRing;
	
	boolean oldHag = false;
	
	
		
	public static void main(String[] args) {
		Game dublin;
		dublin = new Game();
		
		dublin.playerSetUp(); 
	
		dublin.townGate();
	}
	
	public void playerSetUp(){
		
		
		playerHP = 10;
		monsterHP = 15;

		playerWeapon = "Knife";

		System.out.println("Your HP: "+ playerHP);
		System.out.println("Your Weapon: "+ playerWeapon);
		
		System.out.println("Please enter your name:");
		
		playerName = myScanner.nextLine();
		
		System.out.println("Hello " + playerName + ", let's start the game!");	
		
		
	}	
	
	public void townGate(){
		
		System.out.println("\n------------------------------------------------------------------\n");
		System.out.println("You are at the gate of the town.");
		System.out.println("A guard is standing in front of you.");
		System.out.println("");
		System.out.println("What do you want to do?");
		System.out.println("");
		System.out.println("1: Talk to the guard");
		System.out.println("2: Attack the guard");
		System.out.println("3: Leave");
		System.out.println("\n------------------------------------------------------------------\n");

		choice = myScanner.nextInt();
		
		if(choice==1){
			if(silverRing==1){
				ending();
			}
			else{
				System.out.println("Guard: Hello there, stranger. So your name is " + playerName + "? \nSorry but we cannot let stranger enter our town.");
				enterScanner.nextLine();
				townGate();
			}
			
		}
		else if(choice==2){
			playerHP = playerHP-1;
			System.out.println("Guard: Hey don't be stupid.\n\nThe guard hit you so hard and you gave up.\n(You receive 1 damage)\n");
			System.out.println("Your HP: " + playerHP);
			enterScanner.nextLine();
			townGate();
		}
		else if(choice==3){
			crossRoad();
		}	
		else{
			townGate();
		}
	}
	
	public void crossRoad(){
		System.out.println("\n------------------------------------------------------------------\n");
		System.out.println("You are at a crossroad. If you go south, you will go back to the town.\n\n");
		System.out.println("1: Go north");
		System.out.println("2: Go east");
		System.out.println("3: Go south");
		System.out.println("4: Go west");
		System.out.println("\n------------------------------------------------------------------\n");
		
		choice = myScanner.nextInt();
		
		if(choice==1){
			north();
		}
		else if(choice==2){
			east();
		}
		else if(choice==3){
			townGate();
		}
		else if(choice==4){
			west();
		}
		else{
			crossRoad();
		}
	}
	
	public void north(){
		System.out.println("\n------------------------------------------------------------------\n");
		System.out.println("There is a river. You drink the water and rest at the riverside.");
		System.out.println("Your HP is recovered.");
		playerHP = playerHP + 1;
		System.out.println("Your HP: " + playerHP);
		System.out.println("\n\n1: Go back to the crossroad");
		System.out.println("\n------------------------------------------------------------------\n");
		
		choice = myScanner.nextInt();
		
		if(choice==1){
			crossRoad();
		}
		else{
			north();
		}
	}
	
	public void east(){
		System.out.println("\n------------------------------------------------------------------\n");
		System.out.println("You walked into a forest and found a Long Sword!");
		playerWeapon = "Long Sword";
		System.out.println("Your Weapon: "+ playerWeapon);
		System.out.println("\n\n1: Go back to the crossroad");
		System.out.println("\n------------------------------------------------------------------\n");
		
		choice = myScanner.nextInt();
		
		if(choice==1){
			crossRoad();
		}
		else{
			east();
		}
	}
	
	public void west(){
		System.out.println("\n------------------------------------------------------------------\n");
		System.out.println("You encounter a goblin!\n");
		System.out.println("1: Fight");
		System.out.println("2: Run");
		System.out.println("\n------------------------------------------------------------------\n");
		
		choice = myScanner.nextInt();
		
		if(choice==1){
			fight();
		}
		else if(choice==2){
			crossRoad();
		}
		else{
			west();
		}
	}
	
	public void fight(){
		System.out.println("\n------------------------------------------------------------------\n");
		System.out.println("Your HP: "+ playerHP);
		System.out.println("Monster HP: " + monsterHP);
		System.out.println("\n1: Attack");
		System.out.println("2: Run");
		System.out.println("\n------------------------------------------------------------------\n");
		
		choice = myScanner.nextInt();
		
		if(choice==1){
			attack();
		}
		else if(choice==2){
			crossRoad();
		}
		else{
			fight();
		}
	}
	
	public void attack(){
		int playerDamage =0;
		
		
		if(playerWeapon.equals("Knife")){
			playerDamage = new java.util.Random().nextInt(5); 
		}
		else if(playerWeapon.equals("Long Sword")){
			playerDamage = new java.util.Random().nextInt(8); 
		}
		
		System.out.println("You attacked the monster and gave " + playerDamage + " damage!");
		
		monsterHP = monsterHP - playerDamage;
		
		System.out.println("Monster HP: " + monsterHP);
		
		if(monsterHP<1){ win(); } else if(monsterHP>0){
			int monsterDamage = 0;
			
			monsterDamage = new java.util.Random().nextInt(4);
			
			System.out.println("The monster attacked you and gave " + monsterDamage + " damage!");
			
			playerHP = playerHP - monsterDamage;
			
			System.out.println("Player HP: " + playerHP);
			
			if(playerHP<1){ dead_(); } else if(playerHP>0){
				fight();
			}
		}
		
		
	}
	
	public void dead_(){
		System.out.println("\n------------------------------------------------------------------\n");
		System.out.println("You are dead!!!");
		System.out.println("\n\nGAME OVER");
		System.out.println("\n------------------------------------------------------------------\n");
		
	}
	
	public void win(){
		System.out.println("\n------------------------------------------------------------------\n");
		System.out.println("You killed the monster!");
		System.out.println("The monster dropped a ring!");
		System.out.println("You obtaind a silver ring!\n\n");
		System.out.println("1: Go east");
		System.out.println("\n------------------------------------------------------------------\n");
		
		silverRing = 1;
		
		choice = myScanner.nextInt();
		if(choice==1){
			crossRoad();
		}
		else{
			win();
		}
		
	}
	
	public void town() {
		System.out.println("\n------------------------------------------------------------------\n");
		System.out.println("You are in the town!");
		System.out.println("In front of you are people clapping and cheering for you, for defeating the goblin!");
		System.out.println("A woman walks up to you and gives flowers.\n");
		System.out.println("What do you do?\n\n");
		System.out.println("1: Take the flowers");
		if (oldHag == true) {
			System.out.println("2: Refuse to take the flowers");
		}else {
			System.out.print("");
		}
		System.out.println("\n------------------------------------------------------------------\n");
		
		choice = myScanner.nextInt();
		
		if(choice==1){
			takeTheFlowers();
		}
		else if(choice==2 && oldHag == true){
			refuseFlowers();
		}else {
			town();
		}
	}
	
	public void takeTheFlowers() {
		System.out.println("\n------------------------------------------------------------------\n");
		System.out.println("*Darkness enrupts the town, the people dissapear*");
		System.out.println("*The woman turns into an old hag*");
		System.out.println("She says: You really took the flowers, huh?");
		System.out.println("Are you so foolish to think that this would be so easy?");
		System.out.println("You wanted to be on top of the world...");
		System.out.println("But just defeating one goblin won't change things.");
		System.out.println("I will turn back time.\n\n");
		System.out.println("                          THE END                                     \n\n");
		System.out.println("1: Let her turn back time");
		System.out.println("2: Do not let her turn back time");
		System.out.println("\n------------------------------------------------------------------\n");
		
		choice = myScanner.nextInt();
		
		oldHag = true;
		
		playerHP = 10;
		playerWeapon = "Knife";
		monsterHP = 15;
		silverRing = 0;
		playerName = "";
		
		if(choice==1){
			townGate();
		}
		else if(choice==2){
			refuseTurnBackTime();
		}else {
			takeTheFlowers();
		}
	}
	
	public void refuseFlowers() {
		System.out.println("\n------------------------------------------------------------------\n");
		System.out.println("*Darkness enrupts the town, the people dissapear*");
		System.out.println("*The woman turns into an old hag*");
		System.out.println("She says: You knew...");
		System.out.println("You somehow, someway knew, huh?\n\n");
		System.out.println("                          THE END                                     \n\n");
		System.out.println("\n------------------------------------------------------------------\n");
	}
	
	public void refuseTurnBackTime() {
		System.out.println("\n------------------------------------------------------------------\n");
		System.out.println("Again?\n");
		System.out.println("You really thought it'd be that easy again?\n\n");
		System.out.println("                          THE END                                     \n\n");
		System.out.println("\n------------------------------------------------------------------\n");
		
		townGate();
	}
	
	public void ending(){
		System.out.println("\n------------------------------------------------------------------\n");
		System.out.println("Guard: Oh you killed that goblin!?? Great!");
		System.out.println("Guard: It seems you are a trustworthy guy. Welcome to our town!");
		System.out.println("\n------------------------------------------------------------------\n");
		town();
	}
}

