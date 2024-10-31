package cairu.asteroids.gamestates;
import cairu.asteroids.managers.Save;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import cairu.asteroids.main.Game;
import cairu.asteroids.managers.GameKeys;
import cairu.asteroids.managers.GameStateManager;

public class GameOverState extends GameState{

	private SpriteBatch sb;
	private ShapeRenderer sr;
	private boolean newHighScore;
	private char[] newName;
	private int currentChar;
	private BitmapFont gameOverFont;
	private BitmapFont font;

	public GameOverState(GameStateManager gsm) {
		super(gsm);
	}

	public void init() {

		sb = new SpriteBatch();
		sr = new ShapeRenderer();

		newHighScore = Save.gd.isHighScore(Save.gd.getTentativeScore());

        if(newHighScore) {
			newName = new char[] {'A', 'A', 'A'};
			currentChar = 0;
		}
        gameOverFont = new BitmapFont();
        font = new BitmapFont();
	}

	public void update(float dt) {
		handleInput();
	}

	public void draw() {
        GlyphLayout layout = new GlyphLayout();
		sb.setProjectionMatrix(Game.cam.combined);
		sb.begin();
        String s = "Game Over";
        layout.setText(gameOverFont, s);
        float w = layout.width;
		gameOverFont.draw(sb, s, (Game.WIDTH - w) / 2, 220);
		if(!newHighScore) {
			sb.end();
			return;
		}
        s = "New High Score: " + Save.gd.getTentativeScore();
        layout.setText(font, s);
        w = layout.width;
        font.draw(sb, s, (Game.WIDTH - w) / 2, 180);
        for(int i = 0; i < newName.length; i++) {
			font.draw(
				sb,
				Character.toString(newName[i]),
				230 + 14 * i,
				120
			);
		}
		sb.end();
		sr.begin(ShapeType.Line);
		sr.line(
			230 + 14 * currentChar,
			100,
			244 + 14 * currentChar,
			100
		);
		sr.end();
	}

	public void handleInput() {
		if(GameKeys.isPressed(GameKeys.ENTER)) {
			if(newHighScore) {
				Save.gd.addHighScore(
					Save.gd.getTentativeScore(),
					new String(newName)
				);
				Save.save();
			}
			gsm.setState(GameStateManager.MENU);
		}
		if(GameKeys.isPressed(GameKeys.UP)) {
			if(newName[currentChar] == ' ') {
				newName[currentChar] = 'Z';
			}
			else {
				newName[currentChar]--;
				if(newName[currentChar] < 'A') {
					newName[currentChar] = ' ';
				}
			}
		}

		if(GameKeys.isPressed(GameKeys.DOWN)) {
			if(newName[currentChar] == ' ') {
				newName[currentChar] = 'A';
			}
			else {
				newName[currentChar]++;
				if(newName[currentChar] > 'Z') {
					newName[currentChar] = ' ';
				}
			}
		}

		if(GameKeys.isPressed(GameKeys.RIGHT)) {
			if(currentChar < newName.length - 1) {
				currentChar++;
			}
		}

		if(GameKeys.isPressed(GameKeys.LEFT)) {
			if(currentChar > 0) {
				currentChar--;
			}
		}

	}

	public void dispose() {
		sb.dispose();
		sr.dispose();
		gameOverFont.dispose();
		font.dispose();
	}

}









