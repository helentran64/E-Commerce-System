public class Book extends Product
{
  private String author;
  private String title;
  private int yearPublished;
  
  int paperbackStock;
  int hardcoverStock;
  int ebookStock;
  
  public Book(String name, String id, double price, int paperbackStock, int hardcoverStock, String title, String author, int year)
  {
	  super(name, id, price, 100000, Product.Category.BOOKS);
	  this.title = title;
	  this.author = author;
	  this.paperbackStock = paperbackStock;
	  this.hardcoverStock = hardcoverStock;
	  this.yearPublished = year;
  }
    
  // Check if a valid format  
  public boolean validOptions(String productOptions)
  {
	//if the productOptions are either "Paperback", "Hardcover" or "EBook", it will return true. Otherwise, it will return false
	if (productOptions.equalsIgnoreCase("Paperback") || productOptions.equalsIgnoreCase("Hardcover") || productOptions.equalsIgnoreCase("EBook"))
	{
		return true;
	}
	else
	{
		return false;
	}
  	
  }
  
  public int getStockCount(String productOptions)
	{
	  	//check for different product options
		if (productOptions.equalsIgnoreCase("Paperback"))
		{
			//returning the number of paperbackStock
			return paperbackStock;
		}
		else if (productOptions.equalsIgnoreCase("Hardcover"))
		{
			//returning the number of hardcoverStock
			return hardcoverStock;
		}
		else if (productOptions.equalsIgnoreCase("Ebook"))
		{
			//returning the number of Ebooks
			return super.getStockCount(productOptions);
		}
		else
			return 1;
	}
  
  public void setStockCount(int stockCount, String productOptions)
	{
	  if (productOptions.equalsIgnoreCase("Paperback"))
	  {
		  paperbackStock = getStockCount(productOptions);
	  }
	  else if (productOptions.equalsIgnoreCase("Hardcover"))
	  {
		  hardcoverStock = getStockCount(productOptions);
	  }
	  else if (productOptions.equalsIgnoreCase("EBook"))
	  {
		  ebookStock = stockCount;
	  }
	}
  
  /*
   * When a book is ordered, reduce the stock count for the specific stock type
   */
  public void reduceStockCount(String productOptions)
	{
	  if (productOptions.equalsIgnoreCase("Paperback"))
	  {
		  paperbackStock --;
	  }
	  else if (productOptions.equalsIgnoreCase("Hardcover"))
	  {
		  hardcoverStock --;
	  }
	}
  /*
   * Print product information in super class and append Book specific information title and author
   */
  public void print()
  {
	super.print();
	System.out.print(" Book Title: " + title + " Author: " + author + " Year Published: " + yearPublished);
  }
  /*
   * Return the printed information of book products
   */
  public String printBooksProds()
  {
	  String s = super.printProds() + " Book Title: " + title + " Author: " + author + " Year Published: " + yearPublished;
	  return s;
  }
  /*
   * getting the year of publication 
   */
  public int getYear()
  {
	  return this.yearPublished;
  }
  /*
   * getting the author's name
   */
  public String getAuthor()
  {
	  return this.author;
  }
}
