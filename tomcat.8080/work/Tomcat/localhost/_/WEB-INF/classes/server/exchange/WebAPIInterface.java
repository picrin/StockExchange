package server.exchange;

public interface WebAPIInterface {

	public getPendingTransaction(String user);
	public buyCommodity(String name);
	public sellCommodity(String name);
	public getCommodityState(String name);
	public getStockState();
	
}
