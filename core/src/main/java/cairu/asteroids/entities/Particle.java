package cairu.asteroids.entities;

import cairu.asteroids.entities.SpaceObject;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;

public class Particle extends SpaceObject {

	private float timer;
	private float time;
	private boolean remove;

	public Particle(float x, float y) {

		this.x = x;
		this.y = y;
		width = height = 2;

		speed = 50;
		radians = MathUtils.random(2 * 3.1415f);
		dx = MathUtils.cos(radians) * speed;
		dy = MathUtils.sin(radians) * speed;

		timer = 0;
		time = 1;

	}

	public boolean shouldRemove() { return remove; }

	public void update(float dt) {

		x += dx * dt;
		y += dy * dt;

		timer += dt;
		if(timer > time) {
			remove = true;
		}

	}

    public void draw(ShapeRenderer sr) {
        sr.setColor(1, 1, 1, 1); // Define a cor do círculo
        sr.begin(ShapeRenderer.ShapeType.Filled); // Inicia o desenho como um círculo preenchido
        sr.circle(x, y, width / 2); // Desenha o círculo na posição (x, y) com o raio correto
        sr.end();
    }


}

















