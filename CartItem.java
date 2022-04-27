public class CartItem extends Product
{
	//reference to a Product object
	private Product products;
	//reference to productOptions
	private String productOptions;
	
	/*
	 * Constructor method
	 */
	public CartItem(Product products, String productOptions)
	{
		//inherit all the variables from Product
		this.products = products;
		this.productOptions = productOptions;
	}
	/*
	 * @return the productOptions
	 */
	public String getProductOptions()
	{
		return productOptions;
	}
	/*
	 * Returns the product information
	 */
	public Product getProducts()
	{
		return products;
	}
}
