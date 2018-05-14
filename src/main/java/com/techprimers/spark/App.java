package com.techprimers.spark;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.sql.SparkSession;

import java.util.Arrays;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws InterruptedException {


        SparkSession session = SparkSession
                .builder()
                .appName("SparkJavaExample")
                .master("local[3]")
                .getOrCreate();


        try (JavaSparkContext context = new JavaSparkContext(session.sparkContext())) {
            List<Integer> integers = Arrays.asList(1, 4, 5, 6, 7, 8, 9,
                    10, 11, 12, 13, 14, 15);

            JavaRDD<Integer> javaRDD = context.parallelize(integers, 3);

            javaRDD
                    .foreach((VoidFunction<Integer>) integer -> {

                 System.out.println("Java RDD:" + integer);
                 Thread.sleep(3000);
                    });

            Thread.sleep(1000000);
            context.stop();
        }
    }
}
