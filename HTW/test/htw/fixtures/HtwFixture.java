package htw.fixtures;

import htw.HuntTheWumpus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static htw.fixtures.TestContext.game;

public class HtwFixture {
  public boolean ConnectCavernToGoing(String c1, String c2, String dir) {
    game.connectCavern(c1,c2,toDirection(dir));
    return true;
  }

  public boolean putPlayerInCavern(String c) {
    game.setPlayerCavern(c);
    return true;
  }

  public boolean movePlayer(String dir) {
    game.makeMoveCommand(toDirection(dir)).execute();
    return true;
  }

  public String getPlayerCavern() {
    return game.getPlayerCavern();
  }

  public boolean putWumpusInCavern(String c) {
    game.addWumpusCavern(c);
    return true;
  }

  public String getWumpusCavern() {
    return game.getWumpusCaverns().get(0);
  }

  public boolean freezeWumpus() {
    game.freezeWumpus();
    return true;
  }

  public boolean MessageIdWasGiven(String message) {
    return (TestContext.messages.contains(message));
  }

  public boolean MessageIdWasNotGiven(String message) {
    return !MessageIdWasGiven(message);
  }

  public boolean clearMessages() {
    TestContext.messages.clear();
    return true;
  }

  public boolean setCavernAsPit(String cavern) {
    game.addPitCavern(cavern);
    return true;
  }

  public boolean setCavernAsBats(String cavern) {
    game.addBatCavern(cavern);
    return true;
  }

  public boolean rest() {
    game.makeRestCommand().execute();
    return true;
  }

  public boolean RestTimesWithWumpusInEachTime(int times, String cavern) {
    TestContext.wumpusCaverns.clear();
    for (int i=0; i<times; i++) {
      game.setWumpusCaverns(new ArrayList<String>());
      putWumpusInCavern(cavern);
      game.makeRestCommand().execute();
      incrementCounter(TestContext.wumpusCaverns, game.getWumpusCaverns().get(0));
    }
    return true;
  }

  private int zeroIfNull(Integer integer) {
    return integer == null ? 0 : integer;
  }

  public boolean MovePlayerTimesWithPlayerInEachTime(int times, String direction, String startingCavern) {
    TestContext.batTransportCaverns.clear();
    for (int i=0; i<times; i++) {
      putPlayerInCavern(startingCavern);
      game.makeMoveCommand(toDirection(direction)).execute();
      incrementCounter(TestContext.batTransportCaverns, game.getPlayerCavern());
    }
    return true;
  }

  private void incrementCounter(Map<String, Integer> counterMap, String cavern) {
    counterMap.put(cavern, zeroIfNull(counterMap.get(cavern)) + 1);
  }

  public boolean restUntilKilled() {
	System.out.println("resting until killed.");
    for (int i=0; i<100;  i++) {
      game.makeRestCommand().execute();
      if (getWumpusCaverns().contains(getPlayerCavern()))
        return true;
    }

    return false;
  }

  private List<String> getWumpusCaverns() {
    return game.getWumpusCaverns();
  }


  public boolean setArrowsInQuiverTo(int arrows) {
    game.setQuiver(arrows);
    return true;
  }

  public boolean shootArrow(String direction) {
    game.makeShootCommand(toDirection(direction)).execute();
    return true;
  }

  private HuntTheWumpus.Direction toDirection(String direction) {
    return HuntTheWumpus.Direction.valueOf(direction.toUpperCase());
  }

  public int arrowsInQuiver() {
    return game.getQuiver();
  }

  public int arrowsInCavern(String cavern) {
    return game.getArrowsInCavern(cavern);
  }

  public int wumpusesInGame() {
    return game.getWumpusCaverns().size();
  }

  public boolean setWumpusesInGameTo(int wumpuses) {
    game.setLevel(wumpuses);
    return true;
  }

  public int wumpusesInCavern(String cavern) {
    int wumpuses = 0;
    for (String c: game.getWumpusCaverns()) {
      if (c.equals(cavern)) wumpuses++;
    }
    return wumpuses;
  }
}
