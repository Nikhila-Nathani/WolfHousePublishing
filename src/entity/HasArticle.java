package entity;

public class HasArticle {
    Integer periodicPublicationIdn;
    Integer articleId;

    public Integer getPeriodicPublicationIdn() {
        return periodicPublicationIdn;
    }

    public void setPeriodicPublicationIdn(Integer periodicPublicationIdn) {
        this.periodicPublicationIdn = periodicPublicationIdn;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public HasArticle(Integer periodicPublicationIdn, Integer articleId) {
        this.periodicPublicationIdn = periodicPublicationIdn;
        this.articleId = articleId;
    }
}
