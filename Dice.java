import java.util.Random;

public class Dice {
	private int noOfDices;
	private int diceSize;
	public Dice(int noOfDices, int diceSize) {
		this.noOfDices = noOfDices;
		this.diceSize = diceSize;
	}

	public int DiceRolled() {
		int totalSumDice = 0;
		for (int i = 0; i < noOfDices; i++) {
			totalSumDice += new Random().nextInt(diceSize) + 1;
		}
		return totalSumDice;
	}
	
	public int getDiceSize(){
		return diceSize;
	}
	public int getNoOfDices(){
		return noOfDices;
	}
}