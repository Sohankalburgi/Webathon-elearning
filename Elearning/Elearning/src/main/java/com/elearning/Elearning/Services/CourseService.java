package com.elearning.Elearning.Services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.elearning.Elearning.Classes.FileUtils;
import com.elearning.Elearning.Entities.Course;
import com.elearning.Elearning.Entities.CourseStatus;
import com.elearning.Elearning.Entities.Video;
import com.elearning.Elearning.Entities.VideoCompletion;
import com.elearning.Elearning.Repositories.CourseRepository;
import com.elearning.Elearning.Repositories.CourseStatusRepository;
import com.elearning.Elearning.Repositories.VideoRepository;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CourseService {

    Dotenv dotenv = Dotenv.load();

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private CourseStatusRepository courseStatusRepository;

    public Course getCourseById(Long id) throws  Exception{
        Optional<Course> course = courseRepository.findById(id);
        if(course.isPresent()){
            return course.get();
        }
        else{
            throw new Exception("Course Not Found");
        }
    }

    public void uploadVideo(Long id, MultipartFile file) throws IOException,Exception {
        Optional<Course> course = courseRepository.findById(id);
        Video savedVideo ;
        if(course.isPresent()) {
            Video video = new Video();
            video.setCourse(course.get());
            video.setFilename(file.getOriginalFilename());
            // upload and set URL

            Cloudinary cloudinary = new Cloudinary(dotenv.get("CLOUDINARY_URL"));
            Map responseMap = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("resource_type", "video"));
            String videoUploadURL = responseMap.get("url").toString();
            video.setFileURL(videoUploadURL);
            savedVideo = videoRepository.saveAndFlush(video);
        }
        else{
            throw new Exception("Course Not found ");
        }

        List<CourseStatus> courseStatus = courseStatusRepository.findByCourseId(id);
        for(CourseStatus i : courseStatus){
            VideoCompletion video = new VideoCompletion(savedVideo.getId(), false);
            i.getVideoCompletionList().add(video);
            courseStatusRepository.save(i);
        }
    }

    public List<Video> downloadAllFile(Long id) throws Exception{
        Optional<Course> course = courseRepository.findById(id);
        if(course.isPresent()){
            List<Video> videos = course.get().getVideos();

            return videos;
        }
        else {
            throw new Exception("Not Found or Error");
        }
    }

    public List<Course> getAllCoursesById(Long userId) {
        List<Course> courses = (List<Course>) courseRepository.findCoursesByUserId(userId);
        return courses;
    }

    public List<Course> getAllcoursebySearch(String search) {
        return courseRepository.findByCourseNameContaining(search);
    }
}
