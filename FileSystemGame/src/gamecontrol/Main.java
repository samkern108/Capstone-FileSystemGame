package gamecontrol;

public class Main {
	
	private static Parser parser = new Parser();
	private static FileExplorer explorer = new FileExplorer();

	public static void main(String[] args)
	{		
		boolean playing = true;
		
		try {
			displayIntroSequence();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		while(playing)
		{
			System.out.print("Jakes-MacBook-Pro:"+explorer.cDir.alias+" jakewilcox$ ");
			parser.takeUserInput(explorer);
		}
	}
	
	private static void displayIntroSequence() throws InterruptedException
	{		
		System.out.print("Booting");
		for(int i = 0 ; i < 3; i++)
		{
			Thread.sleep(1500);
			System.out.print(".");
		}
		
		System.out.println("\n\n\n");
		
		System.out.println("Restart successful.");
		
		Thread.sleep(500);
		
		System.out.println("Last login: Tue Oct  6 17:54:49 on ttys000\n\n");
		
		Thread.sleep(1500);
		System.out.print(" > ");
		String cat = "cat README.txt";
		char[] catAr = cat.toCharArray();
	
		for(int i = 0; i < catAr.length; i++)
		{
			Thread.sleep((int)(500*Math.random()));
			System.out.print(""+catAr[i]);
		}
		
		System.out.println("\n");
		Thread.sleep(1000);
		
		System.out.print("Opening file");
		for(int i = 0 ; i < 3; i++)
		{
			Thread.sleep(1000);
			System.out.print(".");
		}
		
		System.out.println("\n\n");
		
		explorer.cat("README.txt", false);
	}
}
