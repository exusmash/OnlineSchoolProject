package com.example.onlineschoolproject.repository;

import com.example.onlineschoolproject.model.Course;
import com.example.onlineschoolproject.model.Subject;
import com.example.onlineschoolproject.model.Teacher;
import com.example.onlineschoolproject.model.Webinar;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends GenericRepository<Teacher> {
}
