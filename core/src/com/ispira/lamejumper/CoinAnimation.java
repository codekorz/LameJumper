package com.ispira.lamejumper;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class CoinAnimation {
	TextureRegion[] coinFrames;
	Animation coinAnimation;
	public CoinAnimation(){
		coinFrames = new TextureRegion[4];
		coinFrames[0] = new TextureRegion(Assets.coin, 0, 0, 40, 43);
		coinFrames[1] = new TextureRegion(Assets.coin, 0, 43, 40, 43);
		coinFrames[2] = new TextureRegion(Assets.coin, 0, 87, 40, 43);
		coinFrames[3] = new TextureRegion(Assets.coin, 0, 132, 40, 43);
		coinAnimation = new Animation(0.2f, coinFrames);
	}
}
