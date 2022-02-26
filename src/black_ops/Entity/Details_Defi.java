
package black_ops.Entity;


public class Details_Defi {
    private int id_Statistique ;
    private int EquipeA;
    private String imgScore ;
    private int EquipeB ;
    private String Score_finale;
    private int id_defi;

    public Details_Defi() {
    }

    public int getId_Statistique() {
        return id_Statistique;
    }

    public void setId_Statistique(int id_Statistique) {
        this.id_Statistique = id_Statistique;
    }

    public int getEquipeA() {
        return EquipeA;
    }

    public void setEquipeA(int EquipeA) {
        this.EquipeA = EquipeA;
    }

    public String getImgScore() {
        return imgScore;
    }

    public void setImgScore(String imgScore) {
        this.imgScore = imgScore;
    }

    public int getEquipeB() {
        return EquipeB;
    }

    public void setEquipeB(int EquipeB) {
        this.EquipeB = EquipeB;
    }

    public String getScore_finale() {
        return Score_finale;
    }

    public void setScore_finale(String Score_finale) {
        this.Score_finale = Score_finale;
    }

    public int getId_defi() {
        return id_defi;
    }

    public void setId_defi(int id_defi) {
        this.id_defi = id_defi;
    }

    public Details_Defi( int EquipeA, String imgScore, int EquipeB, String Score_finale, int id_defi) {
       
        this.EquipeA = EquipeA;
        this.imgScore = imgScore;
        this.EquipeB = EquipeB;
        this.Score_finale = Score_finale;
        this.id_defi = id_defi;
    }

    @Override
    public String toString() {
        return "Details_Defi{" + "id_Statistique=" + id_Statistique + ", EquipeA=" + EquipeA + ", imgScore=" + imgScore + ", EquipeB=" + EquipeB + ", Score_finale=" + Score_finale + ", id_defi=" + id_defi + '}';
    }

    public Details_Defi(int id_Statistique) {
        this.id_Statistique = id_Statistique;
    }

    public Details_Defi(int id_Statistique, int EquipeA, String imgScore, int EquipeB, String Score_finale, int id_defi) {
        this.id_Statistique = id_Statistique;
        this.EquipeA = EquipeA;
        this.imgScore = imgScore;
        this.EquipeB = EquipeB;
        this.Score_finale = Score_finale;
        this.id_defi = id_defi;
    }
    
    
    
}
