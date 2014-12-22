// Read inputs from System.in, Write outputs to System.out.
// Your class name has to be Solution

import java.util.*;

class Solution {

	public static void main(String args[]) {
		Solution s = new Solution();
		if (null != s.play())
		{
			System.out.println("Winner is : "+ s.play());
		}
		else
		{
			System.out.println("Error!");
		}
	}

	public Map<Integer,Integer> play()
	{
		int winner = 0;
		Map<Integer,Integer> game = new HashMap<Integer, Integer>();
		Player player1 = new Player(3,"P1");
		Player player2 = new Player(3,"P2");

		player1.push(new Card("AD"));
		player1.push(new Card("KC"));
		player1.push(new Card("QC"));

		player2.push(new Card("KH"));
		player2.push(new Card("QS"));
		player2.push(new Card("JC"));
		int count=0;
		
		
			Card c1 = player1.pop();
			Card c2 = player2.pop();
			
		 while(null != c1 || null != c2){
			
			if(null != c1 && null !=  c2)
			{
				 count++;
				int value1 = convertToValue(c1.getValue());
				int value2 = convertToValue(c2.getValue());

				System.out.println(" : "+value1+" "+" : "+value2);

				if(value1 > value2)
				{
					System.out.println("Player 1 wins this battle");
					player1.push(c1);
					player1.push(c2);
					winner = 1;
				}
				else if (value1 == value2)
				{
					System.out.println("This is a war");	
				}
				else
				{
					System.out.println("Player 2 wins this battle");
					player2.push(c2);
					player2.push(c1);
					
				}
			}
			
			if (null != c1 && null == c2)
			{
				System.out.println("Player1 wins the game with counts :"+ count);
				winner = 1;
				game.put(winner, count);
				return game;
			}
			else if (null != c2 && null == c1)
			{
				System.out.println("Player2 wins the game with counts :"+ count);
				game.put(winner, count);
				return game;
			}	
			
			c1 = player1.pop();
			c2 = player2.pop();
		}
		
		 return null;
	}

	private int convertToValue(String card) {
		int value=0;
		card = card.substring(0, 1);
		switch (card) {
		case "2":  value = 2;
		break;
		case "3":  value = 3;
		break;
		case "4":  value = 4;
		break;
		case "5":  value = 5;
		break;
		case "6":  value = 6;
		break;
		case "7":  value = 7;
		break;
		case "8":  value = 8;
		break;
		case "9":  value = 9;
		break;
		case "10": value = 10;
		break;
		case "J": value = 11;
		break;
		case "Q": value = 12;
		break;
		case "K": value = 13;
		break;
		case "A": value = 14;
		break;
		default: value = 0;
		break;
		}
		return value;
	}

	public class Card{
		String value;

		public String getValue() {
			return value;
		}

		public Card(String value) {
			this.value = value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}

	public class Player {
		int noOfCards=0;
		Queue deckStack = null;
		int top;
		String name;
		int rear = 0;  

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void push(Card j) {
			System.out.println(j.getValue()+" added");
			deckStack.add((Card)j);
		}

		public Card pop() {

			Card ret = null;
			if(deckStack.size() != 0)
			{
				try
				{
					ret = (Card) deckStack.poll();
					System.out.println("Value popped out : "+ ret.getValue());
				}
				catch(Exception e)
				{

				}
			}
			return ret;
		}

		public boolean isEmpty() {
			return (top == -1);
		}

		public Player(int cards,String nameOfPlayer)
		{
			noOfCards = cards;
			deckStack = new LinkedList<Card>();
			top = -1;
			name = nameOfPlayer;
		}

		public int getCards()
		{
			return noOfCards;
		}

		public void setCards(int cards)
		{
			noOfCards = cards;
		}

		public Queue getDeck()
		{
			return deckStack;
		}
	}
}
