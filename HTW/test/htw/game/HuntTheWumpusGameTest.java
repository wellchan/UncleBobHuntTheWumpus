package htw.game;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    // TODO test moveWumpusHelper and moveWumpuses
    @Test
    public void moveOneWumpus() {
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



    private void addCavernsToGame(HuntTheWumpusGame game, Set<String> wumpusCaverns) {
        for (String cavern: wumpusCaverns) {
            game.addWumpusCavern(cavern);
        }
    }

}
