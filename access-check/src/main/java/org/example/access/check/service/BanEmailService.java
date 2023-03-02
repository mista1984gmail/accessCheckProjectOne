package org.example.access.check.service;

import lombok.RequiredArgsConstructor;
import org.example.access.check.repository.BanEmailRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BanEmailService {

    private final BanEmailRepository banEmailRepository;


    public boolean isBanEmail(String email){
        return banEmailRepository.existsBanEmailsByEmail(email);
    }

}
