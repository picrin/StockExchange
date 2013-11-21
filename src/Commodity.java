import java.util.PriorityQueue;

class Commodity{
	PriorityQueue<SellTransaction> sellQueue;
	PriorityQueue<BuyTransaction> buyQueue;

	Commodity(){
		sellQueue = new PriorityQueue<SellTransaction>();
		buyQueue = new PriorityQueue<BuyTransaction>();
	}
	
	public void carryOutDeals(){
		SellTransaction cheapestSell = sellQueue.peek();
		BuyTransaction mostExpensiveBuy = buyQueue.peek();
		while(sellQueue != null && buyQueue != null && cheapestSell.prize > mostExpensiveBuy.prize){
			float revenue = cheapestSell.sellReturnRevenue(mostExpensiveBuy);
			if(cheapestSell.isEmpty()){
				sellQueue.poll();
				//TODO remove from list of trades
			}			
			if(mostExpensiveBuy.isEmpty()){
				buyQueue.poll();
				//TODO remove from list of trades
			}
			Output.notifyWalletChange(cheapestSell.userID, revenue);
			Output.notifyWalletChange(mostExpensiveBuy.userID, -revenue);
			cheapestSell = sellQueue.peek();
			mostExpensiveBuy = buyQueue.peek();
		}
	}
	
	
	public void trade(SellTransaction transaction){
		if (transaction.commodity != this){
			throw new RuntimeException("wrong commodity");
		}
		sellQueue.add(transaction);
	}

	public void trade(BuyTransaction transaction){
		if (transaction.commodity != this){
			throw new RuntimeException("wrong commodity");
		}
		buyQueue.add(transaction);	
	}
	
	public void trade(Transaction _){}

}