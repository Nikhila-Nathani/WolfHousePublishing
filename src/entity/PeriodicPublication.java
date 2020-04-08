package entity;

public class PeriodicPublication {

    private Publication publication;
    private Periodicity periodicity;


    public PeriodicPublication(Publication periodicPublication, Periodicity periodicity) {
        this.publication = periodicPublication;
        this.periodicity = periodicity;
    }



    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }


    public Periodicity getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(Periodicity periodicity) {
        this.periodicity = periodicity;
    }

    @Override
    public String toString() {
        return "\t"+publication.getPublicationId()+"\t"+publication.getPublicationTitle()+"\t"+publication.getPublicationDate()+"\t"+publication.getPrice()+"\t"+
                publication.getPublicationTopic().getPublicationTopicName()+"\t"+periodicity.getPeriodicityName();
    }
}

