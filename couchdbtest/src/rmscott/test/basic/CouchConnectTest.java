/**
 * RMSCOTT Prototype
 */
package rmscott.test.basic;

import java.net.URI;

import org.lightcouch.CouchDbClient;

import com.google.gson.Gson;

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
		System.out.println("CouchConnectTest.main() : ..... begin execution ..... ");
		System.out.println();
		CouchDbClient dbClient = null;
		
		try {
			/*
			dbClient = new CouchDbClient("fantasy", true, 
					"http", "127.0.0.1", 5984, "rmscott", "secret");
			dbClient = new CouchDbClient("fantasy", true, 
					"http", "127.0.0.1", 5984, null, null);
					*/

			// the method below uses the couchdb.properties from classpath
			dbClient = new CouchDbClient();
			Gson gson = dbClient.getGson();
			System.out.println("Returned gson : " + gson);
			URI uri = dbClient.getBaseUri();
			System.out.println("Returned baseUri : " + uri);

			System.out.println();
			System.out.println("DB Names Below");
			System.out.println("--------------------------------------------------------------");

			System.out.println();

		} catch (Exception exc) {
			System.err.println(exc);
		} finally {
			try {
				dbClient.shutdown();
			} catch (Exception excX) {

			}
		}

		System.out.println("CouchConnectTest.main() : ..... ending execution ..... ");
		System.exit(0);

	}

}
