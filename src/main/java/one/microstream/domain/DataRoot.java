package one.microstream.domain;

import java.util.ArrayList;
import java.util.List;


public class DataRoot
{
	private final List<Book>	books		= new ArrayList<Book>();
	private boolean				firstStart	= true;
	
	public List<Book> getBooks()
	{
		return books;
	}
	
	public boolean isFirstStart()
	{
		return firstStart;
	}
	
	public void setFirstStart(boolean firstStart)
	{
		this.firstStart = firstStart;
	}
	
}
