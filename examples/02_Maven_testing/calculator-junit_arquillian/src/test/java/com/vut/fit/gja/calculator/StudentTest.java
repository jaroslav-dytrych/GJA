package com.vut.fit.gja.calculator;

import jakarta.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(ArquillianExtension.class)
public class StudentTest {

    /**
     * Creation of jar deployment with all needed classes (Calculator and
     * Student)
     *
     * @return
     */
    @Deployment
    public static JavaArchive createDeployment() {
        JavaArchive jar = ShrinkWrap.create(JavaArchive.class, "test.jar")
                .addClasses(Calculator.class, Student.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
        return jar;
    }

    @Inject
    Student student;

    /**
     * Integration test of Student and Calculator. If the Student has the
     * Calculator properly injected, he would be able to solve a simple
     * mathematical equasion.
     */
    @Test
    public void studentCalculatorInjectionTest() {

        Assertions.assertEquals(
                "The answer is 25",
                student.answerAddition(5, 20)
        );
    }
}
