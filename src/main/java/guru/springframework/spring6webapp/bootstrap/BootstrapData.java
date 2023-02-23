package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.domain.Publisher;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import guru.springframework.spring6webapp.repositories.PublisherRepository;
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
        Author eric = new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Evans");

        Book book1 = new Book();
        book1.setTitle("Design Patterns");
        book1.setIsbn("12345");

        Author rod = new Author();
        rod.setFirstName("Rod");
        rod.setLastName("Johnson");

        Book book2 = new Book();
        book2.setTitle("J2EE Development");
        book2.setIsbn("55555");

        Author ericSaved = authorRepository.save(eric);
        Author rodSaved = authorRepository.save(rod);
        Book book1Saved = bookRepository.save(book1);
        Book book2Saved = bookRepository.save(book2);

        Publisher publisher = new Publisher();
        publisher.setPublisherName("IKAR");

        Publisher savedPublisher = publisherRepository.save(publisher);

        ericSaved.getBooks().add(book1Saved);
        rodSaved.getBooks().add(book2Saved);
        book1Saved.getAuthors().add(ericSaved);
        book2Saved.getAuthors().add(rodSaved);

        book1Saved.setPublisher(savedPublisher);
        book2Saved.setPublisher(savedPublisher);

        authorRepository.save(ericSaved);
        authorRepository.save(rodSaved);
        bookRepository.save(book1Saved);
        bookRepository.save(book2Saved);


        System.out.println("Bootstrap:");
        System.out.println("Author count: " + authorRepository.count());
        System.out.println("Book count: " + bookRepository.count());
        System.out.println("Publisher count: " + publisherRepository.count());
    }
}
