import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Botiga {
    private static ArrayList<Client> encarrecs = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        
        boolean sortir = false;

        while (!sortir) {
            Scanner scanner = new Scanner(System.in);
            
            System.out.println("Què vols fer?\n1. Generar un nou encàrrec\n2. Mostrar encàrrec\n3. Sortir");
            int opcio = scanner.nextInt();
            scanner.nextLine();

            switch (opcio) {
                case 1: infoClient();
                        if (opcio ==1){
                            fitxerAlbara();
                        }
                case 2: mostrarEncarrecs();
                case 3: sortir = true;
                default: System.out.println("Opció no vàlida. Si us plau, tria una opció correcta.");
            }scanner.close();
        }
    }
    public static Integer infoClient() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introdueix el nom del client:");
        String nomClient = scanner.nextLine();
        System.out.println("Introdueix el telèfon del client:");
        Integer telClient = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Introdueix la data (dd/MM/aaaa):");
        String dataEncarrec = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataLliurament = LocalDate.parse(dataEncarrec, formatter);
        
        ArrayList<Article> articles = demanarArticles(scanner);

        Client nouClient = new Client(nomClient, telClient, dataLliurament, articles);
        encarrecs.add(nouClient);
        System.out.println("Encàrrec generat amb èxit!");

        System.out.println("Com es vol generar el fitxer?\n1. Generar un fitxer de text amb format albarà\n2. Generar un fitxer de text csv\n3. Generar un fitxer binari");
        int opcio = scanner.nextInt();
        return opcio;
    }

    public static ArrayList<Article> demanarArticles(Scanner scanner) {
        ArrayList<Article> articles = new ArrayList<>();
        boolean afegirMesArticles = true;

        while (afegirMesArticles) {
            System.out.println("Introdueix el nom de l'article:");
            String nomArticle = scanner.nextLine();
            System.out.println("Introdueix la quantitat:");
            int quantitat = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Introdueix les unitats:");
            String unitats = scanner.nextLine();
            
            // Crear un nou article i afegir-lo a la llista
            Article nouArticle = new Article(nomArticle, quantitat, unitats);
            articles.add(nouArticle);

            System.out.println("Voleu afegir més articles? (si/no)");
            String resposta = scanner.nextLine();
            if (!resposta.equalsIgnoreCase("si")) {
                afegirMesArticles = false; // Atura la petició d'articles
            }
        }
        return articles;
    }

    public static void fitxerAlbara(){
        String lloc = "C:\\Users\\karolayn\\DAM\\M06\\Act1_UF1_Ra1";
        try {
            FileOutputStream fit1 = new FileOutputStream(lloc);
            DataOutput str1 = new DataOutputStream(fit1);

            
        } catch (FileNotFoundException e) {
            System.out.println("El fitxer no existeix");
        } catch (IOException e) {
            
        }
    }

    public static void mostrarEncarrecs() {
        if (encarrecs.isEmpty()) {
            System.out.println("No hi ha encàrrecs per mostrar.");
        } else {
            for (Client client : encarrecs) {
                System.out.println(client.toString());
            }
        }
    }
}
