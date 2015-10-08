package gamecontrol;
public class FileExplorer {
	
	public Directory root;
	public Directory cDir;
	
	public FileExplorer()
	{
		FileManager fileManager = new FileManager();
		root = fileManager.populateFileSystem();
		cDir = root; //current directory
	}
	
	public void cd(String command)
	{
		String[] cAr = command.split("/");
		for (String s : cAr){
			if (s.equals("..")) {
				if (cDir.parent != null){
					cDir = cDir.parent;
				}
			}
			for (int i = 0; i < cDir.directories.size(); i++){
				if (s.equals(cDir.directories.get(i).name)){
					cDir = cDir.directories.get(i);
					i = 0;
				}
			}
		}
	}
	
	public void cat(String filename)
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
				System.out.println(cDir.files.get(i).contents);
				return;
			}
		}
		System.out.println("Could not find file " + filename);
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
