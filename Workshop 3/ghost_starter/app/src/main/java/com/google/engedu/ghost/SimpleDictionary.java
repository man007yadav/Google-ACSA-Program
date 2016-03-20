package com.google.engedu.ghost;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class SimpleDictionary implements GhostDictionary {
    private ArrayList<String> words;

    public SimpleDictionary(InputStream wordListStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(wordListStream));
        words = new ArrayList<>();
        String line = null;
        while((line = in.readLine()) != null) {
            String word = line.trim();
            if (word.length() >= MIN_WORD_LENGTH)
              words.add(line.trim());
        }
    }

    @Override
    public boolean isWord(String word) {
        return words.contains(word);
    }

    @Override
    public String getAnyWordStartingWith(String prefix) {
        if(prefix==""){
            Random r=new Random();
           return words.get(r.nextInt(words.size()));

        }else{
            int m,l=0,h=words.size()-1;
            while(l<=h){
                m=(l+h)/2;
                if(words.get(m).startsWith(prefix))
                    return words.get(m);
                else if(words.get(m).compareToIgnoreCase(prefix)>0)
                    h=m-1;
                else
                    l=m+1;

            }
            return null;
        }
    }

    @Override
    public String getGoodWordStartingWith(String prefix) {
        return null;
    }
}
