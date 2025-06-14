import java.util.concurrent.ThreadLocalRandom;

public class main {

	public static player speletajs = new player();	
	public static monster zombie = new monster();
	public static items items = new items();
	public static tree tree = new tree();
	
	public static String[][] map = new String[10][10];
	
	public static void main(String[] args) {
		
		speletajs.name = "Aivars";
		speletajs.klase = "Wizard";
		speletajs.icon = "[@]";
		tree.icon = "[T]";
		
		zombie.name = "Zombie";
		
		speletajs.isDead = false;
		printPlayer(speletajs);	

		generateMap();
		drawMap();
		
	}

	public static void generateMap() {
		for(int y = 0; y <= map.length-1; y++) {
			for(int x = 0; x <= map.length-1; x++) {
				map[y][x] = "[ ]";
			}
		}
	}
	
	public static void drawMap() {
		boolean playerOOB = false;
		if(speletajs.x > map.length-1) {
			playerOOB = true;
		}
		
		if(speletajs.y > map.length-1) {
			playerOOB = true;
		}
		
		if(speletajs.x < map.length-1) {
			playerOOB = true;
		}
		
		if(speletajs.y < map.length-1) {
			playerOOB = true;
		}
		
		if(playerOOB) {
			for(int y = 0; y <= map.length-1; y++) {
				for(int x = 0; x <= map.length-1; x++) {
					if(y == speletajs.y && x == speletajs.x) {
						System.out.print(speletajs.icon);
					}else {
						System.out.print(map[y][x]);
					}
					if(y == tree.y && x == tree.x) {
						tree.x = ThreadLocalRandom.current().nextInt(1, 9);
						tree.y = ThreadLocalRandom.current().nextInt(1, 9);
						System.out.print(tree.icon);
					}else {
						System.out.print("");
					}
					if(y == tree.y && x == tree.x) {
						tree.x = ThreadLocalRandom.current().nextInt(1, 9);
						tree.y = ThreadLocalRandom.current().nextInt(1, 9);
						System.out.print(tree.icon);
					}else {
						System.out.print("");
					}
					
				}
				System.out.println("");
			}
			}else {
				System.out.println("");
				System.out.println("Player out of bounds");
			}
			
				
	}
		
	
	public static void attackPlayer(player pl, monster mstr) {
		if(!pl.isDead) {
			System.out.println("");
			System.out.println("Player " + pl.name + " is attacked by " + mstr.name + " for " + mstr.str + " damage");
			pl.health = pl.health - mstr.str; // sheit iedod dmg speletajam
			
			if(pl.health <= 0) {
				System.out.println("You are dead");
				pl.health = 0;
				pl.isDead = true;
			}	
		}else {
			System.out.println("You are dead and cant be attacked anymore");
		}

	}
	
	public static void attackMonster(player pl, monster mstr) {
		System.out.println("");
		System.out.println("Monster " + mstr.name + " is attacked by " + pl.name + " for " + pl.str + " damage");
		mstr.health = mstr.health - pl.str;
	}

	public static void printPlayer(player pl) {
		if(pl.torn_wizard_robe > 0) {
			pl.mana = pl.mana + 20;
		}
		if(pl.torn_wizard_robe > 0) {
			pl.str = pl.str + 5;
		}
		System.out.println("");
		System.out.println("name: " + pl.name);
		System.out.println("-------------");
		System.out.println("x: " + pl.x);
		System.out.println("y: " + pl.y);
		System.out.println("health: " + pl.health);
		System.out.println("str: " + pl.str);
		System.out.println("stamina: " + pl.stamina);
		System.out.println("mana: " + pl.mana);
		System.out.println("klase: " + pl.klase);
		System.out.println("isDead: " + pl.isDead);
		System.out.println("-------------");
		System.out.println("Your inventory");
		System.out.println("-------------");
		if(pl.wood_twig > 0) {
			System.out.println(pl.wood_twig + " " + items.wood_twig_item);
		}
		if(pl.torn_wizard_robe > 0) {
			System.out.println(pl.torn_wizard_robe + " " + items.torn_wizard_robe_item);
		}
		System.out.println("");
	}
}
