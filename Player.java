import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class Player {

		int noOfCards=0;
		Queue deckStack = null;
		int top;
		String name;
		int rear = 0;  
		
		public Player()
		{
			
		}
		
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void push(Card j) {
			deckStack.add((Card)j);
			//printQueue();
		}

		public Card pop() {

			Card ret = null;
			if(deckStack.size() != 0)
			{
				try
				{
					ret = (Card) deckStack.poll();
					System.out.println("Value popped out : "+ ret.getValue());
					//printQueue();
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
		
		public void printQueue()
		{
			Object[] e = deckStack.toArray();
			for(int i=0; i<e.length;i++)
			{
				Card c = (Card)e[i];
				System.out.println(c.getValue());
			}
		}
	}


