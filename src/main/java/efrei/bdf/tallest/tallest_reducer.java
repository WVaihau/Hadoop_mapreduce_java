package efrei.bdf.tallest;

// WILLIAMU Vaihau - GHEYOUCHE Eya

// HADOOP
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

// JAVA
import java.io.IOException;

public class tallest_reducer extends Reducer<Text, FloatWritable, Text, FloatWritable> {

    public void reduce(Text kind, Iterable<FloatWritable> heights, Context context) throws IOException,InterruptedException{
        float max = 0;

        for(FloatWritable height : heights){
         if( height.get() > max){
             max = height.get();
         }
        }
        context.write(kind, new FloatWritable(max));

    }
}
