import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/*
 * Models a simple ECommerce system. Keeps track of products for sale, registered customers, product orders and
 * orders that have been shipped to a customer
 */
public class ECommerceSystem 
{
    private Map<String,Product>  products = new HashMap<String,Product>();
    private ArrayList<Customer> customers = new ArrayList<Customer>();	
    
    //counting the number of products ordered - statistics
    private Map<Product, Integer> orderCount = new HashMap<Product, Integer>();
    
    private ArrayList<ProductOrder> orders = new ArrayList<ProductOrder>();
    private ArrayList<ProductOrder> shippedOrders   = new ArrayList<ProductOrder>();
    
    //add all the products onto this Map
	Map<String, Integer> allGeneralProducts = new HashMap<String, Integer>();
	
	//products for bonus: rating
	ArrayList<Integer> rockHammer           = new ArrayList<Integer>();
	ArrayList<Integer> acerLaptop           = new ArrayList<Integer>();
	ArrayList<Integer> apexDesk             = new ArrayList<Integer>();
	ArrayList<Integer> jeans                = new ArrayList<Integer>();
	ArrayList<Integer> polo                 = new ArrayList<Integer>();
	ArrayList<Integer> tightieWhities       = new ArrayList<Integer>();
	//books for bonus: rating
	ArrayList<Integer> TM1                  = new ArrayList<Integer>();
	ArrayList<Integer> TM2                  = new ArrayList<Integer>();
	ArrayList<Integer> TM3                  = new ArrayList<Integer>();
	ArrayList<Integer> AF                   = new ArrayList<Integer>();
	ArrayList<Integer> DU                   = new ArrayList<Integer>();
	//shoes for bonus: rating
	ArrayList<Integer> AF1                  = new ArrayList<Integer>();
	ArrayList<Integer> UB                   = new ArrayList<Integer>();
	ArrayList<Integer> vans                 = new ArrayList<Integer>();
    
    // These variables are used to generate order numbers, customer id's, product id's 
    private int orderNumber = 500;
    private int customerId = 900;
    private int productId = 700;
    
    // General variable used to store an error message when something is invalid (e.g. customer id does not exist)  
    String errMsg = null;
    
    // Random number generator
    Random random = new Random();
    
    public ECommerceSystem()
    {
    	try
    	{
    		//this will add all the items in the array list to the products array list
    		ArrayList<Product> p = readFile("products.txt");
    		for (int i = 0; i < p.size(); i++)
    		{
    			products.put(p.get(i).getId(), p.get(i));
    		}
    	}
    	//if it was unable to open, it will print an exception message
    	catch (IOException e)
    	{
    		System.out.println(e.getMessage());
    	}
    	// Creating objects for shoes array list
    	//Temporary ArrayList for shoes
    	ArrayList<Product> shoes = new ArrayList<Product>();
    	shoes.add(new Shoes("Shoes", generateProductId(), 135.00, 2, 3, 4, 2, 1, 3, 4, 2, 5, 4, 5, 6, 4, 6, 9, "Air Force 1"));
    	shoes.add(new Shoes("Shoes", generateProductId(), 250.00, 2, 2, 1, 5, 3, 5, 2, 4, 1, 2, 4, 6, 8, 6, 3, "Ultraboosts 4.0 DNA"));
    	shoes.add(new Shoes("Shoes", generateProductId(), 85.00, 1, 1, 2, 4, 3, 4, 3, 3, 2, 1, 2, 6, 7, 6, 3, "Vans Old Skool"));
    	//adding shoes to the products Map
    	for (int i = 0; i < shoes.size(); i++)
    	{
    		products.put(shoes.get(i).getId(), shoes.get(i));
    	}
    	
    	// Create some customers. Notice how generateCustomerId() method is used
    	customers.add(new Customer(generateCustomerId(),"Inigo Montoya", "1 SwordMaker Lane, Florin"));
    	customers.add(new Customer(generateCustomerId(),"Prince Humperdinck", "The Castle, Florin"));
    	customers.add(new Customer(generateCustomerId(),"Andy Dufresne", "Shawshank Prison, Maine"));
    	customers.add(new Customer(generateCustomerId(),"Ferris Bueller", "4160 Country Club Drive, Long Beach"));	
    }
    /*
     * Reads the product.txt
     * @param fileName is the name of the file that will be read
     * @return ArrayList of productObjects
     */
    private ArrayList<Product> readFile(String fileName) throws IOException
    {
    	//create a new ArrayList<Product> to add the products into
    	ArrayList<Product> productList = new ArrayList<Product>();
    	
    	//reading the file
    	File inputFile = new File(fileName);
    	Scanner input = new Scanner(inputFile);
    	
    	//11 different products
    	int numProducts = 11;
    	while (numProducts > 0)
    	{
    		//looks for the category
    		String category = input.nextLine();
    		//for non-books
    		if (!category.equalsIgnoreCase("BOOKS"))
    		{
    			String name = "";
				String price = "";
				String stock = "";
				String productOption = "";
				//filling in the variables
				name = input.nextLine();
				price = input.nextLine();
				stock = input.nextLine();
				productOption = input.nextLine();
				
				//casting
				double priceDouble = Double.parseDouble(price);
				int stockInteger = Integer.parseInt(stock);
				
				//adding the product to the array list
				productList.add(new Product(name, generateProductId(), priceDouble, stockInteger, Product.Category.valueOf(category)));
    		}
    		//for books
    		else
    		{
    			String name = "";
				String price = "";
				String stock = "";
				String productOption = "";
				//filling in the variables
				name = input.nextLine();
				price = input.nextLine();
				stock = input.nextLine();
				productOption = input.nextLine();
				
				//casting price
				double priceDouble = Double.parseDouble(price);
				
				//edit stocks
				int paperback = 0;
				int hardcover = 0;
				//using scanner to split the stock counts
				Scanner stockInput = new Scanner(stock);
				if (stockInput.hasNext())
				{
					paperback = stockInput.nextInt();
				}
				if (stockInput.hasNext())
				{
					hardcover = stockInput.nextInt();
				}
				stockInput.close();
				
				//edit productOptions
				String title = "";
				String author = "";
				int year = 0;
				//using scanner to split the title, author and year
				Scanner optionsInput = new Scanner(productOption);
				optionsInput.useDelimiter(":");
				if (optionsInput.hasNext())
				{
					title = optionsInput.next();
				}
				if (optionsInput.hasNext())
				{
					author = optionsInput.next();
				}
				if (optionsInput.hasNextInt())
				{
					year = optionsInput.nextInt();
				}
				optionsInput.close();
				
				//adding the book product to the array list
				productList.add(new Book(name, generateProductId(), priceDouble, paperback, hardcover, title, author, year));
    		}
    		//subtract each non-book and book products
    		numProducts--;
    	}
    	input.close();
    	return productList;
    }
    private String generateOrderNumber()
    {
    	return "" + orderNumber++;
    }
    private String generateCustomerId()
    {
    	return "" + customerId++;
    }
    private String generateProductId()
    {
    	return "" + productId++;
    }
    public String getErrorMessage()
    {
    	return errMsg;
    }
    public void printAllProducts()
    {
    	for (String key: products.keySet())
    	{
    		Product value = products.get(key);
    		value.print();
    	}
    }
    // Print all products that are books. See getCategory() method in class Product
    public void printAllBooks()
    {	
    	for (String key: products.keySet())
    	{
    		Product value = products.get(key);
    		if (value.getCategory().equals(Product.Category.BOOKS))
    		{
    			value.print();
    		}
    	}
    }
    // Print all current orders
    public void printAllOrders()
    {
    	for (ProductOrder o: orders)
    	{
    		o.print();
    	}
    }
    // Print all shipped orders
    public void printAllShippedOrders()
    {
    	for (ProductOrder shipped: shippedOrders)
    	{
    		shipped.print();
    	}
    }
    // Print all customers
    public void printCustomers()
    {
    	//in an enhanced loop, it will print out the list of customers
    	for (Customer c: customers)
    	{
    		//uses a template to print out information
    		c.print();
    	}
    }
    /*
     * Given a customer id, print all the current orders and shipped orders for them 
     */
    public void printOrderHistory(String customerId)
    {
    	boolean found = false;
    	for (Customer c: customers)
    	{
    		if (customerId.equals(c.getId()))
    		{
    			found = true;
    		}
    	}
    	//if customerId does not match a customerId from the customers array list, return an exception
    	if (!found)
    	{
    		throw new UnknownCustomerException("Customer " + customerId + " Not Found");
    	}
    	// Print current orders of this customer 
    	System.out.println("Current Orders of Customer " + customerId);
    	// enter code here
    	ProductOrder order = null;
    	//loop through the orders list to see if the customer ordered a product
    	for (ProductOrder p: orders)
    	{
    		//calling the Customer class to find the customerId
    		Customer c = p.getCustomer();
    		//checking if the customerId parameter matches the customerId from the orders list
    		if (customerId.equals(c.getId()))
    		{
    			//if true then it will print out the information
    			order = p;
    			order.print();
    		}
    	}
    	// Print shipped orders of this customer 
    	System.out.println("\nShipped Orders of Customer " + customerId);
    	//enter code here
    	ProductOrder ship = null;
    	//loop through the shippedOrders list to see if product is shipped
    	for (ProductOrder shipped: shippedOrders)
    	{
    		//calling the Customer class to find the customerId
    		Customer c = shipped.getCustomer();
    		//checking if the customerId parameter matches the customerId from the shippedOrders list
    		if (customerId.equals(c.getId()))
    		{
    			//if true then it will print out the information
    			ship = shipped;
    			ship.print();
    		}
    	}
    }
    public String orderProduct(String productId, String customerId, String productOptions)
    {
    	// checking if product object exists in array list customers
    	boolean successProds = false;
    	
    	for (String key: products.keySet())
    	{
    		Product value = products.get(key);
    		if (value.getId().equals(productId))
    		{
    			successProds = true;
    		}
    	}
    	if (!successProds)
		{
    		throw new UnknownProductException("Product " + productId + " Not Found");
		}
    	for (String key: products.keySet())
    	{
    		Product value = products.get(key);
    		if (value.getId().equals(productId))
    		{
    			boolean success = value.validOptions(productOptions);
    			if (success == false)
    			{
    				throw new InvalidProductOptionsException("Product Id: " + productId + " Invalid Options: " + productOptions);
    			}
    		}
    	}
    	// Check if the options are valid for the book    	
    	for (String key: products.keySet())
    	{
    		Product value = products.get(key);
    		if (value.getId().equals(productId))
    		{
    			if (value.getCategory().equals(Product.Category.BOOKS))
    			{
    				boolean success = value.validOptions(productOptions);
    				if (success == false)
        	    	{
        	    		throw new InvalidProductOptionsException("Product Id: " + productId + " Invalid Options: " + productOptions);
        	    	}
    			}
    		}
    	}
    	for (String key: products.keySet())
    	{
    		Product value = products.get(key);
    		if (value.getId().equals(productId))
    		{
    			int count = value.getStockCount(productOptions);
    			if (count == 0)
    			{
    				throw new ProductOutOfStockException("Product Id: " + productId + " Out of Stock");
    			}
    		}
    	}
    	// Check for books stock count
    	for (String key: products.keySet())
    	{
    		Product value = products.get(key);
    		if (value.getId().equals(productId))
    		{
    			if (value.getCategory().equals(Product.Category.BOOKS))
    			{
    				int count = value.getStockCount(productOptions);
    				if (count == 0)
        	    	{
    					throw new ProductOutOfStockException("Product Id: " + productId + " Out of Stock");
        	    	}
    			}
    		}
    	}
    	//check for shoes stock count
    	for (String key: products.keySet())
    	{
    		Product value = products.get(key);
    		if (value.getId().equals(productId))
    		{
    			if (value.getCategory().equals(Product.Category.SHOES))
    			{
    				//count will check for the stock count of the shoe size
    				int count = value.getStockCount(productOptions);
    				//colourCount will check for the stock count of the stock of each shoe colour
    				int colourCount = value.getColourStockCount(productOptions);
    				if (count == 0 || colourCount == 0)
        	    	{
    					throw new ProductOutOfStockException("Product Id: " + productId + " Out of Stock");
        	    	}
    			}
    		}
    	}
    	String orderNumber = generateOrderNumber();
    	
    	//adding to order list
    	Product productItem = null;
    	Customer currentCustomer = null;
    	for (String key: products.keySet())
    	{
    		Product value = products.get(key);
    		if (productId.equals(value.getId()))
    		{
    			//getting the product from class Product
    			productItem = value;
    			//checking if the category is BOOKS
    			if (value.getCategory().equals(Product.Category.BOOKS))
    			{
    				value.reduceStockCount(productOptions);
    				break;
    			}
    			value.reduceStockCount(productOptions);
    		}
    	}
    	for (Customer c: customers)
    	{
    		if (customerId.equals(c.getId()))
    		{
    			//getting the product from class Customer
    			currentCustomer = c;
    		}
    	}
    	//creates new object for the orders array list
    	ProductOrder p = new ProductOrder(orderNumber, productItem, currentCustomer, productOptions);
    	orders.add(p);
    	
    	//adding the order count
    	Product cartProduct = productItem;
    	
    	if (!orderCount.containsKey(cartProduct))
    	{
    		orderCount.put(cartProduct, 1);
    	}
    	else
    	{
    		Integer count = orderCount.get(cartProduct);
    		orderCount.put(cartProduct, count+1);
    	}
    	
    	//returning the order number
    	return orderNumber;
    }
    /*
     * Create a new Customer object and add it to the list of customers
     */
    public void createCustomer(String name, String address)
    {
    	//checks to see if the parameters are empty
    	if (!name.equals("") && !address.equals(""))
    	{
    		//creating a new customer object and adding it to the customer array list then returning true
    		customers.add(new Customer(generateCustomerId(), name, address));
    	}
    	//check to see if address parameter is empty
    	else if (!name.equals("") && address.equals(""))
    	{
    		throw new InvalidCustomerAddressException("Invalid Customer Address " + address);
    	}
    	//check to see if name parameter is empty
    	else if (name.equals("") && !address.equals(""))
    	{
    		throw new InvalidCustomerNameException("Invalid Customer Name " + name);
    	}
    	//check to see if name and address parameter is empty
    	else if (name.equals("") && address.equals(""))
    	{
    		throw new InvalidNameAndAddressException("Empty Customer Name and Address");
    	}
    }
    public void shipOrder(String orderNumber)
    {
    	boolean ok = false;
    	ProductOrder order = null;
    	//will check if the order number matches an order number from the orders list
    	int index = -1;
    	for (int i = 0; i < orders.size(); i++)
    	{
    		order = orders.get(i);
    		if (order.getOrderNumber().equals(orderNumber))
    		{
    			index = i;
    			break;
    		}
    	}
    	//if order number is found then it will remove the order from the orders list and add to the shipped orders list
    	if (index != -1)
    	{
    		// Retrieve the order from the orders array list, remove it, then add it to the shippedOrders array list
    		order.print();
			//removing the order
    		orders.remove(index);
			//adding the order to the shipped orders
	    	shippedOrders.add(new ProductOrder(orderNumber, order.getProduct(), order.getCustomer(), order.getProductOptions()));
	    	ok = true;
    	}
    	//if false, it will print an error message
    	else if (!ok)
    	{
    		throw new InvalidOrderNumber("Order " + orderNumber + " Not Found");
    	}
    }
    /*
     * Cancel a specific order based on order number
     */
    public void cancelOrder(String orderNumber)
    {
    	boolean found = false;
    	//will check if the order number exists
    	for (ProductOrder p : orders)
    	{
    		if (orderNumber.equals(p.getOrderNumber()))
    		{
    			found = true;
    		}
    	}
    	//if the order number input is not found in the orders list, it will return false
    	if (!found)
    	{
    		throw new InvalidOrderNumber("Order " + orderNumber + " Not Found");
    	}
    	//deletes the order by looping through the orders array list if it finds a match
    	ProductOrder order = null;
    	int index = -1;
    	for (int i = 0; i < orders.size(); i++)
    	{
    		order = orders.get(i);
    		if (order.getOrderNumber().equals(orderNumber))
    		{
    			index = i;
    			break;
    		}
    	}
    	//if found then it will proceed to remove the object
    	if (index != -1)
    	{
    		orders.remove(index);
    	}
    }    
    /*
     * Sort products by increasing price
     */
    public void sortByPrice()
    {
    	//Temporary ArrayList
    	ArrayList<Product> tempProducts = new ArrayList<Product>();
    	for (String key: products.keySet())
    	{
    		Product value = products.get(key);
    		tempProducts.add(value);
    	}
    	//sorting the list
    	Collections.sort(tempProducts);
    	for (Product p: tempProducts)
    	{
    		p.print();
    	}
    }
    /*
     * Sort products alphabetically by product name
     */
    public void sortByName()
    {
    	//Temporary ArrayList
    	ArrayList<Product> tempProducts = new ArrayList<Product>();
    	for (String key: products.keySet())
    	{
    		Product value = products.get(key);
    		tempProducts.add(value);
    	}
    	//sorting the list
    	Collections.sort(tempProducts, new ProductNameComparator());
    	for (Product p: tempProducts)
    	{
    		p.print();
    	}
    }
    /*
     * Sort products alphabetically by product name
     */
    public void sortCustomersByName()
    {
    	Collections.sort(customers);
    	for (Customer c: customers)
    	{
    		c.print();
    	}
    }
    /*
     * Will find the author's name and sort the year published by increasing order
     * @param authorName is the author's name
     * @return will return true if the name is found and info in printed. Otherwise return false
     */
    public void sortBooksByYear(String authorName)
    {
    	
    	boolean found = false;
    	//Temporary ArrayList
    	ArrayList<Product> tempProducts = new ArrayList<Product>();
    	for (String key: products.keySet())
    	{
    		Product value = products.get(key);
    		tempProducts.add(value);
    	}
    	Collections.sort(tempProducts, new AuthorYearComparator());
    	//for loop to search in products
    	for (Product books: tempProducts)
    	{
    		//if the product category is BOOKS, then print the books' information
    		if (books.getCategory().equals(Product.Category.BOOKS))
    		{
    			if (books.getAuthor().equalsIgnoreCase(authorName))
    			{
    				books.print();
    				found = true;
    			}
    		}
    	}
    	//if not found, return false
    	if (!found)
    	{
    		throw new InvalidAuthorNameException("No Author Found: " + authorName);
    	}
    }
    /*
     * Adding the items in the Cart
     */
    public void addToCart(String productId, String customerId, String productOptions)
    {
    	// Check if the options are valid for the product
    	for (String key: products.keySet())
    	{
    		Product value = products.get(key);
    		if (value.getId().equals(productId))
    		{
    			boolean genProductValid = value.validOptions(productOptions);
    			if (!genProductValid)
    			{
    				throw new InvalidProductOptionsException("Product Id: " + productId + " Invalid Options: " + productOptions);
    			}
    		}
    	}
    	// Check if the options are valid for the shoes and books category
    	for (String key: products.keySet())
    	{
    		Product value = products.get(key);
    		if (value.getId().equals(productId))
    		{
    			if (value.getCategory().equals(Product.Category.BOOKS))
    			{
    				boolean bookProductValid = value.validOptions(productOptions);
    				if (!bookProductValid)
        	    	{
        	    		throw new InvalidProductOptionsException("Product Id: " + productId + " Invalid Options: " + productOptions);
        	    	}
    			}
    			if (value.getCategory().equals(Product.Category.SHOES))
    			{
    				boolean shoesProductValid = value.validOptions(productOptions);
    				if (shoesProductValid == false)
        	    	{
        	    		throw new InvalidProductOptionsException("Product Id: " + productId + " Invalid Options: " + productOptions);
        	    	}
    			}
    		}
    	}
    	// Check for products stock count
    	for (String key: products.keySet())
    	{
    		Product value = products.get(key);
    		if (value.getId().equals(productId))
    		{
    			int count = value.getStockCount(productOptions);
    			if (count == 0)
    			{
    				throw new ProductOutOfStockException("Product Id: " + productId + " Out of Stock");
    			}
    		}
    	}
    	// Check for books stock count
    	for (String key: products.keySet())
    	{
    		Product value = products.get(key);
    		if (value.getId().equals(productId))
    		{
    			if (value.getCategory().equals(Product.Category.BOOKS))
    			{
    				int count = value.getStockCount(productOptions);
    				if (count == 0)
        	    	{
    					throw new ProductOutOfStockException("Product Id: " + productId + " Out of Stock");
        	    	}
    			}
    		}
    	}
    	//check for shoes stock count
    	for (String key: products.keySet())
    	{
    		Product value = products.get(key);
    		if (value.getId().equals(productId))
    		{
    			if (value.getCategory().equals(Product.Category.SHOES))
    			{
    				//count will check for the stock count of the shoe size
    				int count = value.getStockCount(productOptions);
    				//colourCount will check for the stock count of the stock of each shoe colour
    				int colourCount = value.getColourStockCount(productOptions);
    				if (count == 0 || colourCount == 0)
        	    	{
    					throw new ProductOutOfStockException("Product Id: " + productId + " Out of Stock");
        	    	}
    			}
    		}
    	}
    	//search through products to find a match with productId
    	boolean validProduct = false;
    	for (String key: products.keySet())
    	{
    		Product value = products.get(key);
    		if (value.getId().equalsIgnoreCase(productId))
    		{
    			validProduct = true;
    		}
    	}
    	//search through customers to look for a match with customerId
    	boolean validCustomer = false;
    	for (Customer c: customers)
    	{
    		if (c.getId().equals(customerId))
    		{
    			validCustomer = true;
    		}
    	}
    	//adding item in CartItem
    	for (String key: products.keySet())
    	{
    		Product value = products.get(key);
    		if (value.getId().equalsIgnoreCase(productId))
    		{
    			for (Customer c: customers)
    			{
    				if (c.getId().equals(customerId))
    				{
    					//adding the item in CartItem, value is the productId
    					c.addItem(new CartItem(value, productOptions));
    				}
    			}
    		}
    	}
    	//if invalid, it will throw exceptions
    	if (!validProduct)
    	{
    		throw new UnknownProductException("Product " + productId + " Not Found");
    	}
    	else if (!validCustomer)
    	{
    		throw new UnknownCustomerException("Customer " + customerId + " Not Found");
    	}
    }
    /*
     * Removes a product from the customer's cart
     */
    public void removeProduct(String productId, String customerId)
    {
    	boolean ok = false;
    	//check if productId input is valid
    	boolean validProduct = false;
    	for (String key: products.keySet())
    	{
    		Product value = products.get(key);
    		if (value.getId().equalsIgnoreCase(productId))
    		{
    			validProduct = true;
    		}
    	}
    	//check if customerId input is valid
    	boolean validCustomer = false;
    	for (Customer validCust: customers)
    	{
    		if (validCust.getId().equalsIgnoreCase(customerId))
    		{
    			validCustomer = true;
    		}
    	}
    	//removing the item
    	if (validProduct && validCustomer)
    	{
    		//find the Customer object that belongs to customerId
    		for (Customer c: customers)
			{
				if (c.getId().equals(customerId))
				{
					c.removeItem(productId, customerId);
					ok = true;
				}
			}
    	}
    	//if invalid, it will throw exceptions
    	if (!validProduct)
    	{
    		throw new UnknownProductException("Product " + productId + " Not Found");
    	}
    	else if (!validCustomer)
    	{
    		throw new UnknownCustomerException("Customer " + customerId + " Not Found");
    	}
    	else if (!ok)
    	{
    		throw new InvalidProductOptionsException("Product " + productId + " Not Found in Cart.");
    	}
    }
    /*
     * Prints the products in the customer's cart
     */
    public void printCart(String customerId)
    {
    	boolean ok = false;
    	for (Customer c: customers)
    	{
    		//search through customers to look for a match with customerId
    		if (c.getId().equals(customerId))
    		{
    			c.printItems();
    			ok = true;
    		}
    	}
    	// if invalid, it will return exception
    	if (!ok)
    	{
    		throw new UnknownCustomerException("Customer " + customerId + " Not Found");
    	}
    }
    /*
     * Orders all the products in the cart
     */
    public String orderCartItems(String customerId)
    {
    	String info = "";
    	boolean ok = false;
    	for (Customer c: customers)
    	{
    		//search through customers to look for a match with customerId
    		if (c.getId().equals(customerId))
    		{
    			//if found then boolean is set to true
    			info = c.orderItems(customerId);
    			ok = true;
    		}
    	}
    	// if not successful, it will return exception message
    	if (!ok)
    	{
    		throw new UnknownCustomerException("Customer " + customerId + " Not Found");
    	}
    	else
    	{
    		return info;
    	}
    }
    /*
     * Prints out a formatted list of each product name, id,
     * and number of times it was ordered. The list will be printed 
     * in order of products ordered from most to least.
     */
    public void orderCount()
    {
    	//tempMap is used to convert the products and integer to String and integer to print out information
    	Map<String, Integer> tempMap = new HashMap<String, Integer>();
    	for (Product key: orderCount.keySet())
    	{
    		Integer value = orderCount.get(key);
    		//the string will be formatted when it gets printed
    		String productInfo = String.format("\nName: %-20s Id: %-5s", key.getName(), key.getId());
    		tempMap.put(productInfo, value);
    	}
    	//the tempMap will call the sortOrders method to sort the orders and store the information into a new map
    	Map<String, Integer> sorted = sortOrders(tempMap);
    	//the sorted map will be printed
    	printMapOrders(sorted);
    }
    /*
     * This method will compare and sort the numbers of orders
     */
    public static Map<String, Integer> sortOrders(Map<String, Integer> map)
    {
    	//convert map to list of map
    	List<Map.Entry<String, Integer>> listOfOrders = new LinkedList<Map.Entry<String, Integer>>(map.entrySet());
    	//sort the list with collections
    	Collections.sort(listOfOrders, new Comparator<Map.Entry<String, Integer>>()
    	{
    		public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2 ) 
    		{
    			return (o2.getValue()).compareTo(o1.getValue());
    		}
    	});
    	//loop the sorted list
    	Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
    	for (Map.Entry<String, Integer> entry: listOfOrders)
    	{
    		sortedMap.put(entry.getKey(), entry.getValue());
    	}
    	return sortedMap;
    }
    /*
     * This method will print out the elements in the map
     */
    public static <K,V> void printMapOrders(Map<K,V> map)
    {
    	for (Map.Entry<K, V> entry: map.entrySet())
    	{
    		System.out.print(entry.getKey() + " No. of Orders: " + entry.getValue());
    	}
    }
    /*
     * This will keep track of the ratings of general category
     * @param productId keeps track of the Id so it can add to the corresponding array list
     * @param rating keeps track of the ratings from the user
     */
    public void addRating(String productId, int rating)
    {
    	boolean ok = false;
    	//check to see if productId us valid
    	for (String p: products.keySet())
    	{
    		Product v = products.get(p);
    		if (v.getId().equals(productId))
    		{
    			ok = true;
    		}
    	}
    	//if the product does not exist, throw exception
    	if (!ok)
    	{
    		throw new UnknownProductException("Product " + productId + " Not Found");
    	}
    	//checks if the rating is between 1-5
    	if (rating >= 6 || rating < 0)
    	{
    		throw new InvalidRatingException("Invalid rating. Please Enter a number from 1-5 \n");
    	}
    	
    	//Getting the product object
    	Product productItem = null;
    	for (String key: products.keySet())
    	{
    		Product value = products.get(key);
    		if (productId.equals(value.getId()))
    		{
    			productItem = value;
    		}
    	}
    	//adding to the array list - General
    	if (productItem.getId().equals("700"))
    	{
    		acerLaptop.add(rating);
    	}
    	else if (productItem.getId().equals("701"))
    	{
    		apexDesk.add(rating);
    	}
    	else if (productItem.getId().equals("703"))
    	{
    		jeans.add(rating);
    	}
    	else if (productItem.getId().equals("704"))
    	{
    		polo.add(rating);
    	}
    	else if (productItem.getId().equals("705"))
    	{
    		tightieWhities.add(rating);
    	}
    	else if (productItem.getId().equals("709"))
    	{
    		rockHammer.add(rating);
    	}
    	//adding to the array list - Books
    	else if (productItem.getId().equals("702"))
    	{
    		TM1.add(rating);
    	}
    	else if (productItem.getId().equals("706"))
    	{
    		DU.add(rating);
    	}
    	else if (productItem.getId().equals("707"))
    	{
    		AF.add(rating);
    	}
    	else if (productItem.getId().equals("708"))
    	{
    		TM2.add(rating);
    	}
    	else if (productItem.getId().equals("710"))
    	{
    		TM3.add(rating);
    	}
    	//adding to the array list - Shoes
    	else if (productItem.getId().equals("711"))
    	{
    		AF1.add(rating);
    	}
    	else if (productItem.getId().equals("712"))
    	{
    		UB.add(rating);
    	}
    	else if (productItem.getId().equals("713"))
    	{
    		vans.add(rating);
    	}
    }
    public void getRating(String productId)
    {
    	boolean ok = false;
    	//check to see if productId us valid
    	for (String p: products.keySet())
    	{
    		Product v = products.get(p);
    		if (v.getId().equals(productId))
    		{
    			ok = true;
    		}
    	}
    	//if the product does not exist, throw exception
    	if (!ok)
    	{
    		throw new UnknownProductException("Product " + productId + " Not Found");
    	}
    	double avg = 0.0;
    	//Getting the product object
    	Product productItem = null;
    	for (String key: products.keySet())
    	{
    		Product value = products.get(key);
    		if (productId.equals(value.getId()))
    		{
    			productItem = value;
    		}
    	}
    	//product - General
    	if (productItem.getId().equals("700"))
    	{
    		avg = getAvg(acerLaptop);
    	}
    	else if (productItem.getId().equals("701"))
    	{
    		avg = getAvg(apexDesk);
    	}
    	else if (productItem.getId().equals("703"))
    	{
    		avg = getAvg(jeans);
    	}
    	else if (productItem.getId().equals("704"))
    	{
    		avg = getAvg(polo);
    	}
    	else if (productItem.getId().equals("705"))
    	{
    		avg = getAvg(tightieWhities);
    	}
    	else if (productItem.getId().equals("709"))
    	{
    		avg = getAvg(rockHammer);
    	}
    	//product - Books
    	else if (productItem.getId().equals("702"))
    	{
    		avg = getAvg(TM1);
    	}
    	else if (productItem.getId().equals("706"))
    	{
    		avg = getAvg(DU);
    	}
    	else if (productItem.getId().equals("707"))
    	{
    		avg = getAvg(AF);
    	}
    	else if (productItem.getId().equals("708"))
    	{
    		avg = getAvg(TM2);
    	}
    	else if (productItem.getId().equals("710"))
    	{
    		avg = getAvg(TM3);
    	}
    	//product - Shoes
    	else if (productItem.getId().equals("711"))
    	{
    		avg = getAvg(AF1);
    	}
    	else if (productItem.getId().equals("712"))
    	{
    		avg = getAvg(UB);
    	}
    	else if (productItem.getId().equals("713"))
    	{
    		avg = getAvg(vans);
    	}
    	//getting the productId and name from the productItem and store it in a String
    	String productInfo = String.format("\nId: %-5s Category: %-16s Name: %-20s Rating: %7.1f", productItem.getId(), productItem.getCategory(), productItem.getName(), avg);
    	System.out.println(productInfo);
    }
    /*
     * Get all ratings from general products
     */
    public void getGenRating(int rating)
    {
    	//temporary reference to the product general array lists
    	ArrayList<Integer> temp1 = rockHammer;
    	ArrayList<Integer> temp2 = acerLaptop;
    	ArrayList<Integer> temp3 = apexDesk;
    	ArrayList<Integer> temp4 = jeans;
    	ArrayList<Integer> temp5 = polo;
    	ArrayList<Integer> temp6 = tightieWhities;
    	
    	//getting all the averages
    	double tempAvg1 = getAvg(temp1);
    	double tempAvg2 = getAvg(temp2);
    	double tempAvg3 = getAvg(temp3);
    	double tempAvg4 = getAvg(temp4);
    	double tempAvg5 = getAvg(temp5);
    	double tempAvg6 = getAvg(temp6);
    	
    	//putting the averages and string together
    	String productInfo1 = String.format("\nId: %-5s Name: %-20s Average Rating: %7.1f", "709", "Rock Hammer", tempAvg1);
    	String productInfo2 = String.format("\nId: %-5s Name: %-20s Average Rating: %7.1f", "700", "Acer Laptop", tempAvg2);
    	String productInfo3 = String.format("\nId: %-5s Name: %-20s Average Rating: %7.1f", "701", "Apex Desk", tempAvg3);
    	String productInfo4 = String.format("\nId: %-5s Name: %-20s Average Rating: %7.1f", "703", "DadBod Jeans", tempAvg4);
    	String productInfo5 = String.format("\nId: %-5s Name: %-20s Average Rating: %7.1f", "704", "Polo High Socks", tempAvg5);
    	String productInfo6 = String.format("\nId: %-5s Name: %-20s Average Rating: %7.1f", "705", "Tightie Whities", tempAvg6);
    	
    	//printing the products
    	if (rating <= tempAvg1)
    	{
    		System.out.print(productInfo1);
    	}
    	if (rating <= tempAvg2)
    	{
    		System.out.print(productInfo2);
    	}
    	if (rating <= tempAvg3)
    	{
    		System.out.print(productInfo3);
    	}
    	if (rating <= tempAvg4)
    	{
    		System.out.print(productInfo4);
    	}
    	if (rating <= tempAvg5)
    	{
    		System.out.print(productInfo5);
    	}
    	if (rating <= tempAvg6)
    	{
    		System.out.print(productInfo6);
    	}
    }
    /*
     * Get all ratings from books products
     */
    public void getBooksRating(int rating)
    {
    	//temporary reference to the product books array lists
    	ArrayList<Integer> temp1 = TM1;
    	ArrayList<Integer> temp2 = TM2;
    	ArrayList<Integer> temp3 = TM3;
    	ArrayList<Integer> temp4 = AF;
    	ArrayList<Integer> temp5 = DU;
    	
    	//getting all the averages
    	double tempAvg1 = getAvg(temp1);
    	double tempAvg2 = getAvg(temp2);
    	double tempAvg3 = getAvg(temp3);
    	double tempAvg4 = getAvg(temp4);
    	double tempAvg5 = getAvg(temp5);

    	//putting the averages and string together
    	String productInfo1 = String.format("\nId: %-5s Name: %-35s Average Rating: %7.1f", "702", "Ahm Gonna Make You Learn", tempAvg1);
    	String productInfo2 = String.format("\nId: %-5s Name: %-35s Average Rating: %7.1f", "708", "How to Teach Programming Author", tempAvg2);
    	String productInfo3 = String.format("\nId: %-5s Name: %-35s Average Rating: %7.1f", "710", "Ahm Gonna Make You Learn More", tempAvg3);
    	String productInfo4 = String.format("\nId: %-5s Name: %-35s Average Rating: %7.1f", "707", "How to Escape from Prison", tempAvg4);
    	String productInfo5 = String.format("\nId: %-5s Name: %-35s Average Rating: %7.1f", "706", "How to Fool Your Prof", tempAvg5);
    	
    	//printing the products
    	if (rating <= tempAvg1)
    	{
    		System.out.print(productInfo1);
    	}
    	if (rating <= tempAvg2)
    	{
    		System.out.print(productInfo2);
    	}
    	if (rating <= tempAvg3)
    	{
    		System.out.print(productInfo3);
    	}
    	if (rating <= tempAvg4)
    	{
    		System.out.print(productInfo4);
    	}
    	if (rating <= tempAvg5)
    	{
    		System.out.print(productInfo5);
    	}
    }
    /*
     * Get all ratings from shoes products
     */
    public void getShoesRating(int rating)
    {
    	//temporary reference to the product shoes array lists
    	ArrayList<Integer> temp1 = AF1;
    	ArrayList<Integer> temp2 = UB;
    	ArrayList<Integer> temp3 = vans;
    	
    	//getting all the averages
    	double tempAvg1 = getAvg(temp1);
    	double tempAvg2 = getAvg(temp2);
    	double tempAvg3 = getAvg(temp3);
    	
    	//putting the averages and string together
    	String productInfo1 = String.format("\nId: %-5s Name: %-25s Average Rating: %7.1f", "711", "Air Force 1", tempAvg1);
    	String productInfo2 = String.format("\nId: %-5s Name: %-25s Average Rating: %7.1f", "712", "Ultraboosts 4.0 DNA", tempAvg2);
    	String productInfo3 = String.format("\nId: %-5s Name: %-25s Average Rating: %7.1f", "713", "Vans Old Skool", tempAvg3);
    	
    	//printing the products
    	if (rating <= tempAvg1)
    	{
    		System.out.print(productInfo1);
    	}
    	if (rating <= tempAvg2)
    	{
    		System.out.print(productInfo2);
    	}
    	if (rating <= tempAvg3)
    	{
    		System.out.print(productInfo3);
    	}
    }
    /*
     * Get average rating
     * @param array list to get the values from
     * @return average
     */
    private static double getAvg(ArrayList<Integer> list) 
	{
		double avg = list.stream().mapToDouble(Integer::doubleValue).average().orElse(0);
		return avg;
	}   
}
/*
 * Exception class for unknown customer
 */
@SuppressWarnings("serial")
class UnknownCustomerException extends RuntimeException
{
	public UnknownCustomerException() {}
	public UnknownCustomerException(String message)
	{
		super(message);
	}
}
/*
 * Exception class for unknown product
 */
@SuppressWarnings("serial")
class UnknownProductException extends RuntimeException
{
	public UnknownProductException() {}
	public UnknownProductException(String message)
	{
		super(message);
	}
}

/*
 * Exception class for invalid product options
 */
@SuppressWarnings("serial")
class InvalidProductOptionsException extends RuntimeException
{
	public InvalidProductOptionsException() {}
	public InvalidProductOptionsException(String message)
	{
		super(message);
	}
}
/*
 * Exception class for product out of stock
 */
@SuppressWarnings("serial")
class ProductOutOfStockException extends RuntimeException
{
	public ProductOutOfStockException() {}
	public ProductOutOfStockException(String message)
	{
		super(message);
	}
}
/*
 * Exception class for invalid customer name
 */
@SuppressWarnings("serial")
class InvalidCustomerNameException extends RuntimeException
{
	public InvalidCustomerNameException() {}
	public InvalidCustomerNameException(String message)
	{
		super(message);
	}
}
/*
 * Exception class for invalid customer address
 */
@SuppressWarnings("serial")
class InvalidCustomerAddressException extends RuntimeException
{
	public InvalidCustomerAddressException() {}
	public InvalidCustomerAddressException(String message)
	{
		super(message);
	}
}
/*
 * Exception class for invalid name and address
 */
@SuppressWarnings("serial")
class InvalidNameAndAddressException extends RuntimeException
{
	public InvalidNameAndAddressException() {}
	public InvalidNameAndAddressException(String message)
	{
		super(message);
	}
}
/*
 * Exception class for invalid order number
 */
@SuppressWarnings("serial")
class InvalidOrderNumber extends RuntimeException
{
	public InvalidOrderNumber() {}
	public InvalidOrderNumber(String message)
	{
		super(message);
	}
}
/*
 * Exception class for invalid author name
 */
@SuppressWarnings("serial")
class InvalidAuthorNameException extends RuntimeException
{
	public InvalidAuthorNameException() {}
	public InvalidAuthorNameException(String message)
	{
		super(message);
	}
}
/*
 * Exception class for invalid rating
 */
@SuppressWarnings("serial")
class InvalidRatingException extends RuntimeException
{
	public InvalidRatingException() {}
	public InvalidRatingException(String message)
	{
		super(message);
	}
}
