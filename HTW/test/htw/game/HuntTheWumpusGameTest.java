package htw.game;

import htw.HtwMessageReceiver;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by zooK on 3/9/16.
 */
public class HuntTheWumpusGameTest {

    private HuntTheWumpusGame game;
    private Set<String> wumpusCaverns;

    @Before
    public void setUp() {
        game = new HuntTheWumpusGame();
        wumpusCaverns = new HashSet<String>();
    }

    @Test
    public void addOneWumpus_WumpusCavernsSizeOne() {
        wumpusCaverns.add("C1");
        addCavernsToGame(game, wumpusCaverns);

        assertEquals(1, game.getWumpusCaverns().size());
        assertTrue(game.getWumpusCaverns().contains("C1"));
    }

    @Test
    public void addTwoWumpuses_WumpusCavernsSizeTwo() {
        wumpusCaverns.add("C1");
        wumpusCaverns.add("C2");
        addCavernsToGame(game, wumpusCaverns);

        assertEquals(2, game.getWumpusCaverns().size());
        assertTrue(game.getWumpusCaverns().contains("C1"));
        assertTrue(game.getWumpusCaverns().contains("C2"));
    }

    private void addCavernsToGame(HuntTheWumpusGame game, Set<String> wumpusCaverns) {
        for (String cavern: wumpusCaverns) {
            game.addWumpusCavern(cavern);
        }
    }
}
