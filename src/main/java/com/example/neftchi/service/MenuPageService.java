package com.example.neftchi.service;

import com.example.neftchi.dto.response.MenuPageResponse;
import com.example.neftchi.dto.response.SystemMessage;
import com.example.neftchi.model.MenuPage;
import com.example.neftchi.model.enums.Language;
import com.example.neftchi.repository.MenuPageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MenuPageService {
    private final MenuPageRepository menuPageRepository;

    public MenuPageResponse find(Language language) {
        try {
            return map(menuPageRepository.find(language));
        } catch (Exception e) {
            return new MenuPageResponse(0L,
                    null,
                    null,
                    null,
                    null,
                    null);
        }
    }

    public MenuPageResponse updateTitle(String title,
                                        Language language) {
        MenuPage menuPage = menuPageRepository.find(language);
        menuPage.setTitle(title);
        return map(menuPageRepository.save(menuPage));
    }

    public MenuPageResponse updateDescription(String description,
                                              Language language) {
        MenuPage menuPage = menuPageRepository.find(language);
        menuPage.setDescription(description);
        return map(menuPageRepository.save(menuPage));
    }

    public MenuPageResponse updateAbout(String about,
                                        Language language) {
        MenuPage menuPage = menuPageRepository.find(language);
        menuPage.setAbout(about);
        return map(menuPageRepository.save(menuPage));
    }

    public MenuPageResponse updateAboutDescription(String aboutDescription,
                                                   Language language) {
        MenuPage menuPage = menuPageRepository.find(language);
        menuPage.setAboutDescription(aboutDescription);
        return map(menuPageRepository.save(menuPage));
    }

    public MenuPageResponse updateVideo(String video,
                                        Language language) {
        MenuPage menuPage = menuPageRepository.find(language);
        menuPage.setVideo(video);
        return map(menuPageRepository.save(menuPage));
    }

    public SystemMessage deleteVideo(Language language) {
        try {
            MenuPage menuPage = menuPageRepository.find(language);
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