package main;

import File.MyWriter;
import File.Pass;
import File.Reader;

import java.io.IOException;
import java.util.Collections;

public class Test {

    public static void test() {
        Reader reader = new Reader();
        reader.readTxt();
        MyHttpRequest test = new MyHttpRequest();
        for (Pass p : reader.getPasses()) {
            test.changePass(p);
            test.assignValidityState(test.getAsync());
        }

        Collections.sort(reader.getPasses());
        for (Pass p : reader.getPasses()
        ) {
            System.out.println(p.getValidityStatus());
            System.out.println(p.getActivationDate());

        }
        MyWriter w = new MyWriter();
        w.write(reader.getPasses());
    }


    public static void main(String[] args) {
        test();

    }
}
