
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Permutations {

    public static void main(String[] args) {
        String csvFile = args[0];

        BufferedReader br = null;
        String line = "";

        List<String[]> lines = new ArrayList<String[]>();

        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // splitting into rows
                String ar[] = line.split(";");
                StringBuilder stringBuilder = new StringBuilder();
                for (int k = 0; k < ar.length; k++) {
                    stringBuilder.append(ar[k]);
                }
                // splitting into cols
                String f = stringBuilder.toString();
                lines.add(f.split(","));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (br != null) {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // convert our list to a String array.
        String[][] array = new String[lines.size()][0];
        lines.toArray(array);

        getPermutations(array, lines.size());
    }

    public static void getPermutations(String arr[][], int r) {

        // number of arrays
        int n = r;

        // maintaining indexes for each array
        int indexes[] = new int[n];

        // initialize with 1st array index
        for (int i = 0; i < n; i++) {
            indexes[i] = 0;
        }

        while (true) {

            // printing the current combinations
            for (int i = 0; i < n; i++) {
                System.out.print(arr[i][indexes[i]]);
            }
            System.out.print(", ");

            // for check the 2d matrix from rightmost rows
            int next = n - 1;

            // find rightmost arr that has more elements left after current element
            while (next >= 0 && (indexes[next] + 1 >= arr[next].length)) {
                next--;
            }

            // if there is no array with no more elements then return
            if (next < 0) {
                return;
            }
            // if we find an array with more element
            // move to next element in that array
            indexes[next]++;

            for (int i = next + 1; i < n; i++) {
                indexes[i] = 0;
            }
        }
    }
}