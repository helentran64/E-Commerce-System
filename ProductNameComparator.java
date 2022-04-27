import java.util.Comparator;

/*
 * This class will help compare the names of the product and order them alphabetically
 */
public class ProductNameComparator implements Comparator<Product>
{
	/*
	 * Comparator method:
	 * @param a is the first product name to compare with
	 * @param b is the second product name to compare with
	 * @return the result will be returned after comparing the names with each other
	 */
	public int compare(Product a, Product b)
	{
		return a.getName().compareTo(b.getName());
	}
}
