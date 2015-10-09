package gamecontrol;
public class FileExplorer {

	public Directory root;
	public Directory cDir;
	private File helpFile;

	public FileExplorer()
	{
		IOManager fileManager = new IOManager();
		root = fileManager.populateFileSystem();
		cDir = root; //current directory
		helpFile = fileManager.getHelpFile();
	}

	public void cd(String command)
	{
		String[] cAr = command.split("/");
		for (String s : cAr){
			if (s.equals("..")) {
				if (cDir.parent != null){
					cDir = cDir.parent;
				}
			} else if (s.equals(".")){
				cDir = root;
			} else {
				for (int i = 0; i < cDir.directories.size(); i++){
					if (s.equals(cDir.directories.get(i).name)){
						if(cDir.directories.get(i).password != null){
							boolean password = Parser.self.askForPassword(cDir.directories.get(i).name, 
									cDir.directories.get(i).password, cDir.directories.get(i).hint);
							if (!password){
								return;
							}
						}
						cDir = cDir.directories.get(i);
						i = 0;
					}
				}
			}	
		}
	}

	public void help()
	{
		System.out.println("\n"+helpFile.contents);
	}
	
	public void cat(String filename, boolean catf)
	{
		for(int i = 0; i < cDir.files.size(); i++)
		{
			if(filename.equals(cDir.files.get(i).name))
			{
				if(cDir.files.get(i).password != null)
				{
					boolean password = Parser.self.askForPassword(filename, cDir.files.get(i).password, cDir.files.get(i).hint);
					if(!password)
						return;
				}
				if(!catf)
					slowPrint(cDir.files.get(i).contents);
				else
					System.out.println(cDir.files.get(i).contents);
				return;
			}
		}
		System.out.println("Could not find file " + filename);
	}

	public void slowPrint(String s)
	{
		char[] catAr = s.toCharArray();
		try {		
			for(int i = 0; i < catAr.length; i++)
			{
				if(catAr[i] == '\n')
					Thread.sleep(500);
				else
					Thread.sleep((int)(70*Math.random()));
				System.out.print(""+catAr[i]);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	public void ls()
	{
		System.out.printf(cDir.ls()+"\n");
	}

	public void pwd()
	{
		System.out.println(cDir.pwd());
	}

	public void pfs()
	{
		printDirectoriesRecursive(root, "");
	}

	private void printDirectoriesRecursive(Directory dir, String tab)
	{
		System.out.println(tab + dir.name);
		tab+="\t";

		for(int i = 0; i < dir.directories.size(); i++)
		{
			printDirectoriesRecursive(dir.directories.get(i), tab);
		}
		for(int i = 0; i < dir.files.size(); i++)
		{
			System.out.println(tab + dir.files.get(i).name);
		}
	}
}
