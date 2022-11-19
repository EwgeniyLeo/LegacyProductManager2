package ru.netology.manager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

public class ProductManagerTest {
    ProductRepository repository = new ProductRepository();
    ProductManager manager = new ProductManager(repository);

    Book book = new Book(11, "Snow", "Robert NikGarden", 500);
    Book book1 = new Book(22, "Snow", "Robert NikGarden", 550);
    Book book2 = new Book(33, "Evil in the dark", "Dr.Josh", 350);
    Smartphone smartphone1 = new Smartphone(44, "Samsung A7", "Samsung", 25_000);
    Smartphone smartphone2 = new Smartphone(55, "Samsung A52", "Samsung", 50_000);
    Smartphone smartphone3 = new Smartphone(66, "iPhone 13", "Apple", 125_000);

    @BeforeEach
    public void setup() {
        manager.addProduct(book);
        manager.addProduct(book1);
        manager.addProduct(book2);
        manager.addProduct(smartphone1);
        manager.addProduct(smartphone2);
        manager.addProduct(smartphone3);
    }

    @Test
    public void shouldAddandFindAllProducts() {
        Product[] expected = {book, book1, book2, smartphone1, smartphone2, smartphone3};
        Product[] actual = manager.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldRemoveId22() {
        Product[] expected = {book, book2, smartphone1, smartphone2, smartphone3};
        Product[] actual = manager.removeById(22);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearch() {
        Product[] expected = {book, book1};
        Product[] actual = manager.searchBy("Snow");
        Assertions.assertArrayEquals(expected, actual);
    }


    @Test
    public void shouldSearchNotInRepository() {
        Product[] expected = {};
        Product[] actual = manager.searchBy("Light");
        Assertions.assertArrayEquals(expected, actual);
    }
}