import java.util.*;

public class DLB
{
	private int size;
	private DLBNode root;
	private ArrayList<String> prefix_vals;
	
	public DLB()
	{
		size = 0;
		root = new DLBNode('\0');
		prefix_vals = new ArrayList<String>();
	}
	
	public boolean contains(String key)
	{
		return this.get(key) != null;
	}
	
	public String get(String key)
	{
		DLBNode cur_node = root;
		String value = null;
		
		for(int i=0; i<key.length(); i++)
		{
			char c = key.charAt(i);
			int index = cur_node.getList().indexOf(new DLBNode(c));
			
			if(index != -1)
			{
				cur_node = cur_node.getList().get(index);
				if(i == key.length()-1)
				{
					int end_index = cur_node.getList().indexOf(new DLBNode('\0'));
					if(end_index != -1 && cur_node.getList().get(end_index).getValue() != null)
					{
						value = cur_node.getList().get(end_index).getValue();
					}
					else
					{
						break;
					}
				}
			}
			else
			{
				break;
			}
		}
	
		return value;
	}
	
	public void put(String key, String value)
	{
		DLBNode cur_node = root;
		DLBLinkedList temp_list;
		if(!this.contains(key))
		{
			for(int i=0; i<key.length(); i++)
			{
				char c = key.charAt(i);
				DLBNode node_to_add = new DLBNode(c);
				int index = cur_node.getList().indexOf(node_to_add);
				
				if(index == -1)
				{
					temp_list = cur_node.getList();
					temp_list.add(node_to_add);
					cur_node.setList(temp_list);
					cur_node = node_to_add;
				}
				else
				{
					cur_node = cur_node.getList().get(index);
				}
			}
			temp_list = cur_node.getList();
			temp_list.add(new DLBNode('\0', value));
			cur_node.setList(temp_list);
			size++;
		}
	}
	
	//Returns true if the word itself is in the DLB; a word is considered a prefix of itself.
	public boolean containsPrefixOf(String key)
	{
		DLBNode cur_node = root;
		String value = null;
		
		for(int i=0; i<key.length(); i++)
		{
			char c = key.charAt(i);
			int index = cur_node.getList().indexOf(new DLBNode(c));
			
			if(index != -1)
			{
				cur_node = cur_node.getList().get(index);
				int end_index = cur_node.getList().indexOf(new DLBNode('\0'));
				if(end_index != -1 && cur_node.getList().get(end_index).getValue() != null)
				{
					return true;
				}
			}
			else
			{
				break;
			}
		}	
		return false;
	}
	
	public String[] getValuesForPrefix(String key)
	{
		DLBNode cur_node = root;
		
		for(int i=0; i<key.length(); i++)
		{
			char c = key.charAt(i);
			int index = cur_node.getList().indexOf(new DLBNode(c));
			
			if(index != -1)
			{
				cur_node = cur_node.getList().get(index);
				if(i == key.length()-1)
				{
					for(int j=0; j<cur_node.getList().size(); j++)
					{
						getValuesForPrefix(cur_node.getList().get(j));
					}
				}
			}
			else
			{
				break;
			}
		}
		
		String[] arr = prefix_vals.toArray(new String[prefix_vals.size()]);
		prefix_vals.clear();
		return arr;
	}
	
	private void getValuesForPrefix(DLBNode cur_node)
	{
		if(cur_node.getValue() == null)
		{
			for(int j=0; j<cur_node.getList().size(); j++)
			{
				getValuesForPrefix(cur_node.getList().get(j));
			}
		}
		else
		{
			if(!cur_node.getValue().equals(""))
			{
				prefix_vals.add(cur_node.getValue());
			}
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