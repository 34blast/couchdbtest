/**
 * RMSCOTT Prototype
 */
package rmscott.test.basic;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.ListCollectionsIterable;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

/**
 * @author rmscott
 *
 */
public class CouchConnectTest {

	public CouchConnectTest() {

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("MongoConnectTest.main() : ..... begin execution ..... ");
		System.out.println();
		MongoClient mongoClient = null;
		MongoDatabase db = null;

		try {
			mongoClient = new MongoClient();

			// show all database names
			MongoIterable<String> dbNames = mongoClient.listDatabaseNames();
			System.out.println();
			System.out.println("DB Names Below");
			System.out.println("--------------------------------------------------------------");
			for (String dbName : dbNames) {
				System.out.println(dbName);
			}

			db = mongoClient.getDatabase("fantasy");
			// show all collections
			System.out.println();
			System.out.println("DB Collection Names Below for fantasy");
			System.out.println("--------------------------------------------------------------");
			MongoIterable<String> allCollectionNames = db.listCollectionNames();
			for (String coll : allCollectionNames) {
				System.out.println(coll);
			}

			System.out.println();
			System.out.println("Print collection contents");
			System.out.println("--------------------------------------------------------------");
			ListCollectionsIterable<Document> collections = db.listCollections();
			for (Document doc : collections) {
				System.out.print("Guts of collection : ");
				System.out.println(doc.get("name"));
				System.out.println("--------------------------------------------------------------");
				System.out.println(doc.toJson());
				System.out.println();
			}
			System.out.println();

		} catch (Exception exc) {
			System.err.println(exc);
		} finally {
			try {
				mongoClient.close();
			} catch (Exception excX) {

			}
		}

		System.out.println("MongoConnectTest.main() : ..... ending execution ..... ");
		System.exit(0);

	}

}
