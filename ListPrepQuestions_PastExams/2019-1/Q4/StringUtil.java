public class StringUtil {
	
	/*
	 * This method collapses multiple consecutive newline characters 
	 * into a single newline character in a given sequence ('\n')
	 */
	public static String collapseNewlines(String input)
	{
		if (input.length() < 2) {
			return input;
		}
		
		StringBuffer buf = new StringBuffer();
		char last = input.charAt(0);
		buf.append(last);
		
		for (int index = 1 ; index < input.length(); index++)
		{
			char cur = input.charAt(index);
			
			if(cur!='\n') {
				buf.append(cur);
			}else {
				if (last != '\n') {
					buf.append(cur);
				}
			}
			last = cur;
		}
		return buf.toString();
	}
}
