import java.util.*;
import java.io.*;

public class pw_check
{
	private static String[] symbols = {"!", "@", "$", "%", "&", "*"};
	private static String[] sub_out = {"t", "a", "o", "e", "i", "l", "s"};
	private static String[] sub_in = {"7", "4", "0", "3", "1", "1", "5"};
	private static char[] pass_alphabet = "abcdefghijklmnopqrstuvwxyz1234567890!@$%&*".toCharArray();
	private BufferedReader dict_in;
	private FileWriter my_dict_out;
	private FileWriter good_pass_out;
	private DLB non_passes;
	private DLB good_passes;
	
	public pw_check()
	{
		non_passes = new DLB();
		good_passes = new DLB();
		
		try
		{
			dict_in = new BufferedReader(new FileReader("dictionary.txt"));
			my_dict_out = new FileWriter("my_dictionary.txt");
			good_pass_out = new FileWriter("good_passwords.txt");
		}
		catch(IOException e)
		{
			System.out.println(e);
		}
	}
	
	public static void main(String[] args)
	{
		pw_check pw = new pw_check(); 
		
		if(args.length > 0 && args[0].equals("-g"))
		{
			pw.createGoodPasswords();
		}
		else
		{
			System.out.println("Wait; generating password list.");
			pw.createGoodPasswords();
			pw.non_passes = null;
			System.gc();
			
			try
			{
				BufferedReader good_pass_in = new BufferedReader(new FileReader("good_passwords.txt"));
				while(good_pass_in.ready())
				{
					String password = good_pass_in.readLine();
					pw.good_passes.put(password, password);
				}
			}
			catch(IOException e)
			{
				System.out.println(e);
			}
			
			Scanner in = new Scanner(System.in);
			while(true)
			{
				System.out.println("Enter a password.\n>\t");
				String pass = in.next();
				if(pw.good_passes.contains(pass))
				{
					System.out.println("This is a good password.");
				}
				else
				{
					System.out.println("This is a bad password.\nHere are some better ones.\n");
					String[] better_passes;
					int num_alternates = 0;
					int substr_end = pass.length()-1;
					
					while(num_alternates <= 10)
					{
						better_passes = pw.good_passes.getValuesForPrefix(pass.substring(0, substr_end));
						for(int i=0; i<better_passes.length && i <= 10-num_alternates; i++)
						{
							System.out.println("\t" + better_passes[i]);
							num_alternates++;
						}
						substr_end--;
					}
				}
			}
		}	
	}
	
	public void createGoodPasswords()
	{
		try
		{
			while(dict_in.ready())
			{
				String non_word = dict_in.readLine();
				if(non_word.length() < 5 && !non_word.equals(""))
				{
					if(non_word.length() == 4 && non_word.matches("([a-z]*[taoeils]+[a-z]*)"))
					{
						addNumSubsToDLB(non_word, sub_in, sub_out);
					}
					else if(non_word.length() <= 3)
					{
						if(!non_passes.containsPrefixOf(non_word))
						{
							//System.out.println("Should write " + non_word + " to my_dictionary");
							non_passes.put(non_word, non_word);
							//System.out.println(non_passes.get(non_word));
							my_dict_out.write(non_word+"\n", 0, non_word.length()+1);
							addNumSubsToDLB(non_word, sub_in, sub_out);
						}		
					}	
				}
			}
			generate(5);
		}
		catch(IOException e)
		{
			System.out.println(e);
		}	
	}

	public void generate(int word_length)
	{
		StringBuilder word = new StringBuilder();
		word.setLength(word_length);
		generate(word, 0);
	}

	private void generate(StringBuilder word, int pos)
	{
		if(pos == word.length())
		{
			int num_letters = 0;
			int num_numbers = 0;
			int num_symbols = 0;
			
			for(int i=0; i<word.length(); i++)
			{
				if(Character.isDigit(word.charAt(i)))
				{
					num_numbers++;
				}
				else if(Character.isLetter(word.charAt(i)))
				{
					num_letters++;
				}
				else
				{
					num_symbols++;
				}
			}
			
			if(num_letters >= 1 && num_numbers >= 1 && num_symbols >= 1 && num_letters <= 3 && num_numbers <= 2 && num_symbols <= 2 && num_letters+num_numbers+num_symbols == 5)
			{
				boolean contains_prefixes = false;
				for(int i=0; i<word.length(); i++)
				{
					for(int j=1; j <= word.length()-i; j++)
					{
						if(non_passes.contains(word.substring(i, i+j)))
						{
							//System.out.println("Bad password: " + word);
							contains_prefixes = true;
							break;
						}
					}
				}
				
				if(!contains_prefixes)
				{
					try
					{
						//System.out.println("Good password: " + word);
						good_pass_out.write(word.toString() + "\n", 0, word.length()+1);
					}
					catch(IOException e)
					{
						System.out.println(e);
					}
				}
			}
		}
		else
		{
			for(char letter : pass_alphabet)
			{
				word.setCharAt(pos, letter);
				generate(word, pos+1);
			}
		}
	}

	public void addNumSubsToDLB(String non_word,  String[] sub_in, String [] sub_out)
	{
		String[] num_subs = Substitutor.getNumberSubstitutions(non_word, sub_in, sub_out);
		for(String num_sub : num_subs)
		{
			if(!non_passes.containsPrefixOf(num_sub))
			{
				non_passes.put(num_sub, num_sub);
				try
				{
					//System.out.println("Should write " + num_sub + " to my_dictionary");
					my_dict_out.write(num_sub + "\n", 0, num_sub.length()+1);
					//System.out.println("Just wrote " + num_sub + " to my_dictionary");
				}
				catch(IOException e)
				{
					System.out.println(e);
				}
			}
		}
	}
}