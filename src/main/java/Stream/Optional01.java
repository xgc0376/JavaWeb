package Stream;

import java.util.Optional;
import java.util.function.Supplier;

public class Optional01 {


    public static void main(String[] args) {
//        Author author = getAuthor();
//        Optional<Author> optional = Optional.ofNullable(author);
//        optional.ifPresent(author1 -> System.out.println("author1.getAge() = " + author1.getAge()));

        Optional<Author> author = getAuthor();
        author.ifPresent(author1 -> System.out.println("author1.getAge() = " + author1.getAge()));

        Author author1 = author.orElseGet(() -> new Author());//没有给默认
        System.out.println("author1 = " + author1);

    }

    public static Optional<Author> getAuthor(){
        Author author = new Author();
        return Optional.ofNullable(null);
    }
}
