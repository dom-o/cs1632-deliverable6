import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import java.util.*;

public class SubstitutorTest
{
	String[] sub_out = {"t", "a", "o", "e", "i", "l", "s"};
	String[] sub_in = {"7", "4", "0", "3", "1", "1", "5"};
	String[] no_subs;
	String[] one_sub; 
	String[] all_subs; 
	
	@Test
	public void test1_getNumberSubstitutions()
	{
		no_subs = Substitutor.getNumberSubstitutions("duck", sub_in, sub_out);
		assertEquals(no_subs.length, 0);
	}
	
	@Test
	public void test2_getNumberSubstitutions()
	{
		one_sub = Substitutor.getNumberSubstitutions("qwery", sub_in, sub_out);
		assertEquals(one_sub.length, 1);
		assertTrue(Arrays.asList(one_sub).contains("qw3ry"));
	}
	
	@Test
	public void test3_getNumberSubstitutions()
	{
		all_subs = Substitutor.getNumberSubstitutions("til", sub_in, sub_out);
		assertEquals(all_subs.length, 6);
	}
	
	@Test
	public void test4_getNumberSubstitutions()
	{
		String[] acceptable_subs = {"7il", "71l", "7i1", "t1l", "t11", "ti1"};
		all_subs = Substitutor.getNumberSubstitutions("til", sub_in, sub_out);
		
		for(String sub : acceptable_subs)
		{
			assertTrue(Arrays.asList(all_subs).contains(sub));
		}
	}
}