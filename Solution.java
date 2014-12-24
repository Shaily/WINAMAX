

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

		Map<Integer,Integer> game = new HashMap<Integer, Integer>();
		Player player1 = readPlayers(1);
		Player player2 = readPlayers(2);
		int count = 0;
		game = playGame(player1,player2,count);

		return game;
	}

	private Player readPlayers(int p) {
		Player player = new Player();

		File file = new File("c:/temp/data.txt");
		FileReader fr;
		try {
			fr = new FileReader(file);
			int i=0;
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine();
			int noOfCards = Integer.parseInt(line);

			if(p==2){
				//skip first noOfCards+1 lines
				for(int j=1;j<noOfCards+1;j++)
				{
					br.readLine();
				}
				
				line = br.readLine();
				noOfCards = Integer.parseInt(line);
			
			}
			
			
			String[] cards = new String[noOfCards];

				while((line = br.readLine()) != null && i <noOfCards)
				{		   		       
					cards[i] = line; 
					i=i+1;
				}
			
			
			br.close();
			fr.close();
			
			player = new Player (noOfCards,"Player"+p);

			for(int j=0;j<cards.length;j++)
				player.push(new Card(cards[j]));

			return player;
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return player;
	}

	private Map<Integer, Integer> playGame(Player player1,Player player2,int count) {
		Map<Integer,Integer> game = new HashMap<Integer, Integer>();
		int winner = 0;
		System.out.println("size : "+player1.deckStack.size()+" "+"size : "+player2.deckStack.size() + game);
		Card c1 = player1.pop();
		Card c2 = player2.pop();
	
		while(null != c1 || null != c2){

			if(null != c1 && null !=  c2)
			{
				
				int value1 = convertToValue(c1.getValue());
				int value2 = convertToValue(c2.getValue());

				System.out.println(" : "+value1+" "+" : "+value2 + "  : count : "+ count);
				
				if(value1 > value2)
				{
					count++;
					System.out.println("Player 1 wins this battle");
					player1.push(c1);
					player1.push(c2);

					winner = 1;
				}
				else if (value1 == value2)
				{
					System.out.println("This is a war");
					game = playWar(count,c1,c2,player1,player2);
					return game;
				}
				else
				{
					count++;
					System.out.println("Player 2 wins this battle");
					player2.push(c2);
					player2.push(c1);

				}
				
				System.out.println("size : "+player1.deckStack.size()+" "+"size : "+player2.deckStack.size());
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
				winner = 2;
				game.put(winner, count);
				return game;
			}	

			c1 = player1.pop();
			c2 = player2.pop();
		}
		return null;
	}

	private Map<Integer,Integer> playWar(int count,Card c1, Card c2, Player player1, Player player2) {

		System.out.println("Put the next three cards head down");

		Map<Integer,Integer> game = new HashMap<Integer, Integer>();
		Queue c1que = player1.getDeck();
		Queue c2que = player2.getDeck();

		System.out.println(c1que.size()+" "+c2que.size()+" "+game);

		if(c1que.size() <= 3 || c2que.size() <= 3)
		{
			game.put(3, count);
			return game;
		}
		else
		{
			for(int i=0; i <3; i++)
			{
				c1que.poll();
				c2que.poll();
			}
			System.out.println(c1que.size()+" "+c2que.size()+" "+game);
			Player p1 = new Player(c1que.size(),"SP1");
			Player p2 = new Player(c1que.size(),"SP2");

			p1.deckStack = c1que;
			p2.deckStack = c2que;

			game = playGame(p1, p2,count);
			
			return game;
		}


	}

	private int convertToValue(String card) {
		int value=0;
		
		
		card = card.substring(0, card.length()-1);
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

}
