package server.exchange;

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

	public static int transactionVolume = 10;//300000;
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
	@Test
	public void dummyIOTest(){
		Commodity queenBed = new Commodity("queen's sleep");
		Commodity myBed = new Commodity("private bed");
		Commodity bedNgirl = new Commodity("buy 1 get 1 free");
		
		//User 0
		SellTransaction carefullTrans1 = new SellTransaction(81, 100.00, 0, queenBed);
		SellTransaction carefullTrans2 = new SellTransaction(134, 89.1, 0, myBed);

		BuyTransaction carefullBuy2 = new BuyTransaction(89, 8.09, 0, bedNgirl);
		BuyTransaction carefullBuy3 = new BuyTransaction(111, 4.13, 0, myBed);
		BuyTransaction carefullBuy1 = new BuyTransaction(22, 5.00, 0, queenBed);

		//User 1
		SellTransaction carefulTrans1Us1 = new SellTransaction(11, 702.0, 1, bedNgirl);

		BuyTransaction carefullBuy1Us1 = new BuyTransaction(81, 3.13, 1, bedNgirl);
		BuyTransaction carefullBuy2Us1 = new BuyTransaction(8, 0.01, 1, queenBed);
		System.out.println(IO.serveTransactionsByUser(1));
		System.out.println(IO.serveTransactionsByUser(0));
	}
	

}
