package htw.game;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by zooK on 3/9/16.
 */
public class HuntTheWumpusGameTest {

    private HuntTheWumpusGame game;
    private List<String> wumpusCaverns;

    @Before
    public void setUp() {
        game = new HuntTheWumpusGame();
        wumpusCaverns = new ArrayList<String>();
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

    @Test
    public void moveOneWumpus_OneChoice() {
        game = EasyMock.createMockBuilder(HuntTheWumpusGame.class).addMockedMethod("getWumpusChoices").createMock();
        wumpusCaverns.add("C1");
        game.setWumpusCaverns(wumpusCaverns);
        List<String> choices = new ArrayList<String>();
        choices.add("C2");

        EasyMock.expect(game.getWumpusChoices("C1")).andReturn(choices);
        EasyMock.replay(game);
        game.moveWumpuses();
        EasyMock.verify(game);

        assertEquals(1, game.getWumpusCaverns().size());
        assertTrue(game.getWumpusCaverns().contains("C2"));
    }

    @Test
    public void moveOneWumpus_TwoChoices() {
        game = EasyMock.createMockBuilder(HuntTheWumpusGame.class).addMockedMethod("getWumpusChoices").createMock();
        wumpusCaverns.add("C1");
        game.setWumpusCaverns(wumpusCaverns);
        List<String> choices = new ArrayList<String>();
        choices.add("B1");
        choices.add("B2");

        EasyMock.expect(game.getWumpusChoices("C1")).andReturn(choices);
        EasyMock.replay(game);
        game.moveWumpuses();
        EasyMock.verify(game);

        assertEquals(1, game.getWumpusCaverns().size());
        assertTrue(choices.containsAll(game.getWumpusCaverns()));
    }

    @Test
    public void wumpusCavernsContainsPlayer() {
        String playerCavern = "C1";
        List<String> wumpusCaverns = new ArrayList<String>();
        wumpusCaverns.add("C1");
        wumpusCaverns.add("C2");
        game.setPlayerCavern(playerCavern);
        game.setWumpusCaverns(wumpusCaverns);

        assertTrue(game.isWumpusMovedToPlayer());
    }

    @Test
    public void wumpusCavernsDoesNotContainPlayer() {
        String playerCavern = "C1";
        List<String> wumpusCaverns = new ArrayList<String>();
        wumpusCaverns.add("D1");
        wumpusCaverns.add("C2");
        game.setPlayerCavern(playerCavern);
        game.setWumpusCaverns(wumpusCaverns);

        assertFalse(game.isWumpusMovedToPlayer());
    }

    private void addCavernsToGame(HuntTheWumpusGame game, List<String> wumpusCaverns) {
        for (String cavern: wumpusCaverns) {
            game.addWumpusCavern(cavern);
        }
    }

}
