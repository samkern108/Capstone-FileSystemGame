package gamecontrol;

public class Main {

	public static void main(String[] args)
	{
		FileExplorer explorer = new FileExplorer();
		Parser parser = new Parser();
		boolean playing = true;
		
		while(playing)
		{
			parser.takeUserInput(explorer);
		}
	}
}
