package com.monkeymusicchallenge.warmup;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.concurrent.ThreadLocalRandom;

public class AI {


  
 /* private class Monkey {
    static int x; static int y;
  }*/

    public String move(final JSONObject gameState) {

        // Every game has a limited number of turns. Use every turn wisely!
        final int remainingNumberOfTurns = gameState.getInt("turns");

        // The level layout is a 2D-matrix (an array of arrays).
        //
        // Every element in the matrix is a string. The string tells you what's
        // located at the corresponding position on the level.
        //
        // In the warmup challenge, your objective is to find all music items
        // and deliver them to the eagerly waiting Spotify user.
        //
        // "empty": an empty tile, you can move here
        // "monkey": your monkey, this is where you're currently at
        // "song" / "album" / "playlist": a music item, go get them!
        // "user": go here once you've picked up all music items
        //
        // Too easy for you? Good...
        //
        // The real fun begins when the warmup is over and the competition begins!
        final JSONArray currentLevelLayout = gameState.getJSONArray("layout");

        // This is an array of all music items you've currently picked up
        final JSONArray pickedUpMusicItems = gameState.getJSONArray("pickedUp");

        // The position attribute tells you where your monkey is
        final JSONArray currentPositionOfMonkey = gameState.getJSONArray("position");

        // Speaking of positions...
        //
        // X and Y coordinates can be confusing.
        // Which way is up and which way is down?
        //
        // Here is an example explaining how coordinates work in
        // Monkey Music Challenge:
        //
        // {
        //   "layout": [["empty", "monkey"]
        //              ["song",  "empty"]]
        //   "position": [0, 1],
        //   ...
        // }
        //
        // The "position" attribute tells you the location of your monkey
        // in the "layout" matrix. In this example, you're at layout[0][1].
        //
        // If you send { "command": "move", "direction": "down", ... }
        // to the server, you'll get back:
        //
        // {
        //   "layout": [["empty", "empty"]
        //              ["song",  "monkey"]]
        //   "position": [1, 1]
        // }
        //
        // If you instead send { "command": "move", "direction": "left", ... }
        // to the server, you'll get back:
        //
        // {
        //   "layout": [["monkey", "empty"]
        //              ["song",   "empty"]]
        //   "position": [0, 0]
        // }
        //
        // So what about picking stuff up then?
        //
        // It's simple!
        //
        // Just stand next to something you want to pick up and move towards it.
        //
        // For example, say our current game state looks like this:
        //
        // {
        //   "layout": [["empty", "empty"]
        //              ["song",  "monkey"]]
        //   "position": [1, 1],
        //   "pickedUp": []
        // }
        //
        // When you send { "command": "move", "direction": "left", ... }
        // to the server, you'll get back:
        //
        //   "layout": [["empty",  "empty"]
        //              ["empty",  "monkey"]]
        //   "position": [1, 1],
        //   "pickedUp": ["song"],
        //   ...
        // }
        //
        // Instead of moving, your monkey successfully picked up the song!
        //
        // Got it? Sweet! This message will self destruct in five seconds...

        // This will always return the string "monkey" - get it?
        final String monkey = currentLevelLayout
                .getJSONArray(currentPositionOfMonkey.getInt(0))
                .getString(currentPositionOfMonkey.getInt(1));

        //createLayout(currentLevelLayout);
        // TODO: You may want to do something smarter here
        return this.randomDirection();
        // return this.direction(currentLevelLayout, currentPositionOfMonkey);
    }

    private String direction(JSONArray layout, JSONArray monkey) {
        GraphManager.createGraph(layout); // Now we have information of the layout
        return nextDir();
    }

    // Calculates the next best direction to walk according to layout...
    private String nextDir() {
        // TODO: do something smart...
        return null;
    }

    private String randomDirection() {
        return new String[]{"up", "down", "left", "right"}[ThreadLocalRandom.current().nextInt(4)];
    }

    public void initGraph(int noNodes) {
        Graph g = new Graph(noNodes);
    }

}
