package com.nighthawk.spring_portfolio.database.grading;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.nighthawk.spring_portfolio.database.person.Person;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Class on Grade of Each Assignment
 * Relationship to Assignment
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Grade {

    // automatic unique identifier for completion record
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JoinColumn(name = "assignment_id")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Assignment assignment;

    @JoinColumn(name = "user_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Person person;

    private double totalPointValue;
    private double grade;
    private String comment;

    public Grade(double pointValue, Assignment assignment) {
        this.totalPointValue = pointValue;
        this.assignment = assignment;
    }

    public void setGrade(double grade) {
        if (grade < 0 || grade > this.totalPointValue) {
            System.out.println("invalid grade, please enter in a valid grade");
            return;
        }
        this.grade = grade;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}