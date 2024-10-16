package com.revature;



import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Launcher {

    public static void main(String[] args) {

        System.out.println("========================(Threads)");

        //Instantiate two Threads (from InterfaceBasedThread)
        InterfaceBasedThread thread1 = new InterfaceBasedThread();
        InterfaceBasedThread thread2 = new InterfaceBasedThread();

//        thread1.run();
//        thread2.run();

        //This^ doesn't look concurrent at all... we didn't actually create new Threads
        //We just ran the run() method, but we needed the start() method to invoke a new thread
        //That's why we saw "main----" in the output

        //Let's use start() to instantiate 3 Threads
        Thread t1 = new Thread(thread1);
        t1.start();

        Thread t2 = new Thread(thread2);
        t2.start();

        //NOTE: it doesn't matter what Thread we use in the constructor, as long as it's a valid one
        Thread t3 = new Thread(thread2);
        //t3.setName("Cool Named Thread"); //we can name Threads if we want!

        //We'll also try to set this Thread with the highest priority
        //This doesn't necessarily mean it will start and finish first, but will be given more CPU time
        t3.setPriority(Thread.MAX_PRIORITY);
        t3.start();

        System.out.println("========================(Streams)");

        //Let's make a basic Stream of Integers
        IntStream.range(0, 10).skip(3).forEach(System.out::println);
        /*The Stream will have values 0-9 (range() - intermediate)
           The stream will skip the first 3 values (skip() - intermediate)
           For Each value in the stream, print it out (forEach() - terminal)

         What is System.out::println? It's called "method reference syntax" (week 3 of the curric)
         You may notice we never said what's in the print statement
         Method Reference Syntax lets us imply the value that a method will take as a parameter
         Java goes "well there's nothing else here to print, so I guess we'll print the Integer given to us in forEach" */

        //Stream.of() is a convenient way to make a String of any object we want. The data must be of the same type
        Optional<String> firstString = Stream.of("OK", "uhhhh", "Revature", "Infosys", "Pumpkin", "Candle")
                .sorted() //sorts the Stream according to natural order (uses a merge sort! probably)
                .findFirst(); //returns the first element in the Stream

        /*Ok what is an Optional? It's a Java Class that either hold a value or be empty
         * it will OPTIONALLY hold a value. This is useful when we potentially have a null value
         * This is a great way to avoid NullPointerExceptions and cut down on the verbosity of try/catches
         * We do need to extract the values in a specific way (.isPresent() and .get())*/
        if(firstString.isPresent()){
            System.out.println(firstString.get());
        } else {
            System.out.println("No data found! This helps us avoid NullPointerExceptions that can crash our app");
        }

        //Let's use a Stream on an existing data structure to create a new ArrayList after running operations on it
        ArrayList<String> names = new ArrayList<>();

        names.add("Ben");
        names.add("Jamie");
        names.add("Mister");
        names.add("Edward");
        names.add("Eggward");
        names.add("Edward");

        List<String> newNames = names.stream()
                .map(String::toUpperCase) //map() performs an operation for every value in the stream (note method reference syntax)
                .distinct() //distinct() removes duplicate values from the stream
                .filter(name -> name.length() < 7) //remove elements that are not shorter than 7 characters
                .toList(); //terminal operation - end the stream and return the results as a List

        System.out.println(newNames);


        //Quick Reflection API Example-------

        //Using Reflection to get an object that is a representation of the Thread Class
        Class<Thread> threadClassObjectForReflection = Thread.class;

        //Getting every method found in Thread and printing it out
        for(Method m : threadClassObjectForReflection.getMethods()){
            System.out.println(m);
            System.out.println(Arrays.toString(m.getExceptionTypes())); //printing out the methods Exceptions (which is an Array)
        }


    }

}
