package input.types;

public final class CountryMap {
    private final String[][] matrix;

    public CountryMap(String[][] matrix){
        this.matrix = matrix;
    }

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
