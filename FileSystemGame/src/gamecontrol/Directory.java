package gamecontrol;

import java.util.ArrayList;

public class Directory
{
	String name;
	String alias;
	String password;
	String hint;
	ArrayList<Directory> directories;
	ArrayList<File> files;
	Directory parent;

	public Directory(String name, String password, String hint, String alias, Directory p)
	{
		this.name = name;
		this.password = password;
		this.hint = hint;
		if(alias != null) {
			this.alias = alias;
		}
		else {
			this.alias = name;
		}
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
			ls += "      ";
		}
		for(File f : files)
		{
			ls += f.name;
			ls += "      ";
		}
		return ls;
	}
}