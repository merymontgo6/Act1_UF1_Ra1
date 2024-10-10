import java.time.LocalDate;
import java.util.ArrayList;

public class Client {
    private String nomClient;
    private Integer telClient;
    private LocalDate dataEncarrec;
    private ArrayList<Article> articles;

    public Client(String nomClient, Integer telClient, LocalDate dataEncarrec, ArrayList<Article> articles) {
        this.nomClient = nomClient;
        this.telClient = telClient;
        this.dataEncarrec = dataEncarrec;
        this.articles = articles;
    }

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

    public void setNomArticle(String nomClient) {
        this.nomClient = nomClient;
    }

    public void settelClient(Integer telClient) {
        this.telClient = telClient;
    }

    public void setDataEncarrec(LocalDate dataEncarrec) {
        this.dataEncarrec = dataEncarrec;
    }

    public void setArticles(ArrayList articles) {
        this.articles = articles;
    }

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
