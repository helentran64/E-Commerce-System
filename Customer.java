import java.util.ArrayList;
public class Customer extends Cart implements Comparable<Customer>
{
	private String id;  
	private String name;
	private String shippingAddress;
	
	//Cart object contains a list of CartItem objects
	private Cart list;
	
	public Customer(String id)
	{
		this.id = id;
		this.name = "";
		this.shippingAddress = "";
		//each customer will have their own cart
		this.list = new Cart();
	}
	
	public Customer(String id, String name, String address)
	{
		this.id = id;
		this.name = name;
		this.shippingAddress = address;
		//each customer will have their own cart
		this.list = new Cart();
	}
	
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getShippingAddress()
	{
		return shippingAddress;
	}
	
	public void setShippingAddress(String shippingAddress)
	{
		this.shippingAddress = shippingAddress;
	}
	
	public void print()
	{
		System.out.printf("\nName: %-20s ID: %3s Address: %-35s", name, id, shippingAddress);
	}
	
	public boolean equals(Object other)
	{
		Customer otherC = (Customer) other;
		return this.id.equals(otherC.id);
	}
	/*
	 * compares the customer name alphabetically
	 * @param other is to be compared
	 * @return if > 0, string is greater than the other string.
	 *         if < 0, string is less than the other string.
	 *         if = 0, the string is equal to the other string.
	 */
	public int compareTo(Customer other)
	{
		int compare = getName().compareTo(other.getName());
		if (compare > 0)
		{
			return 1;
		}
		else if (compare < 0)
		{
			return -1;
		}
		else
		{
			return 0;
		}
	}
}
