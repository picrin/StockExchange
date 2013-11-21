import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

abstract class Transaction{
	int quantity;
	Date date;
	float prize;
	long userID;
	Commodity commodity;
	Transaction(int quantity, float prize, long userID, Commodity commodityID){
		this.quantity = quantity;
		this.date = new Date();
		this.prize = prize;
		this.commodity = commodityID;
		this.userID = userID;
	}
	
	public void toPendingList(ConcurrentHashMap<Long, ConcurrentLinkedQueue<Transaction>> transactionsByUserID){
		if(!transactionsByUserID.contains(this.userID)){
			transactionsByUserID.put(this.userID, new ConcurrentLinkedQueue<Transaction>());
		}
		else{
			transactionsByUserID.get(this.userID).add(this);
		}
	}
	
	public void toPriorityQueue(){
		commodity.trade(this);
	}
	
	public boolean isEmpty(){
		return (this.quantity == 0);
	}
	
}