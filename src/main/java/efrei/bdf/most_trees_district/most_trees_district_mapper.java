package efrei.bdf.most_trees_district;

// WILLIAMU Vaihau - GHEYOUCHE Eya

// HADOOP
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

// JAVA
import java.io.IOException;

public class most_trees_district_mapper extends Mapper<Object, Text, IntWritable, IntWritable>{
    private int line = 0;

    public void map(Object key, Text value, Mapper.Context context) throws IOException, InterruptedException {
        if (line != 0){ // Skip Header
            try{

                String[] fields = value.toString().split(";");

                int district = Integer.parseInt(fields[1]); // Get the kind

                int counter = 1; // counter

                context.write(new IntWritable(district), new IntWritable(counter) ); // Write both of them in the context

            }catch(NumberFormatException ex) {
                ex.printStackTrace();
            }

        }

        line++;
    }
}

