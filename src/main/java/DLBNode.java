public class DLBNode
{
	private char id;
	private DLBLinkedList list = new DLBLinkedList();
	private String val;
	
	public DLBNode(char c)
	{
		id = c;
		val = null;
	}

	public DLBNode(char c, String value)
	{
		id = c;
		val = value;
	}
	
	public boolean equals(Object o)
	{
		if(o == this)
		{
			return true;
		}
		if(!(o instanceof DLBNode))
		{
			return false;
		}
		
		return this.id == ((DLBNode)o).getId();
	}
	
	public char getId()
	{
		return id;
	}
	
	public String getValue()
	{
		return val;
	}
	
	public DLBLinkedList getList()
	{
		return list;
	}
	
	public void setId(char new_id)
	{
		id = new_id;
	}
	
	public void setValue(String new_val)
	{
		val = new_val;
	}
	
	public void setList(DLBLinkedList new_list)
	{
		list = new_list;
	}
}