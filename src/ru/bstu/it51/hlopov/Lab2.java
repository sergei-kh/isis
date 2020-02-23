package ru.bstu.it51.hlopov;
import org.apache.logging.log4j.*;

public class Lab2 {
    static final Logger logger = LogManager.getLogger(Lab2.class);
    public void run() {
        Lab1 lr1 = new Lab1(logger);
        lr1.run();
    }
}
