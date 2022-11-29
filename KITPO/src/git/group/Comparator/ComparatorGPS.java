package git.group.Comparator;

import git.group.Builder.BuilderGPS;

import java.io.Serializable;
import java.lang.reflect.*;


public class ComparatorGPS implements Comparator, Serializable
{
    @Override
    public int compare(Object o1, Object o2)
    {
        return (int) (((BuilderGPS)o1).latitude - ((BuilderGPS)o2).latitude);
    }
}