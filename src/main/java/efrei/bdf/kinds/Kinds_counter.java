package efrei.bdf.kinds;

// WILLIAMU Vaihau - GHEYOUCHE Eya

// HADOOP
import efrei.bdf.default_reducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.log4j.BasicConfigurator;

public class Kinds_counter {
    // A MapReduce job that calculates the number of trees of each kind in trees.csv
    public static void main(String[] args) throws Exception{
        Configuration conf = new Configuration();

        String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();

        // Show wiki on how to use the command if the number of necessary parameters are not enough
        if (otherArgs.length < 2) {
            System.err.println("Usage: kinds <in> <out>");
            System.err.println("<in>  : must be a file with the same syntax as trees.csv or trees.csv itself");
            System.err.println("<out> : name of the folder to write the result of this program (must not exist)");
            System.exit(2);
        }

        BasicConfigurator.configure();

        //Create a new Jar and set the driver class(this class) as the main class of jar
        Job job = Job.getInstance(conf, "kinds trees"); // Job name
        job.setJarByClass(Kinds_counter.class);

        // Set Mapper to species_mapper
        job.setMapperClass(kinds_mapper.class);

        // Set Combiner and Reducer to default_reducer : no particular function to apply
        job.setCombinerClass(default_reducer.class);
        job.setReducerClass(default_reducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
