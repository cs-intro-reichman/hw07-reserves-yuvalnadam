
public class SpellChecker {


	public static void main(String[] args) {

		String word1 = "hello";
		String word2 = "HILLo";
		System.out.println(levenshtein(word1, word2));

		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {

		return str.substring(1);

	}

	//The function accepts two strings as input and returns the edit distance between these two words, as an integer (known as "levenshtein distance function")

	public static int levenshtein(String word1, String word2) {

		int count1 = 0;
		int count2 = 0;
		int count = 0;

		word1 = word1.toLowerCase();
		word2 = word2.toLowerCase();

		if(word1.length() == 0)
			return word2.length();

		if (word2.length() == 0) 
			return word1.length();

		if (word1.charAt(0) == word2.charAt(0)) 

				count = levenshtein(tail(word1), tail(word2));

			else // 1+min
		{

			 count1 = 1 + levenshtein(tail(word1), word2);
			 count2 = 1 + levenshtein(word1, tail(word2));
        	count = Math.min(Math.min(count1, count2), 1 + levenshtein(tail(word1), tail(word2)));
		}

		return count;
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);


		for (int i = 0; i < 3000 ; i++ ) {

			dictionary[i] = in.readLine();
			
		}

		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {

		String simliarWord = word;

		for (int i = 0; i < dictionary.length; i++ ) {

			if (levenshtein(dictionary[i], word) < threshold)
				simliarWord = dictionary[i];

			
		}

		return simliarWord;

	}

}
