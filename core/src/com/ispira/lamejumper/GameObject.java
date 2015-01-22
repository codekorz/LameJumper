package com.ispira.lamejumper;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class GameObject {
	public final Vector2 position;
	public final Rectangle bounds;
	
	public GameObject(float x, float y, float w, float h){
		this.position = new Vector2(x, y);
		this.bounds = new Rectangle((x-w/2), (y-h/2), w, h);
	}
}
