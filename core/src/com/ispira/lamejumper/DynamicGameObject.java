package com.ispira.lamejumper;

import com.badlogic.gdx.math.Vector2;

public class DynamicGameObject extends GameObject {
	public final Vector2 velocity;
	public final Vector2 accel;
	
	public DynamicGameObject(float x, float y, float w, float h){
		super(x,y,w,h);
		velocity = new Vector2();
		accel = new Vector2();
	}
}
