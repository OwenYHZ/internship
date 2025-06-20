package pm;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class StreamDemo {  // 修改这一行
    public static void main(String[] args) {
        List<String> one = new ArrayList<>();
        one.add("迪丽热巴");
        one.add("宋远桥");
        one.add("苏星河");
        one.add("老子");
        one.add("庄子");
        one.add("孙子");
        one.add("洪七公");

        List<String> two = new ArrayList<>();
        two.add("古力娜扎");
        two.add("张无忌");
        two.add("张三丰");
        two.add("赵丽颖");
        two.add("张二狗");
        two.add("张天爱");
        two.add("张三");

        // 使用Stream API处理第一个队伍
        Stream<String> streamOne = one.stream()
                .filter(s -> s.length() == 3)  // 1. 只要3个字的名字
                .limit(3);                     // 2. 只取前3人

        // 使用Stream API处理第二个队伍
        Stream<String> streamTwo = two.stream()
                .filter(s -> s.startsWith("张")) // 3. 只要姓张的名字
                .skip(2);                       // 4. 跳过前2人

        // 5. 合并两个队伍
        // 6. 根据姓名创建Person对象
        // 7. 打印整个队伍的Person对象信息
        Stream.concat(streamOne, streamTwo)
                .map(Person::new)               // 将字符串映射为Person对象
                .forEach(System.out::println);   // 打印每个Person对象
    }
}

class Person {
    private String name;

    public Person() {}

    public Person(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "'}";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}