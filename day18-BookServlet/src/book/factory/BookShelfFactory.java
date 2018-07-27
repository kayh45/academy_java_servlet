package book.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import book.dao.BookShelf;
import book.dao.JdbcBookShelf;
import book.dao.ListBookShelf;
import book.dao.MapBookShelf;
import book.dao.MybatisBookShelf;
import book.dao.SetBookShelf;
import book.vo.Book;


public class BookShelfFactory {
	
	public static BookShelf getBookShelf(String type) {
		
		BookShelf bookShelf = null;
		
		if ("list".equals(type)) {
			List<Book> books = new ArrayList<Book>();
			bookShelf = new ListBookShelf(books);
			
		} else if ("set".equals(type)) {
			Set<Book> books = new HashSet<Book>();
			bookShelf = new SetBookShelf(books);
			
		} else if ("map".equals(type)) {
			Map<String, Book> books = new HashMap<String, Book>();
			bookShelf = new MapBookShelf(books);
			
		} else if ("jdbc".equals(type)) {
			bookShelf = JdbcBookShelf.getInstance();
			
		} else if ("mybatis".equals(type)) {
			bookShelf = new MybatisBookShelf();
		}
		
		return bookShelf;
	}
}
