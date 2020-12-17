package mongoDemo;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class mongoDemo {

	public static void main(String[] args) throws Exception {
		int i;
		 try
		 {
				MongoClient mongoClient=new MongoClient("localhost",27017);
				DB db=mongoClient.getDB("mapReduceDB");
				System.out.println("connected to mongodb database");
				 DBCollection coll=db.getCollection("example3_results");
				 DBCursor cursor=coll.find();
							 while(cursor.hasNext()){
								 i=1;
								 System.out.println(cursor.next());
								 i++;
							 }
		 }
		 
		 catch(Exception e)
		 {
			 System.out.println(e);
		 }
		 
		 System.out.println("output is generated");

	}

}
