package server.exchange;

@Path("/exchange")
public class WebAPI implements WebAPIInterface {

	@GET
	public String getPendingTransaction(String user)
	{
		return "Hello World";
	}
	
	@PUT
	public String buyCommodity(String name)
	{
		return "Hello World";
	}
	
	@PUT
	public String sellCommodity(String name)
	{
		return "Hello World";
	}
	
	@GET
	public String getCommodityState(String name)
	{
		return "Hello World";
	}
	
	@GET
	public String getStockState()
	{
		return "Hello World";
	}
	
}
