
public class Dice {
	private int diceNum = 0;
	private static final int MIN_VALUE = 1; 
    private static final int MAX_VALUE = 6;
	public Dice() {
		random();
	}

	public int getDiceNum() {
		return diceNum;
	}

	public void setDiceNum(int diceNum) {
		this.diceNum = diceNum;
	}
	
	public void random() {
		setDiceNum((int)(Math.random()*MAX_VALUE)+MIN_VALUE);
	}
	
	public void printDice() {
		System.out.print("주사위 숫자는:  ");
		System.out.print(getDiceNum());
	}
	
}
