package efrei.bdf;

// WILLIAMU Vaihau - GHEYOUCHE Eya

// HADOOP
import org.apache.hadoop.util.ProgramDriver;

// EFREI.BDF
import efrei.bdf.districtsTrees.DistrictsTrees;
import efrei.bdf.height_sorter.Height_sorter;
import efrei.bdf.kinds.Kinds_counter;
import efrei.bdf.most_trees_district.Most_trees_district;
import efrei.bdf.searchOldestTreeDistrict.Search_oldest_tree;
import efrei.bdf.species.Species_counter;
import efrei.bdf.tallest.Tallest;

public class AppDriver {
    public AppDriver() {
    }

    public static void main(String[] argv) {
        int exitCode = -1;
        ProgramDriver programDriver = new ProgramDriver();

        try {
            programDriver.addClass("counter", WordCount.class, "A MapReduce job that counts the words in the input files.");
            programDriver.addClass("dts", DistrictsTrees.class, "A MapReduce job that displays the list of distinct containing trees and number in trees.csv");
            programDriver.addClass("kinds", Kinds_counter.class, "A MapReduce job that calculates the number of trees of each kinds in trees.csv");
            programDriver.addClass("species", Species_counter.class, "A MapReduce job that displays the list of different species trees in trees.csv");
            programDriver.addClass("tallest", Tallest.class, "A MapReduce job that get the height of the tallest tree of each kind in trees.csv");
            programDriver.addClass("sortbyheight", Height_sorter.class, "A MapReduce job that sort the trees from the smallest to the largest in trees.csv");
            programDriver.addClass("oldestTreeDistrict", Search_oldest_tree.class, "A MapReduce job that get district(s) which have the oldest trees in trees.csv");
            programDriver.addClass("mostTreesDistrict", Most_trees_district.class, " A MapReduce job that get the district containing the most trees in trees.csv");

            exitCode = programDriver.run(argv);
        } catch (Throwable var4) {
            var4.printStackTrace();
        }

        System.exit(exitCode);
    }
}
