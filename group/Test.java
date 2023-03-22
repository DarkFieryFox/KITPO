package git.group;

import git.group.Builder.Builder;
import git.group.Builder.BuilderInteger;
import git.group.Builder.BuilderGPS;
import git.group.List.TList;

import java.util.Random;

public class Test {
    public final double MAX = 100.0;
    public final double MIN = -100.0;
    public final int minHour = 0, maxHour = 23;
    public final int minTime = 0, maxTime = 59;

    public Builder settingBuilder(String name) throws Exception
    {
        if (name.equals(BuilderGPS.typename))
       {
            return new BuilderGPS();
       }
        if (name.equals(BuilderInteger.typename))
        {
            return new BuilderInteger();
        }
        else
        {
            Exception e = new Exception("Oшибка: нет такого типа");
            throw e;
        }
    }

    public void run() throws Exception {
        testInt();
       testGPS();
    }

    private void drawList(TList otherlist)
    {
        otherlist.forEach((name) ->
        {
            System.out.println(name);
        });
    }

    void testInt() throws Exception {
        Builder builder = null;
        TList list;
        System.out.print("\tТест Integer");
        try {
            builder = settingBuilder("Integer");
        } catch (Exception e) {
            e.printStackTrace();
        }
        list = new TList(builder);
        System.out.print("\nСписок пуст\n");
        drawList(list);

        int max = (int) (Math.random() * 5) + 4;
        for (int i = 0; i < max; i++) {
            int value = (int) (Math.random() * 100) - 50;
            Builder obj = new BuilderInteger(value);
            obj.toString();
            list.add(obj, i);
        }

        System.out.print("\nСгенерированный список\n");
        drawList(list);

        System.out.print("\nПоиск каждого второго элемента\n");
        for (int i = 0; i < max; i = i + 2)
        {
            System.out.println(list.find(i));
        }

        System.out.println("\nПроизошла сортировка\n");
        list.sort(builder.getComparator());
        drawList(list);
        list.clear();
        System.out.println("Список удален");
    }

    void testGPS() throws Exception {
        Builder builder = null;
        TList list;
        System.out.print("\tTest GPS");
        try {
            builder = settingBuilder("GPS");
        } catch (Exception e) {
            e.printStackTrace();
        }
        list = new TList(builder);
        System.out.print("\nСписок пуст\n");
        drawList(list);


        int max =(int) (Math.random() * 5) + 4;
        for (int i = 0; i < max; i++) {
            Random rand = new Random();
            double latitude = MIN + (MAX-MIN)*rand.nextDouble();
            double longitude = MIN + (MAX-MIN)*rand.nextDouble();
            int hour = rand.nextInt(maxHour - minHour);
            int minute = rand.nextInt(maxTime - minTime);
            int second = rand.nextInt(maxTime - minTime);
            Builder obj = new BuilderGPS(latitude, longitude, hour, minute, second);
            obj.toString();
            list.add(obj, i);
        }

        System.out.print("\nСгенерированный список\n");
        drawList(list);

        System.out.print("\nПоиск каждого второго элемента\n");
        for (int i = 1; i < max; i = i + 2) {
            System.out.println(list.find(i));
        }

        System.out.println("\nПроизошла сортировка\n");
        list.sort(builder.getComparator());
        drawList(list);
        list.clear();

        System.out.println("Список удален");
    }
}
