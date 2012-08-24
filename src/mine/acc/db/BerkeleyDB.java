package mine.acc.db;

import java.io.File;

import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.DatabaseEntry;
import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;
import com.sleepycat.je.LockMode;
import com.sleepycat.je.OperationStatus;

public class BerkeleyDB {

	private Environment env;
	private Database db;

	public BerkeleyDB() {

	}

	/** 构建Environment： 指定存储的文件（一个Environment可以有多个数据库） */
	public void setUp(String path, long cacheSize) {
		EnvironmentConfig envConfig = new EnvironmentConfig();
		envConfig.setAllowCreate(true);
		envConfig.setCacheSize(cacheSize);//The memory available to the database system, in bytes.
		File dir = new File(path);
		if(!dir.exists()) {//如果指定的目录不存在，则自动创建
			dir.mkdir();
			System.out.println("创建目录："+path);
		}
		try {
			env = new Environment(dir, envConfig);
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
	}

	/** 构建Database： 指定数据库名字，如果指定名字的数据库不存在，则自动创建。*/
	public void open(String dbName) {
		DatabaseConfig dbConfig = new DatabaseConfig();
		dbConfig.setAllowCreate(true);
		try {
			db = env.openDatabase(null, dbName, dbConfig);
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
	}

	/** 关闭数据库和环境 */
	public void close() {
		try {
			if (db != null) {
				db.close();
			}
			if (env != null) {
				env.close();
			}
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
	}

	/**数据操作：写
	 * BDB存储的数据是无格式的，都是二进制的数据，无论是KEY，还是VALUE。
	 * 如果我们要存取JAVA对象，需要程序员先序列化成二进制的。
	 * */
	public boolean put(String key, String value) throws Exception {
		byte[] theKey = key.getBytes("UTF-8");
		byte[] theValue = value.getBytes("UTF-8");
		OperationStatus status = db.put(null, new DatabaseEntry(theKey),
				new DatabaseEntry(theValue));
		if (status == OperationStatus.SUCCESS) {
			return true;
		}
		return false;
	}
	
	/**数据操作：读 */
	public String get(String key) throws Exception {
		DatabaseEntry queryKey = new DatabaseEntry();
		DatabaseEntry value = new DatabaseEntry();
		queryKey.setData(key.getBytes("UTF-8"));

		OperationStatus status = db
				.get(null, queryKey, value, LockMode.DEFAULT);
		if (status == OperationStatus.SUCCESS) {
			return new String(value.getData());
		}
		return null;
	}
	
	/**数据操作：修改 （覆盖写就是修改）*/
	public boolean update(String key, String value) throws Exception {
		   byte[] updateKey = key.getBytes("UTF-8");
		   byte[] updateValue = value.getBytes("UTF-8");

		   OperationStatus status = db.put(null, new DatabaseEntry(updateKey), new DatabaseEntry(updateValue));
		   if (status == OperationStatus.SUCCESS) {
		    return true;
		   }
		   return false;
	}
	
	/**数据操作：删除 */
	public boolean delete(String key) throws Exception {
		   byte[] theKey = key.getBytes("UTF-8");
		   OperationStatus status = db.delete(null, new DatabaseEntry(theKey));
		   if(status == OperationStatus.SUCCESS) {
		    return true;
		   }
		   return false;
	}


	public static void main(String[] args) throws Exception {
		BerkeleyDB mbdb = new BerkeleyDB();
		mbdb.setUp("/home/wangfengwei/tmp/bdb/", 1000000);
		mbdb.open("myDB");
		
		System.out.println(mbdb.get("wfwei"));
		
		System.out.println("开始向Berkeley DB中存入数据...");  
    
		for (int i = 0; i < 20; i++) {  
          try {  
              String key = "myKey" + i;  
              String value = mbdb.get(key);//"myValue" + i;  
              System.out.println("[" + key + ":" + value + "]");  
              mbdb.put(key, value);  
          } catch (Exception e) {  
              e.printStackTrace();  
          }  
      }  
		
		mbdb.put("wfwei", "20");
		String value = mbdb.get("wfwei");
		System.out.println(value);
		
		mbdb.close();

	}

}