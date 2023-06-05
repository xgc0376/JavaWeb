package Stream;
//并行流
//大规模数据


import lombok.val;

import java.util.*;

import java.util.stream.Collectors;
import java.util.stream.Stream;
//方法引用

public class Stream01 {


    public static void main(String[] args) {

        List<Author> authors = getAuthors();
        //单列数据
            //test01(authors);
        //数组
            //test02();
        //map集合 双列集合转化为单列集合
            //test03();

        //中间操作 map 类型转换 + 操作数据
            //test04(authors);
        //flatmap 可以吧一个对象转化为多个对象作为流中操作
            //test05(authors);
            //test06(authors);

        // 终结流 collect 将流 转化为 集合  (list set map)
            test07(authors);


        if (authors.stream().anyMatch(author -> author.getAge()>15))
                System.out.println("存在大于15");

        // reduce归并 对流中的数据按照你指定的计算方式计算出一个结果。（缩减操作）
        Integer reduce1 = authors.stream()
                .distinct()
                .map(author -> author.getAge())
                .reduce(0, (reduce, ele) -> reduce + ele);

        System.out.println("reduce1 = " + reduce1);

        Integer reduce3 = authors.stream()
                .map(author -> author.getAge())
                .reduce(Integer.MIN_VALUE, (reduce2, ele) -> reduce2 > ele ? reduce2 : ele);

        System.out.println("reduce3 = " + reduce3);
    }

    private static void test07(List<Author> authors) {
        List<String> collect = authors.stream()
                .map(author -> author.getName())
                .distinct()
                .collect(Collectors.toList());
        System.out.println("collect = " + collect);

        Set<String> collect1 = authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .distinct()
                .map(book -> book.getName())
                .collect(Collectors.toSet());
        System.out.println("collect1 = " + collect1);

        Map<String, List<Book>> collect2 = authors.stream()
                .distinct()
                .collect(Collectors.toMap(author -> author.getName(), author -> author.getBooks()));
    }

    private static void test06(List<Author> authors) {
        authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .distinct()
                .flatMap(book -> Arrays.stream(book.getCategory().split(",")))
                .distinct()
                .skip(2)
                .forEach(System.out::println);
    }

    private static void test05(List<Author> authors) {
        authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .forEach(System.out::println);
    }

    private static void test04(List<Author> authors) {
        authors.stream()
                .map(author -> author.getAge())
                .map(integer -> integer+10)
                .forEach(System.out::println);
    }

    private static void test03() {
        Map<String,Integer> map = new HashMap<>();
        map.put("蜡笔小新",19);
        map.put("黑子",17);
        map.put("日向翔阳",16);

        Stream<Map.Entry<String, Integer>> stream = map.entrySet().stream();
        stream.filter(entry -> entry.getValue()>16)
                .forEach(entry -> System.out.println(entry.getKey()+":"+entry.getValue()));
    }

    private static void test02() {
        Integer[] arr = {1,2,3,4,5};
        Stream<Integer> stream = Arrays.stream(arr);
        stream.distinct()
                .filter(integer -> integer>2)
                .forEach(integer -> System.out.println("integer = " + integer));
    }

    private static void test01(List<Author> authors) {
        authors.stream()
                .distinct()
                .filter(author -> author.getAge()<18)
                .forEach(author -> System.out.println(author.getName()));
    }


    private static List<Author> getAuthors() {
        //数据初始化
        Author author = new Author(1L,"蒙多",33,"一个从菜刀中明悟哲理的祖安人",null);
        Author author2 = new Author(2L,"亚拉索",15,"狂风也追逐不上他的思考速度",null);
        Author author3 = new Author(3L,"易",14,"是这个世界在限制他的思维",null);
        Author author4 = new Author(3L,"易",14,"是这个世界在限制他的思维",null);

        //书籍列表
        List<Book> books1 = new ArrayList<>();
        List<Book> books2 = new ArrayList<>();
        List<Book> books3 = new ArrayList<>();

        books1.add(new Book(1L,"刀的两侧是光明与黑暗","哲学,爱情",88,"用一把刀划分了爱恨"));
        books1.add(new Book(2L,"一个人不能死在同一把刀下","个人成长,爱情",99,"讲述如何从失败中明悟真理"));

        books2.add(new Book(3L,"那风吹不到的地方","哲学",85,"带你用思维去领略世界的尽头"));
        books2.add(new Book(3L,"那风吹不到的地方","哲学",85,"带你用思维去领略世界的尽头"));
        books2.add(new Book(4L,"吹或不吹","爱情,个人传记",56,"一个哲学家的恋爱观注定很难把他所在的时代理解"));

        books3.add(new Book(5L,"你的剑就是我的剑","爱情",56,"无法想象一个武者能对他的伴侣这么的宽容"));
        books3.add(new Book(6L,"风与剑","个人传记",100,"两个哲学家灵魂和肉体的碰撞会激起怎么样的火花呢？"));
        books3.add(new Book(6L,"风与剑","个人传记",100,"两个哲学家灵魂和肉体的碰撞会激起怎么样的火花呢？"));

        author.setBooks(books1);
        author2.setBooks(books2);
        author3.setBooks(books3);
        author4.setBooks(books3);

        List<Author> authorList = new ArrayList<>(Arrays.asList(author,author2,author3,author4));
        return authorList;
    }

}
