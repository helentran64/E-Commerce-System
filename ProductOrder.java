public class ProductOrder
{
	private String 		orderNumber;
	private Product 	product;
	private String    productOptions;
	private Customer 	customer;
	
	public ProductOrder(String orderNumber, Product product, Customer customer, String productOptions)
	{
		this.orderNumber = orderNumber;
		this.product = product;
		this.customer = customer;
		this.productOptions = productOptions;
	}
	/*
	 * will return the productOptions for later use
	 */
	public String getProductOptions()
	{
		return productOptions;
	}
	public String getOrderNumber()
	{
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber)
	{
		this.orderNumber = orderNumber;
	}
	public Product getProduct()
	{
		return product;
	}
	public void setProduct(Product product)
	{
		this.product = product;
	}
	public Customer getCustomer()
	{
		return customer;
	}
	public void setCustomer(Customer customer)
	{
		this.customer = customer;
	}
	
	public void print()
	{
		System.out.printf("\nOrder # %3s Customer Id: %3s Product Id: %3s Product Name: %12s Options: %8s", orderNumber, customer.getId(), product.getId(), product.getName(), 
				               productOptions);
	}
	/*
	 * Two ProductOrder objects are equal if they have the same order number string.
	 */
	public boolean equals(Object other)
	{
		// Compare two ProductOrder objects based on their orderNumber strings
		return false;
	}
}
