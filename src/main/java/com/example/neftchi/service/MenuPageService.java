package com.example.neftchi.service;

import com.example.neftchi.dto.response.MenuPageResponse;
import com.example.neftchi.dto.response.SystemMessage;
import com.example.neftchi.model.MenuPage;
import com.example.neftchi.repository.MenuPageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MenuPageService {
    private final MenuPageRepository menuPageRepository;

    public MenuPageResponse find() {
        return map(menuPageRepository.find());
    }

    public MenuPageResponse updateTitle(String title) {
        MenuPage menuPage = menuPageRepository.find();
        menuPage.setTitle(title);
        return map(menuPageRepository.save(menuPage));
    }

    public MenuPageResponse updateDescription(String description) {
        MenuPage menuPage = menuPageRepository.find();
        menuPage.setDescription(description);
        return map(menuPageRepository.save(menuPage));
    }

    public MenuPageResponse updateAbout(String about) {
        MenuPage menuPage = menuPageRepository.find();
        menuPage.setAbout(about);
        return map(menuPageRepository.save(menuPage));
    }

    public MenuPageResponse updateAboutDescription(String aboutDescription) {
        MenuPage menuPage = menuPageRepository.find();
        menuPage.setAboutDescription(aboutDescription);
        return map(menuPageRepository.save(menuPage));
    }

    public MenuPageResponse updateVideo(String video) {
        MenuPage menuPage = menuPageRepository.find();
        menuPage.setVideo(video);
        return map(menuPageRepository.save(menuPage));
    }

    public SystemMessage deleteVideo() {
        try {
            MenuPage menuPage = menuPageRepository.find();
            menuPage.setVideo(null);
            menuPageRepository.save(menuPage);
            return new SystemMessage("Video successfully remote");
        } catch (Exception e) {
            throw new RuntimeException("Uninstallation attempt failed");
        }
    }


    private MenuPageResponse map(MenuPage menuPage) {
        return new MenuPageResponse(
                menuPage.getId(),
                menuPage.getTitle(),
                menuPage.getDescription(),
                menuPage.getAbout(),
                menuPage.getAboutDescription(),
                menuPage.getVideo());
    }
}