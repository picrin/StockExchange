public class SellTransaction extends Transaction implements Comparable<SellTransaction> {
	SellTransaction(int quantity, float prize, long userID, Commodity commodityID){
		super(quantity, prize, userID, commodityID);
	}
	
	public float sellReturnRevenue(BuyTransaction buyTransaction){
		int sold;
		if(buyTransaction.quantity > this.quantity){
			sold = buyTransaction.quantity - this.quantity;
			this.quantity = 0;
		}
		else{
			sold = buyTransaction.quantity;
			this.quantity = this.quantity - sold;
		}
		return sold * this.prize;
	}

	
	@Override
	public int compareTo(SellTransaction other) {
		int compareByPrize = Float.compare(other.prize, this.prize);
		if (compareByPrize != 0){
			return compareByPrize;
		}
		int compareByDate = other.date.compareTo(this.date);
		if (compareByDate != 0){
			return compareByDate;
		}
		return -1;
	}
}
