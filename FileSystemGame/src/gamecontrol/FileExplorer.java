package gamecontrol;

import java.util.ArrayList;

public class FileExplorer {
	
	public class Directory
	{
		String name;
		String alias;
		ArrayList<Directory> directories;
		ArrayList<File> files;
		Directory parent;
		
		public Directory(String name, Directory p)
		{
			this.name = name;
			this.alias = name;
			directories = new ArrayList<Directory>();
			files = new ArrayList<File>();
			parent = p;
		}
		
		public void AddDirectories(ArrayList<Directory> dirs)
		{
			directories = dirs;
		}
		
		public void AddFiles(ArrayList<File> f)
		{
			files = f;
		}
		
		public String pwd()
		{
			Directory p = this;
			String pwd = "";
			while(p != null)
			{
				pwd = "/" + p.name + pwd;
				p = p.parent;
			}
			return pwd;
		}
		
		public String ls()
		{
			String ls = "";
			for(Directory d : directories)
			{
				ls += d.name;
				ls += "\t";
			}
			for(File f : files)
			{
				ls += f.name;
				ls += "\t";
			}
			return ls;
		}
	}
	
	public class File
	{
		String name;
		String contents;
		
		public File(String name, String contents)
		{
			this.name = name;
			this.contents = contents;
		}
	}
	
	public Directory root;
	public Directory cDir;
	
	public FileExplorer()
	{
		FileSystemBuilder builder = new FileSystemBuilder();
		root = builder.BuildFileSystem();
		cDir = root;
	}
	
	public void cd(String command)
	{
		String[] cAr = command.split("\\");		
	}
	
	public void ls()
	{
		System.out.println(cDir.ls());
	}
	
	public void pwd()
	{
		System.out.println(cDir.pwd());
	}

	
	private class FileSystemBuilder
	{
		public Directory BuildFileSystem()
		{
			Directory root = BuildRoot();
			return root;
		}
		
		private Directory BuildRoot()
		{
			Directory root = new Directory("Jake's Hard Drive", null);
			ArrayList<Directory> dir = new ArrayList<Directory>();
			dir.add(BuildDesktop(root));
			dir.add(BuildDocuments(root));
			dir.add(BuildPictures(root));
			dir.add(BuildDownloads(root));
			ArrayList<File> files = new ArrayList<File>();
			
			root.alias = "~";
			
			root.AddDirectories(dir);
			root.AddFiles(files);
			return root;
		}
		
		private Directory BuildDesktop(Directory parent)
		{
			ArrayList<Directory> dir = new ArrayList<Directory>();
			ArrayList<File> files = new ArrayList<File>();
			
			Directory desktop = new Directory("Desktop", parent);
			desktop.AddDirectories(dir);
			desktop.AddFiles(files);
			
			return desktop;
		}
		
		private Directory BuildDownloads(Directory parent)
		{
			ArrayList<Directory> dir = new ArrayList<Directory>();
			ArrayList<File> files = new ArrayList<File>();
			
			Directory downloads = new Directory("Downloads", parent);
			downloads.AddDirectories(dir);
			downloads.AddFiles(files);
			
			return downloads;
		}
		
		private Directory BuildDocuments(Directory parent)
		{
			ArrayList<Directory> dir = new ArrayList<Directory>();
			ArrayList<File> files = new ArrayList<File>();
			
			Directory documents = new Directory("Documents", parent);
			documents.AddDirectories(dir);
			documents.AddFiles(files);
			
			return documents;
		}
		
		private Directory BuildPictures(Directory parent)
		{
			ArrayList<Directory> dir = new ArrayList<Directory>();
			ArrayList<File> files = new ArrayList<File>();
			
			Directory pictures = new Directory("Pictures", parent);
			pictures.AddDirectories(dir);
			pictures.AddFiles(files);
			
			return pictures;
		}
	}
}
