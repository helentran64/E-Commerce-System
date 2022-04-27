import java.util.ArrayList;
public class Cart
{	
	//Cart object contains a list of CartItem objects 
	private ArrayList<CartItem> itemsInCart;
	
	/*
	 * Constructor method
	 * Every customer will have their own cart
	 */
	public Cart()
	{
		itemsInCart = new ArrayList<CartItem>();
	}
	/*
	 * Adding items to the CartItem ArrayList
	 * @param item that will be added to the CartItem ArrayList
	 */
	public void addItem(CartItem item)
	{
		itemsInCart.add(item);
	}
	/* 
	 * Removing the items in the cart
	 * @param productId information to the product ID
	 * @param customerId information to the customer ID
	 */
	public void removeItem(String productId, String customerId)
	{
		boolean ok = false;
		for (int i = 0; i < itemsInCart.size(); i++)
		{
			if (itemsInCart.get(i).getProducts().getId().equals(productId))
			{
				itemsInCart.remove(i);
				ok = true;
				break;
			}
		}
		//if the cart is empty or the user inputs a wrong product, it will prompt an exception
		if (!ok)
		{
			throw new RemEmptyCartException("Product: " + productId + " is not in your cart.");
		}
	}
	/*
	 * Printing the items in the cart
	 */
	public void printItems()
	{
		//if the cart is empty, it will let the user know that it is empty
		if (itemsInCart.size() == 0)
		{
			System.out.print("Cart is empty.");
		}
		else
		{
			//this will loop through the cart and print the product information
			for (int i = 0; i < itemsInCart.size(); i++)
			{
				//if the product is in the books category, it will print the information
				if (itemsInCart.get(i).getProducts().getCategory().equals(Product.Category.BOOKS))
				{
					System.out.print(itemsInCart.get(i).getProducts().printBooksProds() + " " + itemsInCart.get(i).getProductOptions());
				}
				//if the product is in the shoes category, it will print the information
				else if (itemsInCart.get(i).getProducts().getCategory().equals(Product.Category.SHOES))
				{
					System.out.print(itemsInCart.get(i).getProducts().printShoesProds() + " " + itemsInCart.get(i).getProductOptions());
				}
				//if the product is in the general category, it will print the information
				else
				{
					System.out.print(itemsInCart.get(i).getProducts().printProds() + " " + itemsInCart.get(i).getProductOptions());
				}
			}
		}
	}
	/*
	 * Order items
	 * @param customerId to get customer information to order the products
	 */
	public String orderItems(String customerId)
	{
		String info = "";
		if (itemsInCart.size() == 0)
		{
			//System.out.print("Cart is empty.");
			throw new EmptyCartException("Cart is empty.");
		}
		else
		{
			for (int i = 0; i < itemsInCart.size(); i++)
			{
				//calling the orderProduct method to create the orders
				ECommerceSystem order = new ECommerceSystem();
				
				//ordering the product with orderProduct(productId, customerId, productOptions) method from ECommerceSystem
				order.orderProduct(itemsInCart.get(i).getProducts().getId(), customerId, itemsInCart.get(i).getProductOptions());
				info += itemsInCart.get(i).getProducts().getId() + " " + customerId + " " + itemsInCart.get(i).getProductOptions() + "\n";
			}
			//empties the cart once the order is complete
			itemsInCart = new ArrayList<CartItem>();
		}
		return info;
	}
	/*
	 * Returning items in the cart
	 */
	public ArrayList<CartItem> getCart()
	{
		return itemsInCart;
	}
}
/*
 * Exception message when the customer is trying to order from an empty cart
 */
@SuppressWarnings("serial")
class EmptyCartException extends RuntimeException
{
	public EmptyCartException() {}
	public EmptyCartException(String message)
	{
		super(message);
	}
}
/*
 * Exception messages when the cart is empty and the
 * customer tries to remove an item
 */
@SuppressWarnings("serial")
class RemEmptyCartException extends RuntimeException
{
	public RemEmptyCartException() {}
	public RemEmptyCartException(String message)
	{
		super(message);
	}
}
