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
    private Product productThree = new Smartphone(3, "510", 7000, "Motorolla");
    private Product productFour = new Smartphone(4, "Galaxy S20", 60000, "Samasung");
    private Product[] notProduct = new Product[0];


    @BeforeEach
    public void setUp() {
        manager.add(productOne);
        manager.add(productTwo);
        manager.add(productThree);
        manager.add(productFour);
    }

    @Test
    void shouldSearchAuthor() {
        Product[] expected = {productOne};
        Product[] actual = manager.searchBy("Leo Tolstoy");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchBookName() {
        Product[] expected = {productTwo};
        Product[] actual = manager.searchBy("The Colour of Magic");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchSmarthoneName() {
        Product[] expected = {productFour};
        Product[] actual = manager.searchBy("Galaxy S20");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchManufacturer() {
        Product[] expected = {productThree};
        Product[] actual = manager.searchBy("Motorolla");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldNotSearchManufacturer() {
        Product[] expected = notProduct;
        Product[] actual = manager.searchBy("Nokia");
        assertArrayEquals(expected, actual);
    }


}