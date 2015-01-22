package com.ispira.lamejumper;

public class Bird extends DynamicGameObject {
	public static final float WIDTH = 1.8f;
	public static final float HEIGHT = 1.5f;
	public static final int STATE_FLY = 0;
	public static final int STATE_DEAD = 1;
	public static final int TYPE_RIGHT = 0;
	public static final int TYPE_LEFT = 1;
	
	public static final float VELOCITY = 3f;
	
	int state;
	int type;
	float stateTime = 0;
	
	public Bird(float x, float y, int type){
		super(x, y, WIDTH, HEIGHT);
		this.type = type;
		this.state = STATE_FLY;
	}
	
	public void update(float delta){
		position.add(velocity.x * delta, velocity.y * delta);
		bounds.x = position.x - bounds.width/2;
		bounds.y = position.y - bounds.height/2;
		
		if(type == TYPE_LEFT){
			velocity.x = -VELOCITY;
			
			if(position.x < -WIDTH) position.x = WorldUtil.WIDTH+WIDTH/2;
		}
		if (type == TYPE_RIGHT){
			velocity.x = VELOCITY;
			
			if(position.x > WorldUtil.WIDTH+WIDTH/2) position.x = -WIDTH;
		}
		
		stateTime += delta;
	}
	
}
