import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;


public class DLBLinkedListTest
{

		DLBLinkedList list;
		DLBNode[] keys = {new DLBNode('a'), new DLBNode('b'), new DLBNode('c'), new DLBNode('d')};
		DLBNode[] non_keys = {new DLBNode('x'), new DLBNode('y'), new DLBNode('z')};
		
		
		@Test
		public void test1_indexOf()
		{
			list = new DLBLinkedList();
		
			assertTrue(list.isEmpty());
			assertEquals(list.indexOf(new DLBNode('a')), -1);
		}
		
		@Test
		public void test2_add()
		{
			list = new DLBLinkedList();
			for(DLBNode key : keys)
			{
				list.add(key);
			}
	
			assertFalse(list.isEmpty());
			assertEquals(list.size(), keys.length);
		}
		
		private void fill_DLBLinkedList()
		{
			list = new DLBLinkedList();
			for(DLBNode key : keys)
			{
				list.add(key);
			}
		}
		
		@Test
		public void test3_indexOf()
		{
			fill_DLBLinkedList();
			for(DLBNode key : keys)
			{
				assertTrue(list.indexOf(key) != -1);
			}
		}

		@Test
		public void test4_get()
		{
			fill_DLBLinkedList();
			for(DLBNode key : keys)
			{
				assertEquals(list.get(list.indexOf(key)), key);
			}
		}
		
		@Test
		public void test5_indexOf()
		{
			fill_DLBLinkedList();
			for(DLBNode non_key : non_keys)
			{
				assertEquals(list.indexOf(non_key), -1);
			}
		}
}