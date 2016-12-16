/**
 * RMSCOTT Prototype
 */
package rmscott.test.basic;

import java.util.Collections;
import java.util.List;

import org.lightcouch.CouchDbClient;
import org.lightcouch.NoDocumentException;
import org.lightcouch.View;

import rmscott.common.Person;
import rmscott.football.FootballPosition;
import rmscott.football.Player;
import rmscott.football.PlayerComparator;
import rmscott.football.Team;

/**
 * Test class uses 2 views created in the players design allplayers and giants.
 * The views excluded a dummy record. When I deleted all records the views go
 * away
 * 
 * @author rmscott
 *
 */
public class PlayerCouchCRUDTest {
	static public String FANTASY_DB_NAME = "fantasy";
	static public String PLAYER_COL_NAME = "player";
	private CouchDbClient dbClient = null;

	public PlayerCouchCRUDTest() {
		this.initialize();
	}

	protected void initialize() {

		try {
			dbClient = new CouchDbClient();
		} catch (Exception exc) {
			System.err.println(exc);
		}

	}

	protected void finalize() throws Throwable {
		try {
			dbClient.shutdown();
		} finally {
			super.finalize();
		}
	}

	/**
	 * Keep in a dummy record or couchdb removes the views needed Created it if
	 * needed otherwise do nothing
	 */
	public void addDummyPlayer() {
		System.out.println();
		System.out.println("addDummyPlayer ...................");
		System.out.println();

		try {
			dbClient.find(Person.class, "dummy");
		} catch (NoDocumentException docExc) {
			System.out.println("no document found for key of dummy so add it");
			Person person = PlayerCouchCRUDTest.getDummyRecord();
			try {
				dbClient.save(person);
			} catch (Exception excInner) {
				System.err.println("Error could not add Dummy Record");
			}
		} catch (Exception exc) {
			System.out.println(exc);
			System.out.println("class Name : " + this.getClass().getName());
		}

	} // end of addDummyPlayer

	public void testAddPerson() {
		System.out.println();
		System.out.println("testAddPerson ...................");
		System.out.println();

		Person person = PlayerCouchCRUDTest.getOnePerson();
		try {
			dbClient.save(person);
		} catch (Exception exc) {
			System.err.println("Error Saving adding a person");
			System.err.println(exc);
		}

	} // end of testAddPerson

	public void testReadOnePerson() {
		System.out.println();
		System.out.println("testReadOnePerson ...................");

		Person person = dbClient.find(Person.class, "P1");
		System.out.print("Guts of Person");
		System.out.println("--------------------------------------------------------------");
		System.out.println(person);
		System.out.println();

	} // end of testReadOnePerson

	public void testDeleteOnePerson() {
		System.out.println();
		System.out.println("testDeleteOnePerson ...................");

		try {
			Person person = dbClient.find(Person.class, "P1");
			if (person == null) {
				System.out.println("No player returned");
			} else {
				dbClient.remove(person);
				System.out.println("Person P1 deleted ");
			}
		} catch (NoDocumentException docExc) {
			System.out.println("no document found for key of P1");
		} catch (Exception exc) {
			System.out.println(exc);
			System.out.println("class Name : " + this.getClass().getName());
		}

	} // end of testReadOnePerson

	public void testAddPlayers() {
		System.out.println();
		System.out.println("testAddPlayers ...................");
		System.out.println();

		Player[] players = PlayerCouchCRUDTest.getInitialPlayers();
		for (Player player : players) {
			try {
				dbClient.save(player);
			} catch (Exception exc) {
				System.err.println("Error Saving adding a player");
				System.err.println(exc);
			}
		}

	} // end of testAddPlayers

	public void testAddDuplicate() {
		System.out.println();
		System.out.println("testAddDuplicate ...................");
		System.out.println();

		// Insert the document
		Player player = PlayerCouchCRUDTest.getOnePlayer();
		try {
			dbClient.save(player);
			System.err.println("ERROR should have got exception");
		} catch (Exception exc) {
			System.out.println("Pass: received Couch db exception as expected");
		}

	} // end of testAddDuplicate

	public void testReadAllPlayers() {
		System.out.println();
		System.out.println("testReadAllPlayers ...................");

		View playerView = dbClient.view("players/allplayers");
		playerView.includeDocs(true);
		List<Player> players = playerView.query(Player.class);

		for (Player player : players) {
			System.out.print("Guts of collection : Player");
			System.out.println("--------------------------------------------------------------");
			System.out.println(player);
			System.out.println();
		}
		System.out.println();

	} // end of testReadAllPlayers

	/**
	 * This method assumes there is a design document named players and a view
	 * named giants. It checks that the doc.team.teamName == "Giants"
	 */
	public void testReadGiantsPlayers() {

		System.out.println();
		System.out.println("testReadGiantsPlayers ...................");

		View playerView = dbClient.view("players/giants");
		playerView.includeDocs(true);
		List<Player> players = playerView.query(Player.class);

		for (Player player : players) {
			System.out.print("Guts of collection : Player");
			System.out.println("--------------------------------------------------------------");
			System.out.println(player);
			System.out.println();
		}
		System.out.println();

	} // end of testReadGiantsPlayers

	/**
	 * This method assumes there is a design document named players and a view
	 * named allplayers. Sorting is done after docs are returned. I did not see
	 * an easy way to do this in CouchDB
	 * 
	 */
	public void testRankPlayers() {
		System.out.println();
		System.out.println("testRanklayers ...................");
		System.out.println();

		View playerView = dbClient.view("players/allplayers");
		playerView.includeDocs(true);
		List<Player> players = playerView.query(Player.class);
		Collections.sort(players, new PlayerComparator());

		for (Player player : players) {
			System.out.print("Guts of collection : Player");
			System.out.println("--------------------------------------------------------------");
			System.out.println(player);
			System.out.println();
		}
		System.out.println();

	} // end of testRankPlayers

	public void testUpdatePlayers() {
		System.out.println();
		System.out.println("testUpdatePlayers ...................");
		View playerView = dbClient.view("players/allplayers");
		playerView.includeDocs(true);
		List<Player> players = playerView.query(Player.class);

		for (Player player : players) {
			player.setPosition(FootballPosition.WR);
		}
		try {
			dbClient.bulk(players, false);
		} catch (Exception exc) {
			System.out.println(exc);
			System.out.println("class Name : " + this.getClass().getName());
		}
		System.out.println();

	} // end of testUpdatePlayers()

	public void testDeleteOnePlayer() {
		System.out.println();
		System.out.println("testDeleteOnePlayer ...................");
		System.out.println();

		try {
			Player player = dbClient.find(Player.class, "1");
			if (player == null) {
				System.out.println("No player returned");
			} else {
				dbClient.remove(player);
				System.out.println("Player 1 deleted ");
			}
		} catch (NoDocumentException docExc) {
			System.out.println("no document found for key of 1");
		} catch (Exception exc) {
			System.out.println(exc);
			System.out.println("class Name : " + this.getClass().getName());
		}

	} // end of testDeleteOnePlayer

	public void testDeleteAllPlayers() {
		System.out.println();
		System.out.println("testDeleteAllPlayers ...................");
		System.out.println();

		View playerView = dbClient.view("players/allplayers");
		playerView.includeDocs(true);
		List<Player> players = playerView.query(Player.class);

		int size = players.size();
		if (size == 1) {
			Player player = players.get(0);
			if (player.get_id() == null) {
				System.out.println("Nothing to delete");
				return;
			}
		}

		for (Player player : players) {
			if (!player.get_id().equals("dummy")) {
				dbClient.remove(player);
			}
		}

	} // end of testDeleteAllPlayers

	static public Person getDummyRecord() {
		Person dummy = null;

		dummy = new Person();
		dummy.setType("dummy"); // this won't be found for Person or Player
		dummy.set_id("dummy");
		dummy.setFirstName("dummy");
		dummy.setLastName("dummy");

		return dummy;

	}

	static public Player getOnePlayer() {
		Team giants = new Team();
		giants.setNameName("Giants");
		giants.set_id("1");

		Player odellBeckum = new Player();
		odellBeckum.set_id("1");
		odellBeckum.setFirstName("Odell");
		odellBeckum.setLastName("Beckum");
		odellBeckum.setNotes("Elite Reciever");
		odellBeckum.setRanking((float) 84.3);
		odellBeckum.setPosition("slot");
		odellBeckum.setTeam(giants);

		return odellBeckum;

	}

	static public Person getOnePerson() {

		Person person = new Person();
		person.set_id("P1");
		person.setFirstName("FirstName");
		person.setLastName("LastName");

		return person;

	}

	static public Player[] getInitialPlayers() {

		Team giants = new Team();
		giants.setNameName("Giants");
		giants.set_id("1");

		Team cowboys = new Team();
		cowboys.setNameName("Cowboys");
		cowboys.set_id("2");

		Team bengals = new Team();
		bengals.setNameName("Bengals");
		bengals.set_id("3");

		Player odellBeckum = new Player();
		odellBeckum.set_id("1");
		odellBeckum.setFirstName("Odell");
		odellBeckum.setLastName("Beckum");
		odellBeckum.setNotes("Elite Reciever");
		odellBeckum.setRanking((float) 84.3);
		odellBeckum.setTeam(giants);

		Player dezBryant = new Player();
		dezBryant.set_id("2");
		dezBryant.setFirstName("Dez");
		dezBryant.setLastName("Bryant");
		dezBryant.setNotes("Elite Reciever");
		dezBryant.setRanking((float) 85.3);
		dezBryant.setPosition("slot");
		dezBryant.setTeam(cowboys);

		Player ajGreen = new Player();
		ajGreen.set_id("3");
		ajGreen.setFirstName("Adriel");
		ajGreen.setMiddleName("Jeremiah");
		ajGreen.setLastName("Green");
		ajGreen.setNotes("Elite Reciever");
		ajGreen.setRanking((float) 91.3);
		ajGreen.setPosition(FootballPosition.WR);
		ajGreen.setTeam(bengals);

		Player sheppard = new Player();
		sheppard.set_id("4");
		sheppard.setFirstName("Sterling");
		sheppard.setLastName("Sheppard");
		sheppard.setNotes("Complimentory receiver");
		sheppard.setRanking((float) 55.3);
		sheppard.setTeam(giants);

		Player[] players = new Player[4];
		players[0] = odellBeckum;
		players[1] = dezBryant;
		players[2] = ajGreen;
		players[3] = sheppard;

		return players;

	} // end of getInitialPlayers

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("PlayerCouchCRUDTest.main() : ..... begin execution ..... ");
		System.out.println();

		PlayerCouchCRUDTest test = new PlayerCouchCRUDTest();
		// setup Methods, to start clean
		test.addDummyPlayer();
		test.testDeleteAllPlayers();
		test.testDeleteOnePerson();

		// Test them out
		test.testAddPerson();
		test.testReadOnePerson();
		test.testAddPlayers();
		test.testAddDuplicate();
		test.testReadAllPlayers();
		test.testUpdatePlayers();
		test.testReadAllPlayers();
		test.testRankPlayers();
		test.testDeleteOnePlayer();
		test.testReadAllPlayers();

		// Clean up at end
		test.testDeleteAllPlayers();
		test.testDeleteOnePerson();

		System.out.println();

		System.out.println("PlayerCouchCRUDTest.main() : ..... ending execution ..... ");
		System.exit(0);
		;

	}

}
