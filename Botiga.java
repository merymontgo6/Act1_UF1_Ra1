import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Botiga {
    private static final ArrayList<Client> encarrecs = new ArrayList<>();
    private static File dir = new File ("C:\\Users\\karolayn\\DAM\\M06\\Act1_UF1_Ra1");

    public static void main(String[] args) throws Exception {
        
        boolean sortir = false;

        while (!sortir) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
                
                System.out.println("Què vols fer?\n1. Generar un nou encàrrec\n2. Mostrar encàrrec\n3. Sortir");
                int opcio = Integer.parseInt(reader.readLine());

            switch (opcio) {
                    case 1: 
                        infoClient(reader);
                        break;
                    case 2: 
                        mostrarEncarrecs();
                        break;
                    case 3: 
                        sortir = true;
                        break;
                    default: 
                        System.out.println("Opció no vàlida. Si us plau, tria una opció correcta.");
                        break;
                }
            }
        }
    }

    public static Integer infoClient(BufferedReader reader) throws IOException {
        System.out.println("Introdueix el nom del client:");
        String nomClient = reader.readLine();
        System.out.println("Introdueix el telèfon del client:");
        Integer telClient = Integer.parseInt(reader.readLine());

        System.out.println("Introdueix la data (dd/MM/aaaa):");
        String dataEncarrec = reader.readLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataLliurament = LocalDate.parse(dataEncarrec, formatter);
        
        ArrayList<Article> articles = demanarArticles(reader);

        Client nouClient = new Client(nomClient, telClient, dataLliurament, articles);
        encarrecs.add(nouClient);
        System.out.println("Encàrrec generat amb èxit!");

        System.out.println("Com es vol generar el fitxer?\n1. Generar un fitxer de text amb format albarà\n2. Generar un fitxer de text csv\n3. Generar un fitxer binari");
        int opcio = Integer.parseInt(reader.readLine());
        switch (opcio) {
            case 1:
                generarFitxerAlbara(nouClient);
                break;
            case 2:
                // generarFitxerCSV(nouClient);
                break;
            case 3:
                // generarFitxerBinari(nouClient);
                break;
            default:
                System.out.println("Opció no vàlida. Si us plau, tria una opció correcta.");
                break;
        }
        return opcio;
    }

    public static ArrayList<Article> demanarArticles(BufferedReader reader) throws IOException {
        ArrayList<Article> articles = new ArrayList<>();
        boolean afegirMesArticles = true;

        while (afegirMesArticles) {
            System.out.println("Introdueix el nom de l'article:");
            String nomArticle = reader.readLine();
            System.out.println("Introdueix la quantitat:");
            int quantitat = Integer.parseInt(reader.readLine());
            System.out.println("Introdueix les unitats:");
            String unitats = reader.readLine();
            
            Article nouArticle = new Article(nomArticle, quantitat, unitats);
            articles.add(nouArticle);

            System.out.println("Voleu afegir més articles? (si/no)");
            String resposta = reader.readLine();
            if (!resposta.equalsIgnoreCase("si")) {
                afegirMesArticles = false;
            }
        }
        return articles;
    }

    public static void generarFitxerAlbara(Client client)  throws IOException {
        File f1 = new File (dir, "albara.txt");
        f1.createNewFile();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(f1))) {
            writer.write("Nom del client: " + client.getNomClient());
            writer.newLine();
            writer.write("Telefon del client: " + client.getTelClient());
            writer.newLine();
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            writer.write("Data de l'encarrec: " + client.getDataEncarrec().format(formatter));
            writer.newLine();
            writer.newLine();
            
            writer.write("Quantitat\tUnitats\t\tArticle");
            writer.newLine();
            writer.write("=============== ========== ===============");
            writer.newLine();

            for (Article article : client.getArticles()) {
                writer.write(String.format("%-15.1f %-10s %-15s", (float) article.getQuantitat(), article.getUnitats(), article.getNomArticle()));
                writer.newLine();
            }
            writer.newLine();
            System.out.println("Albarà generat correctament.");
        } catch (IOException e) {
            System.out.println("Error al generar l'albarà.");
            e.printStackTrace();
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
