/**
 * RMSCOTT Prototype
 */
package rmscott.test.basic;

import java.util.Arrays;
import java.util.Comparator;

import rmscott.football.FootballPosition;
import rmscott.football.Player;
import rmscott.football.PlayerComparator;
import rmscott.football.Team;

/**
 * @author rmscott
 *
 */
public class PlayerTest {

	/**
	 * Constructor for the class
	 */
	public PlayerTest() {

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
		
		
		Player ajGreen = null;
		Player odellBeckum = null;
		Player dezBryant = null;

		odellBeckum = new Player();
		odellBeckum.setFirstName("Odell");
		odellBeckum.setLastName("Beckum");
		odellBeckum.setNotes("Elite Reciever");
		odellBeckum.setRanking((float) 84.3);
		odellBeckum.setPosition(FootballPosition.WR);
		odellBeckum.setTeam(giants);

		dezBryant = new Player();
		dezBryant.setFirstName("Dez");
		dezBryant.setLastName("Bryant");
		dezBryant.setNotes("Elite Reciever");
		dezBryant.setRanking((float) 85.3);
		dezBryant.setPosition(FootballPosition.WR);
		dezBryant.setTeam(cowboys);

		ajGreen = new Player();
		ajGreen.setFirstName("Adriel");
		ajGreen.setMiddleName("Jeremiah");
		ajGreen.setLastName("Green");
		ajGreen.setNotes("Elite Reciever");
		ajGreen.setRanking((float) 91.3);
		ajGreen.setPosition(FootballPosition.WR);
		ajGreen.setTeam(bengals);

		Player[] players = new Player[3];
		players[0] = odellBeckum;
		players[1] = dezBryant;
		players[2] = ajGreen;

		return players;

	} // end of getInitialPlayers

	static public Player[] orderByRankingCompareTo(Player[] pPlayers) {
		Arrays.sort(pPlayers);
		return pPlayers;
	}

	static public Player[] orderByRankingComparator(Player[] pPlayers) {

		Arrays.sort(pPlayers, new PlayerComparator());
		return pPlayers;
	}

	static public Player[] orderByRankingInline(Player[] pPlayers) {

		Arrays.sort(pPlayers, new Comparator<Player>() {
			public int compare(Player pOne, Player pTwo) {
				Float one = new Float(pOne.getRanking());
				Float two = new Float(pTwo.getRanking());
				return two.compareTo(one);
			}
		});

		return pPlayers;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("PlayerCRUDTest.main() : ..... begin execution ..... ");
		System.out.println();

		Player[] players = PlayerTest.getInitialPlayers();

		System.out.println("Original Player List");
		System.out.println("---------------------------------------------------------------");
		for (Player player : players) {
			System.out.println(player);
			System.out.println();
		}

		System.out.println("CompareTo Player List");
		System.out.println("---------------------------------------------------------------");
		players = PlayerTest.orderByRankingCompareTo(players);
		for (Player player : players) {
			System.out.println(player);
			System.out.println();
		}

		System.out.println("Comparator Player List");
		System.out.println("---------------------------------------------------------------");
		players = PlayerTest.orderByRankingComparator(players);
		for (Player player : players) {
			System.out.println(player);
			System.out.println();
		}

		System.out.println("inline Player List");
		System.out.println("---------------------------------------------------------------");
		players = PlayerTest.orderByRankingInline(players);
		for (Player player : players) {
			System.out.println(player);
			System.out.println();
		}

		System.out.println("PlayerTEst.main() : ..... ending execution ..... ");
		System.exit(0);
		;

	}

}
