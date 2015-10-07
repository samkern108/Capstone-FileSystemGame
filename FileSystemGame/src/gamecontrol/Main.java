package gamecontrol;

public class Main {

	public static void main(String[] args)
	{		
		Parser parser = new Parser();
		FileExplorer explorer = new FileExplorer();
		
		boolean playing = true;
		
		System.out.println("Last login: Tue Oct  6 17:54:49 on ttys000");
		
		while(playing)
		{
			System.out.print("Jakes-MacBook-Pro:"+explorer.cDir.alias+" jakewilcox$ ");
			parser.takeUserInput(explorer);
		}
	}
}
