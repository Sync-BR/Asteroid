package cairu.asteroids.entities;

import cairu.asteroids.entities.SpaceObject;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;

public class Bullet extends SpaceObject {

	private float lifeTime;
	private float lifeTimer;

	private boolean remove;

	public Bullet(float x, float y, float radians) {

		this.x = x;
		this.y = y;
		this.radians = radians;

		float speed = 350;
		dx = MathUtils.cos(radians) * speed;
		dy = MathUtils.sin(radians) * speed;

		width = height = 2;

		lifeTimer = 0;
		lifeTime = 1;

	}

	public boolean shouldRemove() { return remove; }

	public void update(float dt) {

		x += dx * dt;
		y += dy * dt;

		wrap();

		lifeTimer += dt;
		if(lifeTimer > lifeTime) {
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

















