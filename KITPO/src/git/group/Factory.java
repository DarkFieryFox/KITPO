package git.group;

import git.group.Builder.Builder;
import git.group.Builder.BuilderGPS;
import git.group.Builder.BuilderInteger;

import git.group.List.TList;

import java.util.ArrayList;
import java.util.Arrays;

public class Factory {


    public static ArrayList<String> getTypeNameList() {
        ArrayList<String> list = new ArrayList<>(Arrays.asList("  ","Integer","GPS"));
        return list;
    }

    public static Builder getBuilderByName(String name) throws Exception
    {
        if (name.equals(BuilderGPS.typename))
        {
            return new BuilderGPS();
        }
        else if (name.equals(BuilderInteger.typename))
        {
            return new BuilderInteger();
        }
        else
        {
            return null;
        }
    }

}
