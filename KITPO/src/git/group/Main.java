package git.group;
import git.group.Builder.Builder;
import git.group.Builder.BuilderInteger;
import git.group.List.TList;
import java.util.Arrays;
import java.util.Scanner;


public class Main {

        public static void main(String[] args) throws Exception {
        Builder builder = Factory.getBuilderByName("Integer,GPS");

        new Gui();
            Test test = new Test();
            test.run();
    }

}
