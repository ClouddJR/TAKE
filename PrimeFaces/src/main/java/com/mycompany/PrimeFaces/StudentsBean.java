/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.PrimeFaces;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Arkadiusz
 */
@Named(value = "studentsBean")
@RequestScoped
public class StudentsBean {
    
    private List<Student> students;

    /**
     * Creates a new instance of StudentsBean
     */
    public StudentsBean() {
        students = new ArrayList();
        students.add(new Student("Jan", "Kowalski", 4.75));
        students.add(new Student("Tomasz", "Niewiedział", 2.75));
        students.add(new Student("Janina", "Kowalska", 4.5));
        students.add(new Student("Krzystof", "Nowak", 3.75));
        students.add(new Student("Robert", "Koszlajda", 4.6));
        students.add(new Student("Juliusz", "Jezierski", 4.25));
        students.add(new Student("Marcin", "Dziamdziak", 4.35));
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
    
    
    
}
