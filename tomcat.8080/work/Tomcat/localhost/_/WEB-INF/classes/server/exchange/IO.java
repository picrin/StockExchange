package server.exchange;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
public class IO{
	public static double buyer = 0;
	public static double seller = 0;
	static ConcurrentHashMap<Long, ConcurrentLinkedQueue<Transaction>> transactionsByUserID = new ConcurrentHashMap<Long, ConcurrentLinkedQueue<Transaction>>();
	public static String serveTransactionsByUser(long userID){
		ObjectMapper mapper = new ObjectMapper();
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    try {
			return ow.writeValueAsString(transactionsByUserID);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
		    return "died 1";
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
		    return "died 2";
		} catch (IOException e) {
			// TODO Auto-generated catch block
		    return "died 3";
		}
	}
	public static void notifyWalletChange(long clientID, double value){
		if(clientID == 1){
			buyer += value;
		}
		if(clientID == 0){
			seller += value;
		}

	}
}
