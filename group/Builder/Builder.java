package git.group.Builder;

import git.group.Comparator.Comparator;

import java.io.InputStreamReader;


public interface Builder
{
    Object createObject();
    Object readObject();
    Object clone();
    Object parseObject(String ss);
    Comparator getComparator();
    String getName();

}
