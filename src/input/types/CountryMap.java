package input.types;

/**
 * Data-holder klasa koja reprezentuje mapu drzave u kojoj se sprovodi simulacija. Sastoji se iz matrice sa imenima gradova.
 * @author Nikola Markovic
 */
public final class CountryMap {
    private final String[][] matrix;

    /**
     * @param matrix Matrica imena gradova
     */
    public CountryMap(String[][] matrix){
        this.matrix = matrix;
    }

    /**
     * Metoda koja efektivno "poravnava" matricu imena gradova u jednodimenzionalan niz imena gradova.
     * @return String[] niz imena gradova
     * @author Nikola Markovic
     */
    public String[] getCityNames(){
        String[] res = new String[matrix.length * matrix[0].length];

        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[0].length; j++){
                res[i * matrix[0].length + j] = matrix[i][j];
            }
        }

        return res;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[0].length; j++){
                sb.append(matrix[i][j]).append(" ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
