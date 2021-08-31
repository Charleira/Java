package MAIN;
import java.util.Scanner;
import java.util.Arrays;

public class GAME {

	public static void main(String[] args)
	{
		String p1, p2;
		char cp1 = 'n', cp2 = 'n';
		int f = 0;

		int totalPlays = 0;
		
		String[][] grid = new String[3][3];
		
		Scanner input = new Scanner(System.in);
		
		for(int i = 0; i < grid.length; i++)
		{
			for(int j = 0; j < grid.length; j++)
			{
				grid[i][j] = String.valueOf(f);
				f++;
			}
		}
		
		System.out.println("Seja bem-vindo ao jogo da velha!");
		
		while(cp1 != 'X' || cp1 != 'O')
		{
			System.out.print("Jogador 1, escolha seu símbolo (Digite X ou O): ");
			p1 = input.next();
			cp1 = p1.toUpperCase().charAt(0);
			
			if(cp1 == 'X') 
			{
				cp2 = 'O';
				System.out.println("Jogador 1: " + cp1 + " / " + "Jogador 2: " + cp2);
				break;
			}
			else if(cp1 == 'O') 
			{
				cp2 = 'X';
				System.out.println("Jogador 1: " + cp1 + " / " + "Jogador 2: " + cp2);
				break;
			}
			else 
			{
				System.out.println("ERRO! Você deve digitar X ou O, para identificar os jogadores");
			}
		}

		PrintGrid(grid);
		
		boolean cp1Win = false, cp2Win = false;
		int nextPlayer = 1;
		int play = 0;
		
		while(!cp1Win || !cp2Win)
		{
			System.out.println("Jogador " + nextPlayer + ", escolha uma posição para jogar.");
			play = input.nextInt();
			
			if(play > 9)
			{
				System.out.println("ERRO! Você deve jogar em uma posição entre 0 e 9");
				continue;
			}
			if(grid[play / 3][play % 3].equals("X") || grid[play / 3][play % 3].equals("O"))
			{
				System.out.println("ERRO! Esse campo já está preenchido");
				continue;
			}
			else if(nextPlayer == 1)
			{
				grid[play / 3][play % 3] = String.valueOf(cp1);
				nextPlayer = 2;
				totalPlays++;
			}
			else
			{
				grid[play / 3][play % 3] = String.valueOf(cp2);
				nextPlayer = 1;
				totalPlays++;
			}
			if(WinCheck(grid, totalPlays))
			{
				if(nextPlayer == 1)
				{
					nextPlayer = 2;
				}
				else
				{
					nextPlayer = 1;
				}
				PrintGrid(grid);
				System.out.println("O jogador " + nextPlayer + " ganhou!");
				break;
			}
			if(totalPlays == 9)
			{
				PrintGrid(grid);
				System.out.println("O jogo empatou!");
				break;
			}
			PrintGrid(grid);
 		}
		
		System.out.print("Execute o código para jogar novamente");
	}
	
	public static void PrintGrid(String[][] grid) 
	{
		System.out.println("");
		System.out.println(Arrays.deepToString(grid).replace("],","\n━━━━━━━━━━━━━━━\n").replace(","," │ ").replaceAll("[\\[\\]]", " "));
		System.out.println("");
	}
	
	public static boolean WinCheck(String[][] grid, int totalPlays)
	{
		String[] winGrid = new String[9];
		
		boolean win = false;
		
		for(int i = 0; i < winGrid.length; i++)
		{
			winGrid[i] = grid[i / 3][i % 3];
		}
		
		for(int i = 0, j = 0, k = 4; i < 9; i+=3, j+=1, k-=2)
		{

			if(winGrid[i].equals(winGrid[i + 1]) && winGrid[i + 1].equals(winGrid[i + 2]))
			{
				return win = true;
			}
			if(winGrid[j].equals(winGrid[j + 3]) && winGrid[j + 3].equals(winGrid[j + 6]))
			{
				return win = true;
			}
			if(winGrid[4 - k].equals(winGrid[4]) && winGrid[4].equals(winGrid[4 + k]) && 0 < k && k <= 8)
			{
				return win = true;
			}
		}

		return false;
		
	}
	
}