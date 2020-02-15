
package entits;

import java.util.Objects;

/**
 *
 * @author Dreamer
 */

public class Geste {
    private int id;
    private String nom_geste;
    private String mois_geste;
    private String desc_geste;
    private String image_geste;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_geste() {
        return nom_geste;
    }

    public void setNom_geste(String nom_geste) {
        this.nom_geste = nom_geste;
    }

    public String getMois_geste() {
        return mois_geste;
    }

    public void setMois_geste(String mois_geste) {
        this.mois_geste = mois_geste;
    }

    public String getDesc_geste() {
        return desc_geste;
    }

    public void setDesc_geste(String desc_geste) {
        this.desc_geste = desc_geste;
    }

    public String getImage_geste() {
        return image_geste;
    }

    public void setImage_geste(String image_geste) {
        this.image_geste = image_geste;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.id;
        hash = 67 * hash + Objects.hashCode(this.nom_geste);
        hash = 67 * hash + Objects.hashCode(this.mois_geste);
        hash = 67 * hash + Objects.hashCode(this.desc_geste);
        hash = 67 * hash + Objects.hashCode(this.image_geste);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Geste other = (Geste) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nom_geste, other.nom_geste)) {
            return false;
        }
        if (!Objects.equals(this.mois_geste, other.mois_geste)) {
            return false;
        }
        if (!Objects.equals(this.desc_geste, other.desc_geste)) {
            return false;
        }
        if (!Objects.equals(this.image_geste, other.image_geste)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Geste{" + "id=" + id + ", nom_geste=" + nom_geste + ", mois_geste=" + mois_geste + ", desc_geste=" + desc_geste + ", image_geste=" + image_geste + '}';
    }

    public Geste() {
    }

    public Geste(String nom_geste, String mois_geste, String desc_geste, String image_geste) {
        this.nom_geste = nom_geste;
        this.mois_geste = mois_geste;
        this.desc_geste = desc_geste;
        this.image_geste = image_geste;
    }
    
    
    
    
    
}

