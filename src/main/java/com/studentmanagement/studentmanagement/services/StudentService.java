package com.studentmanagement.studentmanagement.services;
import com.studentmanagement.studentmanagement.model.Student;
import com.studentmanagement.studentmanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
  private StudentRepository repository;

    //Getting Data from database
    public List<Student> getStudent(){
       return repository.findAll();
    }
    //Adding Student in database
    public Student addStudent(Student student){
       return repository.save(student);
    }

    //Getting by their id
    public Optional<Student> getStudentById(Long id ){
        return  repository.findById(id);
    }

    //Delete by id
    public void deleteById(Long id){
         repository.deleteById(id);
    }

    //Update Student

    public void updateStudent(Long id, Student student) {
        Optional<Student> existingStudentOpt = repository.findById(id);
        if (existingStudentOpt.isPresent()) {
            Student existingStudent = existingStudentOpt.get();

            // Update fields
            existingStudent.setRollNo(student.getRollNo());
            existingStudent.setFirstName(student.getFirstName());
            existingStudent.setLastName(student.getLastName());
            existingStudent.setAddress(student.getAddress());
            existingStudent.setBloodGroup(student.getBloodGroup());

            // Save the updated student to the repository
            repository.save(existingStudent);
        } else {
            throw new RuntimeException("Student not found");
        }
    }

}
