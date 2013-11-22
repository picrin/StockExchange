import java.util.PriorityQueue;

class Commodity{
	PriorityQueue<SellTransaction> sellQueue;
	PriorityQueue<BuyTransaction> buyQueue;
	String description;
	Commodity(String description){
		sellQueue = new PriorityQueue<SellTransaction>();
		buyQueue = new PriorityQueue<BuyTransaction>();
		this.description = description;
	}
	
	public void carryOutDeals(){
		SellTransaction cheapestSell = sellQueue.peek();
		BuyTransaction mostExpensiveBuy = buyQueue.peek();
		while(cheapestSell != null && mostExpensiveBuy != null && cheapestSell.prize < mostExpensiveBuy.prize){
			double revenue = cheapestSell.sellReturnRevenue(mostExpensiveBuy);
			if(cheapestSell.isEmpty()){
				sellQueue.poll();
				//TODO remove from list of trades
			}			
			if(mostExpensiveBuy.isEmpty()){
				buyQueue.poll();
				//TODO remove from list of trades
			}
			//TODO przepisac dla concurrency
			IO.notifyWalletChange(cheapestSell.userID, revenue);
			IO.notifyWalletChange(mostExpensiveBuy.userID, -revenue);
			cheapestSell = sellQueue.peek();
			mostExpensiveBuy = buyQueue.peek();
		}
	}
	
	
	public void trade(SellTransaction transaction){
		if (transaction.commodity != this){
			throw new RuntimeException("wrong commodity");
		}
		sellQueue.add(transaction);
		carryOutDeals();
	}

	public void trade(BuyTransaction transaction){
		if (transaction.commodity != this){
			throw new RuntimeException("wrong commodity");
		}
		buyQueue.add(transaction);	
		carryOutDeals();
	}

}