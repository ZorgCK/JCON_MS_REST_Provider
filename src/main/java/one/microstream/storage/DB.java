package one.microstream.storage;

import one.microstream.domain.DataRoot;
import one.microstream.storage.embedded.configuration.types.EmbeddedStorageConfiguration;
import one.microstream.storage.embedded.types.EmbeddedStorageManager;


public class DB
{
	public static EmbeddedStorageManager	storageManager;
	public final static DataRoot			root	= new DataRoot();
	
	static
	{
		storageManager = EmbeddedStorageConfiguration.Builder().setStorageDirectory("data").setChannelCount(
			4).createEmbeddedStorageFoundation().setRoot(root).createEmbeddedStorageManager().start();
	}
}
