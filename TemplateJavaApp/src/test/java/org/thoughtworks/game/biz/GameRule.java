package org.thoughtworks.game.biz;

import java.util.List;

public interface GameRule {

	public GLGameInfo process(List <GLGameInfo> gameInfoList);
	
}
