package gamecontrol;

import java.util.Scanner;

public class Parser {
	
	public enum commands {cd, ls};
	Scanner in;
	
	public Parser()
	{
		in = new Scanner(System.in);
	}

	public void takeUserInput(FileExplorer explorer)
	{
		String input = in.nextLine();
		
		switch(input)
		{
		case "pwd":
			explorer.pwd();
			break;
		case "ls":
			explorer.ls();
			break;
		}
	}
}
