package git.group.Comparator;

import git.group.Builder.BuilderInteger;

import java.io.Serializable;


public class ComparatorInteger implements Comparator, Serializable
{
    @Override
    public int compare(Object o1, Object o2) {
        if (((BuilderInteger) o1).value == ((BuilderInteger) o2).value) return 0;
        if (((BuilderInteger) o1).value > ((BuilderInteger) o2).value) return 1;
        else return -1;
    }
}
