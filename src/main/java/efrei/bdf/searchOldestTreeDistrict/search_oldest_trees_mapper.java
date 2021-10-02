package efrei.bdf.searchOldestTreeDistrict;

// WILLIAMU Vaihau - GHEYOUCHE Eya

// HADOOP
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

// JAVA
import java.io.IOException;

public class search_oldest_trees_mapper extends Mapper<Object, Text, IntWritable, IntWritable>{

    private int line = 0;

public void map(Object key, Text value, Mapper.Context context) throws IOException, InterruptedException {
        if (line != 0){ // Skip Header
            try{

                String[] fields = value.toString().split(";");

                int district = Integer.parseInt(fields[1]); // Get the kind

                int year = Integer.parseInt(fields[5]); // Get the year

                context.write(new IntWritable(year), new IntWritable(district)); // Write both of them in the context

            }catch(NumberFormatException ex){

            }

        }

        line++;
        }
}
