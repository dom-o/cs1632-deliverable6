import java.util.*;

public class Substitutor
{
	//sub_out is characters that will be subbed out, i.e. t, a, e, etc. sub_in is characters that will be subbed in, i.e. 1, 4, 0, 3, etc. We expect that sub_out[i] is to be replaced by sub_in[i] and no duplicate replacements.
	public static String[] getNumberSubstitutions(String to_sub, String[] sub_in, String [] sub_out)
	{
		ArrayList<String> subs = new ArrayList<String>();
		char[] sub_copy = to_sub.toCharArray();
		
		for(int i=0; i<to_sub.length(); i++)
		{
			if(Arrays.asList(sub_out).contains(to_sub.charAt(i)+""))
			{
				//replace and add to list
				int sub_index = Arrays.asList(sub_out).indexOf(to_sub.charAt(i)+"");
				sub_copy[i] = sub_in[sub_index].charAt(0);
				subs.add(new String(sub_copy));
				
				for(int j=i+1; j<to_sub.length(); j++)
				{
					if(Arrays.asList(sub_out).contains(to_sub.charAt(j)+""))
					{
						//replace and add to list
						int second_sub_index = Arrays.asList(sub_out).indexOf(to_sub.charAt(j)+"");
						sub_copy[j] = sub_in[second_sub_index].charAt(0);
						subs.add(new String(sub_copy));
						sub_copy[j] = to_sub.charAt(j);
					}
				}
				sub_copy[i] = to_sub.charAt(i);
			}
		}
		
		subs.trimToSize();
		return subs.toArray(new String[subs.size()]);
	}
}