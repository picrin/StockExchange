
public class BuyTransaction extends Transaction implements Comparable<BuyTransaction>{
	BuyTransaction(int quantity, float prize, long userID, Commodity commodityID){
		super(quantity, prize, userID, commodityID);
	}

	@Override
	public int compareTo(BuyTransaction other) {
		int compareByPrize = Float.compare(this.prize, other.prize);
		if (compareByPrize != 0){
			return compareByPrize;
		}
		int compareByDate = this.date.compareTo(other.date);
		if (compareByDate != 0){
			return compareByDate;
		}
		return -1; 
	}
}
