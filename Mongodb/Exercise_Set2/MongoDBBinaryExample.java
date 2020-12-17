package mongoDemo;

import java.io.File;
import java.io.IOException;
 
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
 
public class MongoDBBinaryExample
{
    public static void main(String[] args) throws IOException
    {
        MongoClient mongo = new MongoClient("localhost", 27017);
        DB db = mongo.getDB("howtodoinjava");
        //Save a image in DB
        saveImageIntoMongoDB(db);
        //Get a image from DB
        getSingleImageExample(db);
        //Get all images from DB
        listAllImages(db);
        saveToFileSystem(db);      
        //Delete images from DB
        deleteImageFromMongoDB(db);
         
        //Verifying if image was deleted or not
        getSingleImageExample(db);
    }
     
    private static void saveImageIntoMongoDB(DB db) throws IOException {
        String dbFileName = "DemoImage";
        File imageFile = new File("C:/Users/GOD/Desktop/mongodb.png");
        GridFS gfsPhoto = new GridFS(db, "photo");
        GridFSInputFile gfsFile = gfsPhoto.createFile(imageFile);
        gfsFile.setFilename(dbFileName);
        gfsFile.save();
    }
     
    private static void getSingleImageExample(DB db) {
        String newFileName = "C:/Users/GOD/Desktop/mongodb.png";
        GridFS gfsPhoto = new GridFS(db, "photo");
        GridFSDBFile imageForOutput = gfsPhoto.findOne(newFileName);
        System.out.println(imageForOutput);
    }
 
     
    private static void listAllImages(DB db) {
        GridFS gfsPhoto = new GridFS(db, "photo");
        DBCursor cursor = gfsPhoto.getFileList();
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }
    }
     
    private static void saveToFileSystem(DB db) throws IOException {
        String dbFileName = "DemoImage";
        GridFS gfsPhoto = new GridFS(db, "photo");
        GridFSDBFile imageForOutput = gfsPhoto.findOne(dbFileName);
        imageForOutput.writeTo("C:/Users/GOD/Desktop/mongodb.png");
    }
     
    private static void deleteImageFromMongoDB(DB db) {
        String dbFileName = "DemoImage";
        GridFS gfsPhoto = new GridFS(db, "photo");
        gfsPhoto.remove(gfsPhoto.findOne(dbFileName));
    }
}