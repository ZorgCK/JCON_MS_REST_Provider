package one.microstream.controller;

import java.util.List;
import java.util.Optional;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import one.microstream.domain.Book;
import one.microstream.storage.DB;


@Controller("/books")
public class BookController
{
	@Get("/list")
	public List<Book> getBook()
	{
		return DB.root.getBooks();
	}
	
	@Post("/update")
	public HttpResponse<Book> update(Book dtoBook)
	{
		Optional<Book> bookOptional =
			DB.root.getBooks().stream().filter(b -> b.getIsbn().equalsIgnoreCase(dtoBook.getIsbn())).findFirst();
		
		if(bookOptional.isPresent())
		{
			Book book = bookOptional.get();
			book.setName(dtoBook.getName());
			book.setPrice(dtoBook.getPrice());
			book.setRelease(dtoBook.getRelease());
			book.setAuthor(dtoBook.getAuthor());
			
			DB.storageManager.store(book);
			
			return HttpResponse.ok(book);
		}
		
		return HttpResponse.notFound();
	}
	
	@Post("/insert")
	public HttpResponse<Book> insert(Book dtoBook)
	{
		Optional<Book> bookOptional =
			DB.root.getBooks().stream().filter(b -> b.getIsbn().equalsIgnoreCase(dtoBook.getIsbn())).findFirst();
		
		if(!bookOptional.isPresent())
		{
			Book book = new Book(
				dtoBook.getIsbn(),
				dtoBook.getName(),
				dtoBook.getRelease(),
				dtoBook.getPrice(),
				dtoBook.getAuthor());
			
			DB.root.getBooks().add(book);
			DB.storageManager.store(DB.root.getBooks());
			
			return HttpResponse.ok(book);
		}
		
		return HttpResponse.notAllowed();
	}
	
	@Post("/delete")
	public HttpResponse<String> delete(Book dtoBook)
	{
		Optional<Book> bookOptional =
			DB.root.getBooks().stream().filter(b -> b.getIsbn().equalsIgnoreCase(dtoBook.getIsbn())).findFirst();
		
		if(bookOptional.isPresent())
		{
			Book book = bookOptional.get();
			
			DB.root.getBooks().remove(book);
			DB.storageManager.store(DB.root.getBooks());
			
			return HttpResponse.ok("Book successfully deleted");
		}
		
		return HttpResponse.notFound();
	}
}
