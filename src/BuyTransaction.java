public class BuyTransaction extends Transaction implements Comparable<BuyTransaction>{
	BuyTransaction(int quantity, double prize, long userID, Commodity commodityID){
		super(quantity, prize, userID, commodityID);
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
