package com.studentmanagement.studentmanagement.controller;
import com.studentmanagement.studentmanagement.model.Student;
import com.studentmanagement.studentmanagement.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    @Autowired
    private StudentService service;

    //Getting Student from database
    @GetMapping("/students")
    public List<Student> getStudent() {
        return service.getStudent();
    }

    //Adding student in database
    @PostMapping("/students/add")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        try {
            service.addStudent(student);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //Get student by their id
    @GetMapping("/students/{id}")
    public Optional<Student> getStudent(@PathVariable Long id ){
      return service.getStudentById(id);
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<Student> deleteById(@PathVariable Long id){
        try {
            service.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Update Student
    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        try {
            // Fetch existing student by id
            Optional<Student> existingStudentOpt = service.getStudentById(id);

            if (existingStudentOpt.isPresent()) {
                // Get the existing student from the optional
                Student existingStudent = existingStudentOpt.get();

                // Update the fields with the new data
                existingStudent.setRollNo(student.getRollNo());
                existingStudent.setFirstName(student.getFirstName());
                existingStudent.setLastName(student.getLastName());
                existingStudent.setAddress(student.getAddress());
                existingStudent.setBloodGroup(student.getBloodGroup());

                // Save the updated student object
                service.updateStudent(id, existingStudent);

                return new ResponseEntity<>(existingStudent, HttpStatus.OK);
            } else {
                // If student is not found, return a NOT_FOUND status
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            // In case of any error during the update process
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
