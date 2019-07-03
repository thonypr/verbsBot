public class Verb {

    private char letter;
    private String infinitive;
    private String pastSimple;
    private String pastParticiple;
    private String translation;

    public Verb (char letter, String infinitive, String pastSimple, String pastParticiple, String translation) {
        this.letter = letter;
        this.infinitive = infinitive;
        this.pastSimple = pastSimple;
        this.pastParticiple = pastParticiple;
        this.translation = translation;
    }

    public char getLetter() {return this.letter;}
    public String getInfinitive() {return this.infinitive;}
    public String getPastSimple() {return this.pastSimple;}
    public String getPastParticiple() {return this.pastParticiple;}
    public String getTranslation() {return this.translation;}
}
