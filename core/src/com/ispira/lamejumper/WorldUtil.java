package com.ispira.lamejumper;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World; 

public class WorldUtil {
	public static final Vector2 gravity = new Vector2(0, -9.8f);
	public static final float WIDTH = 16;
	public static final float HEIGHT = 26 * 10;
	public static final float SCALE = 30;
	
	public final Lame lame;
	public final List<Platform> platforms;
	public final List<Bird> birds;
	public final List<Coin> coins;
	public final Random rand;
	
	public int score = 0;
	public float heightCam;
	
	public static World createWorld(){
		return new World(gravity, true);
	}
	
	public WorldUtil(){
		rand = new Random();
		platforms = new ArrayList<Platform>();
		birds = new ArrayList<Bird>();
		coins = new ArrayList<Coin>();
		lame = new Lame(10, 0);
		generateLevel();
		heightCam = 0;
	}
	public void update(float delta){
		updateLame(delta);
		updatePlatforms(delta);
		updateBirds(delta);
		updateCoins(delta);
		checkCollisions();
	}
	private void checkCollisions(){
		platformsCollisions();
		birdsCollisions();
		coinsCollisions();
	}
	private void updateLame(float delta){
		lame.update(delta);
		heightCam = Math.max(heightCam, lame.position.y);
		lameDead();
	}
	private void updatePlatforms(float delta){
		for(Platform p : platforms){
			p.update(delta);
		}
	}
	
	private void updateBirds(float delta){
		for(Bird b : birds){
			b.update(delta);
		}
	}
	private void updateCoins(float delta){
		for(Coin c : coins){
			c.update(delta);
		}
	}
	private void generateLevel(){
		float y = rand.nextFloat() * (6f - 5f) + 5f;
		while(y < HEIGHT){
			int type;
			float r = rand.nextFloat();
			if(r > 0.8f) type = Platform.TYPE_MOVING_Y;
			else if(r > 0.5f) type = Platform.TYPE_MOVING_X;
			else type = Platform.TYPE_STATIC;
			float x = rand.nextFloat()*(WIDTH-Platform.WIDTH)+1;
			if(type == Platform.TYPE_MOVING_Y) y += rand.nextFloat()*2;
			Platform p = new Platform(x, y, type);
			platforms.add(p);
			
			
			
			// add coins
			if(rand.nextFloat() > 0.5f){
				Coin c = new Coin(p.position.x+rand.nextFloat(), p.position.y+Coin.HEIGHT+rand.nextFloat()*3);
				coins.add(c);
			}
			
			// add birds
			if(rand.nextFloat() > 0.5f){
				Bird b = new Bird(p.position.x+rand.nextFloat(), p.position.y+Bird.HEIGHT+rand.nextFloat()*3, rand.nextInt(2));
				birds.add(b);
			}
			
			y += rand.nextFloat() * (6f - 3f) + 3f;

		}
	}

	private void lameDead(){
		if(heightCam - WorldRender.HEIGHT/2 > lame.position.y+Lame.HEIGHT) lame.dead();
	}
	private void birdsCollisions(){
		for(Bird b : birds){
			if(b.bounds.overlaps(lame.bounds)){
				if(lame.position.y > b.position.y){
					lame.hitBird();
					score = score*2;
				}
				else lame.dead();
			}
		}
	}
	private void platformsCollisions(){
		if(lame.state == Lame.STATE_JUMP) return;
		for(Platform p : platforms){
			if(p.bounds.overlaps(lame.bounds) && lame.position.y > p.position.y){
				if(lame.position.x+1f < p.bounds.x + Platform.WIDTH){
					lame.hitPlatform(p);
					break;
				}
			}
		}
	}
	private void coinsCollisions(){
		int size = coins.size();
		for(int i=0;i<size;i++){
			Coin c = coins.get(i);
			if(c.bounds.overlaps(lame.bounds)){
				score += Coin.POINTS;
				coins.remove(c);
				size = coins.size();
			}
		}
	}
}
