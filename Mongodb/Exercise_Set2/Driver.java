package mongoDemo;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Driver {

	public static void main(String[] args) throws IOException {
		   DB db=(new MongoClient("localhost",27017)).getDB("test");
	       BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
           boolean flag=false;
           while(!flag) flag=authenticate(db,bufferedReader);
	}
	private static boolean authenticate(DB db,BufferedReader bufferedReader)throws IOException
	{
		boolean flag=true;
		System.out.println("User:");
		String user=bufferedReader.readLine();
		System.out.println("Password:");
		String Password=bufferedReader.readLine();
		
		if(db.authenticate(user, Password.toCharArray())){
		   DBCollection channelDBCollection= db.getCollection("babuinvtable");	
		   String command=null;
		   while(true){
			   System.out.println("what do you want to do?");
			   command=bufferedReader.readLine();
			     if(command.equals("exit")) break;
			     else if(command.equals("findAll"))	findall(channelDBCollection);
			     else if(command.equals("insertJSON")) insertJSON(bufferedReader,channelDBCollection);
		   }
		   
		   
		}
		else
		{
			System.out.println("invalid username/password...");
			flag=false;
		}
		return flag;
	}
	private static void insertJSON(BufferedReader bufferedReader,
			DBCollection channelDBCollection) throws IOException {
	  System.out.println("JSON");
	 // WriteResult insert = channelDBCollection.insert((List<DBObject>) JSON.parse(bufferedReader.readLine()));
		
	}
	private static void findall(DBCollection channelDBCollection) {
		DBCursor dbCursor =channelDBCollection.find();
		while(dbCursor.hasNext()) System.out.println(dbCursor.next());
		
	}
	

}
