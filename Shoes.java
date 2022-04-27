import java.util.Scanner;

public class Shoes extends Product
{
	//instance variables
	//private String colour;
	private String shoeName;
	
	//stock related information
	//stock of all the colours in different sizes
	int colourBlackStockSize6;
	int colourBrownStockSize6;
	int colourBlackStockSize7;
	int colourBrownStockSize7;
	int colourBlackStockSize8;
	int colourBrownStockSize8;
	int colourBlackStockSize9;
	int colourBrownStockSize9;
	int colourBlackStockSize10;
	int colourBrownStockSize10;
	//size stock
	int size6Stock;
	int size7Stock;
	int size8Stock;
	int size9Stock;
	int size10Stock;
	
	/*
	 * Shoes constructor method. It will initialize the variables
	 */
	public Shoes(String name, String id, double price, int colourBlackStockSize6,  int colourBrownStockSize6, int colourBlackStockSize7, int colourBrownStockSize7, int colourBlackStockSize8, int colourBrownStockSize8, int colourBlackStockSize9, int colourBrownStockSize9, int colourBlackStockSize10, int colourBrownStockSize10, int size6Stock, int size7Stock, int size8Stock, int size9Stock, int size10Stock, String shoeName)
	{
		super(name, id, price, 100000, Product.Category.SHOES);
		//this.colour = colour;
		this.shoeName = shoeName;
		this.colourBlackStockSize6 = colourBlackStockSize6;
		this.colourBrownStockSize6 = colourBrownStockSize6;
		this.colourBlackStockSize7 = colourBlackStockSize7;
		this.colourBrownStockSize7 = colourBrownStockSize7;
		this.colourBlackStockSize8 = colourBlackStockSize8;
		this.colourBrownStockSize8 = colourBrownStockSize8;
		this.colourBlackStockSize9 = colourBlackStockSize9;
		this.colourBrownStockSize9 = colourBrownStockSize9;
		this.colourBlackStockSize10 = colourBlackStockSize10;
		this.colourBrownStockSize10 = colourBrownStockSize10;
		this.size6Stock = size6Stock;
		this.size7Stock = size7Stock;
		this.size8Stock = size8Stock;
		this.size9Stock = size8Stock;
		this.size10Stock = size8Stock;
	}
	/*
	 * check if the options are valid
	 * @param productOptions imports the options that customer entered
	 * @return if it is valid, return true. Otherwise return false
	 */
	public boolean validOptions(String productOptions)
	{
		boolean found = false;
		Scanner options = new Scanner(productOptions);
		while (options.hasNext())
		{
			String size = options.next();
			if(size.equals("6") || size.equals("7") || size.equals("8") || size.equals("9") || size.equals("10"))
			{
				String colour = options.next();
				if (colour.equalsIgnoreCase("Black") || colour.equalsIgnoreCase("Brown"))
				{
					found = true;
				}
			}
		}
		//Closing the scanner
		options.close();
		//if not found (if it is not a valid option, it will print an error message
		if (found)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	/*
	 * Getting the stockCount. It will override the superclass stockCount 
	 * @param productOptions imports the options that customer entered
	 * @return the stock count of each size
	 */
	public int getStockCount(String productOptions)
	{
		Scanner getStock = new Scanner(productOptions);
		while (getStock.hasNext())
		{
			String size = getStock.next();
			if (size.equals("6"))
			{
				return size6Stock;
			}
			else if (size.equals("7"))
			{
				return size7Stock;
			}
			else if (size.equals("8"))
			{
				return size8Stock;
			}
			else if (size.equals("9"))
			{
				return size9Stock;
			}
			else if (size.equals("10"))
			{
				return size10Stock;
			}
			else
			{
				return 0;
			}
		}
		getStock.close();
		return 0;
	}
	/*
	 * getting the stock counts of each shoe colour
	 * @param productOptions imports the options that customer entered
	 * @return the stock count of each colour of shoes within their sizes
	 */
	public int getColourStockCount(String productOptions)
	{
		Scanner getStock = new Scanner(productOptions);
		while (getStock.hasNext())
		{
			//size is the first string of productOptions
			String size = getStock.next();
			//colour is the first string of productOptions
			String colour = getStock.next();
			if (size.equals("6"))
			{
				if (colour.equalsIgnoreCase("Black"))
				{
					return colourBlackStockSize6;
				}
				else if (colour.equalsIgnoreCase("Brown"))
				{
					return colourBrownStockSize6;
				}
			}
			else if (size.equals("7"))
			{
				if (colour.equalsIgnoreCase("Black"))
				{
					return colourBlackStockSize7;
				}
				else if (colour.equalsIgnoreCase("Brown"))
				{
					return colourBrownStockSize7;
				}
			}
			else if (size.equals("8"))
			{
				if (colour.equalsIgnoreCase("Black"))
				{
					return colourBlackStockSize8;
				}
				else if (colour.equalsIgnoreCase("Brown"))
				{
					return colourBrownStockSize8;
				}
			}
			else if (size.equals("9"))
			{
				if (colour.equalsIgnoreCase("Black"))
				{
					return colourBlackStockSize9;
				}
				else if (colour.equalsIgnoreCase("Brown"))
				{
					return colourBrownStockSize9;
				}
			}
			else if (size.equals("10"))
			{
				if (colour.equalsIgnoreCase("Black"))
				{
					return colourBlackStockSize10;
				}
				else if (colour.equalsIgnoreCase("Brown"))
				{
					return colourBrownStockSize10;
				}
			}
			else
			{
				return 0;
			}
		}
		getStock.close();
		return 0;
	}
	/*
	 * Setting the stockCount
	 */
	public void setStockCount(int stockCount, String productOptions)
	{
		Scanner setStock = new Scanner(productOptions);
		while (setStock.hasNext())
		{
			String stock = setStock.next();
			if (stock.equals("6"))
			{
				size6Stock = getStockCount(stock);
			}
			else if (stock.equals("7"))
			{
				size7Stock = getStockCount(stock);
			}
			else if (stock.equals("8"))
			{
				size8Stock = getStockCount(stock);
			}
			else if (stock.equals("9"))
			{
				size9Stock = getStockCount(stock);
			}
			else if (stock.equals("10"))
			{
				size10Stock = getStockCount(stock);
			}
		}
		setStock.close();
	}
	/*
	 * Reducing the stock count for both shoe size and each shoe colours
	 * @param productOptions imports the options that customer entered
	 */
	public void reduceStockCount(String productOptions)
	{
		Scanner reduceStock = new Scanner(productOptions);
		while (reduceStock.hasNext())
		{
			//calls the shoe size
			String stock = reduceStock.next();
			//calls the colour of the shoes
			String colour = reduceStock.next();
			//will find the size and colour then decrement the stock count
			if (stock.equals("6"))
			{
				size6Stock --;
				if (colour.equalsIgnoreCase("Black"))
				{
					colourBlackStockSize6--;
				}
				else
				{
					colourBrownStockSize6--;
				}
			}
			else if (stock.equals("7"))
			{
				size7Stock --;
				if (colour.equalsIgnoreCase("Black"))
				{
					colourBlackStockSize7--;
				}
				else
				{
					colourBrownStockSize7--;
				}
			}
			else if (stock.equals("8"))
			{
				size8Stock --;
				if (colour.equalsIgnoreCase("Black"))
				{
					colourBlackStockSize8--;
				}
				else
				{
					colourBrownStockSize8--;
				}
			}
			else if (stock.equals("9"))
			{
				size9Stock --;
				if (colour.equalsIgnoreCase("Black"))
				{
					colourBlackStockSize9--;
				}
				else
				{
					colourBrownStockSize9--;
				}
			}
			else if (stock.equals("10"))
			{
				size10Stock --;
				if (colour.equalsIgnoreCase("Black"))
				{
					colourBlackStockSize10--;
				}
				else
				{
					colourBrownStockSize10--;
				}
			}
		}
		reduceStock.close();
	}
	/*
	 * print product information in superclass and append Shoes information
	 */
	public void print()
	{
		super.print();
		System.out.print(" Shoe Name: " + shoeName);
	}
	/*
	 * Return the printed format of the shoes product
	 */
	public String printShoesProds()
	{
		String shoeInfo = super.printProds() + " Shoe Name: " + shoeName;
		return shoeInfo;
	}
	
}
