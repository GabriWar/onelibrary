package com.example.restservice;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import com.example.restservice.model.Author;
import com.example.restservice.model.Book;
import com.example.restservice.model.Publisher;
import com.example.restservice.repository.AuthorRepository;
import com.example.restservice.repository.BookRepository;
import com.example.restservice.repository.PublisherRepository;
import com.example.restservice.repository.PeriodicRepository;
import com.example.restservice.repository.StudentRepository;
import com.example.restservice.repository.ProfessorRepository;
import com.example.restservice.repository.StaffRepository;
import com.example.restservice.model.Student;
import com.example.restservice.model.Professor;
import com.example.restservice.model.Staff;

/**
 * Seeds the database with realistic Brazilian literature and academic data when started with --app.seed.bulk=true
 */
@Component
@ConditionalOnProperty(name = "app.seed.bulk", havingValue = "true")
public class BulkDataLoader implements CommandLineRunner {

    private final AuthorRepository authorRepo;
    private final PublisherRepository pubRepo;
    private final PeriodicRepository periodicRepo;
    private final BookRepository bookRepo;
    private final StudentRepository studentRepo;
    private final ProfessorRepository professorRepo;
    private final StaffRepository staffRepo;

    public BulkDataLoader(AuthorRepository authorRepo, PublisherRepository pubRepo, BookRepository bookRepo,
                          StudentRepository studentRepo, ProfessorRepository professorRepo, StaffRepository staffRepo,
                          PeriodicRepository periodicRepo) {
        this.authorRepo = authorRepo;
        this.pubRepo = pubRepo;
        this.periodicRepo = periodicRepo;
        this.bookRepo = bookRepo;
        this.studentRepo = studentRepo;
        this.professorRepo = professorRepo;
        this.staffRepo = staffRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("BulkDataLoader: starting realistic bulk seeding...");

        Random rnd = ThreadLocalRandom.current();

        // Real Brazilian and international authors
        String[] realAuthors = {
            "Machado de Assis", "José de Alencar", "Lima Barreto", "Graciliano Ramos", "Jorge Amado",
            "Clarice Lispector", "Guimarães Rosa", "Rachel de Queiroz", "Cecília Meireles", "Carlos Drummond de Andrade",
            "Mario de Andrade", "Oswald de Andrade", "Manuel Bandeira", "Vinicius de Moraes", "Paulo Coelho",
            "Lygia Fagundes Telles", "Rubem Fonseca", "Luis Fernando Veríssimo", "Chico Buarque", "Ignácio de Loyola Brandão",
            "Moacyr Scliar", "João Ubaldo Ribeiro", "Caio Fernando Abreu", "Marina Colasanti", "Ana Maria Machado",
            "Monteiro Lobato", "José Saramago", "Fernando Pessoa", "Eça de Queirós", "Camões",
            "Gabriel García Márquez", "Jorge Luis Borges", "Octavio Paz", "Pablo Neruda", "Isabel Allende",
            "Mario Vargas Llosa", "Julio Cortázar", "Carlos Fuentes", "Alejo Carpentier", "Miguel de Cervantes",
            "Federico García Lorca", "Antonio Machado", "Miguel de Unamuno", "Rubén Darío", "José Martí",
            "Erico Verissimo", "Mário Quintana", "Ferreira Gullar", "Adélia Prado", "Cora Coralina"
        };

        String[] nationalities = {"Brasileira", "Portuguesa", "Espanhola", "Argentina", "Chilena", "Colombiana", "Mexicana", "Uruguaia", "Peruana"};

        // Real publishers (50 entries) to make the seeded data more realistic
        String[] realPublishers = {
            "Companhia das Letras",
            "Editora Record",
            "Editora Globo",
            "Editora 34",
            "Cosac Naify",
            "Ática",
            "Saraiva",
            "Pearson Brasil",
            "Campus/Elsevier",
            "Elsevier Brasil",
            "Rocco",
            "L&PM",
            "Objetiva",
            "Intrínseca",
            "Planeta",
            "FTD",
            "Moderna",
            "Scipione",
            "Contexto",
            "Boitempo",
            "Martins Fontes",
            "Nova Fronteira",
            "José Olympio",
            "Civilização Brasileira",
            "Zahar",
            "Paz e Terra",
            "Brasiliense",
            "Papirus",
            "Cortez",
            "Loyola",
            "Editora UNESP",
            "EDUSP",
            "Editora UFMG",
            "Editora UFRGS",
            "Editora UnB",
            "Vozes",
            "Paulus",
            "Santuário",
            "Ave Maria",
            "Penguin Random House Brasil",
            "HarperCollins Brasil",
            "Alfaguara Brasil",
            "Anagrama",
            "Todavia",
            "LeYa Brasil",
            "Summus",
            "Hedra",
            "Senac",
            "Editora UFRJ"
        };

        String[] cities = {
            "São Paulo", "Rio de Janeiro", "Belo Horizonte", "Porto Alegre", "Salvador",
            "Curitiba", "Recife", "Fortaleza", "Brasília", "Florianópolis",
            "Goiânia", "Vitória", "Natal", "João Pessoa", "Maceió",
            "Aracaju", "São Luís", "Teresina", "Macapá", "Boa Vista",
            "Lisboa", "Porto", "Madrid", "Barcelona", "Buenos Aires"
        };

        // Real book titles from Brazilian literature and academia
        String[] realBookTitles = {
            "Dom Casmurro", "O Cortiço", "O Guarani", "Iracema", "Senhora",
            "Vidas Secas", "São Bernardo", "Angústia", "Memórias Póstumas de Brás Cubas", "Quincas Borba",
            "A Hora da Estrela", "Água Viva", "Perto do Coração Selvagem", "O Livro dos Nomes", "A Via Crucis do Corpo",
            "Grande Sertão: Veredas", "Sagarana", "Primeiras Estórias", "Manuelzão e Miguilim", "Tutaméia",
            "O Quinze", "As Três Marias", "Memorial de Maria Moura", "Dôra, Doralina", "O Galo de Ouro",
            "Romanceiro da Inconfidência", "Viagem", "Vaga Música", "Metal Rosicler", "Solombra",
            "Sentimento do Mundo", "A Rosa do Povo", "Claro Enigma", "Fazendeiro do Ar", "A Vida Passada a Limpo",
            "Paulicéia Desvairada", "Macunaíma", "Amar, Verbo Intransitivo", "O Turista Aprendiz", "Ensaio sobre a Música Brasileira",
            "Manifesto Antropófago", "Pau Brasil", "Memórias Sentimentais de João Miramar", "Serafim Ponte Grande", "Marco Zero",
            "Libertinagem", "Estrela da Manhã", "Lira dos Cinquent'Anos", "Opus 10", "Belo Belo",
            "Soneto de Fidelidade", "Livro de Sonetos", "Para Viver um Grande Amor", "A Arca de Noé", "O Poeta da Paixão",
            "Ciranda de Pedra", "As Meninas", "Antes do Baile Verde", "Seminário dos Ratos", "A Estrutura da Bolha de Sabão",
            "O Cobrador", "Feliz Ano Novo", "A Grande Arte", "Agosto", "José",
            "Comédias da Vida Privada", "O Analista de Bagé", "As Mentiras que os Homens Contam", "Borges e os Orangotangos Eternos", "O Clube dos Anjos",
            "A Hora e Vez de Augusto Matraga", "Manuelzão e Miguilim", "No Urubuquaquá, no Pinhém", "Estas Estórias", "Ave, Palavra",
            "Sargento Getúlio", "Viva o Povo Brasileiro", "O Sorriso do Lagarto", "O Conselheiro Crispim", "Política",
            "Morangos Mofados", "Os Dragões não Conhecem o Paraíso", "Triângulo das Águas", "Onde Andará Dulce Veiga?", "Pequenas Epifanias",
            "A Moreninha", "O Guarani", "Cinco Minutos", "A Viuvinha", "Encarnação",
            "O Ateneu", "A Normalista", "Bom-Crioulo", "O Homem", "A Carne",
            "Os Sertões", "Canudos", "À Margem da História", "Peru versus Bolívia", "Contrastes e Confrontos"
        };

        String[] categories = {
            "Literatura Brasileira", "Romance", "Poesia", "Crônica", "Conto",
            "Ensaio", "Teatro", "Literatura Infantil", "Literatura Juvenil", "Biografia",
            "História", "Filosofia", "Sociologia", "Antropologia", "Psicologia",
            "Ciência Política", "Economia", "Direito", "Educação", "Arte",
            "Música", "Cinema", "Arquitetura", "Design", "Comunicação",
            "Jornalismo", "Linguística", "Literatura Comparada", "Estudos Culturais", "Geografia"
        };

        // Real periodic titles
        String[] periodicTitles = {
            "Revista Brasileira de Literatura", "Estudos Avançados", "Novos Estudos CEBRAP", "Revista USP", "Ciência Hoje",
            "Pesquisa FAPESP", "Revista de História", "Tempo Social", "Lua Nova", "Dados",
            "Revista Brasileira de Ciências Sociais", "Cadernos Pagu", "Horizontes Antropológicos", "Mana", "Vibrant",
            "Revista de Antropologia", "Etnográfica", "Anuário Antropológico", "Revista Brasileira de História", "História Oral",
            "Revista de Filosofia Aurora", "Trans/Form/Ação", "Kriterion", "Síntese", "Veritas",
            "Educação & Realidade", "Cadernos de Pesquisa", "Revista Brasileira de Educação", "Educação e Sociedade", "Pro-Posições",
            "Revista de Economia Política", "Economia Aplicada", "Revista Brasileira de Economia", "Pesquisa e Planejamento Econômico", "Nova Economia",
            "Revista Direito GV", "Revista da Faculdade de Direito USP", "Sequência", "Revista de Informação Legislativa", "Cadernos Adenauer",
            "Comunicação & Sociedade", "Intercom", "Galáxia", "E-Compós", "Revista FAMECOS",
            "Arquitextos", "Revista Projeto", "AU - Arquitetura e Urbanismo", "Vitruvius", "Pós - Revista do Programa de Pós-Graduação em Arquitetura"
        };

        String[] periodicTypes = {"Científico", "Acadêmico", "Cultural", "Informativo", "Especializado", "Técnico"};

        // Real names for users
        String[] firstNames = {
            "João", "Maria", "José", "Ana", "Pedro", "Mariana", "Carlos", "Paula", "Rafael", "Fernanda",
            "Gustavo", "Beatriz", "Bruno", "Larissa", "Diego", "Camila", "André", "Aline", "Felipe", "Juliana",
            "Lucas", "Gabriela", "Matheus", "Amanda", "Thiago", "Letícia", "Ricardo", "Natália", "Rodrigo", "Isabella",
            "Daniel", "Carolina", "Marcelo", "Vanessa", "Leonardo", "Patrícia", "Fábio", "Priscila", "Eduardo", "Cláudia",
            "Alexandre", "Simone", "Guilherme", "Mônica", "Fernando", "Sandra", "Henrique", "Cristina", "Vitor", "Denise",
            "Roberto", "Silvia", "Antonio", "Regina", "Marcos", "Eliane", "Paulo", "Rosana", "Sérgio", "Luciana"
        };

        String[] lastNames = {
            "Silva", "Santos", "Oliveira", "Souza", "Rodrigues", "Ferreira", "Alves", "Pereira", "Lima", "Gomes",
            "Ribeiro", "Carvalho", "Almeida", "Lopes", "Soares", "Fernandes", "Vieira", "Barbosa", "Rocha", "Dias",
            "Monteiro", "Mendes", "Cardoso", "Reis", "Araújo", "Cavalcanti", "Nascimento", "Moura", "Castro", "Pinto",
            "Machado", "Freitas", "Campos", "Correia", "Teixeira", "Martins", "Costa", "Cunha", "Moreira", "Nunes",
            "Ramos", "Andrade", "Azevedo", "Farias", "Melo", "Batista", "Nogueira", "Miranda", "Brito", "Morais"
        };

        String[] courses = {
            "Ciência da Computação", "Engenharia Civil", "Direito", "Medicina", "Administração",
            "Psicologia", "Arquitetura e Urbanismo", "Engenharia Elétrica", "Jornalismo", "Design",
            "História", "Geografia", "Letras", "Matemática", "Física",
            "Química", "Biologia", "Filosofia", "Sociologia", "Pedagogia",
            "Enfermagem", "Odontologia", "Veterinária", "Farmácia", "Fisioterapia",
            "Engenharia Mecânica", "Engenharia de Produção", "Economia", "Contabilidade", "Marketing"
        };

        String[] departments = {
            "Ciências Exatas", "Ciências Humanas", "Ciências Biológicas", "Engenharia", "Artes e Comunicação",
            "Ciências Sociais Aplicadas", "Linguística e Literatura", "História e Geografia", "Matemática e Estatística", "Física e Astronomia",
            "Química", "Medicina", "Odontologia", "Psicologia", "Filosofia",
            "Sociologia", "Antropologia", "Ciência Política", "Economia", "Administração"
        };

        List<Author> authors = new ArrayList<>();
        List<Publisher> publishers = new ArrayList<>();
        Set<String> usedAuthorNames = new HashSet<>();
        Set<String> usedPublisherNames = new HashSet<>();
        Set<String> usedBookTitles = new HashSet<>();
        Set<String> usedPeriodicTitles = new HashSet<>();
        Set<String> usedUserNames = new HashSet<>();

        int count = Math.min(50, realAuthors.length);

        // Create unique authors
        for (int i = 0; i < count && i < realAuthors.length; i++) {
            String authorName = realAuthors[i];
            if (!usedAuthorNames.contains(authorName)) {
                Author a = new Author();
                a.setNome(authorName);
                a.setNacionalidade(nationalities[rnd.nextInt(nationalities.length)]);
                authors.add(authorRepo.save(a));
                usedAuthorNames.add(authorName);
            }
        }

        // Create unique publishers
        for (int i = 0; i < count && i < realPublishers.length; i++) {
            String pubName = realPublishers[i];
            if (!usedPublisherNames.contains(pubName)) {
                Publisher p = new Publisher();
                p.setNome(pubName);
                p.setCidade(cities[rnd.nextInt(cities.length)]);
                publishers.add(pubRepo.save(p));
                usedPublisherNames.add(pubName);
            }
        }

        // Create unique books
        int bookCount = 0;
        for (String title : realBookTitles) {
            if (bookCount >= count) break;
            if (!usedBookTitles.contains(title)) {
                Book b = new Book();
                b.setTitulo(title);
                b.setIdioma(rnd.nextBoolean() ? "PT-BR" : "PT");
                b.setAnoEdicao(1850 + rnd.nextInt(175)); // 1850-2024
                b.setCategoriaCientifica(categories[rnd.nextInt(categories.length)]);

                // Assign 1-3 random authors
                int numAuthors = 1 + rnd.nextInt(Math.min(3, authors.size()));
                List<Author> bookAuthors = new ArrayList<>();
                Set<Integer> usedAuthorIndices = new HashSet<>();
                for (int j = 0; j < numAuthors; j++) {
                    int authorIndex;
                    do {
                        authorIndex = rnd.nextInt(authors.size());
                    } while (usedAuthorIndices.contains(authorIndex));
                    usedAuthorIndices.add(authorIndex);
                    bookAuthors.add(authors.get(authorIndex));
                }
                b.setAutores(bookAuthors);

                // Assign random publisher
                if (!publishers.isEmpty()) {
                    b.set(publishers.get(rnd.nextInt(publishers.size())));
                }

                bookRepo.save(b);
                usedBookTitles.add(title);
                bookCount++;
            }
        }

        System.out.println("BulkDataLoader: completed seeding " + authors.size() + " authors, " + 
                          publishers.size() + " publishers, " + bookCount + " books.");

        // Create unique periodics
        int periodicCount = 0;
        for (String title : periodicTitles) {
            if (periodicCount >= count) break;
            if (!usedPeriodicTitles.contains(title)) {
                com.example.restservice.model.Periodic per = new com.example.restservice.model.Periodic();
                per.setTitulo(title);
                per.setIdioma("PT-BR");
                per.setAnoEdicao(1990 + rnd.nextInt(35)); // 1990-2024
                per.setTipo(periodicTypes[rnd.nextInt(periodicTypes.length)]);
                
                if (!publishers.isEmpty()) {
                    per.set(publishers.get(rnd.nextInt(publishers.size())));
                }
                
                periodicRepo.save(per);
                usedPeriodicTitles.add(title);
                periodicCount++;
            }
        }

        System.out.println("BulkDataLoader: completed seeding " + periodicCount + " periodics.");

        // Helper to set CPF on entities
        java.util.function.BiConsumer<Object,String> setCpfOn = (obj, cpfVal) -> {
            try {
                java.lang.reflect.Method m = obj.getClass().getMethod("setCpf", String.class);
                m.invoke(obj, cpfVal);
            } catch (NoSuchMethodException nsme) {
                // ignore
            } catch (Exception e) {
                // ignore
            }
            try {
                java.lang.reflect.Method m2 = obj.getClass().getMethod("setCpfId", String.class);
                m2.invoke(obj, cpfVal);
            } catch (NoSuchMethodException nsme) {
                // ignore
            } catch (Exception e) {
                // ignore
            }
        };

        // Create unique students
        for (int i = 0; i < count; i++) {
            String firstName = firstNames[rnd.nextInt(firstNames.length)];
            String lastName = lastNames[rnd.nextInt(lastNames.length)];
            String fullName = firstName + " " + lastName;
            
            // Ensure uniqueness
            int counter = 1;
            String uniqueName = fullName;
            while (usedUserNames.contains(uniqueName)) {
                uniqueName = fullName + " " + counter++;
            }
            usedUserNames.add(uniqueName);
            
            Student s = new Student();
            s.setNome(uniqueName);
            String cpfVal = String.format("%011d", 10000000000L + i);
            setCpfOn.accept(s, cpfVal);
            s.setContato(firstName.toLowerCase() + "." + lastName.toLowerCase() + i + "@estudante.edu.br");
            s.setCurso(courses[rnd.nextInt(courses.length)]);
            studentRepo.save(s);
        }

        // Create unique professors
        for (int i = 0; i < count; i++) {
            String firstName = firstNames[rnd.nextInt(firstNames.length)];
            String lastName = lastNames[rnd.nextInt(lastNames.length)];
            String fullName = "Prof. " + firstName + " " + lastName;
            
            // Ensure uniqueness
            int counter = 1;
            String uniqueName = fullName;
            while (usedUserNames.contains(uniqueName)) {
                uniqueName = fullName + " " + counter++;
            }
            usedUserNames.add(uniqueName);
            
            Professor p = new Professor();
            p.setNome(uniqueName);
            String cpfVal = String.format("%011d", 20000000000L + i);
            setCpfOn.accept(p, cpfVal);
            p.setContato(firstName.toLowerCase() + "." + lastName.toLowerCase() + "@professor.edu.br");
            p.setDepartamento(departments[rnd.nextInt(departments.length)]);
            p.setFormacao(rnd.nextBoolean() ? "Doutorado" : "Mestrado");
            p.setAreaAtuacao(categories[rnd.nextInt(categories.length)]);
            professorRepo.save(p);
        }

        // Create unique staff
        for (int i = 0; i < count; i++) {
            String firstName = firstNames[rnd.nextInt(firstNames.length)];
            String lastName = lastNames[rnd.nextInt(lastNames.length)];
            String fullName = firstName + " " + lastName + " (Staff)";
            
            // Ensure uniqueness
            int counter = 1;
            String uniqueName = fullName;
            while (usedUserNames.contains(uniqueName)) {
                uniqueName = firstName + " " + lastName + " (Staff " + counter++ + ")";
            }
            usedUserNames.add(uniqueName);
            
            Staff st = new Staff();
            st.setNome(uniqueName);
            String cpfVal = String.format("%011d", 30000000000L + i);
            setCpfOn.accept(st, cpfVal);
            st.setContato(firstName.toLowerCase() + "." + lastName.toLowerCase() + "@funcionario.edu.br");
            st.setDepartamento(rnd.nextBoolean() ? "Biblioteca" : "Administração");
            staffRepo.save(st);
        }

        System.out.println("BulkDataLoader: completed seeding students, professors and staff (" + count + " each).");
        System.out.println("BulkDataLoader: All data seeded with realistic Brazilian literature and academic information!");
    }
}