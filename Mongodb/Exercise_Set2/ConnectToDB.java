package mongoDemo;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Set;
 
import com.mongodb.DB;
import com.mongodb.MongoClient;
 
public class ConnectToDB {
 
    public static void main(String[] args) {
        try {
             
            MongoClient mongoClient = new MongoClient("localhost");
             
            List<String> databases = mongoClient.getDatabaseNames();
             
            for (String dbName : databases) {
                System.out.println("- Database: " + dbName);
                 
                DB db = mongoClient.getDB(dbName);
                 
                Set<String> collections = db.getCollectionNames();
                for (String colName : collections) {
                    System.out.println("\t + Collection: " + colName);
                }
            }
            
            
            
            MongoClient mongoClient1 = new MongoClient("localhost");
            DB db = mongoClient1.getDB("test");
             
            char[] password = new char[] {'b', 'a', 'b', 'u'};
            boolean authenticated = db.authenticate("babu", password);
             
            if (authenticated) {
                System.out.println("Successfully logged in to MongoDB!");
            } else {
                System.out.println("Invalid username/password");
            }
            
            
             
            mongoClient.close();
            mongoClient1.close();
             
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        }
         
    }
}