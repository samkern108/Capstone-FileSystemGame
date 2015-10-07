package gamecontrol;
public class FileExplorer {
	
	public Directory root;
	public Directory cDir;
	
	public FileExplorer()
	{
		FileManager fileManager = new FileManager();
		root = fileManager.populateFileSystem();
		cDir = root;
	}
	
	public void cd(String command)
	{
		String[] cAr = command.split("\\");		
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
