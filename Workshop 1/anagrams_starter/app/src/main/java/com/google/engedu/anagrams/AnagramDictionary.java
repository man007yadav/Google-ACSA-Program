package com.google.engedu.anagrams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.*;
import java.lang.*;

public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();


    /*My Implementation*/
    HashSet<String> wordSet=new HashSet<String>();
    ArrayList<String> wordList=new ArrayList<String>();
    HashMap<String,ArrayList<String>> lettersToWord= new HashMap<>();
    HashMap<Integer,ArrayList<String>> sizeToWords= new HashMap<>();
    int wordLength;
    /*My Implementation*/


    public AnagramDictionary(InputStream wordListStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(wordListStream));
        String line;
        wordLength=DEFAULT_WORD_LENGTH;
        while((line = in.readLine()) != null) {
            String word = line.trim();


            wordSet.add(word);
            wordList.add(word);


            String key = sort(word);

            if (!lettersToWord.containsKey(key)) {
                lettersToWord.put(key, new ArrayList<String>());
            }

            lettersToWord.get(key).add(word);

            int key1= word.length();
            if (!sizeToWords.containsKey(key1)) {
                sizeToWords.put(key1, new ArrayList<String>());
            }
            sizeToWords.get(key1).add(word);

        }
    }

    public String sort(String s)
    {
        char arr[]=s.toCharArray();
        Arrays.sort(arr);
        return String.valueOf(arr);
    }

    public boolean isGoodWord(String word, String base) {

        if(wordSet.contains(word) && word.toLowerCase().contains(base.toLowerCase()))
            return false;

            return true;
    }

    public ArrayList<String> getAnagramsWithOneMoreLetter(String word) {
        ArrayList<String> result = new ArrayList<String>();
        char i,k;
        String str="";
        for(i='a';i<='z';i++){
            String str1 = Character.toString(i);
          for(k='a';k<='z';k++) {

              str = word + str1;
              String str2 = Character.toString(k);
              str += str2;

              str = sort(str);
              if (lettersToWord.containsKey(str)) {
                  result.addAll(lettersToWord.get(str));
              }
              int j;
              for (j = 0; j < result.size(); j++) {
                  if (!isGoodWord(result.get(j), word))
                      result.remove(j);

              }
          }
        }


        return result;
    }

    public String pickGoodStarterWord() {
        /*int i = Math.abs(random.nextInt() % wordList.size());
        for (; i < wordList.size(); i++) {
            String str = wordList.get(i);
            str = sort(str);
            if (lettersToWord.get(str).size() == MIN_NUM_ANAGRAMS) {
                return wordList.get(i);
            }
        }
        return null;*/


        int i = Math.abs(random.nextInt() % sizeToWords.get(wordLength).size());
        int wc=wordLength;
        if(wordLength!=MAX_WORD_LENGTH)
         wordLength++;
         return sizeToWords.get(wc).get(i);
    }
}
