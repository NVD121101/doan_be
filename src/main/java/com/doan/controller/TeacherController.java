package com.doan.controller;

import com.doan.config.Constants;
import com.doan.dto.TeacherDTO;
import com.doan.payload.*;
import com.doan.repository.TeacherRepository;
import com.doan.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private TeacherRepository teacherRepository;
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addTeacher(@RequestBody AddTeacherRequest addTeacherRequest) {
        return teacherService.addTeacher(addTeacherRequest);
    }

    @GetMapping
    public  ResponseEntity<?> getAllTeacher(
            @RequestParam(defaultValue = "") String teacherName,
            @RequestParam(defaultValue = "0", required = false) Integer offset,
            @RequestParam(defaultValue = "5", required = false) Integer limit,
            @RequestParam(defaultValue = "") Long subjectId
            ) {
        return teacherService.findAllWithProperty(teacherName, subjectId, limit, offset);
    }

    @PutMapping()
    public ResponseEntity<?> editTeacher(@RequestBody EditTeacherRequest editTeacherRequest) {
        return teacherService.editTeacher(editTeacherRequest);
    }

    @PutMapping("edit-num-student")
    public ResponseEntity<?> editTeacherByMaxStudentsHDAndPB(@RequestBody EditTeacherByMaxStudentsRequest editTeacherByMaxStudentsHDRequest) {
        return teacherService.editTeacherByMaxStudentsHDAndPB(editTeacherByMaxStudentsHDRequest);
    }

    @GetMapping("/gvhd/{subjectId}")
    public  ResponseEntity<?> findTeachersWithStudents(@PathVariable Long subjectId) {
        return teacherService.findTeachersHDWithStudents(subjectId);
    }

    @GetMapping("/gvpb/{subjectId}")
    public  ResponseEntity<?> findTeachersPBWithStudents(@PathVariable Long subjectId) {
        return teacherService.findTeachersPBWithStudents(subjectId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTeacherById(@PathVariable Long id) {
        return teacherService.getTeacherById(id);
    }

    @DeleteMapping("/{teacherId}")
    public ResponseEntity<?> deleteTeacher(@PathVariable Long teacherId) throws Exception{
        teacherRepository.deleteById(teacherId);
        return new ResponseEntity<>(new ResponseSucces(Constants.SUCCCES_CODE, Constants.DELETE_SUCCESS), HttpStatus.OK);
    }

    @GetMapping("/getTeacherById")
    public TeacherDTO getTeacherDTO(){
        return teacherService.getTeacherByUserId();
    }

    @GetMapping("/getAll")
    public  ResponseEntity<?> getAll(){
        return new ResponseEntity<>(teacherRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/count")
    public Long getCountOfMasterDetails() {
//        Map<String, Long> message = new HashMap<>();
        return teacherRepository.count();
//        message.put("count", teacherRepository.count());
//        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
