package by.leonovich.booklibrary;

import by.leonovich.booklibrary.service.BookService;
import by.leonovich.booklibrary.util.ConsoleScanner;
import by.leonovich.booklibrary.util.Constants;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import static java.lang.System.out;

@Log4j2
@SpringBootApplication
public class BookLibraryApplication {

    public static void main(final String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(BookLibraryApplication.class, args);
        log.info("Context initialized : {}", context.getEnvironment());
        menu(context);
    }

    @SuppressWarnings("all")
    public static void menu(ApplicationContext context) {
        out.println("Hello, " + System.getProperty("user.name") + "! You are in menu. Do action: ");
        while (true) {
            ConsoleScanner scn = context.getBean(Constants.SCANNER_BEAN, ConsoleScanner.class);
            BookService service = context.getBean(Constants.BOOK_SERVICE_BEAN, BookService.class);
            printMenu();
            switch (scn.nextInt()) {
                case 0 -> {
                    //scn.close();
                    System.exit(0);
                }
                case 1 -> service.createBook();
                case 2 -> service.deleteBook();
                case 3 -> service.getBooks();
                case 4 -> service.findBook();
                default -> out.println("Please make your choice");
            }
        }
    }

    private static void printMenu() {
        out.println("                                                          +--------------------+");
        out.println("                                                          |        MENU        |");
        out.println("+----------------------------------------------------------------------------------------------------------------------------------------+");
        out.println("|  0. Exit     |      1. Add book  |     2. Delete book   |      3. Get Books   |    4. Get Book  |  5. Parse file & write content in db |");
        out.println("+----------------------------------------------------------------------------------------------------------------------------------------+");

    }
}
