package cairu.asteroids.gamestates;

import java.util.ArrayList;

import cairu.asteroids.gamestates.GameState;
import cairu.asteroids.managers.GameStateManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import cairu.asteroids.entities.Asteroid;
import cairu.asteroids.main.Game;
import cairu.asteroids.managers.GameKeys;
import cairu.asteroids.managers.GameStateManager;
import cairu.asteroids.managers.Save;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class MenuState extends GameState {

	private SpriteBatch sb;
	private ShapeRenderer sr;

	private BitmapFont titleFont;
	private BitmapFont font;

	private final String title = "Asteroids";

	private int currentItem;
	private String[] menuItems;

	private ArrayList<Asteroid> asteroids;


    public MenuState(GameStateManager gsm) {
        super(gsm);
    }


    public void init() {

		sb = new SpriteBatch();
		sr = new ShapeRenderer();
        font = new BitmapFont();
        titleFont = new BitmapFont();
        try {
            BitmapFont font = new BitmapFont();
        } catch (GdxRuntimeException e) {
            Gdx.app.error("Font Loading Error", "Error loading font: " + e.getMessage());
        }

        titleFont.setColor(Color.WHITE);
		titleFont.setColor(Color.WHITE);


		menuItems = new String[] {
			"Play",
			"Highscores",
			"Quit"
		};

		asteroids = new ArrayList<Asteroid>();
		for(int i = 0; i < 6; i++) {
			asteroids.add(
				new Asteroid(
					MathUtils.random(Game.WIDTH),
					MathUtils.random(Game.HEIGHT),
					Asteroid.LARGE
				)
			);
		}

		Save.load();

	}

	public void update(float dt) {

		handleInput();

		for(int i = 0 ; i < asteroids.size(); i++) {
			asteroids.get(i).update(dt);
		}

	}

	public void draw() {
//
		sb.setProjectionMatrix(Game.cam.combined);
		sr.setProjectionMatrix(Game.cam.combined);

		// draw asteroids
		for(int i = 0; i < asteroids.size(); i++) {
			asteroids.get(i).draw(sr);
		}

		sb.begin();

		// draw title
        float width = titleFont.getRegion().getRegionWidth() * titleFont.getScaleX();
        titleFont.draw(
            sb,
            title,
            (Game.WIDTH - width) / 2,
            300
        );

        for (int i = 0; i < menuItems.length; i++) {
            // Calcula a largura do item do menu
            width = font.getRegion().getRegionWidth() * font.getScaleX();
            if (currentItem == i) font.setColor(Color.RED);
            else font.setColor(Color.WHITE);
            font.draw(
                sb,
                menuItems[i],
                (Game.WIDTH - width) / 2,
                180 - 35 * i
            );
		}

		sb.end();

	}

	public void handleInput() {

		if(GameKeys.isPressed(GameKeys.UP)) {
			if(currentItem > 0) {
				currentItem--;
			}
		}
		if(GameKeys.isPressed(GameKeys.DOWN)) {
			if(currentItem < menuItems.length - 1) {
				currentItem++;
			}
		}
		if(GameKeys.isPressed(GameKeys.ENTER)) {
			select();
		}

	}

	private void select() {
		// play
		if(currentItem == 0) {
			gsm.setState(GameStateManager.PLAY);
		}
		// high scores
		else if(currentItem == 1) {
			gsm.setState(GameStateManager.HIGHSCORE);
		}
		else if(currentItem == 2) {
			Gdx.app.exit();
		}
	}

	public void dispose() {
		sb.dispose();
		sr.dispose();
		titleFont.dispose();
		font.dispose();
	}

}










