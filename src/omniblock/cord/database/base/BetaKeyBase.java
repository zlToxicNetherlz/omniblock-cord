package omniblock.cord.database.base;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.UUID;

import omniblock.cord.database.sql.make.MakeSQLQuery;
import omniblock.cord.database.sql.make.MakeSQLUpdate;
import omniblock.cord.database.sql.make.MakeSQLUpdate.TableOperation;
import omniblock.cord.database.sql.type.TableType;
import omniblock.cord.database.sql.util.Resolver;
import omniblock.cord.database.sql.util.SQLResultSet;

public class BetaKeyBase {

	protected static String key_inserter_sql = "INSERT INTO betakeys (p_id, p_key) SELECT * FROM (SELECT VAR_P_ID a, VAR_P_KEY b) AS tmp WHERE NOT EXISTS (SELECT 1 FROM betakeys WHERE p_id = p_id);";
	
	public static String insertKey(String player) {
		
		String newKey = generateRandomBetaKey();
		
		MakeSQLUpdate msu = new MakeSQLUpdate(TableType.BETAKEYS, TableOperation.INSERT);
		
		msu.rowOperation("p_id", Resolver.getNetworkIDByName(player));
		msu.rowOperation("p_key", newKey);
		
		try {
			msu.execute();
		} catch (IllegalArgumentException | SQLException e) {
			e.printStackTrace();
		}
		 
		return newKey;
		
	}
	
	public static void removeKey(String player) {
		
		MakeSQLUpdate msu = new MakeSQLUpdate(TableType.BETAKEYS, TableOperation.DELETE);
		msu.whereOperation("p_id", Resolver.getNetworkIDByName(player));
		
		try {
			msu.execute();
		} catch (IllegalArgumentException | SQLException e) {
			e.printStackTrace();
		}

	}
	
	public static String getKey(String player) {
		
		MakeSQLQuery msq = new MakeSQLQuery(TableType.BETAKEYS)
				.select("p_key")
				.where("p_id", Resolver.getNetworkIDByName(player));
		
		try {
			
			SQLResultSet result = msq.execute();
			
			if(result.next()){
				
				return result.get("p_key");
				
			}
			
		} catch (SQLException e) { e.printStackTrace(); }
		
		return "NONE";
		
	}
	
	public static String generateRandomBetaKey() {
	
		String randUId = UUID.randomUUID().toString().substring(0, 6);
		
		try {
			
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(randUId.getBytes());
			byte[] digest = md.digest();
			
			StringBuilder sb = new StringBuilder();

			for (byte b : digest) {
				sb.append(String.format("%02x", b & 0xff));
			}
			
			return sb.toString().substring(0, 6);
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		System.err.println("No se pudo obtener el NetworkID usando MD5! Devolviendo UUID como NetworkID!");
		return randUId;
	
	}
	
}
