package com.ispira.lamejumper;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class BirdAnimation {
	TextureRegion[] flyFrames;
	TextureRegion dieFrames;
	Animation flyAnimation;
	public BirdAnimation(){
		flyFrames = new TextureRegion[2];
		flyFrames[0] = new TextureRegion(Assets.bird, 0, 0, 57, 45);
		flyFrames[1] = new TextureRegion(Assets.bird, 57, 0, 65, 45);
		dieFrames = new TextureRegion(Assets.bird, 122, 0, 57, 45);
		flyAnimation = new Animation(0.3f, flyFrames);
	}
}
