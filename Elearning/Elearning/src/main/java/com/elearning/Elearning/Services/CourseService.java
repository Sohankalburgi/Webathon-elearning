package com.elearning.Elearning.Services;

import com.elearning.Elearning.Classes.FileUtils;
import com.elearning.Elearning.Entities.Course;
import com.elearning.Elearning.Entities.Video;
import com.elearning.Elearning.Repositories.CourseRepository;
import com.elearning.Elearning.Repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private VideoRepository videoRepository;

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
        if(course.isPresent()) {
            Video video = new Video();
            video.setCourse(course.get());
            video.setFilename(file.getOriginalFilename());
            video.setFileData(FileUtils.compressFile(file.getBytes()));

            videoRepository.save(video);
        }
        else{
            throw new Exception("Course Not found ");
        }
    }

    public List<Video> downloadAllFile(Long id) throws Exception{
        Optional<Course> course = courseRepository.findById(id);
        if(course.isPresent()){
            List<Video> videos = course.get().getVideos();
            for(int i=0;i< videos.size();i++){
                videos.get(i).setFileData(FileUtils.decompressFile(videos.get(i).getFileData()));
            }
            return videos;
        }
        else {
            throw new Exception("Not Found or Error");
        }
    }
}
