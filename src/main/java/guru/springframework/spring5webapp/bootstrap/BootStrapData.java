package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(
            AuthorRepository authorRepository,
            BookRepository bookRepository,
            PublisherRepository publisherRepository
    ) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) {
        Author solo = new Author("Solomon", "Aryeetey");
        Book akiOla = new Book("Aki Ola", "GH-235325434");

        solo.getBooks().add(akiOla);
        akiOla.getAuthors().add(solo);

        authorRepository.save(solo);
        bookRepository.save(akiOla);

        Author adama = new Author("Adama", "Gariba");
        Book gibsonTheory = new Book("Gibson Theory", "US-554325434");

        adama.getBooks().add(gibsonTheory);
        gibsonTheory.getAuthors().add(adama);

        authorRepository.save(adama);
        bookRepository.save(gibsonTheory);

        Publisher eppBooks = new Publisher(
                "EPP Book Services",
                "Legon",
                "Accra",
                "Greater-Accra",
                "+233"
        );

        eppBooks.getBooks().add(gibsonTheory);
        eppBooks.getBooks().add(akiOla);

        publisherRepository.save(eppBooks);

        System.out.println("Started in bootstrap");
        System.out.println("Number of publishers: " + publisherRepository.count());
        System.out.println("Number of book: " + bookRepository.count());
    }
}
