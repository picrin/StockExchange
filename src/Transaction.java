import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

abstract class Transaction{
	public int quantity;
	public Date date;
	public double prize;
	long userID;
	Commodity commodity;
	Transaction(int quantity, double prize, long userID, Commodity commodityID){
		this.quantity = quantity;
		this.date = new Date();
		this.prize = prize;
		this.commodity = commodityID;
		this.userID = userID;
		toPendingList(IO.transactionsByUserID);
	}
	//TODO przepisac funkcje
	public void toPendingList(ConcurrentHashMap<Long, ConcurrentLinkedQueue<Transaction>> transactionsByUserID){
		transactionsByUserID.putIfAbsent(this.userID, new ConcurrentLinkedQueue<Transaction>());
		transactionsByUserID.get(this.userID).add(this);
	}
	
	
	public boolean isEmpty(){
		return (this.quantity == 0);
	}
	
	public void printInfo(){
		
	}
	
}