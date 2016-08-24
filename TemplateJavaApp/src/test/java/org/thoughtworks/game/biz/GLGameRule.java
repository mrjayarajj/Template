package org.thoughtworks.game.biz;

import java.util.List;

import org.thoughtworks.game.vo.Card;

public class GLGameRule implements GameRule {

	public GLGameInfo process(List<GLGameInfo> gameInfoList) {

		GLGameInfo winner = null;		
		
		for (GLGameInfo info : gameInfoList) {

			int tot = 0;
			
			System.out.println(" Person :"+info.getPerson().getName());

			for (Card c : info.getCardList()) {
				
				System.out.print(c+",");
				
				tot = tot + c.getValue();
			}
			
			System.out.print("=="+tot);
			
			System.out.println();

			info.setTotal(tot);
			
			if(winner == null){
				winner = info;
			}else{				
				if(winner.getTotal() < info.getTotal()){
					winner = info;
				}				
			}		
			
		}
		
		return winner;
		
	}
}
