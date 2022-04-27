public class Product implements Comparable<Product>
{
	public static enum Category {GENERAL, CLOTHING, BOOKS, FURNITURE, COMPUTERS, SHOES};
	
	private String name;
	private String id;
	private Category category;
	private double price;
	private int stockCount;
	
	public Product()
	{
		this.name = "Product";
		this.id = "001";
		this.category = Category.GENERAL;
		this.stockCount = 0;
	}
	
	public Product(String id)
	{
		this("Product", id, 0, 0, Category.GENERAL);
	}

	public Product(String name, String id, double price, int stock, Category category)
	{
		this.name = name;
		this.id = id;
		this.price = price;
		this.stockCount = stock;
		this.category = category;
	}

	public boolean validOptions(String productOptions)
	{
		if (productOptions.equals(""))
		{
			return true;
		}
		return false;
	}
	
	public Category getCategory()
	{
		return category;
	}
	
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public double getPrice()
	{
		return price;
	}

	public void setPrice(double price)
	{
		this.price = price;
	}

	/*
	 * Return the number of items currently in stock for this product
	 */
	public int getStockCount(String productOptions)
	{
		return stockCount;
	}
	
	/*
	 * Return the number of colour shoe stocks. It will be used in Shoes subclass
	 */
	public int getColourStockCount(String productOptions)
	{
		return 0;
	}
	/*
	 * Set (or replenish) the number of items currently in stock for this product
	 */
	public void setStockCount(int stockCount, String productOptions)
	{
		this.stockCount = stockCount;
	}
	/*
	 * Reduce the number of items currently in stock for this product by 1 
	 */
	public void reduceStockCount(String productOIptions)
	{
		stockCount--;
	}
	
	public void print()
	{
		System.out.printf("\nId: %-5s Category: %-9s Name: %-20s Price: %7.1f", id, category, name, price);
	}
	/*
	 * Return the printed format of general products
	 */
	public String printProds()
	{
		String fs = String.format("\nId: %-5s Category: %-9s Name: %-20s Price: %7.1f", id, category, name, price);
		return fs;
	}
	/*
	 * Return the printed format of books product
	 */
	public String printBooksProds()
	{
		return "";
	}
	/*
	 * Return the printed format of the shoes product
	 */
	public String printShoesProds()
	{
		return "";
	}

	public boolean equals(Object other)
	{
		Product otherP = (Product) other;
		return this.id.equals(otherP.id);
	}
	/*
	 * Compares the prices
	 */
	public int compareTo(Product other)
    {
    	if (getPrice() > other.getPrice())
    	{
    		return 1;
    	}
    	else if (getPrice() < other.getPrice())
    	{
    		return -1;
    	}
    	else
    	{
    		return 0;
    	}
    }
	/*
	 * getting the year of publication. Will be used in Book subclass
	 */
	public int getYear()
	{
		return 0;
	}
    /*
     * getting the author's name. Will be used in Book subclass
     */
	public String getAuthor()
	{
		return "";
	}
	/*
	 * @param the productOption that will be passed
	 * @return the productOption to pass it to CartItem
	 */
	public String getProductOption(String productOption)
	{
		return productOption;
	}
}
