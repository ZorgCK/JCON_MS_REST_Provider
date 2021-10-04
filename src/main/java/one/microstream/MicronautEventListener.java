package one.microstream;

import java.util.List;

import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.event.annotation.EventListener;
import jakarta.inject.Singleton;
import one.microstream.domain.Book;
import one.microstream.storage.DB;
import one.microstream.utils.MockupUtils;


@Singleton
public class MicronautEventListener
{
	@EventListener
	public void onStartup(StartupEvent event)
	{
		if(DB.root.isFirstStart())
		{
			List<Book> allCreatedBooks = MockupUtils.loadMockupData();
			
			DB.root.getBooks().addAll(allCreatedBooks);
			DB.storageManager.store(DB.root.getBooks());
			
			DB.root.setFirstStart(false);
			DB.storageManager.store(DB.root);
		}
	}
}
