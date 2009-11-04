package com.bryanestrada.facebull;

public class Machine implements Comparable<Machine>
{
    private final int _name;
    private final int _cost;
       
    public Machine(int name, int cost)
    {
        _name = name;
        _cost = cost;
    }
    
    public int getName() { return _name; }
    public int getCost() { return _cost; }

    public int compareTo(Machine other)
    {
        return null == other ? 1 : _cost - other._cost;
    }
}
