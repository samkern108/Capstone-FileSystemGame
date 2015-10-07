package gamecontrol;

import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;


public class FileManager 
{
	private String pathToJSONFile = "data/filedirectory.json";
	private JSONParser parser = new JSONParser();

	public Directory populateFileSystem()
	{
		File currDir = new File(".");
		String path = currDir.getAbsolutePath();
		path = path.substring(0, path.length()-1) + pathToJSONFile;

		File jsonFile = new File(path);

		Scanner fileInput = null;
		try 
		{
			fileInput = new Scanner(jsonFile);
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}

		String json = "";

		while(fileInput.hasNextLine())
		{
			json += fileInput.nextLine();
		}

		Directory root = null;
		
		try{
			JSONObject obj = (JSONObject)parser.parse(json); 
			root = ParseDirectory(obj);
		}
		catch(Exception e){
			System.out.println("Exception caught while parsing json file:  " + e);
		}
		
		return root;
	}
	
	private Directory ParseDirectory(JSONObject obj)
	{
		return ObjectToDirectory((JSONObject) obj, null);
	}
	
	private ArrayList<gamecontrol.File> JSONFetchFiles(JSONObject obj)
	{
		JSONArray array = (JSONArray)obj.get("files");
		ArrayList<gamecontrol.File> files = new ArrayList<gamecontrol.File>();
		for(int i = 0; i < array.size(); i++)
		{
			files.add(ObjectToFile((JSONObject)array.get(i)));
		}
		return files;
	}
	
	private ArrayList<Directory> JSONFetchDirectories(JSONObject obj, Directory parent)
	{
		JSONArray array = (JSONArray)obj.get("dirs");
		ArrayList<Directory> dirs = new ArrayList<Directory>();
		for(int i = 0; i < array.size(); i++)
		{
			dirs.add(ObjectToDirectory((JSONObject)array.get(i), parent));			
		}
		return dirs;
	}
	
	private Directory ObjectToDirectory(JSONObject obj, Directory parent)
	{
		Directory dir = new Directory((String)obj.get("name"), (String)obj.get("alias"), parent);
		dir.AddDirectories(JSONFetchDirectories(obj, dir));
		dir.AddFiles(JSONFetchFiles(obj));
		return dir;
	}
	
	private gamecontrol.File ObjectToFile(JSONObject obj)
	{
		return new gamecontrol.File((String)obj.get("name"), (String)obj.get("password"), (String)obj.get("hint"), (String)obj.get("content"));
	}
}
