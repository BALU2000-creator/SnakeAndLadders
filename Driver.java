import java.util.*;
public class Driver {
	public static void main(String[] args) {
		Scanner in=new Scanner(System.in);
		
		//Input Board size
		System.out.print("Enter Board size: ");
		int boardSize=Integer.parseInt(in.nextLine());
		
		//Input Dice
		System.out.print("Enter dice size:");
		int diceSize=Integer.parseInt(in.nextLine());
		System.out.print("Enter number of dices: ");
		int noOfDices=Integer.parseInt(in.nextLine());
		
		Dice dice=new Dice(noOfDices,diceSize);
		System.out.println(dice.getNoOfDices());
		System.out.println(dice.getDiceSize());
		
		//Input snakes;
		HashSet<Integer,Integer> SnakeMap=new HashMap<>();
		System.out.print("Enter number of snakes");
		int noOfSnakes=Integer.parseInt(in.nextLine());
		System.out.println("Enter intial and final position of each snakes in <Intital>space<Final>");
		for(int i=0;i<noOfSnakes;i++){
			String arr[]=in.nextLine().split(" ");
			//Snake obj=new Snake(Integer.parseInt(arr[0]),Integer.parseInt(arr[1]));
			//SnakeSet.add(obj);
			SnakeMap.put(Integer.parseInt(arr[0]),Integer.parseInt(arr[1]));
		}
		//Input Ladders
		HashSet<Ladder> LadderMap=new HashMap<>();
		System.out.print("Enter number of ladders");
		int noOfLadders=Integer.parseInt(in.nextLine());
		System.out.println("Enter intial and final position of each ladder in <Intital>space<Final>");
		for(int i=0;i<noOfLadders;i++){
			String arr[]=in.nextLine().split(" ");
			//Ladder obj=new Ladder(Integer.parseInt(arr[0]),Integer.parseInt(arr[1]));
			//LadderSet.add(obj);
			LadderMap.put(Integer.parseInt(arr[0]),Integer.parseInt(arr[1]));
		}

		SnakeAndLadderCorrector obj=new SnakeAndLadderCorrector(SnakeMap,LadderMap);
		obj.corrector();
		
		
	}
}