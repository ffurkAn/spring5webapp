package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData(){
        Author kemal = new Author();
        kemal.setFirstName("Kemal");
        kemal.setLastName("BAYINDIR");

        Book abuzer = new Book();
        abuzer.setIsbn("DFG345SG34H");
        abuzer.setTitle("Acıların Cocugu Abuzer");
        kemal.getBooks().add(abuzer);
        abuzer.getAuthors().add(kemal);

        Publisher p1 = new Publisher("P1 Yayinevi", "abc st. NY / USA");
        abuzer.setPublisher(p1);

        publisherRepository.save(p1);
        authorRepository.save(kemal);
        bookRepository.save(abuzer);

        Author gokhan = new Author();
        gokhan.setFirstName("Gokhan");
        gokhan.setLastName("ASLAN");

        Book gomis = new Book();
        gomis.setTitle("Gol Kralı Gomis");
        gokhan.getBooks().add(gomis);
        Publisher p2 = new Publisher("P2 Yayinevi", "qwerty st. CH / USA");
        gomis.setPublisher(p2);

        publisherRepository.save(p2);
        authorRepository.save(gokhan);
        bookRepository.save(gomis);
    }
}
