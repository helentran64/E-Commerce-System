import java.util.InputMismatchException;
import java.util.Scanner;

public class ECommerceUserInterface
{
	public static void main(String[] args)
	{
		// Create the system
		ECommerceSystem amazon = new ECommerceSystem();

		Scanner scanner = new Scanner(System.in);
		System.out.print(">");
		
		// Process keyboard actions
		while (scanner.hasNextLine())
		{
			String action = scanner.nextLine();
			
			if (action == null || action.equals("")) 
			{
				//System.out.print("\n>");
				continue;
			}
			else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
				return;

			else if (action.equalsIgnoreCase("PRODS"))	// List all products for sale
			{
				amazon.printAllProducts(); 
			}
			else if (action.equalsIgnoreCase("BOOKS"))	// List all books for sale
			{
				amazon.printAllBooks(); 
			}
			else if (action.equalsIgnoreCase("CUSTS")) 	// List all registered customers
			{
				amazon.printCustomers();	
			}
			else if (action.equalsIgnoreCase("ORDERS")) // List all current product orders
			{
				amazon.printAllOrders();	
			}
			else if (action.equalsIgnoreCase("SHIPPED"))	// List all orders that have been shipped
			{
				amazon.printAllShippedOrders();	
			}
			else if (action.equalsIgnoreCase("NEWCUST"))	// Create a new registered customer
			{
				String name = "";
				String address = "";
				
				//get name from scanner
				System.out.print("Name: ");
				if (scanner.hasNextLine())
					name = scanner.nextLine();
				//get address from scanner
				System.out.print("\nAddress: ");
				if (scanner.hasNextLine())
					address = scanner.nextLine();
				
				//the user will enter the name and address of the new customer
				boolean done = false;
				while (!done)
				{
					try
					{
						amazon.createCustomer(name, address);
						System.out.println("Customer has been added.");
						done = true;
					}
					//catch exception if customer address is empty
					catch (InvalidCustomerAddressException e1)
					{
						System.out.println(e1.getMessage());
						System.out.print("\n" + "Please Re-Enter Address Id or Enter e to exit: ");
						if (scanner.hasNextLine())
						{
							address = scanner.nextLine();
						}
						if (address.equalsIgnoreCase("E"))
						{
							done = true;
						}
					}
					//catch exception if customer name is empty
					catch (InvalidCustomerNameException e2)
					{
						System.out.println(e2.getMessage());
						System.out.print("\n" + "Please Re-Enter Name or Enter e to exit: ");
						if (scanner.hasNextLine())
						{
							name = scanner.nextLine();
						}
						if (name.equalsIgnoreCase("E"))
						{
							done = true;
						}
					}
					//catch exception if customer name and address is empty
					catch (InvalidNameAndAddressException e3)
					{
						System.out.println(e3.getMessage());
						System.out.print("\n" + "Please Re-Enter Name or Enter e to exit: ");
						if (scanner.hasNextLine())
						{
							name = scanner.nextLine();
						}
						if (name.equalsIgnoreCase("E"))
						{
							done = true;
						}
						else
						{
							System.out.print("\n" + "Please Re-Enter Address Id: ");
							if (scanner.hasNextLine())
							{
								address = scanner.nextLine();
							}
						}
					}
				}
			}
			else if (action.equalsIgnoreCase("SHIP"))	// ship an order to a customer
			{
					String orderNumber = "";
        
					System.out.print("Order Number: ");
					// Get order number from scanner
					if (scanner.hasNextLine())
					{
						//the order number will be the next input
						orderNumber = scanner.nextLine();
					}
					ProductOrder order; 
					boolean done = false;
					while (!done)
					{
						try
						{
							amazon.shipOrder(orderNumber);
							done = true;
						}
						catch (InvalidOrderNumber e)
						{
							System.out.println(e.getMessage() + "\n");
							System.out.print("Please Re-Enter Order Number or Enter e to exit: ");
							if (scanner.hasNextLine())
							{
								orderNumber = scanner.nextLine();
							}
							if (orderNumber.equalsIgnoreCase("E"))
							{
								done = true;
							}
						}
					}
			}
			else if (action.equalsIgnoreCase("CUSTORDERS")) // List all the current orders and shipped orders for this customer id
			{
				String customerId = "";

				System.out.print("Customer Id: ");
				// Get customer Id from scanner
				if (scanner.hasNextLine())
				{
					customerId = scanner.nextLine();
				}
				
				// Print all current orders and all shipped orders for this customer
				boolean done = false;
				while (!done)
				{
					try
					{
						amazon.printOrderHistory(customerId);
						done = true;
					}
					//if the customer is not found, it will print an exception message
					catch (UnknownCustomerException e)
					{
						//will ask user to re-enter the customer id until it is valid
						System.out.print(e.getMessage() + "\n\n");
						System.out.print("Please Re-Enter Customer Id or Enter e to exit: ");
						if (scanner.hasNextLine())
						{
							customerId = scanner.nextLine();
						}
						if (customerId.equalsIgnoreCase("E"))
						{
							done = true;
						}
					}
				}
			}
			else if (action.equalsIgnoreCase("ORDER")) // order a product for a certain customer
			{
				String productId = "";
				String customerId = "";
				String options = ""; //added to fit into parameter

				System.out.print("Product Id: ");
			    // Get product Id from scanner
				if (scanner.hasNextLine())
				{
					//will store the next string to productId
					productId = scanner.nextLine();
				}
				
				System.out.print("\nCustomer Id: ");
			    // Get customer Id from scanner
				if (scanner.hasNextLine())
				{
					//will store the next string to customerId
					customerId = scanner.nextLine();
				}
				// Order the product. Check for valid orderNumber string return and for error message set in ECommerceSystem
				boolean done = false;
				while (!done)
				{
					// Print Order Number string returned from method in ECommerceSystem
					try
					{
						String orderNumber = amazon.orderProduct(productId, customerId, options);
						//if the orderNumber isn't null, it will generate the orderNumber
						System.out.println("order #" + orderNumber);
						done = true;
					}
					//if null, it will get the exceptions
					catch (UnknownCustomerException e)
					{
						System.out.println(e.getMessage());
					}
					catch (UnknownProductException e2)
					{
						System.out.println(e2.getMessage());
					}
					catch (InvalidProductOptionsException e3)
					{
						System.out.println(e3.getMessage());
					}
					catch (ProductOutOfStockException e4)
					{
						System.out.println(e4.getMessage());
						done = true;
					}
					//will prompt user to re-enter valid information
					if (!done)
					{
						System.out.print("\n" + "Please Re-Enter Product Id or Enter e to exit: ");
						if (scanner.hasNextLine())
						{
							productId = scanner.nextLine();
						}
						if (productId.equalsIgnoreCase("E"))
						{
							done = true;
						}
						else
						{
							System.out.print("\n" + "Please Re-Enter Customer Id: ");
							if (scanner.hasNextLine())
							{
								customerId = scanner.nextLine();
							}
						}
					}
				}
			}
			else if (action.equalsIgnoreCase("ORDERBOOK")) // order a book for a customer, provide a format (Paperback, Hardcover or EBook)
			{
				String productId = "";
				String customerId = "";
				String options = "";

				System.out.print("Product Id: ");
				// get product id
				if (scanner.hasNextLine())
				{
					productId = scanner.nextLine();
				}

				System.out.print("\nCustomer Id: ");
				// get customer id
				if (scanner.hasNextLine())
				{
					customerId = scanner.nextLine();
				}
				System.out.print("\nFormat [Paperback Hardcover EBook]: ");
				// get book format and store in options string
				if (scanner.hasNextLine())
				{
					options = scanner.nextLine();
				}
				// Order product. Check for error message set in ECommerceSystem
				boolean done = false;
				while (!done)
				{
					try
					{
						String orderBook = amazon.orderProduct(productId, customerId, options);
						System.out.println("order #" + orderBook);
						done = true;
					}
					//if null, it will get the exceptions
					catch (UnknownCustomerException e)
					{
						System.out.println(e.getMessage());
					}
					catch (UnknownProductException e2)
					{
						System.out.println(e2.getMessage());
					}
					catch (InvalidProductOptionsException e3)
					{
						System.out.println(e3.getMessage());
					}
					catch (ProductOutOfStockException e4)
					{
						System.out.println(e4.getMessage());
						done = true;
					}
					//will prompt user to re-enter valid information
					if (!done)
					{
						System.out.print("\n" + "Please Re-Enter Product Id or Enter e to exit: ");
						if (scanner.hasNextLine())
						{
							productId = scanner.nextLine();
						}
						if (productId.equalsIgnoreCase("E"))
						{
							done = true;
						}
						else
						{
							System.out.print("\n" + "Please Re-Enter Customer Id: ");
							if (scanner.hasNextLine())
							{
								customerId = scanner.nextLine();
							}
							System.out.print("\n" + "Please Re-Enter Format [Paperback Hardcover EBook]: ");
							if (scanner.hasNextLine())
							{
								options = scanner.nextLine();
							}
						}
					}
				}
			}
			else if (action.equalsIgnoreCase("ORDERSHOES")) // order shoes for a customer, provide size and color 
			{
				String productId = "";
				String customerId = "";
				String options = "";
				
				System.out.print("Product Id: ");
				// get product id
				if (scanner.hasNextLine())
				{
					productId = scanner.nextLine();
				}
				System.out.print("\nCustomer Id: ");
				// get customer id
				if (scanner.hasNextLine())
				{
					customerId = scanner.nextLine();
				}
				System.out.print("\nSize: \"6\" \"7\" \"8\" \"9\" \"10\": ");
				// get shoe size and store in options	
				if (scanner.hasNextLine())
				{
					options = scanner.nextLine();
				}
				System.out.print("\nColor: \"Black\" \"Brown\": ");
				// get shoe color and append to options
				if (scanner.hasNextLine())
				{
					options = options + " " + scanner.nextLine();
				}
				//order shoes. check if information is valid. Otherwise, prompt with exception
				boolean done = false;
				while (!done)
				{
					try
					{
						String orderShoes = amazon.orderProduct(productId, customerId, options);
						System.out.println("order #" + orderShoes);
						done = true;
					}
					//if null, it will get the exceptions
					catch (UnknownCustomerException e)
					{
						System.out.println(e.getMessage());
					}
					catch (UnknownProductException e2)
					{
						System.out.println(e2.getMessage());
					}
					catch (InvalidProductOptionsException e3)
					{
						System.out.println(e3.getMessage());
					}
					catch (ProductOutOfStockException e4)
					{
						System.out.println(e4.getMessage());
						done = true;
					}
					//will prompt user to re-enter valid information
					if (!done)
					{
						System.out.print("\n" + "Please Re-Enter Product Id or Enter e to exit: ");
						if (scanner.hasNextLine())
						{
							productId = scanner.nextLine();
						}
						if (productId.equalsIgnoreCase("E"))
						{
							done = true;
						}
						else
						{
							System.out.print("\n" + "Please Re-Enter Customer Id: ");
							if (scanner.hasNextLine())
							{
								customerId = scanner.nextLine();
							}
							System.out.print("\n" + "Please Re-Enter Size \"6\" \"7\" \"8\" \"9\" \"10\": "); 
							if (scanner.hasNextLine())
							{
								options = scanner.nextLine();
							}
							System.out.print("\n" + "Please Re-Enter Color: \"Black\" \"Brown\": ");
							if (scanner.hasNextLine())
							{
								options = options + " " + scanner.nextLine();
							}
						}
					}
				}
			}
			else if (action.equalsIgnoreCase("CANCEL")) // Cancel an existing order
			{
				String orderNumber = "";

				System.out.print("Order Number: ");
				// get order number from scanner
				if (scanner.hasNextLine())
				{
					orderNumber = scanner.nextLine();
				}
				// cancel order. Check for error
				boolean done = false;
				while (!done)
				{
					try
					{
						amazon.cancelOrder(orderNumber);
						System.out.println("order #" + orderNumber + " cancelled.");
						done = true;
					}
					//if order number is invalid it will prompt user to re-enter until it is valid
					catch (InvalidOrderNumber e)
					{
						System.out.println(e.getMessage());
						System.out.print("\n" + "Please Re-Enter Order Number or Enter e to exit: ");
						if (scanner.hasNextLine())
						{
							orderNumber = scanner.nextLine();
						}
						if (orderNumber.equalsIgnoreCase("E"))
						{
							done = true;
						}
					}
				}
			}
			else if (action.equalsIgnoreCase("PRINTBYPRICE")) // sort products by price
			{
				amazon.sortByPrice();
			}
			else if (action.equalsIgnoreCase("PRINTBYNAME")) // sort products by name (alphabetic)
			{
				amazon.sortByName();
			}
			else if (action.equalsIgnoreCase("SORTCUSTS")) // sort products by name (alphabetic)
			{
				amazon.sortCustomersByName();
			}
			else if (action.equalsIgnoreCase("BOOKSBYAUTHOR")) // sort books by author (alphabetic)
			{
				String authorName = "";
				System.out.print("Author's Name: ");
				if (scanner.hasNextLine())
				{
					authorName = scanner.nextLine();
				}
				//check if user input valid information
				boolean done = false;
				while (!done)
				{
					try
					{
						amazon.sortBooksByYear(authorName);
						done = true;
					}
					//if invalid, it will prompt user to re-enter until information is valid
					catch (InvalidAuthorNameException e)
					{
						System.out.println(e.getMessage());
						System.out.print("\n" + "Please Re-Enter Author's Name or Enter e to exit: ");
						if (scanner.hasNextLine())
						{
							authorName = scanner.nextLine();
						}
						if (authorName.equalsIgnoreCase("E"))
						{
							done = true;
						}
					}
				}
			}
			else if (action.equalsIgnoreCase("ADDTOCART")) //Adding items to the cart
			{
				//takes in the productId, customerId, and productOptions from input
				String productId = "";
				String customerId = "";
				String productOptions = "";
				
				System.out.print("Product Id: ");
				if (scanner.hasNextLine())
				{
					productId = scanner.nextLine();
				}
				System.out.print("Customer Id: ");
				if (scanner.hasNextLine())
				{
					customerId = scanner.nextLine();
				}
				//asks the user for the category of the products so order can be valid
				System.out.print("\nPick a category to shop from (general/books/shoes): ");
				if (scanner.hasNextLine())
				{
					String category = scanner.nextLine();
					if (category.equalsIgnoreCase("general"))
					{
						productOptions = "";
					}
					else if (category.equalsIgnoreCase("books"))
					{
						System.out.print("Format [Paperback Hardcover EBook]: ");
						if (scanner.hasNextLine())
						{
							productOptions = scanner.nextLine();
						}
					}
					else if (category.equalsIgnoreCase("shoes"))
					{
						System.out.print("\nSize: \"6\" \"7\" \"8\" \"9\" \"10\": ");
						// get shoe size and store in options	
						if (scanner.hasNextLine())
						{
							productOptions = scanner.nextLine();
						}
						System.out.print("\nColor: \"Black\" \"Brown\": ");
						// get shoe color and append to options
						if (scanner.hasNextLine())
						{
							productOptions = productOptions + " " + scanner.nextLine();
						}
					}
				}
				//if the action is successful, it will tell the user that action is complete
				//else the action is not successful, it will tell the user that action is incomplete
				boolean done = false;
				String info = "";
				while (!done)
				{
					try
					{
						amazon.addToCart(productId, customerId, productOptions);
						System.out.println("Item was added to the cart.");
						done = true;
					}
					//will prompt user to re-enter until information is valid
					catch (UnknownProductException e1)
					{
						System.out.print(e1.getMessage());
					}
					catch (UnknownCustomerException e2)
					{
						System.out.print(e2.getMessage());
					}
					catch (ProductOutOfStockException e3)
					{
						System.out.print(e3.getMessage());
					}
					catch (InvalidProductOptionsException e4)
					{
						System.out.print(e4.getMessage());
					}
					if (!done)
					{
						System.out.print("\n" + "Please Re-Enter Product Id or Enter e to exit: ");
						if (scanner.hasNextLine())
						{
							productId = scanner.nextLine();
						}
						if (productId.equalsIgnoreCase("E"))
						{
							done = true;
						}
						else
						{
							System.out.print("\n" + "Please Re-Enter Customer Id: ");
							if (scanner.hasNextLine())
							{
								customerId = scanner.nextLine();
							}
							System.out.print("\nPick a category to shop from (general/books/shoes): ");
							if (scanner.hasNextLine())
							{
								String category = scanner.nextLine();
								if (category.equalsIgnoreCase("general"))
								{
									productOptions = "";
								}
								else if (category.equalsIgnoreCase("books"))
								{
									System.out.print("Format [Paperback Hardcover EBook]: ");
									if (scanner.hasNextLine())
									{
										productOptions = scanner.nextLine();
									}
								}
								else if (category.equalsIgnoreCase("shoes"))
								{
									System.out.print("\nSize: \"6\" \"7\" \"8\" \"9\" \"10\": ");
									// get shoe size and store in options	
									if (scanner.hasNextLine())
									{
										productOptions = scanner.nextLine();
									}
									System.out.print("\nColor: \"Black\" \"Brown\": ");
									// get shoe color and append to options
									if (scanner.hasNextLine())
									{
										productOptions = productOptions + " " + scanner.nextLine();
									}
								}
							}
						}
					}
				}
			}
			else if (action.equalsIgnoreCase("REMCARTITEM")) //Removing items in the cart
			{
				//takes in the productId, customerId from input
				String productId = "";
				String customerId = "";
				
				System.out.print("Product Id: ");
				if (scanner.hasNextLine())
				{
					productId = scanner.nextLine();
				}
				System.out.print("Customer Id: ");
				if (scanner.hasNextLine())
				{
					customerId = scanner.nextLine();
				}
				//Removes a product from the customer’s cart
				//if successful or unsuccessful, it will notify the user
				boolean done = false;
				while (!done)
				{
					try
					{
						amazon.removeProduct(productId, customerId);
						System.out.print("Item was removed.");
						done = true;
					}
					//will prompt user to re-enter until information is valid
					catch (UnknownProductException e1)
					{
						System.out.print(e1.getMessage());
					}
					catch (UnknownCustomerException e2)
					{
						System.out.print(e2.getMessage());
					}
					catch (InvalidProductOptionsException e3)
					{
						System.out.print(e3.getMessage());
					}
					catch (RemEmptyCartException e4)
					{
						System.out.print(e4.getMessage());
						done = true;
					}
					if (!done)
					{
						System.out.print("\n" + "Please Re-Enter Product Id or Enter e to exit: ");
						if (scanner.hasNextLine())
						{
							productId = scanner.nextLine();
						}
						if (productId.equalsIgnoreCase("E"))
						{
							done = true;
						}
						else
						{
							System.out.print("\n" + "Please Re-Enter Customer Id: ");
							if (scanner.hasNextLine())
							{
								customerId = scanner.nextLine();
							}
						}
					}
				}
			}
			else if (action.equalsIgnoreCase("PRINTCART")) //Printing items in the cart
			{	
				//takes in the customerId from input
				String customerId = "";
				
				System.out.print("Customer Id: ");
				if (scanner.hasNextLine())
				{
					customerId = scanner.nextLine();
				}
				//if a cart does not belong to the customerId input, it will notify the user
				boolean done = false;
				while (!done)
				{
					//check if information is valid
					try
					{
						amazon.printCart(customerId);
						done = true;
					}
					//will prompt user to re-enter until information is valid
					catch (UnknownCustomerException e)
					{
						System.out.print(e.getMessage());
						System.out.print("\n\n" + "Please Re-Enter Customer Id: ");
						if (scanner.hasNextLine())
						{
							customerId = scanner.nextLine();
						}
					}
				}
			}
			else if (action.equalsIgnoreCase("ORDERITEMS")) //Ordering all items in the cart
			{
				//takes in the customerId from input
				String customerId = "";
				System.out.print("Customer Id: ");
				if (scanner.hasNextLine())
				{
					customerId = scanner.nextLine();
				}
				//order the products 
				//if successful or unsuccessful, it will notify the user to re-enter information
				boolean done = false;
				String info = "";
				while (!done)
				{
					//check if information is valid
					try
					{
						info = amazon.orderCartItems(customerId);
						done = true;
					}
					//if cart is empty it will return an exception message
					catch (EmptyCartException cart)
					{
						System.out.println(cart.getMessage());
						break;
					}
					//will throw exception until information is valid
					catch (UnknownCustomerException e)
					{
						System.out.println(e.getMessage());
						
						System.out.print("\n" + "Please Re-Enter Customer Id or Enter e to exit: ");
						if (scanner.hasNextLine())
						{
							customerId = scanner.nextLine();
						}
						if (customerId.equalsIgnoreCase("E"))
						{
							done = true;
						}
					}
					catch (InvalidProductOptionsException e2)
					{
						System.out.print("You have ordered from the wrong category, please remove item");
						done = true;
					}
					catch (ProductOutOfStockException e3)
					{
						System.out.print("Out of Stock");
						done = true;
					}
				}
				//calls to the orderProduct() method to make the order
				if (done)
				{
					boolean ok = false;
					String product = "";
					String customer = "";
					String productOp = "";
					Scanner inputInfo = new Scanner(info);
					while (inputInfo.hasNextLine())
					{
						try
						{
							//gets the productId, customerId, and productOptions to call method
							product = inputInfo.next();
							customer = inputInfo.next();
							productOp = inputInfo.nextLine().trim();
							amazon.orderProduct(product, customer, productOp);
							ok = true;
						}
						catch (ProductOutOfStockException e)
						{
							ok = false;
							System.out.println("Out of Stock");
						}
					}
					if (ok)
					{
						System.out.println("Order is complete.");
					}
					inputInfo.close();
				}
			}
			else if (action.equalsIgnoreCase("STATS")) //See how many products have been ordered
			{
				amazon.orderCount();
			}
			//action will help rate each product
			else if (action.equalsIgnoreCase("RATE")) //Rate each product
			{
				//takes in productId and rating 1-5
				String productId = "";
				int rating = 0;
				//asks for the product ID
				System.out.print("Product Id: ");
				if (scanner.hasNextLine())
				{
					productId = scanner.nextLine();
				}
				//asks for the rating
				System.out.print("Rate the Product (1-5): ");
				if (scanner.hasNextLine())
				{
					//checks if the rating is an integer, otherwise exception is implemented
					try
					{
						rating = scanner.nextInt();
					}
					catch (InputMismatchException e2)
					{
						System.out.print("Invalid rating. Please Enter a number from 1-5 \n");
					}
				}
				try
				{
					amazon.addRating(productId, rating);
				}
				catch (UnknownProductException e)
				{
					System.out.print(e.getMessage());
				}
				catch (InvalidRatingException e3)
				{
					System.out.print("Invalid rating. Please Enter a number from 1-5 \n");
				}
			}
			//lets you see each individual rating for each product
			else if (action.equalsIgnoreCase("SEERATING")) //See each product's rating
			{
				boolean ok = false;
				String productId = "";
				//asks for category to see each rating
				System.out.print("Product Id: ");
				if (scanner.hasNextLine())
				{
					productId = scanner.nextLine();
				}
				while (!ok)
				{
					try
					{
						amazon.getRating(productId);
						ok = true;
					}
					catch (UnknownProductException e)
					{
						System.out.println(e.getMessage());
						System.out.print("\n" + "Please Re-Enter Product Id or Enter e to exit: ");
						if (scanner.hasNextLine())
						{
							productId = scanner.nextLine();
						}
						if (productId.equalsIgnoreCase("E"))
						{
							ok = true;
						}
					}
				}
			}
			//seeing all products from the general category with a threshold
			else if (action.equalsIgnoreCase("GENRATING"))
			{
				int rate = 0;
				System.out.print("See Rating Above (Choose Rating Between 1-5): ");
				if (scanner.hasNext())
				{
					rate = scanner.nextInt();
					amazon.getGenRating(rate);
				}
			}
			//seeing all products from the books category with a threshold
			else if (action.equalsIgnoreCase("BOOKSRATING"))
			{
				int rate = 0;
				System.out.print("See Rating Above (Choose Rating Between 1-5): ");
				if (scanner.hasNext())
				{
					rate = scanner.nextInt();
					amazon.getBooksRating(rate);
				}
			}
			//seeing all products from the shoes category with a threshold
			else if (action.equalsIgnoreCase("SHOESRATING"))
			{
				int rate = 0;
				System.out.print("See Rating Above (Choose Rating Between 1-5): ");
				if (scanner.hasNext())
				{
					rate = scanner.nextInt();
					amazon.getShoesRating(rate);
				}
			}
			System.out.print("\n>");
		}
	}
}
