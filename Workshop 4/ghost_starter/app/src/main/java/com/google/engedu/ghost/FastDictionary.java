package com.google.engedu.ghost;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Stack;


public class FastDictionary implements GhostDictionary {

    private TrieNode root;
    ArrayList<String> words;
    TrieNode prefixRoot;
    String curPrefix;

    public FastDictionary(InputStream wordListStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(wordListStream));
        root = new TrieNode();
        words=new ArrayList<>();
        String line = null;
        while((line = in.readLine()) != null) {
            String word = line.trim();
            if (word.length() >= MIN_WORD_LENGTH)
                root.add(line.trim());
        }
    }
    @Override
    public boolean isWord(String word) {
        TrieNode t = searchNode(word);

        if(t != null && t.isLeaf)
            return true;
        else
            return false;
    }

    @Override
    public String getAnyWordStartingWith(String prefix) {

        Random r=new Random();
         if(prefix==""){
            char ch= (char) ('a'+r.nextInt()%27);
             ch=Character.toLowerCase(ch);
            return Character.toString(ch);
         }
        else {
             if (searchNode(prefix) == null)
                 return null;
             else {
                 wordsFinderTraversal(prefixRoot, 0);

                 return words.get(r.nextInt(words.size()));
             }
         }
    }

    @Override
    public String getGoodWordStartingWith(String prefix) {
        Random r=new Random();
        if(prefix==""){
            char ch= (char) ('a'+Math.abs(r.nextInt()%27));
            ch=Character.toLowerCase(ch);
            return Character.toString(ch);
        }
        else {
            if (searchNode(prefix) == null)
                return null;
            else {
                wordsFinderTraversal(prefixRoot, 0);

                return words.get(r.nextInt(words.size()));
            }
        }
    }

    public TrieNode searchNode(String str){
        Map<Character, TrieNode> children = root.children;
        TrieNode t = null;
        for(int i=0; i<str.length(); i++)
        {
            char c = str.charAt(i);
            if(children.containsKey(c))
            {
                t = children.get(c);
                children = t.children;
            }
            else{return null;}
        }

        prefixRoot = t;
        curPrefix = str;
        words.clear();
        return t;
    }

    void wordsFinderTraversal(TrieNode node, int offset)
    {


        if(node.isLeaf)
        {
            //println("leaf node found");

            TrieNode altair;
            altair = node;

            Stack<String> hstack = new Stack<String>();

            while(altair != prefixRoot)
            {
                //println(altair.c);
                hstack.push( Character.toString(altair.c) );
                altair = altair.parent;
            }

            String wrd = curPrefix;

            while(!hstack.empty())
            {
                wrd = wrd + hstack.pop();
            }

            //println(wrd);
            words.add(wrd);

        }

        Set<Character> kset = node.children.keySet();

        Iterator itr = kset.iterator();
        ArrayList<Character> aloc = new ArrayList<Character>();

        while(itr.hasNext())
        {
            Character ch = (Character)itr.next();
            aloc.add(ch);
            //println(ch);
        }



        for( int i=0;i<aloc.size();i++)
        {
            wordsFinderTraversal(node.children.get(aloc.get(i)), offset + 1);
        }

    }
}
