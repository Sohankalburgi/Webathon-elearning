package com.elearning.Elearning.Services;

import com.elearning.Elearning.Entities.*;
import com.elearning.Elearning.Repositories.CourseRepository;
import com.elearning.Elearning.Repositories.CourseStatusRepository;
import com.elearning.Elearning.Repositories.StudentRepository;
import com.elearning.Elearning.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseStatusService {

    @Autowired
    private CourseStatusRepository courseStatusRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    public void registerCourseByStudent(Long id, Long courseId) throws Exception{
        Student student = studentRepository.findAllByUserId(id);
        Optional<Course> course = courseRepository.findById(courseId);

        if(student!=null){
            CourseStatus courseStatus = new CourseStatus();
            courseStatus.setStudent(student);
            courseStatus.setCourse(course.get());

            List<Video> videoList = course.get().getVideos();
            List<VideoCompletion> videoCompletionList = new ArrayList<>();

            for(Video i : videoList){
                VideoCompletion newVideoCompletion = new VideoCompletion(i.getId(),false);
                videoCompletionList.add(newVideoCompletion);
            }


            courseStatus.setVideoCompletionList(videoCompletionList);
            courseStatus.setStatus(0);
            courseStatusRepository.save(courseStatus);
        }else {
            throw new Exception("Error or Either student or course Not found");
        }
    }

    public List<CourseStatus> getAllCoursesregistered(Long id) throws Exception{
        Student student = studentRepository.findAllByUserId(id);
        List<CourseStatus> courses = courseStatusRepository.findAllByStudentId(student.getId());
        if(!courses.isEmpty()){
            return courses;
        }
        else{
            throw new Exception("Not Found");
        }
    }

    public boolean markVideo(Long studentId, Long courseId, Long videoId) {
        Optional<CourseStatus> courseStatus = courseStatusRepository.findByCourseStatus(studentId,courseId);
        boolean flag = false;
        if(courseStatus.isPresent()){
            List<VideoCompletion> list = courseStatus.get().getVideoCompletionList();
            int count = 0;
            for(VideoCompletion i : list){
                if(i.getVideoId()==videoId){
                    i.setCompleted(!i.isCompleted());
                    flag = i.isCompleted();
                }
                if(i.isCompleted()==true){
                    count++;
                }
            }
            System.out.println("the count"+count);
            System.out.println("the listsize"+list.size());
            float percentage =( ((float)count/(float)list.size()))*100;
            System.out.println("the percentage"+percentage);
            courseStatus.get().setStatus((int)percentage);
            courseStatus.get().setVideoCompletionList(list);
            courseStatusRepository.save(courseStatus.get());
        }

        return flag;
    }



    public List<VideoCompletion> getmarkedstatus(Long studentId, Long courseId) {
        System.out.println(studentId+"    "+courseId);
        Optional<CourseStatus> courseStatus = courseStatusRepository.findByCourseStatus(studentId,courseId);

        System.out.println("the size"+courseStatus.get().getVideoCompletionList().size());
        if(courseStatus.isPresent()){
            List<VideoCompletion> list = courseStatus.get().getVideoCompletionList();
            System.out.println(list.size());
            return list;
        }
        return  new ArrayList<>();
    }
}
