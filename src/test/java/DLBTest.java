import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import java.util.*;

public class DLBTest
{

	DLB dlb;
	String[] keys = {"she", "sells", "sea", "shells", "by", "the", "sea", "shore"};
	
	@Test
	//Ensures that after items are put into the DLB that it is not empty and that it is the right size.
	public void test1_put()
	{
		dlb  = new DLB();
		
		assertTrue(dlb.isEmpty());
		
		
		for(String key : keys)
		{
			dlb.put(key, key);
		}
		
		assertFalse(dlb.isEmpty());
		assertEquals(dlb.size(), keys.length-1);
	}
	
	private void fill_DLB()
	{
		dlb = new DLB();
		for(String key : keys)
		{
			dlb.put(key, key);
		}
	}
	
	@Test
	//Ensures that contains method works after key is added.
	public void test2_contains()
	{
		fill_DLB();
		for(String key : keys)
		{
			assertTrue(dlb.contains(key));
		}
	}
	
	@Test
	//Ensures that keys can be retrieved after being added to the DLB.
	public void test3_get()
	{
		fill_DLB();
		for(String key : keys)
		{
			assertEquals(dlb.get(key), (key));
		}
	}
	
	@Test
	//Ensures the DLB does not contain any keys that haven't been added.
	public void test4_contains()
	{
		fill_DLB();
		String[] non_keys = {"big", "shel", "there"};
		for(String non_key : non_keys)
		{
			assertFalse(dlb.contains(non_key));
		}
	}
	
	@Test
	//
	public void test5_containsPrefixOf()
	{
		fill_DLB();
		String[] prefixeds = {"shellsort", "sheet", "them", "the"};
		for(String prefixed : prefixeds)
		{
			assertTrue(dlb.containsPrefixOf(prefixed));
		}
	}
	
	@Test
	//
	public void test6_containsPrefixOf()
	{
		fill_DLB();
		String[] non_prefixeds = {"thl", "q", "mambo", "s", ""};
		for(String non_prefixed : non_prefixeds)
		{
			assertFalse(dlb.containsPrefixOf(non_prefixed));
		}
	}
	
	@Test
	//
	public void test7_getValuesForPrefix()
	{
		fill_DLB();
		String[] sh = {"she", "shells", "shore"};
		String[] vals_sh = dlb.getValuesForPrefix("sh");
		for(String str : sh)
		{
			assertTrue(Arrays.asList(vals_sh).contains(str));
		}
	}
	
	@Test
	public void test8_getValuesForPrefix()
	{
		fill_DLB();
		String[] th = {"the"};
		String[] vals_th = dlb.getValuesForPrefix("t");
		for(String str : th)
		{
			assertTrue(Arrays.asList(vals_th).contains(str));
		}
	}
	
	@Test
	public void test9_getValuesForPrefix()
	{
		fill_DLB();
		String[] vals = dlb.getValuesForPrefix("abc");
		for(String str : vals)
		{
			System.out.println(str);
		}
		assertEquals(vals.length, 0);
	}
}