import java.util.Comparator;

/*
 * This class will compare the years from each book
 */
public class AuthorYearComparator implements Comparator<Product>
{
	/*
	 * Comparator method:
	 * @param a is the first year publication to compare with
	 * @param b is the second year publication to compare with
	 * @return 1 if a > b
	 *        -1 if a < b
	 *         0 if a == b
	 */
	public int compare(Product a, Product b)
	{
		if (a.getYear() > b.getYear())
		{
			return 1;
		}
		else if (a.getYear() < b.getYear())
		{
			return -1;
		}
		else
		{
			return 0;
		}
	}
}
