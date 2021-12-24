package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {
    ProductRepository repository = new ProductRepository();
    ProductManager manager = new ProductManager(repository);

    private Product productOne = new Book(1, "War and Peace", 250, "Leo Tolstoy");
    private Product productTwo = new Book(2, "The Colour of Magic", 200, "Terry Pratchett");
    private Product productThree = new Smartphone(3, "510", 7000, "Motorola");
    private Product productFour = new Smartphone(4, "Galaxy S20", 60000, "Samsung");
    private Product productFive = new Book(5, "Anna Karenina", 270, "Leo Tolstoy");
    private Product[] notProduct = new Product[0];


    @BeforeEach
    public void setUp() {
        manager.add(productOne);
        manager.add(productTwo);
        manager.add(productThree);
        manager.add(productFour);
        manager.add(productFive);
    }

    @Test
    void shouldSearchAuthor() {
        Product[] expected = {productTwo};
        Product[] actual = manager.searchBy("Terry Pratchett");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchBookName() {
        Product[] expected = {productTwo};
        Product[] actual = manager.searchBy("The Colour of Magic");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchSmartphoneName() {
        Product[] expected = {productFour};
        Product[] actual = manager.searchBy("Galaxy S20");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchManufacturer() {
        Product[] expected = {productThree};
        Product[] actual = manager.searchBy("Motorola");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldNotSearchManufacturer() {
        Product[] expected = notProduct;
        Product[] actual = manager.searchBy("Nokia");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindAllByAuthor() {
        Product[] expected = new Product[]{productOne, productFive};
        Product[] actual = manager.searchBy("Leo Tolstoy");
        assertArrayEquals(expected, actual);
    }
}