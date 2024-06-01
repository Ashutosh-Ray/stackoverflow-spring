package com.stackoverflow.services.user.image;

import com.stackoverflow.entities.Answer;
import com.stackoverflow.entities.Image;
import com.stackoverflow.repositories.AnswerRepository;
import com.stackoverflow.repositories.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageServiceImp implements ImageService{

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public void storeFile(MultipartFile multipartFile, Long answerId) throws IOException {
        Optional<Answer> optionalAnswer = answerRepository.findById(answerId);
        if (optionalAnswer.isPresent()){
            String fileName= StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
            Image image = new Image();
            image.setName(fileName);
            image.setAnswer(optionalAnswer.get());
            image.setType(multipartFile.getContentType());
            image.setData(multipartFile.getBytes());
            imageRepository.save(image);
        }else{
            throw new IOException("Answer not found");
        }
    }
}
