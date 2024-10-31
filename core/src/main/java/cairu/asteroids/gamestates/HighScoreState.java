package cairu.asteroids.gamestates;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import cairu.asteroids.main.Game;
import cairu.asteroids.managers.GameKeys;
import cairu.asteroids.managers.GameStateManager;
import cairu.asteroids.managers.Save;

public class HighScoreState extends GameState {

	private SpriteBatch sb;

	private BitmapFont font;

	private long[] highScores;
	private String[] names;

	public HighScoreState(GameStateManager gsm) {
		super(gsm);
	}

	public void init() {
		sb = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        Save.load();
		highScores = Save.gd.getHighScores();
		names = Save.gd.getNames();
	}

	public void update(float dt) {
		handleInput();
	}

    public void draw() {
        sb.setProjectionMatrix(Game.cam.combined);
        sb.begin();
        String s;
        GlyphLayout layout = new GlyphLayout();
        s = "High Scores";
        layout.setText(font, s);
        float w = layout.width;
        font.draw(sb, s, (Game.WIDTH - w) / 2, 300);

        for (int i = 0; i < highScores.length; i++) {
            s = String.format("%2d. %7s %s", i + 1, highScores[i], names[i]);
            layout.setText(font, s);
            w = layout.width;
            font.draw(sb, s, (Game.WIDTH - w) / 2, 270 - 20 * i);
        }

        sb.end();
    }

	public void handleInput() {
		if(GameKeys.isPressed(GameKeys.ENTER) ||
			GameKeys.isPressed(GameKeys.ESCAPE)) {
			gsm.setState(GameStateManager.MENU);
		}
	}

	public void dispose() {
		sb.dispose();
		font.dispose();
	}

}








