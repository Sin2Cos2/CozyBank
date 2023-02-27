package com.sin2cos2.springsecurity.controllers;

import com.sin2cos2.springsecurity.models.Authority;
import com.sin2cos2.springsecurity.models.Notice;
import com.sin2cos2.springsecurity.repositories.AuthorityRepository;
import com.sin2cos2.springsecurity.repositories.NoticeRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

@AllArgsConstructor
@RestController
public class NoticesController {

    private final NoticeRepository noticeRepository;
    private final AuthorityRepository authorityRepository;

    @GetMapping("/notices")
    public ResponseEntity<List<Notice>> getNotices() {
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(30, TimeUnit.SECONDS))
                .body(noticeRepository.findAllActiveNotices());
    }
}
