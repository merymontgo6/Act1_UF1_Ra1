public class Article {
    private String nomArticle;
    private int quantitat;
    private String unitats;

    // Constructor
    public Article(String nomArticle, int quantitat, String unitats) {
        this.nomArticle = nomArticle;
        this.quantitat = quantitat;
        this.unitats = unitats;
    }
    
    public String getNomArticle() {
        return nomArticle;
    }

    public int getQuantitat() {
        return quantitat;
    }

    public String getUnitats() {
        return unitats;
    }

    @Override
    public String toString() {
        return "Article: " + nomArticle + ", Quantitat: " + quantitat + " " + unitats;
    }
}
