package gamecontrol;

public class File
{
	String name;
	String contents;
	String password;
	String hint;

	public File(String name, String password, String hint, String contents)
	{
		this.name = name;
		this.contents = contents;
		this.password = password;
		this.hint = hint;
	}
}
