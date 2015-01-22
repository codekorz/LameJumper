package com.ispira.lamejumper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Lame extends DynamicGameObject {
	public static final int STATE_INGROUND = 0;
	public static final int STATE_JUMP = 1;
	public static final int STATE_FALL = 2;
	public static final int STATE_DEAD = 3;
	public static final float JUMP_VELOCITY = 11f;
	public static final float WIDTH = 2.4f;
	public static final float HEIGHT = 3f;
	
	public int state;
	float stateTime = 0;
	
	protected Body body;
	
	public Lame(float x, float y){
		super(x,y,WIDTH,HEIGHT);
		state = STATE_INGROUND;
		stateTime = 0;
	}
	/*public Lame(Body b){
		this.body = b;
	}*/
	public void update(float delta){
		velocity.add(WorldUtil.gravity.x * delta, WorldUtil.gravity.y * delta);
		position.add(velocity.x * delta, velocity.y * delta);

		bounds.x = position.x - bounds.width/2;	
		bounds.y = position.y - bounds.height/2;
		if(state == STATE_DEAD) return;
		float accel = 0;
		if(Gdx.input.isKeyPressed(Keys.DPAD_RIGHT)) accel = WIDTH+(WIDTH/2);
		if(Gdx.input.isKeyPressed(Keys.DPAD_LEFT)) accel = -(WIDTH+(WIDTH/2));
		velocity.x = accel;	
		if(state == STATE_INGROUND) jump();
		
		if(velocity.y < 0 && state != STATE_INGROUND){
			if(state != STATE_FALL) state = STATE_FALL;
		}
		if(position.y <= 0){
			position.y = 0f;
			if(state != STATE_DEAD){
				state = STATE_INGROUND;
			}
		}
		if(position.x+WIDTH < 0) position.x = WorldUtil.WIDTH;
		if(position.x > WorldUtil.WIDTH) position.x = -WIDTH;
		stateTime += delta;
	}
	private void jump(){
		velocity.y = JUMP_VELOCITY;
		state = STATE_JUMP;
		stateTime = 0;
	}
	public void dead(){
		velocity.set(0,0);
		state = STATE_DEAD;
		stateTime = 0;
	}
	public void hitPlatform(Platform p){
		velocity.y = 0;
		position.y = p.position.y+0.3f;
		state = STATE_INGROUND;
		stateTime = 0;
	}

	public void hitBird(){
		velocity.y = JUMP_VELOCITY*1.5f;
		state = STATE_JUMP;
		stateTime = 0;
	}
}