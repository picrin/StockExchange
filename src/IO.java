
public class IO{
	public static double buyer = 0;
	public static double seller = 0;

	public static void notifyWalletChange(long clientID, double value){
		if(clientID == 1){
			buyer += value;
		}
		if(clientID == 0){
			seller += value;
		}

	}
}
