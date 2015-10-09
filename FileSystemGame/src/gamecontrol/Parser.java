package gamecontrol;

import java.util.Scanner;

public class Parser {
	
	public static Parser self;
	Scanner in;
	
	public Parser()
	{
		self = this;
		in = new Scanner(System.in);
	}

	public void takeUserInput(FileExplorer explorer)
	{
		System.out.print(" > ");
		String input = in.nextLine();
		String[] inputAr = input.split(" ", 2);

		switch(inputAr[0])
		{
		case "cd":
			if (inputAr.length == 2){
				explorer.cd(inputAr[1]);
			}
			break;
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
			explorer.cat(inputAr.length == 1 ? null : inputAr[1], false);
			break;
		case "catf":
			explorer.cat(inputAr.length == 1 ? null : inputAr[1], true);
			break;
		case "help":
			explorer.help();
			break;
		}
	}

	public boolean askForPassword(String name, String password, String hint) {
		System.out.print("Enter Password for " + name + " (or type \"hint\"):  ");
		String passInput = in.nextLine();
		
		if(passInput.equals("hint"))
		{
			System.out.println(hint);
			return askForPassword(name, password, hint);
		}
		
		if(!passInput.equals(password)) {
			System.out.println("Incorrect password for " + name);
			return false;
		}
		return true;
	}
}
