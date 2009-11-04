package com.bryanestrada.facebull;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.SortedMap;
import java.util.TreeMap;

public class Driver 
{
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        if (0 == args.length)
        {
            // they didn't specify a file, so we can't continue
            return;
        }
        
        // first go through the file once to find all the unique compounds and 
        // create a two-dimensional array out of them
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(args[0])));
        SortedMap<String, Integer> compounds = new TreeMap<String, Integer>();
        for (String s = br.readLine(); null != s; s = br.readLine())
        {
            // split the string into whitespace characters
            String[] data = s.split("\\s+");
            
            compounds.put(data[1], null);
            compounds.put(data[2], null);
        }
        br.close();
        int count = 0;
        for (String key : compounds.keySet())
        {
            compounds.put(key, count);
            count += 1;
        }
        
       
        // make the double array based on the unique compounds
        Machine[][] machines = new Machine[count][count];
        
        // initialize price to infinity for now
        Machine infinity = new Machine(-1, Integer.MAX_VALUE);
        for (int i = 0; i < count; i++)
            for (int j = 0; j < count; j++)
                machines[i][j] = infinity;
        
        // except it costs nothing to go to your own self
        Machine zero = new Machine(0, 0);
        for (int i = 0; i < count; i++)
            machines[i][i] = zero;
        
        
        br = new BufferedReader(new InputStreamReader(new FileInputStream(args[0])));
        for (String s = br.readLine(); null != s; s = br.readLine())
        {
            // split the string into whitespace characters
            String[] data = s.split("\\s+");
            
            int machineName = Integer.parseInt(data[0].substring(1));
            int price = Integer.parseInt(data[3]);
            
            Machine machine = new Machine(machineName, price);
            int origCompound = compounds.get(data[1]);
            int newCompound = compounds.get(data[2]);
            machines[origCompound][newCompound] = machine;
        }
        br.close();

        for (int i = 0; i < machines.length; i++)
        {
            for (int j = 0; j < machines[i].length; j++)
            {
                System.out.print(machines[i][j].getCost() + " ");
            }
            System.out.print('\n');
        }
                
    }
}
