import java.time.LocalDate;
import java.util.ArrayList;

public class Client {
    private String nomClient;
    private Integer telClient;
    private LocalDate dataEncarrec;
    private ArrayList<Article> articles;

    // Constructor
    public Client(String nomClient, Integer telClient, LocalDate dataEncarrec, ArrayList<Article> articles) {
        this.nomClient = nomClient;
        this.telClient = telClient;
        this.dataEncarrec = dataEncarrec;
        this.articles = articles;
    }

    // Getters
    public String getNomClient() {
        return nomClient;
    }

    public Integer getTelClient() {
        return telClient;
    }

    public LocalDate getDataEncarrec() {
        return dataEncarrec;
    }

    public ArrayList<Article> getArticles() {
        return articles;
    }

    //Mètode per mostrar l'encàrrec
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nom del client: ").append(nomClient).append("\n");
        sb.append("Telèfon: ").append(telClient).append("\n");
        sb.append("Data de lliurament: ").append(dataEncarrec).append("\n");
        sb.append("Articles:\n");
        for (Article article : articles) {
            sb.append(article.toString()).append("\n");
        }
        return sb.toString();
    }
}
