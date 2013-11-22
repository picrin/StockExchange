package server.exchange;
public class BuyTransaction extends Transaction implements Comparable<BuyTransaction>{
	
	public BuyTransaction(int quantity, double prize, long userID, Commodity commodity){
		super(quantity, prize, userID, commodity);
		toPriorityQueue();
	}

	public void toPriorityQueue(){
		commodity.trade(this);
	}
	
	@Override
	public int compareTo(BuyTransaction other) {
		int compareByPrize = Double.compare(other.prize, this.prize);
		if (compareByPrize != 0){
			return compareByPrize;
		}
		int compareByDate = this.date.compareTo(other.date);
		return compareByDate;
	}
}
