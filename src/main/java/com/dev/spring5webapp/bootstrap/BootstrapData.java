package com.dev.spring5webapp.bootstrap;

import com.dev.spring5webapp.domain.Author;
import com.dev.spring5webapp.domain.Book;
import com.dev.spring5webapp.domain.Publisher;
import com.dev.spring5webapp.repository.AuthorRepository;
import com.dev.spring5webapp.repository.BookRepository;
import com.dev.spring5webapp.repository.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Starting Data Bootstrap");

        Author bob = new Author("Robert", "Martin");
        Book cleanCode = new Book("Clean Code", "123456");
        Publisher publisher = new Publisher("Oreilly");
        publisherRepository.save(publisher);

        System.out.println("Number of publishers: "+publisherRepository.count());

        bob.getBooks().add(cleanCode);
        cleanCode.getAuthors().add(bob);
        cleanCode.setPublisher(publisher);
        publisher.getBooks().add(cleanCode);


        authorRepository.save(bob);
        bookRepository.save(cleanCode);
        publisherRepository.save(publisher);


        System.out.println("Number of books: "+bookRepository.count());
        System.out.println("Publisher books number: "+publisher.getBooks().size());
    }
}
