import java.lang.*;

public class DLBLinkedList
{
	private Node root;
	private int size;
	
	private class Node
	{
		DLBNode val;
		Node next;
		
		private Node(DLBNode dlb_node)
		{
			val = dlb_node;
			next = null;
		}
	}
	
	public DLBLinkedList()
	{
		root = new Node(null);
		size = 0;
	}
	
	public int indexOf(DLBNode dlb_node)
	{
		Node cur_node = root;
		for(int i=0; i<size; i++)
		{
			cur_node = cur_node.next;
			if(cur_node.val.equals(dlb_node))
			{
				return i;
			}
		}	
		return -1;
	}
	
	public void add(DLBNode dlb_node)
	{
		Node cur_node = root;
		while(cur_node.next != null)
		{
			cur_node = cur_node.next;
		}
		cur_node.next = new Node(dlb_node);
		size ++;
	}
	
	public DLBNode get(int index)
	{
		if(index > size || index < 0)
		{
			throw new IndexOutOfBoundsException();
		}
		else
		{
			Node cur_node = root;
			for(int i =0; i<=index; i++)
			{
				cur_node = cur_node.next;
			}
			return cur_node.val;
		}
	}
	
	public int size()
	{
		return size;
	}
	
	public boolean isEmpty()
	{
		return size == 0;
	}
}