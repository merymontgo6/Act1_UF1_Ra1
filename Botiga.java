import java.io.BufferedReader;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Botiga {
    private static final ArrayList<Client> encarrecs = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        
        boolean sortir = false;

        while (!sortir) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
                
                System.out.println("Què vols fer?\n1. Generar un nou encàrrec\n2. Mostrar encàrrec\n3. Sortir");
                int opcio = Integer.parseInt(reader.readLine());

            switch (opcio) {
                    case 1: 
                        infoClient(reader);
                        if (opcio == 1) {
                            fitxerAlbara();
                        }
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

    public static void fitxerAlbara(){
        String lloc = "C:\\Users\\karolayn\\DAM\\M06\\Act1_UF1_Ra1";
        try {
            FileOutputStream fit1 = new FileOutputStream(lloc);
            DataOutput str1 = new DataOutputStream(fit1);

            
        } catch (FileNotFoundException e) {
            System.out.println("El fitxer no existeix");
        } catch (IOException e) {
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
