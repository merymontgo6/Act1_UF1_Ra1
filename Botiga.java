import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Botiga {
    private static ArrayList<Client> encarrecs = new ArrayList<>();
    private static final File dir = new File ("C:\\Users\\karolayn\\DAM\\M06\\Act1_UF1_Ra1");

    public static void main(String[] args) throws Exception {
        
        boolean sortir = false;

        while (!sortir) {
             System.out.println("Què vols fer?\n1. Generar un nou encàrrec\n2. Mostrar encàrrec\n3. Sortir");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try{
               
                String oo = reader.readLine();
                int opcio = Integer.parseInt(oo);

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
            } catch (IOException e) {
                throw new RuntimeException(e);
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
                generarFitxerCSV(nouClient);
                break;
            case 3:
                generarFitxerBinari(nouClient, dataEncarrec, articles);
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
        String nomCli = client.getNomClient().replace(" ", "_");
        String fileName = "C:\\Users\\karolayn\\DAM\\M06\\Act1_UF1_Ra1\\encarrecs_albara_client_" + nomCli + "_" + System.currentTimeMillis() + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
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
        }
    }

    public static void generarFitxerCSV(Client client) throws IOException {
        String nomCli = client.getNomClient().replace(" ", "_");
        String fileName = "C:\\Users\\karolayn\\DAM\\M06\\Act1_UF1_Ra1\\encarrecs_csv_client_" + nomCli + "_" + System.currentTimeMillis() + ".csv";
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("Nom Client;Telèfon Client;Data Encarrec;Quantitat;Unitats;Article");
            writer.newLine();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            writer.write(client.getNomClient() + ";" + client.getTelClient() + ";" + client.getDataEncarrec().format(formatter) + ";");
        
            for (Article article : client.getArticles()) {
                writer.write(article.getNomArticle() + ";" + article.getQuantitat() + ";" + article.getUnitats() + ";");
            }
        
            writer.newLine();
            System.out.println("Fitxer CSV generat correctament.");
        } catch (IOException e) {
        System.out.println("Error al generar el fitxer CSV.");
        }
    }

    public static void generarFitxerBinari(Client client, String dataEncarrec, ArrayList<Article> articles) throws IOException {
        String nomCli = client.getNomClient().replace(" ", "_");
        String fileName = "C:\\Users\\karolayn\\DAM\\M06\\Act1_UF1_Ra1\\encarrecs_binari_client_" + nomCli + "_" + System.currentTimeMillis() + ".dat";
        
        try {
            FileOutputStream fileStr1 = new FileOutputStream(fileName);
            DataOutputStream str1 = new DataOutputStream(fileStr1); 
			//se pasa el cliente la fecha y la lista
            str1.writeUTF(client.getNomClient());
            str1.writeUTF(dataEncarrec);
            for(Article article : articles) {
                str1.writeUTF(article.toString());
            }
            str1.close(); 
            fileStr1.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error al generar el fitxer binari.");
            e.printStackTrace();
        } catch (IOException e) {

        }
    }

    public static void mostrarEncarrecs() {
        if (encarrecs.isEmpty()) {
            System.out.println("No hi ha encàrrecs per mostrar.");
        } else {
            
        }
    }

}
