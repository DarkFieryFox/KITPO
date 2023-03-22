package git.group;

import git.group.Builder.Builder;
import git.group.List.TList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Objects;

public class Serialization {
    public  static void saveToFile(TList list, String filename, String type) throws Exception {
        try (PrintWriter writer = new PrintWriter(filename)) {
            writer.println(type);
            list.forEach(writer::print);
        }
        System.out.println("\nСписок не сохранен!");
    }

    public static TList loadFile(String filename) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String type = br.readLine();
            if (!Factory.getTypeNameList().contains(type)) {
                throw new Exception("Неправильная файловая структура");
            }
            String line = br.readLine();
            String[] items = line.split(" ");
            Builder[] arr = new Builder[items.length];

            for (int i = 0; i < arr.length; i++)
                if (!Objects.equals(items[i], "null"))
                    arr[i] = (Builder) Factory.getBuilderByName(type).parseObject(items[i]);

            System.out.println("\nСписок был загружен!");
            return new TList(arr);
        }
    }
}
