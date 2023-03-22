package git.group;

import git.group.Builder.Builder;
import git.group.Builder.BuilderInteger;
import git.group.Builder.BuilderGPS;
import git.group.List.TList;

import javax.swing.*;
import java.awt.*;

public class Gui extends JFrame{
    private Builder builder;
    private TList list;

    Gui() {
        super("Lab1");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        //окно вывода
        JTextArea out = new JTextArea();
        out.setEditable(false);
        out.setFont(new Font("Time New Roman", Font.PLAIN, 20));
        JPanel menu = new JPanel();
        Box box = Box.createVerticalBox();
        JComboBox builderType = new JComboBox(Factory.getTypeNameList().toArray());
        builderType.addActionListener(view -> {
            System.out.println(builderType.getSelectedItem());
            Object type = builderType.getSelectedItem();
            String type1 = type.toString();
            try
            {
                builder = Factory.getBuilderByName(type1);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            list = new TList(builder);
        });

        //loadsave
        JPanel workFiles = new JPanel(new FlowLayout());
        workFiles.setBorder(BorderFactory.createEmptyBorder(10,0,30,0));
        JButton load = new JButton("Загрузить из файла");
        load.addActionListener(v -> {
            try {
                list = Serialization.loadFile("file.txt");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            String str = out.getText() + "\nСписок был загружен!";
            out.setText(str);
        });
        JButton save = new JButton("Сохранить");
        save.addActionListener(v -> {
            try {
                Serialization.saveToFile(list, "file.txt", builderType.getSelectedItem().toString());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            String str = out.getText() + "\nСписок сохранен!";
            out.setText(str);
        });
        workFiles.add(load);
        workFiles.add(save);

        //insert
        JPanel insertIndexPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel insertValuePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel insertIndexLabel = new JLabel("Индекс: ");
        JTextField insertIndexField = new JTextField(4);
        JLabel insertValueLabel = new JLabel("Значение: ");
        JTextField insertValueField = new JTextField(10);
        insertValueField.setToolTipText("integer:int | GPS: double;double:int:int:int");

        insertIndexPanel.add(insertIndexLabel);
        insertIndexPanel.add(insertIndexField);
        insertValuePanel.add(insertValueLabel);
        insertValuePanel.add(insertValueField);
        JPanel insert = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton insertBtn = new JButton("Вставка элемента в начало");
        insertBtn.addActionListener(view -> {
            Builder obj = null;
            try {
                obj = (Builder) Factory
                        .getBuilderByName(builderType.getSelectedItem().toString())
                        .parseObject(insertValueField.getText());
            } catch (Exception e) {
                e.printStackTrace();
            }
            insertValueField.setText("");
            list.pushFront(obj);
            String str = out.getText() + "\nВставка элемента в начало " + obj.toString();
            out.setText(str);
        });

        JPanel insertbyIndex = new JPanel(new FlowLayout(FlowLayout.LEFT));
        insertbyIndex.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
        JButton insertBtnbyIndex = new JButton("Вставка по индексу");
        insertBtnbyIndex.addActionListener(view -> {
            Builder obj = null;
            try {
                obj = (Builder) Factory
                        .getBuilderByName(builderType.getSelectedItem().toString())
                        .parseObject(insertValueField.getText());
            } catch (Exception e) {
                e.printStackTrace();
            }
            int index = Integer.parseInt(insertIndexField.getText());
            insertIndexField.setText("");
            insertValueField.setText("");
            list.add(obj, index);
            String str = out.getText() + "\nВставка по индексу " + obj.toString() + " в " + index;
            out.setText(str);
        });

        JButton insertBtnBack = new JButton("Вставить в конец");
        insertBtnBack.addActionListener(view -> {
            Builder obj = null;
            try {
                obj = (Builder) Factory
                        .getBuilderByName(builderType.getSelectedItem().toString())
                        .parseObject(insertValueField.getText());
            } catch (Exception e) {
                e.printStackTrace();
            }
            insertValueField.setText("");
            list.pushEnd(obj);
            String str = out.getText() + "\nВставить в конец " + obj.toString();
            out.setText(str);
        });

        JButton deleteBtn = new JButton("Удалить элемент по индексу");
        deleteBtn.addActionListener(view -> {
            int index = Integer.parseInt(insertIndexField.getText());
            insertIndexField.setText("");
            insertValueField.setText("");
            list.delete(index);
            String str = out.getText() + "\n" + "Индекс: " + index + " удален";
            out.setText(str);
        });

        JPanel search = new JPanel(new FlowLayout(FlowLayout.CENTER));
        search.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
        JButton searchBtn = new JButton("Поиск элемента по индексу");
        searchBtn.addActionListener(view -> {
            int index = Integer.parseInt(insertIndexField.getText());
            insertIndexField.setText("");
            String str = out.getText() + "\n" + "Индекс " + index + ": " + list.find(index);
            out.setText(str);
        });



        JButton sortBtn = new JButton("Сортировка");
        sortBtn.addActionListener(view -> {
            list.sort(builder.getComparator());
            String str = out.getText() + "\n" + "Список отсортирован! ";
            out.setText(str);

        });


        JButton clrBtn = new JButton("Очистить список");
        clrBtn.addActionListener(view -> {
            list.clear();
            String str = out.getText() + "\n" + "Список очищен! ";
            out.setText(str);

        });

        JPanel show = new JPanel(new FlowLayout(FlowLayout.LEFT));
        show.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
        JButton showBtn = new JButton("Показать список");
        showBtn.addActionListener(view -> {
            String str = out.getText() + "\n" + list.toString();
            out.setText(str);
        });

        box.add(builderType);
        menu.add(box);

        builderType.addActionListener(e -> {
            box.add(builderType);
            insert.add(insertBtn);
            insert.add(insertBtnBack);
            insertbyIndex.add(insertBtnbyIndex);
            insertbyIndex.add(deleteBtn);
            search.add(searchBtn);
            show.add(sortBtn);
            show.add(showBtn);
            show.add(clrBtn);
            box.add(builderType);
            box.add(workFiles);
            box.add(insertIndexPanel);
            box.add(insertValuePanel);
            box.add(insert);
            box.add(insertbyIndex);
            box.add(search);
            box.add(show);
            menu.add(box);
            revalidate();
            repaint();
        });



        JPanel frame = new JPanel();
        frame.setLayout(new BorderLayout());
        frame.add(menu, BorderLayout.WEST);
        frame.add(new JScrollPane(out),BorderLayout.CENTER);

        setContentPane(frame);
        setSize(1200,600);
        setResizable(false);
        setVisible(true);
    }
}
