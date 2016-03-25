package com.google.engedu.ghost;

import java.util.HashMap;
import java.util.Map;


public class TrieNode {
    char c;
    TrieNode parent;
    HashMap<Character, TrieNode> children = new HashMap<>();
    boolean isLeaf;

    public TrieNode() {}
    public TrieNode(char c){this.c = c;}

    public void add(String s) {
        HashMap<Character, TrieNode> children = this.children;

        TrieNode crntparent;

        crntparent = this;



        for(int i=0; i<s.length(); i++)
        {
            char c = s.charAt(i);

            TrieNode t;
            if(children.containsKey(c)){ t = children.get(c);}
            else
            {
                t = new TrieNode(c);
                t.parent = crntparent;
                children.put(c, t);
            }

            children = t.children;
            crntparent = t;

            //set leaf node
            if(i==s.length()-1)
                t.isLeaf = true;
        }


    }

    public boolean isWord(String s) {
       return false;

    }

    public String getAnyWordStartingWith(String s) {

            return null;

    }

    public String getGoodWordStartingWith(String s) {
        return null;
    }


}

