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
		String[] inputAr = input.split(" ", 2);

		switch(inputAr[0])
		{
		case "pwd":
			explorer.pwd();
			break;
		case "ls":
			explorer.ls();
			break;
		case "pfs":
			explorer.pfs();
			break;
		case "cat":
			explorer.cat(inputAr.length == 1 ? null : inputAr[1]);
			break;
		}
	}
}
