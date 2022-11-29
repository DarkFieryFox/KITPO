package git.group.Builder;

import git.group.Builder.Builder;
import git.group.Comparator.Comparator;
import git.group.Comparator.ComparatorGPS;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class BuilderGPS implements Builder
{
    public static final String typename = new String("GPS");


    public double latitude; //шитрота
    public double longitude; //долгота
    public int hour;
    public int minute;
    public int second;

    public BuilderGPS(double latitude, double longitude, int hour, int minute, int second){

        this.latitude = latitude;
        this.longitude = longitude;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public BuilderGPS() {

    }

    @Override
    public Object createObject() {
       return null;
    }

    @Override
    public Object readObject() {
      return latitude+longitude+hour+minute+second;
    }

    @Override
    public Object clone() {
        return null;
    }

    @Override
    public Object parseObject(String ss) {
        String[] numStr = ss.split(";|:");
        latitude = Double.parseDouble(numStr[0]);
        longitude = Double.parseDouble(numStr[1]);
        hour = Integer.parseInt(numStr[2]);
        minute = Integer.parseInt(numStr[3]);
        second = Integer.parseInt(numStr[4]);
        return this;
    }

    @Override
    public Comparator getComparator()
    {
        Comparator comparator = new ComparatorGPS();
        return comparator;
    }

    @Override
    public String getName() {
        return typename;
    }

    @Override
    public String toString() {
        return latitude+";"+longitude+":"+hour+":"+minute+":"+second;
    }
}