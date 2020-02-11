package module05.task01_3.XMarksTheSpot;

public class Loader
{
	
	public static void main(String[] args)
	{
		
		outputArray(makeArray(7));
		
	}
	
	private static char[][] makeArray(int dimension)
	{
		char[][] cross = new char[dimension][dimension];
		for (int i = 0; i < dimension; i++)
		{
			for (int j = 0; j < dimension; j++)
			{
					cross[i][j] = (i == j || i + j == dimension - 1)?'x':' ';
			}
		}
		return cross;
	}
	
	private static void outputArray(char[][] arr)
	{
		for (char[] i : arr)
		{
			for (char j : i) System.out.print(j);
			System.out.println();
		}
	}
}
