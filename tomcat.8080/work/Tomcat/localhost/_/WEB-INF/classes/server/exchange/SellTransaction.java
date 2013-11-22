package server.exchange;

public class SellTransaction extends Transaction implements Comparable<SellTransaction> {
	SellTransaction(int quantity, double prize, long userID, Commodity commodityID){
		super(quantity, prize, userID, commodityID);
		toPriorityQueue();
	}
	
	public void toPriorityQueue(){
		commodity.trade(this);
	}
	
	public double sellReturnRevenue(BuyTransaction buyTransaction){
		int sold;
		if(buyTransaction.quantity > this.quantity){
			sold = this.quantity;
			buyTransaction.quantity -= sold;
			this.quantity = 0;
		}
		else{
			sold = buyTransaction.quantity;
			this.quantity -= sold;
			buyTransaction.quantity = 0;
		}
		return sold * this.prize;
	}
	
	@Override
	public int compareTo(SellTransaction other) {
		int compareByPrize = Double.compare(this.prize, other.prize);
		if (compareByPrize != 0){
			return compareByPrize;
		}
		int compareByDate = this.date.compareTo(date);
		return compareByDate;
	}
}
