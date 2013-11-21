import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import junit.framework.Assert;

import org.junit.Test;


public class Tests {

	@Test
	public void cheapSellerBest() {
		Commodity teamA = new Commodity("Team A");
		//ConcurrentHashMap<Long, ConcurrentLinkedQueue<Transaction>> traders = new ConcurrentHashMap<Long, ConcurrentLinkedQueue<Transaction>>();
		SellTransaction sellOrderCheap = new SellTransaction(30, 1.25, 0, teamA);
		SellTransaction sellOrderExpensive = new SellTransaction(30, 2.57, 0, teamA);
		//BuyTransaction buyOrder = new BuyTransaction(30)
		Assert.assertEquals(teamA.sellQueue.poll(), sellOrderCheap);
	}

	@Test
	public void bullyBuyerBest() {
		Commodity teamA = new Commodity("Team A");
		//ConcurrentHashMap<Long, ConcurrentLinkedQueue<Transaction>> traders = new ConcurrentHashMap<Long, ConcurrentLinkedQueue<Transaction>>();
		BuyTransaction buyOrderCheap = new BuyTransaction(30, 1.25, 0, teamA);
		BuyTransaction buyOrderExpensive = new BuyTransaction(30, 2.57, 0, teamA);
		//BuyTransaction buyOrder = new BuyTransaction(30)
		Assert.assertEquals(teamA.buyQueue.poll(), buyOrderExpensive);
	}
	
	@Test
	public void soldEverything(){
		Commodity teamA = new Commodity("Team A");
		//ConcurrentHashMap<Long, ConcurrentLinkedQueue<Transaction>> traders = new ConcurrentHashMap<Long, ConcurrentLinkedQueue<Transaction>>();
		SellTransaction bullySell = new SellTransaction(30, 1.25, 0, teamA);
		BuyTransaction bullyBuyer = new BuyTransaction(30, 2.57, 0, teamA);
		//BuyTransaction buyOrder = new BuyTransaction(30)
		
		Assert.assertEquals(bullySell.quantity, 0);
		Assert.assertEquals(bullySell.quantity, 0);

	}

	@Test
	public void soldAllButOne(){
		Commodity teamA = new Commodity("Team A");
		//ConcurrentHashMap<Long, ConcurrentLinkedQueue<Transaction>> traders = new ConcurrentHashMap<Long, ConcurrentLinkedQueue<Transaction>>();
		SellTransaction bullySell = new SellTransaction(31, 1.25, 0, teamA);
		BuyTransaction bullyBuyer = new BuyTransaction(30, 2.57, 0, teamA);
		//BuyTransaction buyOrder = new BuyTransaction(30)
		
		Assert.assertEquals(bullyBuyer.quantity, 0);
		Assert.assertEquals(bullySell.quantity, 1);

	}

	@Test
	public void soldAllAndNoMore(){
		Commodity teamA = new Commodity("Team A");
		//ConcurrentHashMap<Long, ConcurrentLinkedQueue<Transaction>> traders = new ConcurrentHashMap<Long, ConcurrentLinkedQueue<Transaction>>();
		SellTransaction bullySell = new SellTransaction(30, 1.25, 0, teamA);
		BuyTransaction bullyBuyer = new BuyTransaction(31, 2.57, 0, teamA);
		//BuyTransaction buyOrder = new BuyTransaction(30)		
		Assert.assertEquals(bullyBuyer.quantity, 1);
		Assert.assertEquals(bullySell.quantity, 0);
	}

	public static int transactionVolume = 300000;
	@Test
	public void massiveTrading(){
		Commodity teamA = new Commodity("Team A");
		//ConcurrentHashMap<Long, ConcurrentLinkedQueue<Transaction>> traders = new ConcurrentHashMap<Long, ConcurrentLinkedQueue<Transaction>>();
		Random generator = new Random();
		ArrayList<SellTransaction> sellTrans = new ArrayList<SellTransaction>(transactionVolume);
		ArrayList<BuyTransaction> buyTrans = new ArrayList<BuyTransaction>(transactionVolume);
		double moneyInBuying = 0;
		double moneyInSelling = 0;

		for(int i = 0; i<transactionVolume; i++){
			double sellAt = generator.nextDouble();
			double buyAt = generator.nextDouble();
			int sellNo = generator.nextInt(100);
			int buyNo = generator.nextInt(100);
			moneyInBuying += buyAt * buyNo;
			moneyInSelling += sellAt * sellNo;
			sellTrans.add(new SellTransaction(sellNo, sellAt, 0, teamA));
			buyTrans.add(new BuyTransaction(buyNo, buyAt, 1, teamA));
		}
		

		double sellerMoneyPending = 0;
		double buyerMoneyPending = 0;

		for(SellTransaction st: sellTrans){
			sellerMoneyPending += st.prize * st.quantity;
		}

		for(BuyTransaction bt: buyTrans){
			buyerMoneyPending += bt.prize * bt.quantity;
		}
		//System.out.println("money in selling: " + moneyInSelling);
		//System.out.println("money in buying: " + moneyInBuying);
		
		//System.out.println("money paid to sellers: " + IO.seller);
		//System.out.println("money paid to buyers: " + IO.buyer);
	
		//System.out.println("money pending in sellers: " + sellerMoneyPending);
		//System.out.println("money pending in buyers: " + buyerMoneyPending);
		Assert.assertEquals(moneyInSelling, IO.seller + sellerMoneyPending, 0.000001);
	}

	

}
